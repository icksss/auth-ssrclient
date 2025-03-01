package com.kr.jikim.oauth.ssrclient.repository;

import com.kr.jikim.oauth.ssrclient.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
