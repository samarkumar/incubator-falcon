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
package org.apache.falcon.designer.core.service;

import java.util.List;

import org.apache.falcon.designer.core.configuration.FlowConfig;

/**
 * Service contract.
 */
public interface FalconDesigner {

    /**
     * Would it return all flows x all version or just latest flow.
     * @return List of flowNames
     */
    List<String> getListOfFlowsNames();

    /**
     * Return List of versions supported for a given FlowName.
     * @param flowName
     * @return List of versions supported for a given FlowName
     */

    List<Integer> getAllVersionsForAFlow(String flowName);

    /**
     * Get A flow for with a flowName and a particular Version.
     * @param flowName
     * @param version
     * @return
     */
    FlowConfig getAFlow(String flowName, int version);

    /**
     * Will take care of creating/updating new existing version.
     * @param newFlow
     * @param overwrite
     * @return FlowConfig with new updated version
     */
    FlowConfig createFlow(FlowConfig newFlow, Boolean overwrite);

    /**
     * Compile a flow give flowName and a version.
     * @param flowName
     * @param version
     * @return
     */
    String compileFlow(String flowName, Integer version);

    /**
     * Validate a flow give flowName and a version.
     * @param flowName
     * @param version
     * @return
     */
    String validateFlow(String flowName, Integer version);

    /**
     * Build a flow give flowName and a version.
     * @param flowName
     * @param version
     * @return
     */
    String buildFlow(String flowName, Integer version);

    /**
     * Deploy a flow give flowName and a version.
     * @param flowName
     * @param version
     * @return
     */
    String deployFlow(String flowName, Integer version);

    /**
     * Delete a flow give flowName and a version.
     * @param flowName
     * @param version
     * @return
     */
    void deleteFlow(String flowName, Integer version);

}
