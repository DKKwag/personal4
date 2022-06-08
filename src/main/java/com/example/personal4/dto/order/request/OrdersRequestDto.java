package com.example.personal4.dto.order.request;

import com.example.personal4.domain.OrderItem;
import lombok.Getter;

import java.util.List;

@Getter
public class OrdersRequestDto {
    private Long restaurantId;
    private List<OrderItem> foods;
}