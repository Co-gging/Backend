package com.cogging.cogging.service;

import com.cogging.cogging.dto.*;
import com.cogging.cogging.entity.Community;
import com.cogging.cogging.entity.Member;
import com.cogging.cogging.entity.Place;
import com.cogging.cogging.entity.Plogging;
import com.cogging.cogging.exceptions.BaseException;
import com.cogging.cogging.repository.PloggingRepository;
import com.cogging.cogging.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PloggingService {

    private final PloggingRepository ploggingRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public int createPlogging(Member member, PloggingCreateDto ploggingCreateDto){
        Place place = placeRepository.findById(ploggingCreateDto.getPlaceId())
                .orElseThrow(() -> new BaseException("존재하지 않는 장소입니다.", null, HttpStatus.NOT_FOUND));

        Plogging plogging = ploggingRepository.save(ploggingCreateDto.toEntity(member, place));

        return plogging.getId();
    }

    @Transactional
    public List<PloggingListDto> getPloggingList(){
        List<Plogging> ploggings = ploggingRepository.findAll();
        List<PloggingListDto> ploggingListDtos = new ArrayList<>();

        for (Plogging plogging : ploggings) {
            ploggingListDtos.add(plogging.toListDto());
        }

        return ploggingListDtos;
    }
}
