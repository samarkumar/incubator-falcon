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

package org.apache.falcon.designer.transformation.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.falcon.designer.core.configuration.TransformConfiguration;

/**
 * PlaceHolder for implementation of a Projection transformation. Projection
 * refers to that subset of the set of all columns found in a table, that you
 * want returned.
 */
@XmlRootElement(name = "projectionTransformation")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProjectionTransformation extends
    TransformConfiguration<ProjectionTransformation> {

    public ProjectionTransformation(String name) {
        super(name);
    }

    @Override
    public Class<ProjectionTransformation> getConfigClass() {

        return ProjectionTransformation.class;
    }

}
