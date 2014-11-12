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
 * PlaceHolder for implementation of a CoGroup transformation. <b>Note:</b> The GROUP
 * and COGROUP operators are identical. Both operators work with one or more
 * relations. For readability GROUP is used in statements involving one relation
 * and COGROUP is used in statements involving two or more relations.
 */
@XmlRootElement(name = "coGroupTransformation")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CoGroupTransformation extends
    TransformConfiguration<CoGroupTransformation> {

    public CoGroupTransformation(String name) {
        super(name);
    }

    @Override
    public Class<CoGroupTransformation> getConfigClass() {

        return CoGroupTransformation.class;
    }

}
