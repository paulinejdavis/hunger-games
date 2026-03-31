package com.hungergames.restaurant.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hungergames.restaurant.dto.RestaurantDto;
import com.hungergames.restaurant.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{postcode}")
    public List<RestaurantDto> getRestaurants(@PathVariable String postcode) {
        return restaurantService.getRestaurantsByPostcode(postcode);
    }
}