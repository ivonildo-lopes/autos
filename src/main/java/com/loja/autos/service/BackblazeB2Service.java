package com.loja.autos.service;

import java.io.IOException;
import java.util.Base64;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Service
public class BackblazeB2Service {

    private static final String AUTH_URL = "https://api.backblazeb2.com/b2api/v2/b2_authorize_account";
    
    @Value("${b2.applicationKeyId}")
    private String applicationKeyId;
    
    @Value("${b2.applicationKey}")
    private String applicationKey;

    private String apiUrl;
    private String authorizationToken;
    private String downloadUrl;

    @PostConstruct
    public void init() throws IOException, ParseException {
        authorizeAccount();
    }

    private void authorizeAccount() throws IOException, ParseException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String authString = Base64.getEncoder().encodeToString((applicationKeyId + ":" + applicationKey).getBytes());
            HttpGet request = new HttpGet(AUTH_URL);
            request.addHeader("Authorization", "Basic " + authString);

            try (CloseableHttpResponse response = client.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                JsonNode rootNode = new ObjectMapper().readTree(jsonResponse);

                this.apiUrl = rootNode.get("apiUrl").asText();
                this.authorizationToken = rootNode.get("authorizationToken").asText();
                this.downloadUrl = rootNode.get("downloadUrl").asText();
            }
        }
    }

    public String generatePreSignedUrl(String fileName) {
        return downloadUrl + "/file/apu-autos/" + fileName;
    }
    
    public String getUploadUrl(String bucketId) throws IOException, ParseException {
    	bucketId = "c1176a3e272d80ed9d42021a";
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(apiUrl + "/b2api/v2/b2_get_upload_url");

            // Headers necessários - a autorização deve estar correta
            httpPost.addHeader("Authorization", authorizationToken);

            String requestBody = "{\"bucketId\":\"" + bucketId.trim() + "\"}";
            
            httpPost.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));

            try (CloseableHttpResponse response = client.execute(httpPost)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                
                System.out.println("Raw JSON Response: " + jsonResponse);
                
                JsonNode rootNode = new ObjectMapper().readTree(jsonResponse);

                if (rootNode.has("error")) {
                    throw new RuntimeException("Erro na resposta: " + rootNode.get("message").asText());
                }

                String uploadUrl = rootNode.get("uploadUrl").asText();
                String uploadAuthToken = rootNode.get("authorizationToken").asText();
                System.out.println("link: " + uploadUrl);
                System.out.println("token: " + uploadAuthToken);
                return uploadUrl + "|" + uploadAuthToken; 
            }
        }
    }
}