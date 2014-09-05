/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.falcon.designer.core.primitive.builder;

import java.util.Set;

import javax.annotation.Nonnull;

import org.apache.falcon.designer.core.configuration.ActionConfiguration;
import org.apache.falcon.designer.core.configuration.FlowConfig;

import com.sun.istack.NotNull;

/**
 * Create a flow.
 */
public class FlowBuilder {
    private FlowConfig flowConfigInst = new FlowConfig();

    public FlowBuilder(@NotNull String nameSpace, @NotNull String entity,
        @NotNull String name, @NotNull ActionConfiguration... startNodes) {
        flowConfigInst = new FlowConfig(nameSpace, entity, name);
        for (ActionConfiguration eachStartNode : startNodes) {
            flowConfigInst.addActions(eachStartNode);
        }
    }

    public FlowBuilder(@NotNull String nameSpace, @NotNull String entity,
        @NotNull String name, @Nonnull Set<ActionConfiguration> startNodes) {
        flowConfigInst = new FlowConfig(nameSpace, entity, name);
        flowConfigInst.setActionsNodes(startNodes);
    }

    public FlowBuilder addAction(
        @Nonnull ActionConfiguration p_leftActionName,
        @Nonnull String p_condition, @Nonnull ActionConfiguration p_rightAction) {
        ActionConfiguration leftAction =
            flowConfigInst.findAction(p_leftActionName.getName());

        ActionConfiguration rightActionFromCache =
            flowConfigInst.findAction(p_rightAction.getName());
        if (rightActionFromCache == null) {
            flowConfigInst.addActions(p_rightAction);
        }
        leftAction.addNextAction(p_condition, p_rightAction.getName());
        return this;
    }

    public FlowConfig build() {
        return flowConfigInst;

    }
}
