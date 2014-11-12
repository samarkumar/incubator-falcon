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

package org.apache.falcon.designer.examples.flow;

import org.apache.falcon.designer.action.configuration.EmailActionConfiguration;
import org.apache.falcon.designer.action.configuration.TransformationActionConfiguration;
import org.apache.falcon.designer.action.primitive.builder.TransformationActionConfigurationBuilder;
import org.apache.falcon.designer.core.configuration.Feed;
import org.apache.falcon.designer.core.configuration.FlowConfig;
import org.apache.falcon.designer.core.configuration.SerdeException;
import org.apache.falcon.designer.core.primitive.builder.BuilderException;
import org.apache.falcon.designer.core.primitive.builder.FlowBuilder;
import org.apache.falcon.designer.transformation.configuration.FilterTransformation;
import org.apache.falcon.designer.transformation.configuration.JoinTransformation;

/**
 * A simple flow created using the FlowBuilder. This will help test the
 * flowBuilder and understand the serialized JSON representation of the flow.
 */
public class SimpleFlowExample {

    public static void main(String[] args) throws BuilderException, SerdeException {
        SimpleFlowExample simpleFlowConfig = new SimpleFlowExample();
        FlowConfig newFlowConfig = simpleFlowConfig.createAnotherFlow();
        System.out.println(newFlowConfig.serialize());

    }

    public FlowConfig createAnotherFlow() throws BuilderException {
        EmailActionConfiguration firstEmail =
            new EmailActionConfiguration("startEmailAction_1");
        firstEmail.setSubject("frist action body");
        firstEmail.setTo("grid@grid.com");
        firstEmail.setBody("About to start the job");

        TransformationActionConfigurationBuilder transformationBuilder =
            new TransformationActionConfigurationBuilder(
                "Transformation_action2");

        EmailActionConfiguration thirdEmail =
            new EmailActionConfiguration("endEmailAction_3");
        thirdEmail.setSubject("last action body");
        thirdEmail.setTo("sucessgrid@grid.com");
        thirdEmail.setBody("the transformationFailed");

        FlowBuilder fb =
            new FlowBuilder("nameSpace", "flow", "firstFlow", firstEmail);

        // list of transformations
        FilterTransformation firstTrans =
            new FilterTransformation("firstFilter");
        JoinTransformation secondTrans =
            new JoinTransformation("joinTransfer");
        FilterTransformation thirdTrans =
            new FilterTransformation("secondFilter");

        // list of feeds
        Feed feed1 = new Feed("Feed1");
        Feed feed2 = new Feed("Feed2");
        Feed feed3 = new Feed("Feed3");

        // build the tranformations

        TransformationActionConfiguration secondTranformationAction;

        secondTranformationAction =
            transformationBuilder.addInputFeeds(firstTrans, feed1)
                .addTransformation(secondTrans, firstTrans)
                .addInputFeeds(secondTrans, feed2)
                .addTransformation(thirdTrans, secondTrans)
                .addOutPutFeed(thirdTrans, feed3).build();

        // build flow

        fb.addAction(secondTranformationAction).addAction(thirdEmail).
            addToActionDAG(firstEmail, "sucess", secondTranformationAction)
            .addToActionDAG(firstEmail, "failure", thirdEmail);
        return fb.build();
    }

}
