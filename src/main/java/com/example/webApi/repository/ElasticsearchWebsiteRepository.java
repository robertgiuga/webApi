package com.example.webApi.repository;

import com.example.webApi.model.Website;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Repository
public class ElasticsearchWebsiteRepository implements WebsiteRepository{

    private OkHttpClient client;
    public ElasticsearchWebsiteRepository() {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        OkHttpClient.Builder newBuilder = new OkHttpClient.Builder();
        newBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
        newBuilder.hostnameVerifier((hostname, session) -> true);

        client = newBuilder.build();
    }

    @Override
    public Website findByDomain(String domain) {
        Request request = new Request.Builder()
                .url("https://localhost:9200/website/_doc/"+domain)
                .method("GET", null)
                .addHeader("Authorization", "Basic ZWxhc3RpYzp0U0hEZmRmTTlzeUdTcDVrMGlHcA==")
                .build();
        return getWebsite(request);
    }

    @Override
    public Website findByPhone(String phone) {
        Request request = new Request.Builder()
                .url("https://localhost:9200/website/_search?q=telephone:"+phone)
                .method("GET", null)
                .addHeader("Authorization", "Basic ZWxhc3RpYzp0U0hEZmRmTTlzeUdTcDVrMGlHcA==")
                .build();
        return getWebsite(request);
    }

    @Nullable
    private Website getWebsite(Request request) {
        Website website= null;
        try {
            Response response = client.newCall(request).execute();
            System.err.println(response.code());
            //System.err.println(response.body().string());
            ObjectMapper objectMapper = new ObjectMapper();
            website = objectMapper.readValue(response.body().string(), Website.class);
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return website;
    }
}
