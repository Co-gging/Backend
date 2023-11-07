package com.cogging.cogging.service;

import com.cogging.cogging.dto.MemberDto;
import com.cogging.cogging.dto.MemberSingUpDto;
import com.cogging.cogging.entity.Member;
import com.cogging.cogging.exceptions.BaseException;
import com.cogging.cogging.jwt.JwtTokenProvider;
import com.cogging.cogging.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public int signUp(MemberSingUpDto requestDto){
        if(checkEmail(requestDto.getEmail())){
            throw new BaseException("이미 존재하는 이메일입니다.", null, null);
        }

        Member newMember = memberRepository.save(requestDto.toEntity());
        newMember.encodePassword(passwordEncoder);

        return newMember.getId();
    }

    @Transactional
    public String login(Map<String, String> member) {
        Member loginMember = memberRepository.findByEmail(member.get("email"))
                .orElseThrow(() -> new BaseException("존재하지 않는 이메일입니다.", null, HttpStatus.NOT_FOUND));

        if (!passwordEncoder.matches(member.get("password"), loginMember.getPassword())) {
            throw new BaseException("잘못된 비밀번호입니다.", null, null);
        }

        return jwtTokenProvider.createToken(loginMember.getUsername());
    }

    @Transactional
    public boolean checkEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        return member.isPresent();
    }

    @Transactional
    public boolean checkNickname(String nickname) {
        Optional<Member> member = memberRepository.findByNickname(nickname);
        return member.isPresent();
    }

    @Transactional
    public MemberDto getMember(int memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException("존재하지 않는 유저입니다", null, HttpStatus.NOT_FOUND));

        return member.toMemberDto();
    }

    @Transactional
    public List<MemberDto> getMemberList() {
        Sort sort = Sort.by(Sort.Order.asc("id"));
        List<Member> memberList = memberRepository.findAll(sort);
        List<MemberDto> memberDtoList = new ArrayList<>();

        for (Member member : memberList) {
            memberDtoList.add(member.toMemberDto());
        }

        return memberDtoList;
    }
}
