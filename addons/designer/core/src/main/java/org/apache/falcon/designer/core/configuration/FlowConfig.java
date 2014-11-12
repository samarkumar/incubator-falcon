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

package org.apache.falcon.designer.core.configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is top level config object represents the designer flow. This would
 * contain the entire dag of {@link ActionConfiguration}. This is the top level
 * object which would be exchange from the client to server.Flow data. More
 * parameters would need to be added.
 */
@XmlRootElement(name = "flow")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class FlowConfig extends Configuration<FlowConfig> {

    private String namespace;
    private String entity;
    private String name;
    private Integer version;
    private static final String CATEGORY = "FLOW";
    /**
     * The set of ActionConfiguration which forms the nodes of the DAG represent
     * by the FlowCOnfig object.
     */
    private Set<ActionConfiguration> actionsNodes =
        new HashSet<ActionConfiguration>();

    /**
     * Cache of actionConfigName to ActionConfiguration objects.
     * Mainly used for validation.
     */
    //private final Map<String, ActionConfiguration> cacheOfActions =
     //   new HashMap<String, ActionConfiguration>();

    private Map<String, Map<String, String>> dagOfActions =
        new HashMap<String, Map<String, String>>();

    public FlowConfig() {
    }

    public FlowConfig(String namespace, String entity, String name) {
        this.namespace = namespace;
        this.entity = entity;
        this.name = name;
    }

    @Override
    public String getCategory() {
        return CATEGORY;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    @Override
    public Class<FlowConfig> getConfigClass() {
        return FlowConfig.class;
    }

    public Set<ActionConfiguration> getActionsNodes() {
        return actionsNodes;
    }

    public void setActionsNodes(Set<ActionConfiguration> actionsNodes) {
        this.actionsNodes = actionsNodes;
    }

    public void addActions(ActionConfiguration actionsNode) {
        this.actionsNodes.add(actionsNode);

    }

    public void addToDag(String leftAction, String condition, String rightAction) {
        Map<String, String> conditionMap = this.dagOfActions.get(leftAction);
        if (conditionMap == null){
            conditionMap = new HashMap<String, String>();
            this.dagOfActions.put(leftAction, conditionMap);
        }
        conditionMap.put(condition, rightAction);
    }

    public String getNextAction(String leftAction, String condition) {
        Map<String, String> conditionMap = this.dagOfActions.get(leftAction);
        if (conditionMap == null){
            return null;
        }
        return conditionMap.get(condition);

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Map<String, Map<String, String>> getDagOfActions() {
        return dagOfActions;
    }

    public void setDagOfActions(Map<String, Map<String, String>> dagOfActions) {
        this.dagOfActions = dagOfActions;
    }

}
