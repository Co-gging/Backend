package com.cogging.cogging.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "place")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String name;

    String address;

    private double latitude; // 위도

    private double longitude; // 경도

    @ManyToOne
    @JoinColumn(name = "place_category_id")
    private PlaceCategory categoryId;

}