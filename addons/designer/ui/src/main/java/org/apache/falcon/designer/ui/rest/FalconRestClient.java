/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.falcon.designer.ui.rest;

import java.io.IOException;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.falcon.client.FalconCLIException;
import org.apache.hadoop.security.authentication.client.AuthenticatedURL;
import org.apache.hadoop.security.authentication.client.KerberosAuthenticator;
import org.apache.hadoop.security.authentication.client.PseudoAuthenticator;
import org.apache.falcon.entity.v0.cluster.Cluster;
import org.apache.falcon.entity.v0.feed.Clusters;
import org.apache.falcon.entity.v0.feed.Feed;
import org.apache.falcon.resource.EntityList;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.net.URL;
import org.apache.commons.net.util.TrustManagerUtils;
import java.security.SecureRandom;

public class FalconRestClient {

  private final WebResource service;
  private final AuthenticatedURL.Token authenticationToken;

  public static final String WS_HEADER_PREFIX = "header:";
  private static final String REMOTE_USER = "Remote-User";
  private static final String USER = System.getProperty("user.name");
  public static final String AUTH_URL = "api/options?"
      + PseudoAuthenticator.USER_NAME + "=" + USER;

  /**
   * Name of the HTTP cookie used for the authentication token between the
   * client and the server.
   */
  public static final String AUTH_COOKIE = "hadoop.auth";
  private static final String AUTH_COOKIE_EQ = AUTH_COOKIE + "=";
  private static final KerberosAuthenticator AUTHENTICATOR =
      new KerberosAuthenticator();

  public static final HostnameVerifier ALL_TRUSTING_HOSTNAME_VERIFIER =
      new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession sslSession) {
          return true;
        }
      };

  /**
   * Create a Falcon client instance.
   * 
   * @param falconUrl
   *          of the server to which client interacts
   * @throws IOException
   */
  public FalconRestClient(String falconUrl) throws Exception {
    String baseUrl = notEmpty(falconUrl, "FalconUrl");
    if (!baseUrl.endsWith("/")) {
      baseUrl += "/";
    }
    Client client = Client.create(new DefaultClientConfig());
    setFalconTimeOut(client);
    service = client.resource(UriBuilder.fromUri(baseUrl).build());
    client.resource(UriBuilder.fromUri(baseUrl).build());
    authenticationToken = getToken(baseUrl);
    // addHeaders();
  }

  public static AuthenticatedURL.Token getToken(String baseUrl)
      throws FalconCLIException {
    AuthenticatedURL.Token currentToken = new AuthenticatedURL.Token();
    try {
      URL url = new URL(baseUrl + AUTH_URL);
      // using KerberosAuthenticator which falls back to PsuedoAuthenticator
      // instead of passing authentication type from the command line - bad
      // factory
      HttpsURLConnection.setDefaultSSLSocketFactory(getSslContext()
          .getSocketFactory());
      HttpsURLConnection
          .setDefaultHostnameVerifier(ALL_TRUSTING_HOSTNAME_VERIFIER);
      new AuthenticatedURL(AUTHENTICATOR).openConnection(url, currentToken);
    } catch (Exception ex) {
      throw new FalconCLIException(
          "Could not authenticate, " + ex.getMessage(), ex);
    }

    return currentToken;
  }

  private static SSLContext getSslContext() throws Exception {
    SSLContext sslContext = SSLContext.getInstance("SSL");
    sslContext.init(null, new TrustManager[] { TrustManagerUtils
        .getValidateServerCertificateTrustManager() }, new SecureRandom());
    return sslContext;
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
            .header("Cookie", AUTH_COOKIE_EQ + authenticationToken)
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
