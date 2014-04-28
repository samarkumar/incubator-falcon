package org.apache.falcon.designer.ui.vo;

import java.io.Serializable;
import java.util.List;

public class FeedVO implements Serializable {

  List<String> clusters;
  String frequency;
  String tableName;
  String databaseName;
  String hcatURL;

  public String getFrequency() {
    return frequency;
  }

  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }

  public List<String> getClusters() {
    return clusters;
  }

  public void setClusters(List<String> clusters) {
    this.clusters = clusters;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getDatabaseName() {
    return databaseName;
  }

  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }

  public String getHcatURL() {
    return hcatURL;
  }

  public void setHcatURL(String hcatURL) {
    this.hcatURL = hcatURL;
  }

  @Override
  public String toString() {

    return "returnString.toString()";
  }
}
