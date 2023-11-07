package com.cogging.cogging.controller;

import com.cogging.cogging.dto.CommunityCreateReqDto;
import com.cogging.cogging.entity.Member;
import com.cogging.cogging.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping("/create")
    public Map<String, Integer> createCommunity(@AuthenticationPrincipal Member member, @RequestBody CommunityCreateReqDto communityCreateReqDto){
        return Map.of("communityId", communityService.createCommunity(member, communityCreateReqDto));
    }

}
