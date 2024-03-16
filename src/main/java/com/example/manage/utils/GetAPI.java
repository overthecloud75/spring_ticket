package com.example.manage.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.HashMap;

public class GetAPI {

    ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, Object> retrieveJsonDataFromApi(String apiUrl) throws IOException, InterruptedException {
        // Http request 
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        int statusCode = httpResponse.statusCode();
        String responseBody = httpResponse.body();

        Map<String, Object> jsonObject = pageObject();
        if (statusCode == 200) { 
            jsonObject = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
        }
        // ObjectMapper.writeValueAsString() is used to serialize Object to JSON string.
        // ObjectMapper.readValue() is used to deserialize JSON string to Java Object.
        // https://www.baeldung.com/java-json
        return jsonObject;
    }

    public Map<String, Object> pageObject() {
        Map<String, Object> object = new HashMap<String, Object>();
        return object; 
    }

}

