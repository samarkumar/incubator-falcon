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

package org.apache.falcon.designer.server.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.falcon.designer.core.configuration.FlowConfig;
import org.apache.falcon.designer.core.service.FalconDesigner;
import org.apache.falcon.designer.core.service.impl.FalconDesignerImpl;
import org.apache.falcon.designer.core.sysconfig.SystemConfiguration;

/**
 * Rest service implementation of the {@link FalconDesigner} contract.This class
 * would expose the same api over rest.
 */
@Path("flow")
public class FalconDesignerResource implements FalconDesigner {

    private final FalconDesigner designerFlowInst;

    public FalconDesignerResource() {
        // Should be able to get all the system configurations
        SystemConfiguration sysConfigs = new SystemConfiguration();
        designerFlowInst =
            new FalconDesignerImpl(sysConfigs);
    }

    /**
     * Get all the unique flows in the designer.
     */
    @GET
    @Path("list")
    @Produces({ MediaType.APPLICATION_JSON })
    @Override
    public List<String> getListOfFlowsNames() {
        return designerFlowInst.getListOfFlowsNames();
    }

    /**
     * Given a flowName a get the flow configuration.
     */
    @GET
    @Path("get")
    @Produces({ MediaType.APPLICATION_JSON })
    @Override
    public FlowConfig getAFlow(@PathParam("flowName") String flowName,
        @PathParam("version") int version) {
        return designerFlowInst.getAFlow(flowName, version);
    }


    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public FlowConfig createFlow(FlowConfig newFlow, Boolean overwrite) {
        return designerFlowInst.createFlow(newFlow, overwrite);

    }

    @Override
    @POST
    @Path("compile")
    @Consumes(MediaType.APPLICATION_JSON)
    public String compileFlow(String flowName, Integer version) {
        return designerFlowInst.compileFlow(flowName, version);
    }

    @Override
    @POST
    @Path("validate")
    @Consumes(MediaType.APPLICATION_JSON)
    public String validateFlow(String
        flowName, Integer version) {
        return designerFlowInst.validateFlow(flowName, version);
    }

    @POST
    @Path("build")
    @Override
    public String buildFlow(String
        flowName, Integer version) {
        return designerFlowInst.buildFlow(flowName, version);
    }

    @POST
    @Path("deploy")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public String deployFlow(String
        flowName, Integer version) {
        return designerFlowInst.deployFlow(flowName, version);
    }

    @DELETE
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void deleteFlow(String
        flowName, Integer version) {
        designerFlowInst.deleteFlow(flowName, version);

    }

    @GET
    @Path("allversions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_JSON })
    @Override
    public List<Integer> getAllVersionsForAFlow(
        @PathParam("flowName") String flowName) {
        return designerFlowInst.getAllVersionsForAFlow(flowName);
    }

}
