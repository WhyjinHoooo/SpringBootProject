# 스프링 게시판 프로젝트
---
### 프로젝트 개요
* [이거 하나로 종결-스프링 기반 풀스택 웹 개발 무료강의](https://www.inflearn.com/course/%EC%9D%B4%EA%B1%B0%ED%95%98%EB%82%98%EB%A1%9C%EC%A2%85%EA%B2%B0-%EC%8A%A4%ED%94%84%EB%A7%81%EA%B8%B0%EB%B0%98-%ED%92%80%EC%8A%A4%ED%83%9D%EA%B0%9C%EB%B0%9C/dashboard)을 학습하며 따라한 게시판입니다.
---
### 기술
* IDE : Eclipse
* DBMS : MySQL 8.0.30
* WAS : Apache Tomcat 9.0
* Frontend : HTML, CSS, JavaScript
* Backend : Java 17, Spring Boot 3.3.4, Spring Security, MyBatis 3.0.3
* 빌드 도구 : Gradle
* API 문서화 : Swagger (springdoc-openapi 2.2.0)
* JSP 관련 : JSTL
---
### 시스템 아키택쳐

#### ERD
<img width="347" height="226" alt="스프링eer" src="https://github.com/user-attachments/assets/a333e201-e9bf-445c-a6fd-52bff4c3bdaa" />
---
#### API 설계
<img width="2400" height="911" alt="Swagger_API_메서드별_역할_요약표" src="https://github.com/user-attachments/assets/19bec25b-7837-43aa-b1c2-2b0b5574d9a9" />
---
### 구현한 기능
* 회원가입, 로그인
* 게시글 CRUD
---
#### 프로젝트 수행 과정 및 결과

### 느낀점
* 온라인 강의를 보고 따라한 프로젝트로 개발기간 : 12일
* 스프링에 대해 처음 학습했고, 다양하고 좋은 기능들이 있어 추후 다른 프로젝트를 진행할 때 좋은 영향을 줄 것 같음.

### 개선 사항
- 게시글이 100개 이상 등록될 경우, 메인 페이지에 모두 표시되어 페이지 길이가 과도하게 길어지는 문제 발생
- 이 문제를 해결하기 위해 한 페이지에 10개씩 게시글을 분할하여 페이지별로 보여주는 페이징 기능을 도입
- 사용자 요청에 따라 특정 페이지의 게시글을 조회할 수 있도록 하여 화면 로딩 속도 및 가독성 향상
- Spring Data JPA의 Pageable, Page 인터페이스를 활용하여 백엔드에서 페이징 처리 구현
