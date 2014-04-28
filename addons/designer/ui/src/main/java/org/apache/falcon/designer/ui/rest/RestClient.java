package org.apache.falcon.designer.ui.rest;

import java.io.IOException;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.falcon.client.FalconCLIException;
import org.apache.falcon.entity.v0.cluster.Cluster;
import org.apache.falcon.entity.v0.feed.Clusters;
import org.apache.falcon.entity.v0.feed.Feed;
import org.apache.falcon.resource.EntityList;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class RestClient {

  private final WebResource service;

  public static final String WS_HEADER_PREFIX = "header:";
  private static final String REMOTE_USER = "Remote-User";
  private static final String USER = System.getProperty("user.name");

  /**
   * Create a Falcon client instance.
   * 
   * @param falconUrl
   *          of the server to which client interacts
   * @throws IOException
   */
  public RestClient(String falconUrl) throws IOException {
    String baseUrl = notEmpty(falconUrl, "FalconUrl");
    if (!baseUrl.endsWith("/")) {
      baseUrl += "/";
    }
    Client client = Client.create(new DefaultClientConfig());
    setFalconTimeOut(client);
    service = client.resource(UriBuilder.fromUri(baseUrl).build());
    client.resource(UriBuilder.fromUri(baseUrl).build());

    // addHeaders();
  }

  private void setFalconTimeOut(Client client) throws IOException {
    int readTimeout;
    int connectTimeout;
    readTimeout = 180000;
    connectTimeout = 180000;
    client.setConnectTimeout(connectTimeout);
    client.setReadTimeout(readTimeout);
  }

  /**
   * Methods allowed on Entity Resources.
   */
  protected static enum Entities {
    VALIDATE("api/entities/validate/", HttpMethod.POST, MediaType.TEXT_XML), STATUS(
        "api/entities/status/", HttpMethod.GET, MediaType.TEXT_XML), DEFINITION(
        "api/entities/definition/", HttpMethod.GET, MediaType.TEXT_XML), LIST(
        "api/entities/list/", HttpMethod.GET, MediaType.TEXT_XML), DEPENDENCY(
        "api/entities/dependencies/", HttpMethod.GET, MediaType.TEXT_XML);

    private String path;
    private String method;
    private String mimeType;

    Entities(String path, String method, String mimeType) {
      this.path = path;
      this.method = method;
      this.mimeType = mimeType;
    }
  }

  public String notEmpty(String str, String name) {
    if (str == null) {

      throw new IllegalArgumentException(name + " cannot be null");
    }
    if (str.length() == 0) {
      throw new IllegalArgumentException(name + " cannot be empty");
    }
    return str;
  }

  public Feed getFeedDefinition(String entityName) throws FalconCLIException {

    return sendDefinitionRequest(Entities.DEFINITION, "feed", entityName)
        .getEntity(Feed.class);

  }

  public Cluster getClusterDefinition(String entityName)
      throws FalconCLIException {

    return sendDefinitionRequest(Entities.DEFINITION, "cluster", entityName)
        .getEntity(Cluster.class);
  }

  public EntityList getEntityList(String entityType) throws FalconCLIException {
    return sendListRequest(Entities.LIST, entityType);
  }

  public Clusters getClusterList(String entityType) throws FalconCLIException {
    return sendClusterListRequest(Entities.LIST, entityType);
  }

  private ClientResponse sendDefinitionRequest(Entities entities,
      String entityType, String entityName) throws FalconCLIException {

    ClientResponse clientResponse =
        service.path(entities.path).path(entityType).path(entityName)
            .header(REMOTE_USER, USER).accept(entities.mimeType)
            .type(MediaType.TEXT_XML)
            .method(entities.method, ClientResponse.class);

    checkIfSuccessfull(clientResponse);
    return clientResponse;
  }

  private Clusters sendClusterListRequest(Entities entities, String entityType)
      throws FalconCLIException {

    ClientResponse clientResponse =
        service.path(entities.path).path(entityType).header(REMOTE_USER, USER)
            .accept(entities.mimeType).type(MediaType.TEXT_XML)
            .method(entities.method, ClientResponse.class);

    checkIfSuccessfull(clientResponse);

    return clientResponse.getEntity(Clusters.class);

  }

  private EntityList sendListRequest(Entities entities, String entityType)
      throws FalconCLIException {

    ClientResponse clientResponse =
        service.path(entities.path).path(entityType).header(REMOTE_USER, USER)
            .accept(entities.mimeType).type(MediaType.TEXT_XML)
            .method(entities.method, ClientResponse.class);

    checkIfSuccessfull(clientResponse);

    return clientResponse.getEntity(EntityList.class);

  }

  void checkIfSuccessfull(ClientResponse clientResponse)
      throws FalconCLIException {

    if (clientResponse.getStatus() == Response.Status.BAD_REQUEST
        .getStatusCode()) {
      throw FalconCLIException.fromReponse(clientResponse);
    }
  }

  public WebResource getService() {
    return service;
  }
}
