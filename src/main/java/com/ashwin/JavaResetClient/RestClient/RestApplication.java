/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ashwin.JavaResetClient.RestClient;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
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

            HttpResponse<String> res = HttpClient
                    .newBuilder()
                    .proxy(ProxySelector.getDefault())
                    .build()
                    .send(req, HttpResponse.BodyHandlers.ofString());
            
            Response response = new Response();
            response.setBody(res.body());
            
            Map<String, List<String>> headers = res.headers().map();
            Map<String, String> responseHeaders = new HashMap<>();
            
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                String val = entry.getValue().get(0);
                responseHeaders.put(key, val);
                if (key.toLowerCase().equals("content-type")) {
                    String contentType = val.split("/")[1];
                    response.setBodyType(contentType.toUpperCase());
                }
            }
            response.setHeaders(responseHeaders);
            return response;
        } catch (IOException | InterruptedException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
