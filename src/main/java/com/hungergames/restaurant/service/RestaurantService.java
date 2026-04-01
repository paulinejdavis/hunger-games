package com.hungergames.restaurant.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.hungergames.restaurant.client.RestaurantClient;
import com.hungergames.restaurant.dto.RestaurantDto;

@Service
public class RestaurantService {

    private final RestaurantClient restaurantClient;

    public RestaurantService(RestaurantClient restaurantClient) {
        this.restaurantClient = restaurantClient;
    }

    public List<RestaurantDto> getRestaurantsByPostcode(String postcode) {
        Map<?, ?> response;

        try {
            response = restaurantClient.getRestaurantsByPostcode(postcode);
        } catch (RestClientException | IllegalArgumentException ex) {
            return List.of();
        }

        if (response == null) {
            return List.of();
        }

        Object restaurantsObject = response.get("restaurants");
        if (!(restaurantsObject instanceof List<?> restaurantsList)) {
            return List.of();
        }

        return restaurantsList.stream()
                .filter(Map.class::isInstance)
                .map(r -> (Map<?, ?>) r)
                .limit(10)
                .map(this::toRestaurantDto)
                .toList();
    }

    private RestaurantDto toRestaurantDto(Map<?, ?> restaurant) {
        String name = getString(restaurant.get("name"));
        String cuisines = extractCuisines(restaurant);
        Double rating = extractRating(restaurant);

        return new RestaurantDto(name, cuisines, rating);
    }

    private String extractCuisines(Map<?, ?> restaurant) {
        Object cuisinesObject = restaurant.get("cuisines");

        if (!(cuisinesObject instanceof List<?> cuisinesList)) {
            return "";
        }

        return cuisinesList.stream()
                .filter(Map.class::isInstance)
                .map(c -> (Map<?, ?>) c)
                .map(cuisine -> getString(cuisine.get("name")))
                .filter(name -> !name.isBlank())
                .collect(Collectors.joining(", "));
    }

    private Double extractRating(Map<?, ?> restaurant) {
        Object ratingObject = restaurant.get("rating");

        if (!(ratingObject instanceof Map<?, ?> ratingMap)) {
            return null;
        }

        Object starRating = ratingMap.get("starRating");

        if (starRating instanceof Number number) {
            return number.doubleValue();
        }

        return null;
    }

    private String getString(Object value) {
        return value == null ? "" : String.valueOf(value);
    }
}
