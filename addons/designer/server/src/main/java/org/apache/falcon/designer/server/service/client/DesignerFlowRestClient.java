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
package org.apache.falcon.designer.server.service.client;

import java.util.List;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import org.apache.falcon.designer.core.configuration.FlowConfig;
import org.apache.falcon.designer.core.service.FalconDesigner;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * Client library to access the DesingerFlowRestServices.
 */
public class DesignerFlowRestClient implements FalconDesigner {

    private final WebResource service;

    /*
 * Will pick up from the systemProperties/hadoopConfig etc;
 */
    public DesignerFlowRestClient(String baseURL) {
        ClientConfig config = new DefaultClientConfig();
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
            Boolean.TRUE);
        Client client = Client.create(config);
        service = client.resource(baseURL);

    }

    @Override
    public FlowConfig getAFlow(String flowName, int version) {
        ClientResponse a =
            service.path("flow").path("get")
                .type(MediaType.APPLICATION_JSON).
                method(HttpMethod.GET, ClientResponse.class);

        return a.getEntity(FlowConfig.class);

    }

    @Override
    public List<String> getListOfFlowsNames() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Integer> getAllVersionsForAFlow(String flowName) {

        return null;
    }

    @Override
    public FlowConfig createFlow(FlowConfig newFlow, Boolean overwrite) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String compileFlow(String flowName, Integer version) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String validateFlow(String flowName, Integer version) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String buildFlow(String flowName, Integer version) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String deployFlow(String flowName, Integer version) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteFlow(String flowName, Integer version) {
        // TODO Auto-generated method stub
    }

}
