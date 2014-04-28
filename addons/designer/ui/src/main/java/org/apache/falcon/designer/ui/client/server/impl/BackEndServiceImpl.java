package org.apache.falcon.designer.ui.client.server.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.falcon.client.FalconCLIException;
import org.apache.falcon.designer.ui.client.server.BackEndService;
import org.apache.falcon.designer.ui.hcat.HCatClientFactory;
import org.apache.falcon.designer.ui.rest.DesignerRestClient;
import org.apache.falcon.designer.ui.rest.RestClient;
import org.apache.falcon.designer.ui.util.UtilFunctions;
import org.apache.falcon.designer.ui.vo.FeedVO;
import org.apache.falcon.resource.EntityList;
import org.apache.falcon.resource.EntityList.EntityElement;
import org.apache.falcon.entity.v0.cluster.Interface;
import org.apache.falcon.entity.v0.cluster.Interfacetype;
import org.apache.falcon.entity.v0.feed.Cluster;
import org.apache.falcon.entity.v0.feed.Clusters;
import org.apache.falcon.entity.v0.feed.Feed;
import org.apache.hcatalog.api.HCatClient;
import org.apache.hcatalog.common.HCatException;
import org.apache.hcatalog.data.schema.HCatFieldSchema;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class BackEndServiceImpl extends RemoteServiceServlet implements
    BackEndService {

  /**
   * 
   */
  private static final long serialVersionUID = 9135387885271735102L;
  private transient RestClient rClient;
  private transient DesignerRestClient desingerRestClient;

  private static final String falconRestURL = "http://databusdev2:15000";
  private static final String desginerRestURL =
      "http://localhost:8080/designer-rest";
  // private static final String hcatThriftURL = "http://databusdev2:15000";

  {
    try {
      rClient = new RestClient(falconRestURL);
      desingerRestClient = new DesignerRestClient(desginerRestURL);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<String> getAllFeedNames() {
    try {

      EntityList eList = rClient.getEntityList("feed");
      List<String> returnList = new ArrayList<String>();
      for (EntityElement eachElement : eList.getElements()) {
        returnList.add(eachElement.name);
      }
      return returnList;

    } catch (Exception e) {
      // e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<String> getAllActions() {

    try {
      return jsonArrayToList(desingerRestClient.getAllActions());
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  @Override
  public List<String> getAllTransformations() {
    try {
      return jsonArrayToList(desingerRestClient.getAllTransformations());
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private List<String> jsonArrayToList(JSONArray jsonArrayInst) {

    List<String> listOfNames = new ArrayList<String>();

    try {
      for (int index = 0; index < jsonArrayInst.length(); index++) {
        listOfNames.add(jsonArrayInst.getString(index));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return listOfNames;

  }

  public FeedVO getFeedDetails(String feedName) {

    FeedVO returnFeed = new FeedVO();

    Feed feedDetails;
    try {

      feedDetails = rClient.getFeedDefinition(feedName);
      List<String> clusterList = new ArrayList<String>();

      returnFeed.setFrequency(feedDetails.getFrequency().toString());
      returnFeed.setClusters(clusterList);

      for (Cluster eachCluster : feedDetails.getClusters().getClusters()) {
        clusterList.add(eachCluster.getName());
        if (returnFeed.getTableName() == null) {
          returnFeed.setTableName(UtilFunctions.getTableName(eachCluster
              .getTable().getUri()));
          returnFeed.setDatabaseName(UtilFunctions.getDatabase(eachCluster
              .getTable().getUri()));
        }
      }
      if (clusterList.size() > 0) {
        org.apache.falcon.entity.v0.cluster.Cluster clusterDetails =
            rClient.getClusterDefinition(clusterList.get(0));
        for (Interface eachInterface : clusterDetails.getInterfaces()
            .getInterfaces()) {
          if (eachInterface.getType().equals(Interfacetype.REGISTRY)) {
            returnFeed.setHcatURL(eachInterface.getEndpoint());
          }
        }
      }

    } catch (FalconCLIException e) {
      e.printStackTrace();
      return null;
    }
    System.out
        .println("here  ************ ************ ************ ************"
            + feedDetails.toShortString());

    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return returnFeed;

  }

  public String getClusters() {
    try {

      Clusters clusters = rClient.getClusterList("cluster");
      List<org.apache.falcon.entity.v0.feed.Cluster> listOfClusters =
          clusters.getClusters();
      return listOfClusters.get(0).getName();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  public Map<String, String> getColumnsForTable(String hcatUrl, String dbName,
      String tableName) {

    Map<String, String> columns = new HashMap<String, String>();
    try {
      HCatClient hcatClientInstance = HCatClientFactory.getHCatClient(hcatUrl);
      List<HCatFieldSchema> schemaList;

      schemaList = hcatClientInstance.getTable(dbName, tableName).getCols();

      for (HCatFieldSchema eachColumnSchema : schemaList) {
        columns.put(eachColumnSchema.getName(),
            eachColumnSchema.getTypeString());
      }
    } catch (HCatException e) {
      e.printStackTrace();
    }
    return columns;
  }
}
