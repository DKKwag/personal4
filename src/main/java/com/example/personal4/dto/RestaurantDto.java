package com.example.personal4.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestaurantDto {

    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
