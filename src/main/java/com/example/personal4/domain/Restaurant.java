package com.example.personal4.domain;


import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor // 매개변수를 가지고있는 생성자 생성
@NoArgsConstructor //기본생성자 생성(매개변수 없는것)
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column (nullable = false)
    private int minOrderPrice;

    @Column (nullable = false)
    private int deliveryFee;
}
