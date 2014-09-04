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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Flow data. More parameters would need to be added.
 */
@XmlRootElement(name = "flow")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class FlowConfig extends Configuration<FlowConfig> {

    private String namespace;
    private String entity;
    private String name;
    private Integer version;
    private static final String CATEGORY = "FLOW";
    private Set<ActionConfiguration> actionsNodes;
    private Map<String, ActionConfiguration> cacheOfActions;

    public FlowConfig() {
    }

    public FlowConfig(String namespace, String entity, String name) {
        this.namespace = namespace;
        this.entity = entity;
        this.name = name;
        this.actionsNodes = new HashSet<ActionConfiguration>();
        this.cacheOfActions = new HashMap<String, ActionConfiguration>();
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

    @XmlElements({
        @XmlElement(name="emailAction"),
        @XmlElement(name="transformationAction"),
    })
    public Set<ActionConfiguration> getActionsNodes() {
        return actionsNodes;
    }

    public void setActionsNodes(Set<ActionConfiguration> actionsNodes) {
        for(ActionConfiguration actionConfig: actionsNodes){
            this.cacheOfActions.put(actionConfig.getName(), actionConfig);
        }
        this.actionsNodes = actionsNodes;
    }

    public ActionConfiguration findAction(String actionName){
        return this.cacheOfActions.get(actionName);
    }

    public void addActions(ActionConfiguration actionsNode){
        this.cacheOfActions.put(actionsNode.getName(), actionsNode);
        this.actionsNodes.add(actionsNode);

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

}
