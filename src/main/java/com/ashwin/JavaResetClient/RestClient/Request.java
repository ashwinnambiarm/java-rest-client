/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ashwin.JavaResetClient.RestClient;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ashwin
 */
public class Request {
    private Map<String, String> params = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();
    private String body = "";
    private BodyType bodyType = null;
    private Type type = null;
    private String url = null;
    
    public enum Type{
        GET,
        POST
    }
    public enum BodyType{
        NONE,
        TEXT,
        JSON,
        XML
    }
    
    public Request() {
    }

    private Request(Builder builder) {

    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
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

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
    public static class Builder{
        private Map<String, String> params = new HashMap<>();
        private Map<String, String> headers = new HashMap<>();
        private String body = "";
        private BodyType bodyType = null;

        public Request Build(){
            return new Request(this);
        }
    }
}
