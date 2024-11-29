# CH4. User-Currency 환전요청

## Lv0. API 명세서, ERD, SQL 작성
### API 명세서 작성 - postman 사용
https://documenter.getpostman.com/view/39376424/2sAYBXAAAX

----

### ERD 작성 - DB diagram 사용
<img width="1259" alt="스크린샷 2024-11-28 오후 5 43 19" src="https://github.com/user-attachments/assets/4fe1da43-f432-4d36-a85c-2f5eb24dafb5">

----

### SQL 작성

```
-- User 테이블
CREATE TABLE User (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(200) NOT NULL,
email VARCHAR(200) NOT NULL UNIQUE,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Currency 테이블
CREATE TABLE Currency (
id INT AUTO_INCREMENT PRIMARY KEY,
currency_name VARCHAR(200) NOT NULL,
exchange_rate DECIMAL(10,2) NOT NULL,
symbol VARCHAR(20) NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ExchangeRequest 테이블
CREATE TABLE ExchangeRequest (
id INT AUTO_INCREMENT PRIMARY KEY,
user_id INT NOT NULL,
currency_id INT NOT NULL,
before_exchange DECIMAL(10,2) NOT NULL,
after_exchange DECIMAL(10,2) NOT NULL,
status VARCHAR(20) DEFAULT 'normal',
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE,
FOREIGN KEY (currency_id) REFERENCES Currency(id)
);
```

----

## LV1.고객(User)과 통화(Currency) 복잡한 연관관계

### 1. 환전 요청 중간 테이블 생성
#### -필드 : 고객 고유 식별자, 환전 대상 통화 식별자, 환전 전 금액, 환전 후 금액, 상태

### 2. 고객과 통화 테이블 간 연관관계
#### -한 고객이 여러 통화로 환전할 수 있고 한 통화는 여러 고객들에 의해 환전될 수 있다.
#### -환전 요청 테이블은 중간 테이블이고 User와 Currency 간 관계를 관리

----

## LV2.환전 요청 CRUD

### C : 환전 요청 수행
#### -환전 후 금액 = 환전 전 금액 / 환율

### R : 환전 요청 조회
#### -고객 고유 식별자를 기반으로 특정 고객이 수행한 환전 요청 조회 : UserID

### U : 환전 요청 상태 변경
#### -상태값 : normal, cancelled

### D : 환전 요청 삭제
#### -고객이 삭제될 때 해당 고객이 수행한 모든 환전 요청도 삭제
#### -영속성 전이, cascade

----

## LV3.예외처리

### 1. 유효성 검사 추가
### 2. 예외 처리 강화

----

## LV4.PostConstruct 적용

### 1. 스프링이 구동될 때 통화 테이블에 있는 환율이 0이거나 음수이거나 지정된 범위를 벗어나는 경우
### 2. 유효하지 않은 값으로 간주하고 로그를 기록 - @Component, @Slf4j

----

## LV5.JPQL

### 1. 고객의 모든 환전 요청을 그룹화하여 조회
### 2. @Query, Group By, SUM, COUNT

----

## LV6. 달러 이외의 통화를 환전할 수 있도록 수정

### 1. Currency 테이블에 다른 통화에 대한 데이터를 추가
### 2. 각 통화마다 다른 자리수를 적용하도록 개발