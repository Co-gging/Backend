package com.cogging.cogging.controller;

import com.cogging.cogging.dto.*;
import com.cogging.cogging.entity.User;
import com.cogging.cogging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-AUTH-TOKEN")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/members/signup")
    public Map<String, Integer> signup(@RequestBody UserSignUpDto memberSingUpDto){
        return Map.of("memberId", userService.signUp(memberSingUpDto));
    }

    @PostMapping("/members/login")
    public Map<String, String> login(@RequestBody Map<String, String> member) {
        return Map.of("token", userService.login(member));
    }

    @PostMapping("/check-email")
    public Map<String, Boolean> checkEmail(@RequestBody Map<String, String> validEmail){
        return Map.of("isExist", userService.checkEmail(validEmail.get("email")));
    }

    @PostMapping("/check-nickname")
    public Map<String, Boolean> checkNickname(@RequestBody Map<String, String> validNickname){
        return Map.of("isExist", userService.checkNickname(validNickname.get("nickname")));
    }

    @GetMapping("/member")
    public UserDto getMember(@AuthenticationPrincipal User user){
        return userService.getMember(user.getId());
    }

    @GetMapping("/members/list")
    public List<UserDto> getMemberList(){
        return userService.getMemberList();
    }

    @GetMapping("/members/community")
    public List<CommunityDto> getMyCommunity(@AuthenticationPrincipal User user){
        return userService.getMyCommunity(user);
    }

    @GetMapping("/members/review")
    public List<ReviewDto> getMyReview(@AuthenticationPrincipal User user){
        return userService.getMyReview(user);
    }

    @GetMapping("/members/plogging")
    public List<PloggingListDto> getMyPlogging(@AuthenticationPrincipal User user){
        return userService.getMyPlogging(user);
    }

}
