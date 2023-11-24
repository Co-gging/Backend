package com.cogging.cogging.dto;

import com.cogging.cogging.entity.Community;
import com.cogging.cogging.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommunityUpdateDto {
    private int id;
    private String title;
    private String content;

    public Community toEntity(User user){
        return Community.builder()
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .comments(0)
                .user(user)
                .build();
    }
}
