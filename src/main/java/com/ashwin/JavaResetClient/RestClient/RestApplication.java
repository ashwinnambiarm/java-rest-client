/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ashwin.JavaResetClient.RestClient;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestApplication {

    private static final Logger LOGGER = Logger.getLogger(RestApplication.class.getName());
    
    public Response execute(Request request) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        
        try {
            builder.uri(new URI(request.getUrl()));
        } catch (URISyntaxException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        
        if (request.getType().equals(Request.Type.POST)){
            builder.POST(HttpRequest.BodyPublishers.ofString(request.getBody()));
        }else{
            builder.GET();
        }
        
        if (!request.getBodyType().equals(Request.BodyType.NONE)){
            builder.header("Content-Type", "application/" + request.getBodyType().toString().toLowerCase());
        }
        
        for (Map.Entry<String, String> header : request.getHeaders().entrySet()) {
            builder.header(header.getKey(), header.getValue());
        }
        
        try {
            HttpRequest req = builder.build();

            HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .proxy(ProxySelector.getDefault())
                    .build()
                    .send(req, HttpResponse.BodyHandlers.ofString());
            
            System.out.println(response.body());
            return null;
        } catch (IOException | InterruptedException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
