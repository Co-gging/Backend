package com.cogging.cogging.dto;

import com.cogging.cogging.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpDto {
    private String email;

    private String password;

    private String nickname;

    private int profileImage;

    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .profileImage(profileImage)
                .participation(0)
                .build();
    }
}
