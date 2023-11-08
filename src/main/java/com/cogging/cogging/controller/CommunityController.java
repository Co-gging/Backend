package com.cogging.cogging.controller;

import com.cogging.cogging.dto.CommunityCreateReqDto;
import com.cogging.cogging.dto.CommunityDto;
import com.cogging.cogging.entity.Member;
import com.cogging.cogging.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/list")
    public List<CommunityDto> getCommunityList(){
        return communityService.getCommunityList();
    }

    @PostMapping("")
    public CommunityDto getCommunity(@AuthenticationPrincipal Member member, @RequestBody Map<String, Integer> communityId){
        return communityService.getCommunity(communityId.get("id"));
    }

}
