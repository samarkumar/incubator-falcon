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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.falcon.designer.core.configuration.ActionConfiguration;
import org.apache.falcon.designer.core.configuration.TransformConfiguration;
import org.apache.falcon.designer.primitive.builder.BuilderException;

/**
 * Action holding a DAG of transformation. It represents a pig action.
 */
@XmlRootElement(name = "transformationAction")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TransformationActionConfiguration extends
    ActionConfiguration<TransformationActionConfiguration> {
    private String name;
    private final Set<TransformConfiguration> tranformationList;
    private final Map<String, TransformConfiguration> cacheOfTransformations =
        new HashMap<String, TransformConfiguration>();

    public TransformationActionConfiguration(String name) {
        tranformationList = new HashSet<TransformConfiguration>();
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

    public Set<TransformConfiguration> getTranformationList() {
        return tranformationList;
    }

    public void setTranformationList(
        Set<TransformConfiguration> tranformationSet) throws BuilderException {
        for (TransformConfiguration eachTrasformation : tranformationSet) {
            addTransformation(eachTrasformation);
        }
    }

    public void addTransformation(TransformConfiguration eachTrasformation) throws BuilderException {
        TransformConfiguration transformationFromCache =
            cacheOfTransformations.get(eachTrasformation.getName());
        if (transformationFromCache == null) {
            cacheOfTransformations.put(eachTrasformation.getName(),
                eachTrasformation);
            tranformationList.add(eachTrasformation);
        } else {
            throw new BuilderException("Tranformation already exists :"
                + transformationFromCache.getName());
        }

    }

    public TransformConfiguration readTransformation(String transformationName) {
        return cacheOfTransformations.get(transformationName);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
