package com.cogging.cogging.controller;


import com.cogging.cogging.dto.CommunityDto;
import com.cogging.cogging.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping("/api/community/create")
    public ResponseEntity<String> createCommunity(@RequestBody Map<String, String> param){
        System.out.println(param);

        communityService.createCommunity(param);

        return ResponseEntity.ok("글 작성이 완료 되었습니다.");
    }

}
