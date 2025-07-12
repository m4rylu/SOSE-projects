package it.sose.client;

import java.net.http.*;
import java.net.URI;
import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();

        
        /*
         * Request to coffee-tracker (producer) for adding the today's number of coffee
         */
        var request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8087/api/coffee/add/8"))
            .GET()
            .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        
        
        /*
         * Request to coffee-tracker (producer) to print the coffee tracker
         */
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8087/api/coffee/print"))
            .GET()
            .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        
        
        /*
         * Request to rate-tracker (producer) to add the day rate value
         */
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8087/api/rate/add/5"))
            .GET()
            .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        
        
        /*
         * Request to weather-tracker (producer) to add today's weather value
         */
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8087/api/weather/add/1"))
            .GET()
            .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        
        
        /*
         * Request to rate-tracker (producer) to add the day rate value
         */
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8087/api/sleep/add/10"))
            .GET()
            .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        
        
        /*
         * Request to rate-tracker (producer) to add the humor rate value
         */
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8087/api/humor/add/6"))
            .GET()
            .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        
        
        /*
         * Request to advice-coffee (prosumer) to print the advice for today
         */
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8087/api/advice/coffee/advice"))
            .GET()
            .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        
        
        /*
         * Request to advice-mood (prosumer) to print the advice for today
         */
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8087/api/advice/mood/advice"))
            .GET()
            .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        

    
        /*
         * Request to data-analysis-service (prosumer) to print the sleep data analysis
         * for this week
         */
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8087/api/data/analysis/sleepData"))
            .GET()
            .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        
        
        /*
         * Request to data-analysis-service (prosumer) to print the coffee data analysis
         * for this week
         */
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8087/api/data/analysis/coffeeData"))
            .GET()
            .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        
        
        /*
         * Request to data-analysis-service (prosumer) to print the coffee data analysis
         * for this week
         */
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8087/api/data/analysis/humorData"))
            .GET()
            .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
    
    }
}
