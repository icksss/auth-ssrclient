package com.kr.jikim.oauth.ssrclient.dto;

import java.util.Map;

public class GoogleResponse implements OAuth2Response{
    private final Map<String, Object> attribute;


    /**
     * google response
     {
     resultcode=00, message=success, id=123123123, name=개발자유미
     }
     */
    public GoogleResponse(Map<String,Object> attribute){
        this.attribute = attribute;
    }

    @Override
    public String getProvider() {
        return "Google";
    }

    @Override
    public String getProviderId() {
        return attribute.get("sub").toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }

    @Override
    public String toString() {
        return "GoogleResponse{" +
                "attribute=" + attribute +
                '}';
    }
}
