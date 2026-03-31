package com.hungergames.restaurant.dto;

public class RestaurantDto {

    private final String name;
    private final String cuisines;
    private final Double rating;

    public RestaurantDto(String name, String cuisines, Double rating) {
        this.name = name;
        this.cuisines = cuisines;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getCuisines() {
        return cuisines;
    }

    public Double getRating() {
        return rating;
    }
}