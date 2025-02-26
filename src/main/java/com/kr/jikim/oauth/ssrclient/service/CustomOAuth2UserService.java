package com.kr.jikim.oauth.ssrclient.service;

import com.kr.jikim.oauth.ssrclient.dto.CustomAuth2User;
import com.kr.jikim.oauth.ssrclient.dto.GoogleResponse;
import com.kr.jikim.oauth.ssrclient.dto.NaverResponse;
import com.kr.jikim.oauth.ssrclient.dto.OAuth2Response;
import com.kr.jikim.oauth.ssrclient.entity.UserEntity;
import com.kr.jikim.oauth.ssrclient.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        //naver..google 에서 넘어오는 데이터
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("OAuth2User : {} ", oAuth2User.getAttributes());
        //어떤 곳에서 넘어 왔는지(naver, google)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if(registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }else if(registrationId.equals("google")){
            oAuth2Response = new GoogleResponse((oAuth2User.getAttributes()));
        }else{
            return null;
        }

        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        UserEntity existData = userRepository.findByUsername(username);
        String role = null;
        if(existData == null){
            UserEntity createUser = new UserEntity();
            createUser.setUsername(username);
            createUser.setEmail(oAuth2Response.getEmail());
            createUser.setRole("ROLE_USER");
            userRepository.save(createUser);
        }else{
            //update 된 값 모두 저장
            existData.setEmail(oAuth2Response.getEmail());
            role = existData.getRole();
        }

        log.info("oAuth2Response : {} ", oAuth2Response);
        return new CustomAuth2User(oAuth2Response, role);
    }
}
