package com.example.personal4.repository;

import com.example.personal4.domain.Food;
import com.example.personal4.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {


    List<Food> findFoodsByRestaurant(Restaurant restaurant);
    Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String foodName);
    //optional은 null값도 json형태로 만들어줄려고씀
}
