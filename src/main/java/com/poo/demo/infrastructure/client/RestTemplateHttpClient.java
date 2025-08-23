package com.poo.demo.infrastructure.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Implementação concreta do cliente HTTP usando RestTemplate
 * Segue o princípio de responsabilidade única (SRP)
 */
@Component
public class RestTemplateHttpClient implements HttpClient {

    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateHttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> T get(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }

    @Override
    public <T> T post(String url, Object request, Class<T> responseType) {
        return restTemplate.postForObject(url, request, responseType);
    }
}
