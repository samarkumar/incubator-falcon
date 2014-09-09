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
package org.apache.falcon.designer.action.primitive.builder;

import javax.annotation.Nonnull;

import org.apache.falcon.designer.action.configuration.TransformationActionConfiguration;
import org.apache.falcon.designer.core.configuration.Feed;
import org.apache.falcon.designer.core.configuration.TransformConfiguration;
import org.apache.falcon.designer.core.primitive.builder.BuilderException;

/**
 * Builder to build TransformationActionConfiguration by adding more
 * transformations to existing DAG of transformation. Each addXX() method will
 * add to the existing DAG of {@link TransformConfiguration}
 */
public class TransformationActionConfigurationBuilder {

    private final TransformationActionConfiguration transformationActionInst;

    public TransformationActionConfigurationBuilder(String name) {

        transformationActionInst =
            new TransformationActionConfiguration(name);

    }

    @SuppressWarnings("rawtypes")
    public TransformationActionConfigurationBuilder addTransformation(
        @Nonnull TransformConfiguration outputTransformation,
        @Nonnull TransformConfiguration... inputTransformations) throws BuilderException {

        // check if all input Tranformations allready exist
        for (TransformConfiguration eachInputTransformations : inputTransformations) {
            if (transformationActionInst
                .readTransformation(eachInputTransformations.getName()) == null) {
                throw new BuilderException(
                    "input transformations should exists before");
            }
        }

        if (transformationActionInst.readTransformation(outputTransformation
            .getName()) == null) {
            transformationActionInst.addTransformation(outputTransformation);

        }

        for (TransformConfiguration eachInputTransformations : inputTransformations) {
            eachInputTransformations.setNextTransformation(outputTransformation
                .getName());
        }

        return this;

    }

    @SuppressWarnings("unchecked")
    public TransformationActionConfigurationBuilder addInputFeeds(
        @Nonnull TransformConfiguration outputTransformation,
        @Nonnull Feed... inputFeeds) throws BuilderException {

        if (transformationActionInst.readTransformation(outputTransformation
            .getName()) == null) {
            transformationActionInst.addTransformation(outputTransformation);
        }

        for (Feed eachFeed : inputFeeds) {
            transformationActionInst.readTransformation(outputTransformation
                .getName()).getInputFeed().add(eachFeed);
        }

        return this;

    }

    public TransformationActionConfigurationBuilder addOutPutFeed(
        @Nonnull TransformConfiguration outputTransformation,
        @Nonnull Feed outputFeed) throws BuilderException {

        if (transformationActionInst.readTransformation(outputTransformation
            .getName()) == null) {
            transformationActionInst.addTransformation(outputTransformation);

        }

        transformationActionInst.readTransformation(outputTransformation
            .getName()).setProducedFeed(outputFeed.getName());

        return this;

    }

    public TransformationActionConfiguration build() {
        return transformationActionInst;
    }

}
