import com.amazonaws.services.dynamodbv2.xspec.S;
import com.amazonaws.services.machinelearning.AmazonMachineLearning;
import com.amazonaws.services.machinelearning.AmazonMachineLearningAsyncClient;
import com.amazonaws.services.machinelearning.AmazonMachineLearningClient;
import com.amazonaws.services.machinelearning.AmazonMachineLearningClientBuilder;
import com.amazonaws.services.machinelearning.model.*;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;

import java.util.HashMap;
import java.util.Map;


public class NewestPredict {

    public static void main(String[] args) throws InterruptedException
    {
        //Get credentials
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAIE4IYORQY3X274FQ", "7vdsTPydH93ZqCTB6xos7REC2fcmhGUnj4ZXjj7E");
        AWSCredentials credentials = awsCreds;

        AmazonMachineLearningClient awsMlClient = new AmazonMachineLearningAsyncClient(credentials);
        System.out.println("Client Created.");

        DescribeMLModelsResult awsModels = awsMlClient.describeMLModels();

        MLModel myModel = awsModels.getResults().get(3);
        if(myModel == null)
        {
            System.out.println("Model not found.");
            //Handle exception
        }

        System.out.println("Model name: " + myModel.getName());
        System.out.println("Model id: " + myModel.getMLModelId());
        System.out.println("Model status: " + myModel.getStatus());

        CreateRealtimeEndpointRequest endpointRequest = new CreateRealtimeEndpointRequest().withMLModelId("ml-NIbTA88qWgL");
        if(endpointRequest == null)
        {
            System.out.println("Endpoint request not constructed.");
            //Handle exception
        }

        CreateRealtimeEndpointResult endpointResult = awsMlClient.createRealtimeEndpoint(endpointRequest);
        System.out.println("endpoint url: " + endpointResult.getRealtimeEndpointInfo().getEndpointUrl());
        System.out.println("endpoint status: " + endpointResult.getRealtimeEndpointInfo().getEndpointStatus());


        PredictRequest predictionRequest = new PredictRequest().withMLModelId("ml-NIbTA88qWgL");
        predictionRequest.setPredictEndpoint(endpointResult.getRealtimeEndpointInfo().getEndpointUrl());
        System.out.println("prediction request model id: " + predictionRequest.getMLModelId());
        System.out.println("prediction request endpoint url: " + predictionRequest.getPredictEndpoint());

        Map<String, String> patientInfo = new HashMap<String, String>();
        patientInfo.put("Body Temp", "101.7");
        patientInfo.put("Heart Rate", "93");
        patientInfo.put("Respiratory Rate", "18");
        patientInfo.put("Urine Output", "False");
        patientInfo.put("Platelet Count", "378188");
        patientInfo.put("Difficulty Breathing", "False");
        patientInfo.put("Abnormal Heart Pump", "False");
        patientInfo.put("Age", "34");
        patientInfo.put("Abdominal Pain", "False");
        patientInfo.put("Weakened Immune System", "True");


        PredictResult finalResult = simplePrediction(awsMlClient, predictionRequest.getMLModelId(), predictionRequest.getPredictEndpoint(), patientInfo);

        System.out.println(finalResult);

        if(finalResult.getPrediction().getPredictedLabel().compareTo("1") == 0)
        {
            System.out.println("Patient has sepsis.");
        }
        else
        {
            System.out.println("Patient doesn't have sepsis.");
        }


        //Delete endpoint to save money
      //  DeleteRealtimeEndpointRequest deleteRealtimeEndpointRequest = new DeleteRealtimeEndpointRequest().withMLModelId("ml-NIbTA88qWgL");
      //  DeleteRealtimeEndpointResult deleteRealtimeEndpointResult = awsMlClient.deleteRealtimeEndpoint(deleteRealtimeEndpointRequest);
        System.out.println("Endpoint has been deleted.");


    }

    public static PredictResult simplePrediction(AmazonMachineLearningClient client, String mlModelId, String predictEndpoint, Map<String,String> record) {


        PredictRequest predictRequest = new PredictRequest()
                .withMLModelId(mlModelId)
                .withPredictEndpoint(predictEndpoint)
                .withRecord(record);
        PredictResult prediction = client.predict(predictRequest);
        return prediction;
    }


}
