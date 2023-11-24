package com.cogging.cogging.dto;

import com.cogging.cogging.entity.User;
import com.cogging.cogging.entity.Place;
import com.cogging.cogging.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateDto {

    private String title;
    private String content;
    private int placeId;

    public Review toEntity(User user, Place place){
        return Review.builder()
                .place(place)
                .user(user)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
