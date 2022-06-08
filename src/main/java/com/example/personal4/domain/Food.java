package com.example.personal4.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @ManyToOne // 단방향 엔티티 한쪽의 엔티티가 상대 엔티티를 참조하고 있는상태(N:1)관계 매핑 정보
    @JoinColumn(name="restaurant_id", nullable = false)
    private Restaurant restaurant;

}
