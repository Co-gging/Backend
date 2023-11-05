package com.cogging.cogging.dto;

import com.cogging.cogging.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSingUpDto {
    private String email;

    private String password;

    private String nickname;

    private int profileImage;

    @Builder
    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .profileImage(profileImage)
                .participation(0)
                .build();
    }
}
