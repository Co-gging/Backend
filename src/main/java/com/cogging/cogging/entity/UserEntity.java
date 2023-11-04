package com.cogging.cogging.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String email;

    private String password;

    private String nickname;

    private int profile_img;

    private int participation;

    @OneToMany(mappedBy = "user")
    private List<CommunityEntity> community = new ArrayList<>();

}
