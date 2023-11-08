package com.cogging.cogging.service;

import com.cogging.cogging.dto.CommunityCreateReqDto;
import com.cogging.cogging.dto.CommunityDto;
import com.cogging.cogging.dto.MemberDto;
import com.cogging.cogging.entity.Community;
import com.cogging.cogging.entity.Member;
import com.cogging.cogging.exceptions.BaseException;
import com.cogging.cogging.repository.CommunityRepository;
import com.cogging.cogging.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final MemberRepository memberRepository;
    private final CommunityRepository communityRepository;

    @Transactional
    public int createCommunity(Member member, CommunityCreateReqDto communityCreateReqDto){
        Community community = communityCreateReqDto.toEntity(member);
        return communityRepository.save(community).getId();
    }

    @Transactional
    public List<CommunityDto> getCommunityList(){
        Sort sort = Sort.by(Sort.Order.asc("id"));
        List<Community> communityList = communityRepository.findAll(sort);
        List<CommunityDto> CommunityDtoList = new ArrayList<>();

        for (Community community : communityList) {
            CommunityDtoList.add(community.toDto());
        }

        return CommunityDtoList;
    }

    @Transactional
    public CommunityDto getCommunity(int id){
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new BaseException("존재하지 않는 글입니다.", null, HttpStatus.NOT_FOUND));

        return community.toDto();
    }
}
