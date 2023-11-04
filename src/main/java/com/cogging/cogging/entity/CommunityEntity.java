package com.cogging.cogging.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "community")
public class CommunityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private UserEntity user;

    private String title;

    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private int comments;

    @Builder
    public CommunityEntity(String title, String content, LocalDateTime createdAt, int comments, UserEntity user){
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.comments = comments;
        this.user = user;
    }

}
