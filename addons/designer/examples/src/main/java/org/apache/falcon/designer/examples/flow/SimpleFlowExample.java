package org.apache.falcon.designer.examples.flow;

import org.apache.falcon.designer.action.configuration.EmailActionConfiguration;
import org.apache.falcon.designer.action.configuration.TransformationActionConfiguration;
import org.apache.falcon.designer.core.configuration.Feed;
import org.apache.falcon.designer.core.configuration.FlowConfig;
import org.apache.falcon.designer.core.configuration.SerdeException;
import org.apache.falcon.designer.core.configuration.TransformConfiguration;
import org.apache.falcon.designer.core.service.FalconDesigner;
import org.apache.falcon.designer.primitive.action.builder.TransformationActionConfigurationBuilder;
import org.apache.falcon.designer.primitive.builder.BuilderException;
import org.apache.falcon.designer.primitive.builder.FlowBuilder;
import org.apache.falcon.designer.server.service.client.DesignerFlowRestClient;
import org.apache.falcon.designer.transformation.configuration.CoGroupTransformation;
import org.apache.falcon.designer.transformation.configuration.FilterTransformation;
import org.apache.falcon.designer.transformation.configuration.GroupByTransformation;
import org.apache.falcon.designer.transformation.configuration.JoinTransformation;
import org.apache.falcon.designer.transformation.configuration.ProjectionTransformation;

public class SimpleFlowExample {

    public static void main(String args[]) throws BuilderException, SerdeException {
        final String localbaseURL =
            "http://localhost:8080/designer-server/api/";
        FalconDesigner restClient =
            new DesignerFlowRestClient(localbaseURL);
        // use the builder to create a new flow;
        FlowConfig newFlowConfig = createAnotherFlow();
        // create the flow using the client apis
        // restClient.createFlow(newFlowConfig);
        FlowConfig fl = restClient.getAFlow("anyFlow", 2);
        System.out.println("Final output " + fl.getCategory());
        System.out.println("Final output " + fl.serialize());
        // do somethin with the flow; :)

    }

    private static TransformationActionConfiguration createNetworkfactAction() throws BuilderException {

        TransformationActionConfigurationBuilder transformationBuilder =
            new TransformationActionConfigurationBuilder(
                "networkfact");

        Feed NetworkConversionLog_Thrift_Feed1 =
            new Feed("NetworkConversionLog_Thrift_Feed1");
        Feed NetworkFraudLog_Thrift_Feed1 =
            new Feed("NetworkFraudLog_Thrift_Feed2");

        Feed networkfact_output_Feed = new Feed("networkfact_output_Feed");

        TransformConfiguration coGroupTransformation_networkfactMapper =
            new CoGroupTransformation("networkfactMapper");
        TransformConfiguration projectionTransformation_NetworkFactReducer =
            new ProjectionTransformation("NetworkFactReducer");

        return transformationBuilder
            .addInputFeeds(coGroupTransformation_networkfactMapper,
                NetworkConversionLog_Thrift_Feed1,
                NetworkFraudLog_Thrift_Feed1)
            .addTransformation(projectionTransformation_NetworkFactReducer,
                coGroupTransformation_networkfactMapper)
            .addOutPutFeed(projectionTransformation_NetworkFactReducer,
                networkfact_output_Feed).build();

    }

    private static TransformationActionConfiguration createSupplyFactAction() throws BuilderException {

        TransformationActionConfigurationBuilder transformationBuilder =
            new TransformationActionConfigurationBuilder(
                "SupplyFact");

        Feed SupplyImpressionLog_Thrift_Feed1 =
            new Feed("SupplyImpressionLog_Thrift_Feed1");
        Feed SupplyRequestLog_Thrift_Feed3 =
            new Feed("SupplyRequestLog_Thrift_Feed3");

        Feed networkfact_output_Feed = new Feed("networkfact_output_Feed");

        TransformConfiguration coGroupTransformation_supplyfact_mapper =
            new CoGroupTransformation("supplyfact_mapper");
        TransformConfiguration projectionTransformation_SupplyFactReducer =
            new ProjectionTransformation("SupplyFactReducer");

        return transformationBuilder
            .addInputFeeds(coGroupTransformation_supplyfact_mapper,
                SupplyImpressionLog_Thrift_Feed1,
                SupplyRequestLog_Thrift_Feed3)
            .addTransformation(projectionTransformation_SupplyFactReducer,
                coGroupTransformation_supplyfact_mapper)
            .addOutPutFeed(projectionTransformation_SupplyFactReducer,
                networkfact_output_Feed).build();

    }

    private static TransformationActionConfiguration createAdservesummaryAction() throws BuilderException {

        TransformationActionConfigurationBuilder transformationBuilder =
            new TransformationActionConfigurationBuilder(
                "adservesummary");

        Feed ImpressionRCLog_Thrift_Feed1 =
            new Feed("ImpressionRCLog_Thrift_Feed1");
        Feed RequestRCLog_Thrift_Feed3 =
            new Feed("RequestRCLog_Thrift_Feed3");

        Feed adservesummary_output_Feed =
            new Feed("adservesummary_output_Feed");

        TransformConfiguration coGroupTransformation_supplyfact_mapper =
            new CoGroupTransformation("supplyfact_mapper");
        TransformConfiguration projectionTransformation_SupplyFactReducer =
            new ProjectionTransformation("SupplyFactReducer");

        return transformationBuilder
            .addInputFeeds(coGroupTransformation_supplyfact_mapper,
                ImpressionRCLog_Thrift_Feed1,
                RequestRCLog_Thrift_Feed3)
            .addTransformation(projectionTransformation_SupplyFactReducer,
                coGroupTransformation_supplyfact_mapper)
            .addOutPutFeed(projectionTransformation_SupplyFactReducer,
                adservesummary_output_Feed).build();

    }

    private static TransformationActionConfiguration createDemandfactAction() throws BuilderException {

        TransformationActionConfigurationBuilder transformationBuilder =
            new TransformationActionConfigurationBuilder(
                "demandfact");

        Feed NetworkOutput_Feed1 =
            new Feed("NetworkOutput_Feed1");

        Feed DemandOutput_output_Feed = new Feed("DemandOutput_output_Feed");

        TransformConfiguration groupbyTransformation_supplyfact_mapper =
            new GroupByTransformation("GROUPEDDATA");

        TransformConfiguration projectionTransformation_AGGEREGATEDATA =
            new ProjectionTransformation("AGGEREGATEDATA");

        TransformConfiguration projectionTransformation_PROJECTEDDATA =
            new ProjectionTransformation("PROJECTEDDATA");

        return transformationBuilder
            .addInputFeeds(groupbyTransformation_supplyfact_mapper,
                NetworkOutput_Feed1)
            .addTransformation(projectionTransformation_AGGEREGATEDATA,
                groupbyTransformation_supplyfact_mapper)
            .addTransformation(projectionTransformation_PROJECTEDDATA,
                projectionTransformation_AGGEREGATEDATA)
            .addOutPutFeed(projectionTransformation_PROJECTEDDATA,
                DemandOutput_output_Feed).build();

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

        fb.addAction(_1_email, "sucess", _2_tranformationAction)
            .addAction(_1_email, "failure", _3_email);
        return fb.build();
    }

}
