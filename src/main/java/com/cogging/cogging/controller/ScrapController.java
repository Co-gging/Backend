package com.cogging.cogging.controller;

import com.cogging.cogging.entity.User;
import com.cogging.cogging.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-AUTH-TOKEN")
@RestController
@RequestMapping("/api/scrap")
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapService scrapService;

    @PostMapping("/{placeId}")
    public void createScrap(@AuthenticationPrincipal User user, @PathVariable("placeId") int placeId){
        scrapService.createScrap(user,  placeId);
    }
}
