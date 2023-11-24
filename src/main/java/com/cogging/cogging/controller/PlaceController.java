package com.cogging.cogging.controller;

import com.cogging.cogging.dto.PlaceDto;
import com.cogging.cogging.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-AUTH-TOKEN")
@RestController
@RequestMapping("/api/place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/search")
    public List<PlaceDto> searchPlaceList(@RequestParam(name = "keyword") String keyword){
        return placeService.searchPlaceList(keyword);
    }

    @GetMapping("/top")
    public List<PlaceDto> getTopPlaceList(){
        return placeService.getTopPlaceList();
    }
}
