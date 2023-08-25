package com.example.thyproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final String rapidApiHost;
    private final String apiHost2;

    private final String rapidRTApi;

    public WebClientConfig(@Value("${rapidapi.host}") String rapidApiHost, @Value("${api.host2}") String apiHost2,
                           @Value("${rapidrtapi.host}") String rapidRTApi) {
        this.rapidApiHost = rapidApiHost;
        this.apiHost2 = apiHost2;
        this.rapidRTApi = rapidRTApi;
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient api1WebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl(rapidApiHost).build();
    }

    @Bean
    public WebClient api2WebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl(apiHost2).build();
    }

    @Bean
    public WebClient api3WebClient(WebClient.Builder webClientBuilder){
        return webClientBuilder.baseUrl(rapidRTApi).build();
    }
}
