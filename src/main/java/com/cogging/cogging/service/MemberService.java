package com.cogging.cogging.service;

import com.cogging.cogging.dto.MemberSingUpDto;
import com.cogging.cogging.entity.Member;
import com.cogging.cogging.exceptions.BaseException;
import com.cogging.cogging.jwt.JwtTokenProvider;
import com.cogging.cogging.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
                .orElseThrow(() -> new BaseException("존재하지 않는 이메일입니다.", null ,HttpStatus.NOT_FOUND));

        if (!passwordEncoder.matches(member.get("password"), loginMember.getPassword())) {
            throw new BaseException("잘못된 비밀번호입니다.", null, HttpStatus.CONFLICT);
        }

        return jwtTokenProvider.createToken(loginMember.getUsername());
    }

    public boolean checkEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        return member.isPresent();
    }
}
