package com.hungergames.restaurant.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hungergames.restaurant.dto.RestaurantDto;

@SpringBootTest
class RestaurantServiceTest {

    private static final String VALID_POSTCODE = "EC4M 7RF";
    private static final String INVALID_POSTCODE = "INVALID_POSTCODE";

    @Autowired
    private RestaurantService restaurantService;

    @Test
    void returnsRestaurantsForValidPostcode() {
        List<RestaurantDto> restaurants = restaurantService.getRestaurantsByPostcode(VALID_POSTCODE);

        assertThat(restaurants)
                .isNotEmpty()
                .hasSizeLessThanOrEqualTo(10);
        assertThat(restaurants.get(0).getName()).isNotBlank();
    }

    @Test
    void returnsEmptyListForInvalidPostcode() {
        List<RestaurantDto> restaurants = restaurantService.getRestaurantsByPostcode(INVALID_POSTCODE);

        assertThat(restaurants).isEmpty();
    }
}
