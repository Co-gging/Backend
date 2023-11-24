package com.cogging.cogging.service;

import com.cogging.cogging.dto.*;
import com.cogging.cogging.entity.Community;
import com.cogging.cogging.entity.User;
import com.cogging.cogging.entity.Plogging;
import com.cogging.cogging.entity.Review;
import com.cogging.cogging.exceptions.BaseException;
import com.cogging.cogging.jwt.JwtTokenProvider;
import com.cogging.cogging.repository.CommunityRepository;
import com.cogging.cogging.repository.UserRepository;
import com.cogging.cogging.repository.PloggingRepository;
import com.cogging.cogging.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.cogging.cogging.exceptions.ExceptionConstants.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final CommunityRepository communityRepository;
    private final ReviewRepository reviewRepository;
    private final PloggingRepository ploggingRepository;

    @Transactional
    public int signUp(UserSignUpDto requestDto){
        if(checkEmail(requestDto.getEmail())){
            throw new BaseException("이미 존재하는 이메일입니다.", null, null);
        }

        User newUser = userRepository.save(requestDto.toEntity());
        newUser.encodePassword(passwordEncoder);

        return newUser.getId();
    }

    @Transactional
    public String login(Map<String, String> member) {
        User loginUser = userRepository.findByEmail(member.get("email"))
                .orElseThrow(() -> new BaseException("존재하지 않는 이메일입니다.", null, HttpStatus.NOT_FOUND));

        if (!passwordEncoder.matches(member.get("password"), loginUser.getPassword())) {
            throw new BaseException("잘못된 비밀번호입니다.", null, null);
        }

        return jwtTokenProvider.createToken(loginUser.getUsername());
    }

    @Transactional
    public boolean checkEmail(String email) {
        Optional<User> member = userRepository.findByEmail(email);
        return member.isPresent();
    }

    @Transactional
    public boolean checkNickname(String nickname) {
        Optional<User> member = userRepository.findByNickname(nickname);
        return member.isPresent();
    }

    @Transactional
    public UserDto getMember(int memberId) {
        User user = userRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(USER_NOT_FOUND, null, HttpStatus.NOT_FOUND));

        return user.toDto();
    }

    @Transactional
    public List<UserDto> getMemberList() {
        //Sort sort = Sort.by(Sort.Order.asc("id"));
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : userList) {
            userDtoList.add(user.toDto());
        }

        return userDtoList;
    }

    @Transactional
    public List<CommunityDto> getMyCommunity(User user){
        List<Community> communities = communityRepository.findByMemberIdOrderByCreatedAtDesc(user.getId());
        List<CommunityDto> CommunityDtos = new ArrayList<>();

        for (Community community : communities) {
            CommunityDtos.add(community.toDto());
        }

        return CommunityDtos;
    }

    @Transactional
    public List<ReviewDto> getMyReview(User user){
        List<Review> reviews = reviewRepository.findByMemberIdOrderByCreatedAtDesc(user.getId());
        List<ReviewDto> ReviewDtos = new ArrayList<>();

        for (Review review : reviews) {
            ReviewDtos.add(review.toDto());
        }

        return ReviewDtos;
    }

    @Transactional
    public List<PloggingListDto> getMyPlogging(User user){
        List<Plogging> ploggings = ploggingRepository.findByMemberIdOrderByCreatedAtDesc(user.getId());
        List<PloggingListDto> PloggingDtos = new ArrayList<>();

        for (Plogging plogging : ploggings) {
            PloggingDtos.add(plogging.toListDto());
        }

        return PloggingDtos;
    }
}
