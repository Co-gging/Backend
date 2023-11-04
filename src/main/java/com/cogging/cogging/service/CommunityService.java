package com.cogging.cogging.service;

import com.cogging.cogging.dto.CommunityDto;
import com.cogging.cogging.entity.CommunityEntity;
import com.cogging.cogging.entity.UserEntity;
import com.cogging.cogging.repository.CommunityRepository;
import com.cogging.cogging.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

    @Transactional
    public String getList() {
        JSONObject communityList = new JSONObject();
        JSONArray communityArr = new JSONArray();

        List<CommunityEntity> communityEntities = communityRepository.findAll();

        for(CommunityEntity communityEntity : communityEntities){
            JSONObject post = new JSONObject();

            post.put("id", communityEntity.getId());
            post.put("title", communityEntity.getTitle());
            post.put("content", communityEntity.getContent());
            post.put("date", communityEntity.getCreatedAt());
            post.put("comments", communityEntity.getComments());

            communityArr.put(post);
        }
        communityList.put("list", communityArr);

        return communityList.toString();
    }
}
