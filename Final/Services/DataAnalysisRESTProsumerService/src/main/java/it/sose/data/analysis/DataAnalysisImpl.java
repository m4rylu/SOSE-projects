package it.sose.data.analysis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import it.sose.soap.sleep.LastValuesRequest;
import it.sose.soap.sleep.LastValuesResponse;
import it.sose.soap.sleep.SleepTrackerPort;
import it.sose.soap.sleep.SleepTrackerPortService;
import it.sose.soap.umor.UmorTracker;
import it.sose.soap.umor.UmorTrackerImplService;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.Response;

/**
 * Implementation of the DataAnalysis service that performs statistical computations
 * (mean, sum, min, max) on user-tracked data from the past week.
 * This service can be used as a backend utility to retrieve and analyze numerical data
 * from the following trackers:
 * <ul>
 *   <li>Sleep Tracker</li>
 *   <li>Coffee Tracker</li>
 *   <li>Humor Tracker</li>
 * </ul>
 */
@Path("/")
public class DataAnalysisImpl implements DataAnalysis {

    /**
     * This method implements the first operation of the DataAnalysis interface and
     * computes basic statistical metrics (mean, sum, min, max) for the last 7 days
     * of sleep data.
     * @return a string with the summary of sleep data analysis
     *
     **/
    @Override
    public String getSleepData() throws IOException, InterruptedException, ExecutionException {
        int[] lastValuesSleep = new int[7];
        boolean created = false;
        String filename = "";
        String advice;

		// Create a SOAP service client from generated artifacts
        SleepTrackerPortService service = new SleepTrackerPortService();
		// Retrieve the SOAP port from the generated service class
        SleepTrackerPort endpoint = service.getSleepTrackerPortSoap11();

        // Override endpoint address (because we're using using Docker)
        ((BindingProvider) endpoint).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://sleep-service:8082/services/sleepTracker"
            );
        
        // Prepare and send asynchronous SOAP request for LastValues operation
        LastValuesRequest request = new LastValuesRequest();
        Response<LastValuesResponse> futureSleepResponse = endpoint.lastValuesAsync(request);
 
        // Asynchronous waiting loop with creation of serializable file
        while (!futureSleepResponse.isDone()) {
            if (!created) {
                LocalDate today = LocalDate.now();
                LocalDate endOfPeriod = today.minusDays(1);
                LocalDate startOfPeriod = today.minusDays(7);

                filename = "/usr/local/tomcat/webapps/data/sleep/"+"sleep_" + startOfPeriod + "_to_" + endOfPeriod + ".ser";

                File file = new File(filename);
                file.createNewFile();

                created = true;
            }

            Thread.sleep(1000);
        }

        // When the result is ready extract the response
        String response = futureSleepResponse.get().getReturn();  // E.g. "[6,7,5,8,6,7,4]"

        // Parsing of the response
        response = response.replaceAll("[\\[\\]\\s]", "");        // rimuove [, ], e spazi
        String[] values = response.split(",");

        for (int i = 0; i < values.length && i < 7; i++) {
            lastValuesSleep[i] = Integer.parseInt(values[i]);
        }

        // Statistic calculation
        int sum = 0;
        int min = lastValuesSleep[0];
        int max = lastValuesSleep[0];

        for (int val : lastValuesSleep) {
            sum += val;
            if (val < min) min = val;
            if (val > max) max = val;
        }

        int mean = sum / lastValuesSleep.length;

