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
import java.util.Map;

/**
 * Abstract ActionConfiguration extending Configuration.
 */
public abstract class ActionConfiguration<A extends ActionConfiguration> extends Configuration<A> {

    private static final String CATEGORY = "ACTION";

    private Map<String, String> nextActionMap = new HashMap<String, String>();

    @Override
    public String getCategory() {
        return CATEGORY;
    }

    public Map<String, String> getNextActionMap() {
        return nextActionMap;
    }

    public void setNextActionMap(Map<String, String> nextActionMap) {
        this.nextActionMap = nextActionMap;
    }

    public void addNextAction(String condition, String nextAction) {
        this.nextActionMap.put(condition, nextAction);
    }

}
