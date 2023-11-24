package com.cogging.cogging.service;

import com.cogging.cogging.dto.CommunityReqDto;
import com.cogging.cogging.dto.CommunityDto;
import com.cogging.cogging.dto.CommunityUpdateDto;
import com.cogging.cogging.entity.Community;
import com.cogging.cogging.entity.User;
import com.cogging.cogging.exceptions.BaseException;
import com.cogging.cogging.repository.CommunityRepository;
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

    private final CommunityRepository communityRepository;

    @Transactional
    public int createCommunity(User user, CommunityReqDto communityReqDto){
        Community community = communityReqDto.toEntity(user);
        return communityRepository.save(community).getId();
    }

    @Transactional
    public List<CommunityDto> getCommunities(){
        Sort sort = Sort.by(Sort.Order.asc("id"));
        List<Community> communities = communityRepository.findAll(sort);
        List<CommunityDto> CommunityDtos = new ArrayList<>();

        for (Community community : communities) {
            CommunityDtos.add(community.toDto());
        }

        return CommunityDtos;
    }

    @Transactional
    public CommunityDto getCommunity(int communityId){
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new BaseException("존재하지 않는 글입니다.", null, HttpStatus.NOT_FOUND));

        return community.toDto();
    }

    @Transactional
    public void updateCommunity(User user, int communityId, CommunityUpdateDto communityUpdateDto){
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new BaseException("존재하지 않는 글입니다.", null, HttpStatus.NOT_FOUND));

        if(community.getUser().getId() != user.getId()){
            throw new BaseException("작성자만 수정이 가능합니다.", null, HttpStatus.FORBIDDEN);
        }

        community.update(communityUpdateDto.getTitle(), communityUpdateDto.getContent());
        System.out.println(community);
    }

    @Transactional
    public void deleteCommunity(User user, int communityId){
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new BaseException("존재하지 않는 글입니다.", null, HttpStatus.NOT_FOUND));

        if(community.getUser().getId() != user.getId()){
            throw new BaseException("작성자만 수정이 가능합니다.", null, HttpStatus.FORBIDDEN);
        }

        communityRepository.delete(community);
    }

}
