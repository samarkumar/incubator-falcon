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

import org.apache.falcon.client.FalconCLIException;
import org.codehaus.jettison.json.JSONArray;

import com.sun.jersey.api.client.ClientResponse;

public class DesignerRestClient extends RestClient {

  protected static enum RestCalls {
    TRANSFORMATIONS_LIST("api/transformations/list", HttpMethod.GET,
        MediaType.APPLICATION_JSON), ACTION_LIST("api/action/list",
        HttpMethod.GET, MediaType.APPLICATION_JSON);

    private String path;
    private String method;
    private String mimeType;

    RestCalls(String path, String method, String mimeType) {
      this.path = path;
      this.method = method;
      this.mimeType = mimeType;
    }
  }

  public DesignerRestClient(String designerRestUrl) throws IOException {
    super(designerRestUrl);
  }

  public JSONArray getAllTransformations() throws Exception {

    RestCalls tranformationsList = RestCalls.TRANSFORMATIONS_LIST;
    ClientResponse clientResponse =
        getService().path(tranformationsList.path)
            .accept(tranformationsList.mimeType)
            .type(MediaType.APPLICATION_JSON)
            .method(tranformationsList.method, ClientResponse.class);

    checkIfSuccessfull(clientResponse);
    String responseString = clientResponse.getEntity(String.class);
    return new JSONArray(responseString);
    

  }

  public JSONArray getAllActions() throws Exception {

    RestCalls actionList = RestCalls.ACTION_LIST;
    ClientResponse clientResponse = getService().path(actionList.path)

    .method(actionList.method, ClientResponse.class);

    checkIfSuccessfull(clientResponse);

    String debug = clientResponse.getEntity(String.class);
    return new JSONArray(debug);

  }
}
