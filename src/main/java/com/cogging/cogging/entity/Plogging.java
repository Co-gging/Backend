package com.cogging.cogging.entity;

import com.cogging.cogging.dto.PlaceDto;
import com.cogging.cogging.dto.PloggingDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

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

    @ManyToOne(fetch = FetchType.EAGER)
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

    @Formula("(select count(*) from join_plogging jp where jp.plogging_id = id)")
    private int headcount;

    public PloggingDto toDto(){
        return PloggingDto.builder()
                .id(id)
                .authorNickname(member.getNickname())
                .placeName(place.getName())
                .title(title)
                .content(content)
                .createdAt(createdAt)
                .ploggingDate(ploggingDate)
                .maximumPeople(maximumPeople)
                .headcount(headcount)
                .departures(departures)
                .arrivals(arrivals)
                .chatUrl(chatUrl)
                .build();
    }

}
