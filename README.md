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
