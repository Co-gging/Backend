package com.cogging.cogging.controller;

import com.cogging.cogging.dto.CommunityReqDto;
import com.cogging.cogging.dto.CommunityDto;
import com.cogging.cogging.dto.CommunityUpdateDto;
import com.cogging.cogging.entity.User;
import com.cogging.cogging.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-AUTH-TOKEN")
@RestController
@RequestMapping("/api/communites")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping()
    public Map<String, Integer> createCommunity(@AuthenticationPrincipal User user,
                                                @RequestBody CommunityReqDto communityReqDto){
        return Map.of("communityId", communityService.createCommunity(user, communityReqDto));
    }

    @GetMapping("")
    public List<CommunityDto> getCommunities(){
        return communityService.getCommunities();
    }

    @PostMapping("{communityId}")
    public CommunityDto getCommunity(@PathVariable int communityId){
        return communityService.getCommunity(communityId);
    }

    @PutMapping("{communityId}")
    public void updateCommunity(@AuthenticationPrincipal User user,
                                @PathVariable  int communityId ,
                                @RequestBody CommunityUpdateDto communityUpdateDto){
        communityService.updateCommunity(user, communityId, communityUpdateDto);
    }

    @DeleteMapping("{communityId}")
    public void deleteCommunity(@AuthenticationPrincipal User user, @PathVariable  int communityId){
        communityService.deleteCommunity(user, communityId);
    }

}
