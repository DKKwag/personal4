package com.example.personal4.service;

import com.example.personal4.domain.Food;
import com.example.personal4.domain.Restaurant;
import com.example.personal4.dto.FoodDto;
import com.example.personal4.repository.FoodRepository;
import com.example.personal4.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void addRestaurantFoods(
            Long restaurantId,
            List<FoodDto> requestDtoList
    ) {
        //findById 뒤에(String id)
        Optional<Restaurant> foundRestaurant = restaurantRepository.findById(restaurantId);

        checkRestaurant(foundRestaurant);
        Restaurant restaurant = foundRestaurant.get();

        for (FoodDto requestDto : requestDtoList) {
            String foodName = requestDto.getName();
            int foodPrice = requestDto.getPrice();

            checkDuplicateRestaurantFood(restaurant, foodName);

            checkFoodPrice(foodPrice);

            //Spring 문법
            //build구조(테이블을 눈에 보이게 만들어놓은 부분), 생성자가 많을경우 쓰면 좋음
            Food food = Food.builder()
                    .name(foodName)
                    .price(foodPrice)
                    .restaurant(restaurant)
                    .build();

            foodRepository.save(food);
        }
    }


    private void checkRestaurant(Optional<Restaurant> foundRestaurant) {
        if (!foundRestaurant.isPresent())
            throw new IllegalArgumentException("레스토랑이 존재하지 않습니다.");
    }

    private void checkDuplicateRestaurantFood(Restaurant restaurant, String foodName) {
        //위에 for문 돌리면 Dto에서 받은 foodname 과 restaurant에 있는 name과 비교하여 에러 검출
        Optional<Food> found = foodRepository.findFoodByRestaurantAndName(restaurant, foodName);
        if(found.isPresent())
            //Exception 패키지 파일에 String으로 변환하여 날림
            throw new IllegalArgumentException("중복된 음식명입니다.");
    }

    private void checkFoodPrice(int foodPrice) {
        //
        if (foodPrice < 100)
            throw new IllegalArgumentException("허용값 100 이상으로 적어주세요.");

        if (foodPrice > 1_000_000)
            throw new IllegalArgumentException("허용값 1000000 이하로 적어주세요.");

        if (foodPrice % 100 > 0)
            throw new IllegalArgumentException("100원 단위로 입력해주세요.");
    }


    @Transactional
    public List<Food> findAllRestaurantFoods(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(
                        () -> new NullPointerException("레스토랑 음식이 조회되지 않습니다."));

        return foodRepository.findFoodsByRestaurant(restaurant);
    }
}