        // Serialization of the SleepData object
        Data sleepData = new Data();
        sleepData.setMean(mean);
        sleepData.setSum(sum);
        sleepData.setMin(min);
        sleepData.setMax(max);

        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(sleepData);
        }

        // Final message
        advice = String.format(
            "STATISTICS FOR WEEK - SLEEP\nFile: %s\nMean: %d\nSum: %d\nMin: %d\nMax: %d",
            filename, mean, sum, min, max
        );

        return advice;
    }
    
    /**
     * This method implements the second operation of the DataAnalysis interface and
     * computes basic statistical metrics (mean, sum, min, max) for the last 7 days
     * of coffee data.
     * @return a string with the summary of coffee data analysis
     *
     **/
    public String getCoffeeData() throws IOException, InterruptedException, ExecutionException {
        int[] lastValuesCoffee = new int[7];
        boolean created = false;
        String filename = "";
        String advice;

		// Create a REST client using JAX-RS Client API for the rate service
        Client client = ClientBuilder.newClient();
        // Asynchronous GET call to the REST endpoint for last 7 values
        Future<jakarta.ws.rs.core.Response> futureCoffeeResponse = client.target("http://coffee-service:8080/CoffeeTrackerRESTServiceMaven/coffee/lastValues").request().async().get();
		
        // Asynchronous waiting loop with creation of serializable file
        while (!futureCoffeeResponse.isDone()) {
            if (!created) {
                LocalDate today = LocalDate.now();
                LocalDate endOfPeriod = today.minusDays(1);
                LocalDate startOfPeriod = today.minusDays(7);

                filename = "/usr/local/tomcat/webapps/data/coffee/"+"coffee_" + startOfPeriod + "_to_" + endOfPeriod + ".ser";

                File file = new File(filename);
                file.createNewFile();

                created = true;
            }

            Thread.sleep(1000);
        }

        // When the result is ready extract the response
        String response = futureCoffeeResponse.get().readEntity(String.class);  // E.g. "[6,7,5,8,6,7,4]"

        // Parsing of the response
        response = response.replaceAll("[\\[\\]\\s]", "");        // delete [, ], and spaces
        String[] values = response.split(",");

        for (int i = 0; i < values.length && i < 7; i++) {
            lastValuesCoffee[i] = Integer.parseInt(values[i]);
        }

        // Statistic calculation
        int sum = 0;
        int min = lastValuesCoffee[0];
        int max = lastValuesCoffee[0];

        for (int val : lastValuesCoffee) {
            sum += val;
            if (val < min) min = val;
            if (val > max) max = val;
        }

        int mean = sum / lastValuesCoffee.length;

        // Serialization of the coffeeData object
        Data coffeeData = new Data();
        coffeeData.setMean(mean);
        coffeeData.setSum(sum);
        coffeeData.setMin(min);
        coffeeData.setMax(max);

        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(coffeeData);
        }

        // Final message
        advice = String.format(
            "STATISTICS FOR WEEK - COFFEE\nFile: %s\nMean: %d\nSum: %d\nMin: %d\nMax: %d",
            filename, mean, sum, min, max
        );

        return advice;
    }
    
    /**
     * This method implements the third operation of the DataAnalysis interface and
     * computes basic statistical metrics (mean, sum, min, max) for the last 7 days
     * of humor data.
     * @return a string with the summary of data analysis
     *
     **/
    @Override
    public String getHumorData() throws IOException, InterruptedException, ExecutionException {
        int[] lastValuesHumor = new int[7];
        boolean created = false;
        String filename = "";
        String advice;

		// Create a SOAP service client from generated artifacts
        UmorTrackerImplService service = new UmorTrackerImplService();
        // Retrieve the SOAP port from the generated service class
        UmorTracker endpoint = service.getUmorTrackerImplPort() ;

        // Override endpoint address (because we're using using Docker)
        ((BindingProvider) endpoint).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://humor-service:8085/UmorTrackerSOAPServiceMaven/umor"
            );

        // Send asynchronous SOAP request for LastValues operation
        Response<it.sose.soap.umor.LastValuesResponse> futureHumorResponse = endpoint.lastValuesAsync();
 
        // Asynchronous waiting loop with creation of serializable file
        while (!futureHumorResponse.isDone()) {
            if (!created) {
                LocalDate today = LocalDate.now();
                LocalDate endOfPeriod = today.minusDays(1);
                LocalDate startOfPeriod = today.minusDays(7);

                filename = "/usr/local/tomcat/webapps/data/humor/"+"humor_" + startOfPeriod + "_to_" + endOfPeriod + ".ser";

                File file = new File(filename);
                file.createNewFile();

                created = true;
            }

            Thread.sleep(1000);
        }

        // When the result is ready extract the response
        String response = futureHumorResponse.get().getReturn();  // E.g. "[6,7,5,8,6,7,4]"

        // Parsing of response
        response = response.replaceAll("[\\[\\]\\s]", "");        // rimuove [, ], e spazi
        String[] values = response.split(",");

        for (int i = 0; i < values.length && i < 7; i++) {
            lastValuesHumor[i] = Integer.parseInt(values[i]);
        }

        // Statistical calculation
        int sum = 0;
        int min = lastValuesHumor[0];
        int max = lastValuesHumor[0];

        for (int val : lastValuesHumor) {
            sum += val;
            if (val < min) min = val;
            if (val > max) max = val;
        }

        int mean = sum / lastValuesHumor.length;

        // Serialization of the HumorData object
        Data humorData = new Data();
        humorData.setMean(mean);
        humorData.setSum(sum);
        humorData.setMin(min);
        humorData.setMax(max);

        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(humorData);
        }

        // Final message
        advice = String.format(
            "STATISTICS FOR WEEK - HUMOR\nFile: %s\nMean: %d\nSum: %d\nMin: %d\nMax: %d",
            filename, mean, sum, min, max
        );

        return advice;
    }
}
