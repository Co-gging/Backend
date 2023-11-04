package com.cogging.cogging.service;

import com.cogging.cogging.dto.CommunityDto;
import com.cogging.cogging.entity.CommunityEntity;
import com.cogging.cogging.entity.UserEntity;
import com.cogging.cogging.repository.CommunityRepository;
import com.cogging.cogging.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;

    @Transactional
    public void createCommunity(Map<String, String> param){

        UserEntity user = userRepository.findById(Integer.parseInt(param.get("id")))
                .orElseThrow(() -> new RuntimeException());

        LocalDateTime currentDateTime = LocalDateTime.now();
        CommunityDto communityDto = new CommunityDto(param.get("title"), param.get("content"), currentDateTime, 0);

        communityDto.setUser(user);

        CommunityEntity communityEntity = communityDto.toEntity();
        communityRepository.save(communityEntity);
    }
}
