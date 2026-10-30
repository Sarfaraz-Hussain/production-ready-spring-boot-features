package com.axway.prod_ready_features.production.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${patientService.base.url}")
    private String BASE_URL;

    @Bean
    @Qualifier("patientRestClient")
    public RestClient getPatientRestClient() {
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();
    }
}

