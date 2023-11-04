package com.cogging.cogging.dto;

import com.cogging.cogging.entity.CommunityEntity;
import com.cogging.cogging.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommunityDto {

    private String title;
    private String content;

    private LocalDateTime createdAt;
    private int comments;

    private UserEntity user;

    public CommunityDto(String title, String content, LocalDateTime createdAt, int comments){
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.comments = comments;
    }

    public CommunityEntity toEntity(){
        CommunityEntity communityEntity = new CommunityEntity().builder()
                .title(title)
                .content(content)
                .createdAt(createdAt)
                .comments(comments)
                .user(user)
                .build();

        return communityEntity;
    }

}
