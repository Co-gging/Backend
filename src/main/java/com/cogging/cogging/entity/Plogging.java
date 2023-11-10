package com.cogging.cogging.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "plogging")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Plogging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    private String title;

    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private LocalDateTime ploggingDate; // 플로깅 시간

    private int maximumPeople;

    private String departures; // 출발지

    private String arrivals; // 도착지

    private String chatUrl;

}
