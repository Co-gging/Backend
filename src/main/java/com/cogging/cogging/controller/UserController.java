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

    /** 로그인, 회원가입 관련 api **/

    @PostMapping("/signup")
    public Map<String, Integer> signup(@RequestBody UserSignUpDto memberSingUpDto){
        return Map.of("memberId", userService.signUp(memberSingUpDto));
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> member) {
        return Map.of("token", userService.login(member));
    }

    @PostMapping("/check-email/{email}")
    public Map<String, Boolean> checkEmail(@PathVariable String email){
        return Map.of("isExist", userService.checkEmail(email));
    }

    @PostMapping("/check-nickname/{nickname}")
    public Map<String, Boolean> checkNickname(@PathVariable String nickname){
        return Map.of("isExist", userService.checkNickname(nickname));
    }

    /** 유저 관련 api **/

    @GetMapping("/users")
    public List<UserDto> getMemberList(){
        return userService.getMemberList();
    }

    @GetMapping("/users/me")
    public UserDto getMember(@AuthenticationPrincipal User user){
        return userService.getMember(user.getId());
    }

    /** 마이페이지 관련 api **/

    @GetMapping("/users/me/communities")
    public List<CommunityDto> getMyCommunity(@AuthenticationPrincipal User user){
        return userService.getMyCommunity(user);
    }

    @GetMapping("/users/me/reviews")
    public List<ReviewDto> getMyReview(@AuthenticationPrincipal User user){
        return userService.getMyReview(user);
    }

    @GetMapping("/users/me/ploggings")
    public List<PloggingListDto> getMyPlogging(@AuthenticationPrincipal User user){
        return userService.getMyPlogging(user);
    }

}
