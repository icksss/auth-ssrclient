package com.kr.jikim.oauth.ssrclient.dto;

public interface OAuth2Response {
    //제공자 이름(naver, google)
    String getProvider();
    //제공자 ID
    String getProviderId();
    String getEmail();
    String getName();
}
