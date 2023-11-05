package com.cogging.cogging.service;

import com.cogging.cogging.dto.MemberSingUpDto;
import com.cogging.cogging.entity.Member;
import com.cogging.cogging.jwt.JwtTokenProvider;
import com.cogging.cogging.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public int signUp(MemberSingUpDto requestDto){
        Member newMember = memberRepository.save(requestDto.toEntity());
        newMember.encodePassword(passwordEncoder);

        return newMember.getId();
    }

    public String login(Map<String, String> member) {
        Member loginMember = memberRepository.findByEmail(member.get("email"))
                .orElseThrow(() -> new EntityNotFoundException("가입되지 않은 Email 입니다."));

        if (!passwordEncoder.matches(member.get("password"), loginMember.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        return jwtTokenProvider.createToken(loginMember.getUsername());
    }
}