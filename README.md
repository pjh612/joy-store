# joy-store
DDD &amp; Clean Architecture , SSE + Kafka Practice Application

# Requirements
joy store - 구매자 플랫폼
joy admin - 판매자 플랫폼
joy ad - 판매자용 광고 센터

joy store
- 쿠키로 세션을 유지하는 레거시 시스템이다.
- 회원가입 & 로그인 할 수 있다.
- 주문을 할 수 있다.

joy admin
- 쿠키로 세션을 유지하는 레거시 시스템이다.
- 회원가입 & 로그인 할 수 있다.
- 상품
  - 상품을 등록할 수 있다.
- 주문 
  - 목록을 조회할 수 있다.
  - 주문이 들어오면 실시간으로 알림이 발생한다.

joy ad
- 신규 생성된 플랫폼으로 joy admin의 계정으로 로그인할 수 있다.(OAuth2)
- 주문 정보 요약을 표시할 수 있다.

joy api
- DDD, 클린아키텍처를 지향해서 구현해본다.
- SSE로 실시간으로 알림을 전송할 수 있다.
- SSE 연결이 끊길 시 누락된 메시지를 재전송 할 수 있다.
- kafka를 사용해 비동기 처리를 한다.


