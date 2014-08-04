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

package org.apache.falcon.designer.flow.serde;

import org.apache.falcon.designer.action.serde.PrimitiveSerDe;
import org.apache.falcon.designer.configuration.FlowConfig;

/**
 * Will serialzie a flow from from a FlowConfig object and vice versa.
 */
public final class FlowSerde implements PrimitiveSerDe<FlowConfig> {

    public String serialize(FlowConfig act) {
        throw new RuntimeException("Feature not implemented");

    }

    public FlowConfig deserialize(final String actString) {
        throw new RuntimeException("Feature not implemented");
    }
}
