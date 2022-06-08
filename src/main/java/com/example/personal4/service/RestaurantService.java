package com.example.personal4.service;

import com.example.personal4.domain.Restaurant;
import com.example.personal4.dto.RestaurantDto;
import com.example.personal4.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor //생성자 주입 , private final로 가져올때 씀.
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional // Transactional 어노테이션은 트랜잭션으로 감싼 부분에서 하나라도 실패할 경우 전체 작업이 취소된다.
    public Restaurant registerRestaurant(RestaurantDto requestDto){
        String name = requestDto.getName();
        int minOrderPrice = requestDto.getMinOrderPrice();
        int deliveryFee = requestDto.getDeliveryFee();

        CheckOrderPrice(minOrderPrice);
        CheckDeliveryFee(deliveryFee);

        Restaurant restaurant = Restaurant.builder()
                .name(name)
                .minOrderPrice(minOrderPrice)
                .deliveryFee(deliveryFee)
                .build();

        restaurantRepository.save(restaurant);

        return restaurant;
    }

    private void CheckDeliveryFee(int deliveryFee) {
        if(deliveryFee < 0 || deliveryFee > 10000) {
            throw new IllegalArgumentException("허용값 0~10000원 사이로 입력해주세요.");
        }else if(deliveryFee % 500 != 0){
            throw new IllegalArgumentException("500원 단위로만 입력가능합니다.");
        }
    }

    private void CheckOrderPrice(int minOrderPrice) {
        if (minOrderPrice < 1000 || minOrderPrice > 100000){
            throw new IllegalArgumentException("허용값 1000 ~ 100000원 사이로 입력해주세요.");
        }
        if (minOrderPrice % 100 != 0){
            throw new IllegalArgumentException("100원 단위로만 입력가능합니다.");
        }

    }
    @Transactional
    public List<Restaurant> findAllRestaurant() {
        return restaurantRepository.findAll();
    }

}
