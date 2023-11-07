package com.cogging.cogging.service;

import com.cogging.cogging.dto.CommunityCreateReqDto;
import com.cogging.cogging.entity.Community;
import com.cogging.cogging.entity.Member;
import com.cogging.cogging.repository.CommunityRepository;
import com.cogging.cogging.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
