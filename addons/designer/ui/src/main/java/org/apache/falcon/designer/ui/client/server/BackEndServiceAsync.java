package org.apache.falcon.designer.ui.client.server;

import java.util.List;
import java.util.Map;

import org.apache.falcon.designer.ui.vo.FeedVO;
import org.codehaus.jettison.json.JSONArray;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BackEndServiceAsync {

  void getAllFeedNames(AsyncCallback<List<String>> callback);

  void getFeedDetails(String feedName, AsyncCallback<FeedVO> callback);

  void getAllActions(AsyncCallback<List<String>> callback);

  void getAllTransformations(AsyncCallback<List<String>> callback);

  void getColumnsForTable(String hcatUrl, String dbName, String tableName,
      AsyncCallback<Map<String, String>> callback);

}
