package com.cogging.cogging.dto;

import com.cogging.cogging.entity.Community;
import com.cogging.cogging.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDto {

    private String title;
    private String content;
    private LocalDateTime createdAt;
    private int comments;
    private Member member;

    public Community toEntity(){
       return Community.builder()
                .title(title)
                .content(content)
                .createdAt(createdAt)
                .comments(comments)
                .member(member)
                .build();
    }
}
