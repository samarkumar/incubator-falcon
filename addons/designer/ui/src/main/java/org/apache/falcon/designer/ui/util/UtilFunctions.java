package org.apache.falcon.designer.ui.util;

public class UtilFunctions {
  public static String getDatabase(String uri) {
    return uri.split(":")[1];

  }

  public static String getTableName(String uri) {
    String tableWithPartition = uri.split(":")[2];
    return tableWithPartition.split("#")[0];

  }
}
