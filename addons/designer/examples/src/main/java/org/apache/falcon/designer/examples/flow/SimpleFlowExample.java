package org.apache.falcon.designer.examples.flow;

import org.apache.falcon.designer.action.configuration.EmailActionConfiguration;
import org.apache.falcon.designer.action.configuration.TransformationActionConfiguration;
import org.apache.falcon.designer.action.primitive.builder.TransformationActionConfigurationBuilder;
import org.apache.falcon.designer.core.configuration.Feed;
import org.apache.falcon.designer.core.configuration.FlowConfig;
import org.apache.falcon.designer.core.configuration.SerdeException;
import org.apache.falcon.designer.core.primitive.builder.BuilderException;
import org.apache.falcon.designer.core.primitive.builder.FlowBuilder;
import org.apache.falcon.designer.core.service.FalconDesigner;
import org.apache.falcon.designer.server.service.client.DesignerFlowRestClient;
import org.apache.falcon.designer.transformation.configuration.FilterTransformation;
import org.apache.falcon.designer.transformation.configuration.JoinTransformation;

public class SimpleFlowExample {

    public static void main(String args[]) throws BuilderException, SerdeException {
        final String localbaseURL =
            "http://localhost:8080/designer-server/api/";
        FalconDesigner restClient =
            new DesignerFlowRestClient(localbaseURL);
        // use the builder to create a new flow;
        FlowConfig newFlowConfig = createAnotherFlow();
        newFlowConfig = new FlowConfig();
        // create the flow using the client apis
        //FlowConfig createdFlow = restClient.createFlow(newFlowConfig, true);
        //System.out.println("Created flow " + createdFlow);
        FlowConfig fl = restClient.getAFlow("anyFlow", 2);
        System.out.println("Final output " + fl.getCategory());
        System.out.println("Final output " + fl.serialize());

        // do somethin with the flow; :)

    }

    private static FlowConfig createAnotherFlow() throws BuilderException {
        EmailActionConfiguration _1_email =
            new EmailActionConfiguration("startEmailAction_1");
        _1_email.setSubject("frist action body");
        _1_email.setTo("grid@grid.com");
        _1_email.setBody("About to start the job");

        TransformationActionConfigurationBuilder transformationBuilder =
            new TransformationActionConfigurationBuilder(
                "Transformation_action2");

        EmailActionConfiguration _3_email =
            new EmailActionConfiguration("endEmailAction_3");
        _3_email.setSubject("last action body");
        _3_email.setTo("sucessgrid@grid.com");
        _3_email.setBody("the transformationFailed");

        FlowBuilder fb =
            new FlowBuilder("nameSpace", "flow", "firstFlow", _1_email);

        // list of transformations
        FilterTransformation _1_trans =
            new FilterTransformation("firstFilter");
        JoinTransformation _2_trans =
            new JoinTransformation("joinTransfer");
        FilterTransformation _3_trans =
            new FilterTransformation("secondFilter");

        // list of feeds
        Feed feed1 = new Feed("Feed1");
        Feed feed2 = new Feed("Feed2");
        Feed feed3 = new Feed("Feed3");

        // build the tranformations

        TransformationActionConfiguration _2_tranformationAction;

        _2_tranformationAction =
            transformationBuilder.addInputFeeds(_1_trans, feed1)
                .addTransformation(_2_trans, _1_trans)
                .addInputFeeds(_2_trans, feed2)
                .addTransformation(_3_trans, _2_trans)
                .addOutPutFeed(_3_trans, feed3).build();

        // build flow

      //  fb.addAction(_1_email, "sucess", _2_tranformationAction)
        //    .addAction(_1_email, "failure", _3_email);
        return fb.build();
    }

}
