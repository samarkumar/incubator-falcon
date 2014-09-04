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

/**
 * Represents a falcon feed.
 *
 */
public class Feed extends Configuration<Feed> {

    private String name;

    // Schema type should be thought over
    private String schema;

    private static final String CATEGORY = "FEED";

    public Feed(String name) {
        this.name = name;
    }

    @Override
    public String getCategory() {
        return CATEGORY;
    }

    @Override
    public Class<Feed> getConfigClass() {
        return Feed.class;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
