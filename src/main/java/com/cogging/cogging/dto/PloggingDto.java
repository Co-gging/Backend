package com.cogging.cogging.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PloggingDto {
    private UserDto userDto;
    private String placeName;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime ploggingDate; // 플로깅 시간
    private int maximumPeople;
    private int headcount; // 현재 참여 인원
    private String departures;
    private String arrivals;
    private String chatUrl;
}
