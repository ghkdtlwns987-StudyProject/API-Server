# API 서버 구현
---
### Developer : 황시준

### Build
> ./gradlew clean build


### Introduction
---
- 개인 프로젝트로 진행중인 API 서버 입니다.  
해당 서버는 실질적인 동작을 수행하는 서버로 현재까지 구현된 기능은 다음과 같습니다.
## Member  
---
    1. 회원 가입
    2. 회원 탈퇴
    3. 회원 수정

## Jenkins
---
    1. 프로젝트 생성
    2. 프로젝트 실행


API 서버와 인증서버간 역할을 분리하기 위해 `Member`기능의 `C, U, D`기능을 API 서버에서 수행하도록 했고  Auth 서버에서 `회원 인증 관련 로직`을 수행하도록 작성했습니다.

또한 API서버에서 사용자가 특정 정보를 입력하게 되면 `Kubernetes Cluste`r에 구축된 `Jenkins` 에 `REST` 통신으로 사용자가 입력한 정보가 Jenkins에 전달해 프로젝트를 생성합니다. 생성한 프로젝트는 `API서버에서 프로젝트를 빌드하고 배포하는 기능을 수행합니다.`

### Skills
---
- Spring Boot
- Spring Security
- Jenkins
- RestAPI
- Jenkins-Rest
- JPA
- QueryDSL
- OOP

### Description
--- 
- 사용자는 프로젝트 배포에 필요한 정보를 입력하면 REST 통신으로 사전에 구축된 Jenkins에 해당 요청을 전달하게 됩니다. 전달한 정보를 바탕으로 Jenkins는 사전에 구축한 EKS에 해당 프로젝트를 빌드하게 됩니다.

### 추가할 내용
----
- Apache Kafka