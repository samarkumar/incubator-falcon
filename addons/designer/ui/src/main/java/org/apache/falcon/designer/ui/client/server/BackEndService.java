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
