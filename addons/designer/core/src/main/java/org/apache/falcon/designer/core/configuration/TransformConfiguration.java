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

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a abstract implementation of the a tranformation eg projection,
 * filter, group by .
 */
public abstract class TransformConfiguration<A extends TransformConfiguration> extends Configuration<A> {


    private static final String CATEGORY = "TRANSFORM";
    private String nextTransformation;
    private Set<Feed> inputFeed;
    private String producedFeed;
    private String name;

    public TransformConfiguration(String name) {
        this.name = name;
        this.inputFeed = new HashSet<Feed>();
    }

    @Override
    public String getCategory() {
        return CATEGORY;
    }

    public String getNextTransformation() {
        return nextTransformation;
    }

    public void setNextTransformation(String nextTransformation) {
        this.nextTransformation = nextTransformation;
    }

    public String getProducedFeed() {
        return producedFeed;
    }

    public void setProducedFeed(String producedFeed) {
        this.producedFeed = producedFeed;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Set<Feed> getInputFeed() {
        return inputFeed;
    }

    public void setInputFeed(Set<Feed> inputFeed) {
        this.inputFeed = inputFeed;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
