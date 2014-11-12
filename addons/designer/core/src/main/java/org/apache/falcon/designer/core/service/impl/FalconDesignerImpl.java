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

package org.apache.falcon.designer.core.service.impl;

import java.util.List;

import org.apache.falcon.designer.core.configuration.FlowConfig;
import org.apache.falcon.designer.core.service.FalconDesigner;
import org.apache.falcon.designer.core.sysconfig.SystemConfiguration;

/**
 * The API implementations of the contact between the server and the clients.
 */
public class FalconDesignerImpl implements FalconDesigner {
    private final SystemConfiguration sysConfigs;

    public FalconDesignerImpl(SystemConfiguration sysConfigs) {
        this.sysConfigs = sysConfigs;
    }

    @Override
    public List<String> getListOfFlowsNames() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Integer> getAllVersionsForAFlow(String flowName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FlowConfig getAFlow(String flowName, int version) {
        // TODO Auto-generated method stub
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
