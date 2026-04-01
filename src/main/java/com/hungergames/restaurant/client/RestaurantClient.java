package com.hungergames.restaurant.client;

import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestaurantClient {

    private static final String API_URL =
            "https://uk.api.just-eat.io/discovery/uk/restaurants/enriched/bypostcode/{postcode}";

    private final RestTemplate restTemplate;

    public RestaurantClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public Map<?, ?> getRestaurantsByPostcode(String postcode) {
        return restTemplate.getForObject(API_URL, Map.class, postcode);
    }
}
