# Micro Service Architecture Lecture

이 레포지토리는 마이크로 서비스 아키텍처 강의를 위한 레포지토리입니다.

## 강의 목표
Spring Boot, Spring MVC를 기반으로 블로그 서비스를 마이크로 서비스 아키텍처로 구현해보며 마이크로 서비스 아키텍처의 이해를 목표로 합니다.
기본적으로 Database Per Service 패턴을 사용하며, Kubernetes 환경에서 서비스를 구성하고 서비스 메시 구성(istio 적용)을 하여 
전반적인 인프라까지 기초적인 구현을 목표로 합니다.

## 강의 목차
1. lecture 1: 프로젝트 기본 셋업 및 공통 모듈 구성
2. lecture 2: 인증 서비스 구현(JWT 발급 및 검증)
3. lecture 3: 회원 서비스 구현
4. lecture 4: 인증 서비스와 회원 서비스 간 통신 구현(Feign Client)
5. lecture 5: Spring Security 인증 필터 구현
6. lecture 6: API Gateway 
7. lecture 7: Service Discovery
8. lecture 8: Circuit Breaker
9. lecture 9: Distributed Tracing
10. lecture 10: Kubernetes 환경에서 서비스 배포
11. lecture 11: 서비스 메시 구성(istio 적용)
12. lecture 12: 메시지 큐(Kafka)
13. lecture 13: 분산 트랜잭션(Saga Pattern)
14. lecture 14: 게시글 서비스 구현
15. lecture 15: 댓글 서비스 구현
16. lecture 16: 프론트엔드 어플리케이션 구현
17. lecture 17: BFF 서비스 구현
18. lecture 18: 서비스 모니터링
19. lecture 19: Jenkins CI/CD 파이프라인 구성
20. lecture 20: 마이크로 서비스 아키텍처 리뷰

## 강의 별 완성 소스코드 브랜치
추후 추가 예정

## 서비스 별 README
- [인증 서비스](./auth-service/README.md)
- [회원 서비스](./member-service/README.md)