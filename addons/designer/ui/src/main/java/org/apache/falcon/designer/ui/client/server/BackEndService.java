package org.apache.falcon.designer.ui.client.server;

import java.util.List;
import java.util.Map;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.apache.falcon.designer.ui.vo.FeedVO;
import org.apache.hcatalog.common.HCatException;

@RemoteServiceRelativePath("backEnd")
public interface BackEndService extends RemoteService {

  List<String> getAllFeedNames();

  FeedVO getFeedDetails(String feedName);

  public List<String> getAllActions();

  public List<String> getAllTransformations();

  public Map<String, String> getColumnsForTable(String hcatUrl, String dbName,
      String tableName);
}
