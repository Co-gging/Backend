package com.cogging.cogging.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDto {

    private int id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private int comments;
    private UserDto author;
}
