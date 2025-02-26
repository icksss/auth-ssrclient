# 스프링 부트 프로젝트

## 프로젝트 개요
이 프로젝트는 스프링 부트를 기반으로 한 웹 애플리케이션입니다.
OAuth2 시리지의 클라이언트 서버(SSR 기반)

## 버전 및 의존성
* Spring boot 3.4.3
* Spring Security 6.4.3
* OAuth2 Client 3.4.3
* Lombok
* Spring Data JPA - MySQL
* Gradle - Groovy
* Mysql 8


## 기술 스택
* Java17
* Spring Boot 3.4.3
* Gradle 7.3 

## 의존성
gradle
dependencies {
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'org.springframework.boot:spring-boot-starter-mustache'
implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
implementation 'org.springframework.boot:spring-boot-starter-security'
implementation 'org.springframework.boot:spring-boot-starter-web'
compileOnly 'org.projectlombok:lombok'
runtimeOnly 'com.mysql:mysql-connector-j'
annotationProcessor 'org.projectlombok:lombok'
testImplementation 'org.springframework.boot:spring-boot-starter-test'
testImplementation 'org.springframework.security:spring-security-test'
testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

## 프로젝트 구조
```
src/main/java/com/kr/jikim/
├── config/
├── controller/
├── dto/
├── entity/
├── repository/
├── service/
├── OAuth2ClientApplication.java
```
## 설정
application.properties
server.port=8080

## 실행
./gradlew bootRun

## 빌드
./gradlew build

### 필수 조건 jdk17 과 좋은 궁합은 gradle 7.3
* JDK 17 이상
* Gradle 7.0 이상

### 주요 기능
1. naver, google OAuth2 Client 구현
2. Custom Login page 설정
3. Client 설정 properties 아닌 ClientRegistration 설정

## OAuth2 유저 관리 Table
```
CREATE TABLE oauth2_authorized_client (
  client_registration_id VARCHAR(100) NOT NULL,
  principal_name VARCHAR(200) NOT NULL,
  access_token_type VARCHAR(100) NOT NULL,
  access_token_value BLOB NOT NULL,
  access_token_issued_at TIMESTAMP NOT NULL,
  access_token_expires_at TIMESTAMP NOT NULL,
  access_token_scopes VARCHAR(1000) DEFAULT NULL,
  refresh_token_value BLOB DEFAULT NULL,
  refresh_token_issued_at TIMESTAMP DEFAULT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (client_registration_id, principal_name)
);
```