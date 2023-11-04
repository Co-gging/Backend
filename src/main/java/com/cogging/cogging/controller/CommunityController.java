package com.cogging.cogging.controller;


import com.cogging.cogging.dto.CommunityDto;
import com.cogging.cogging.entity.CommunityEntity;
import com.cogging.cogging.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping("/api/community/create")
    public ResponseEntity<String> createCommunity(@RequestBody Map<String, String> param){

        communityService.createCommunity(param);

        return ResponseEntity.ok("글 작성이 완료 되었습니다.");
    }

    @GetMapping("/api/community/list")
    public ResponseEntity<Object> communityList(){

        String communityList = communityService.getList();

        return new ResponseEntity<>(communityList, HttpStatus.OK);
    }

}
