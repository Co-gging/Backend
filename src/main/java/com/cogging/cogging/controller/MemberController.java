package com.cogging.cogging.controller;

import com.cogging.cogging.dto.MemberSingUpDto;
import com.cogging.cogging.entity.Member;
import com.cogging.cogging.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
        //ResponseEntity.status(HttpStatus.OK).body(memberService.login(member));
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(memberService.login(member));
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("가입되지 않은 E-MAIL입니다.");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("잘못된 비밀번호입니다.");
//        }
    }


}
