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
package org.apache.falcon.designer.action.configuration;

import java.util.ArrayList;
import java.util.List;
import org.apache.falcon.designer.configuration.ActionConfiguration;
import org.apache.falcon.designer.configuration.TransformConfiguration;

/**
 * Action holding a DAG of transformation. It represents a pig action.
 *
 */
public class TransformationActionConfiguration extends
    ActionConfiguration<TransformationActionConfiguration> {
    private String name;
    private List<TransformConfiguration> tranformationList;

    public TransformationActionConfiguration(String name) {
        tranformationList = new ArrayList<TransformConfiguration>();
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<TransformationActionConfiguration> getConfigClass() {
        return TransformationActionConfiguration.class;
    }

    public List<TransformConfiguration> getTranformationList() {
        return tranformationList;
    }

    public void setTranformationList(
        List<TransformConfiguration> tranformationList) {
        this.tranformationList = tranformationList;
    }
}
