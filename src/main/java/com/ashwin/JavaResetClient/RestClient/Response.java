/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ashwin.JavaResetClient.RestClient;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ashwi
 */
public class Response {
    private Map<String, String> headers = new HashMap<>();
    private String body = "";
    private String bodyType = "NONE";
    

    public Response() {
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
    
}
