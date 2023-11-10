package com.cogging.cogging.controller;

import com.cogging.cogging.dto.CommunityReqDto;
import com.cogging.cogging.dto.PloggingCreateDto;
import com.cogging.cogging.entity.Member;
import com.cogging.cogging.service.PloggingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/plogging")
@RequiredArgsConstructor
public class PloggingController {

    private final PloggingService ploggingService;

    @PostMapping("/create")
    public Map<String, Integer> createPlogging(@AuthenticationPrincipal Member member, @RequestBody PloggingCreateDto ploggingCreateDto){
        return Map.of("ploggingId", ploggingService.createPlogging(member, ploggingCreateDto));
    }
}
