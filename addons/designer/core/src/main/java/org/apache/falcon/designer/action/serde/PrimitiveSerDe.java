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
package org.apache.falcon.designer.action.serde;

import org.apache.falcon.designer.configuration.Configuration;


/**
 * interface defining contract for all ActionSerDes.
 * @param <T>
 */
public interface PrimitiveSerDe<T extends Configuration> {

    /**
     * Serialize it from a action type to string type.
     * @param act actual Action Configuration data
     * @return
     */
    String serialize(T act);

    /**
     * Deserialize from string to object of Action type.
     * @param actString actual Data
     * @return
     */
    T deserialize(String actString);

}
