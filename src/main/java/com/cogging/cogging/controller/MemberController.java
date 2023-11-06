package com.cogging.cogging.controller;

import com.cogging.cogging.dto.MemberDto;
import com.cogging.cogging.dto.MemberSingUpDto;
import com.cogging.cogging.entity.Member;
import com.cogging.cogging.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members/signup")
    public Map<String, Integer> signup(@RequestBody MemberSingUpDto memberSingUpDto){
        return Map.of("memberId", memberService.signUp(memberSingUpDto));
    }

    @PostMapping("/members/login")
    public Map<String, String> login(@RequestBody Map<String, String> member) {
        return Map.of("token", memberService.login(member));
    }

    @PostMapping("/check-email")
    public Map<String, Boolean> checkEmail(@RequestBody Map<String, String> validEmail){
        return Map.of("isExist", memberService.checkEmail(validEmail.get("email")));
    }

    @PostMapping("/check-nickname")
    public Map<String, Boolean> checkNickname(@RequestBody Map<String, String> validNickname){
        return Map.of("isExist", memberService.checkNickname(validNickname.get("nickname")));
    }

    @GetMapping("/member")
    public MemberDto getMember(@AuthenticationPrincipal Member member){
        return memberService.getMember(member.getId());
    }

    @GetMapping("/members/list")
    public List<MemberDto> getMemberList(){
        return memberService.getMemberList();
    }


}
