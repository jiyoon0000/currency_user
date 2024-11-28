package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class ExchangeRequest {

    //기본키, 자동생성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //사용자와 n:1 관계
    //lazy fetching : 실제로 사용자 정보가 필요한 경우에만 데이터 조회
    @ManyToOne(fetch = FetchType.LAZY)
    //외래키 설정
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //통화와 n:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    //환전 전 금액(원화 기준)
    private BigDecimal beforeExchange;

    //환전 후 금액(계산된 값)
    private BigDecimal afterExchange;

    //상태를 문자열로 저장
    @Enumerated(EnumType.STRING)
    private ExchangeStatus status;

    //데이터 생성 시간
    @CreatedDate
    private LocalDateTime createdAt;

    //데이터 수정 시간
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    //사용자 정의 생성자
    //환전 요청 생성 시 필요한 데이터를 초기화
    public ExchangeRequest(User user, Currency currency, BigDecimal beforeExchange, BigDecimal afterExchange) {
        this.user = user;
        this.currency = currency;
        this.beforeExchange = beforeExchange;
        this.afterExchange = afterExchange;
        this.status = ExchangeStatus.NORMAL; //기본 상태는 normal
    }

    //상태를 업데이트
    public void updateStatus(ExchangeStatus newStatus){
        this.status = newStatus;
    }

    //환전 요청의 상태를 나타내는 enum
    //normal : 정상
    //cancelled : 취소
    public enum ExchangeStatus{
        NORMAL,
        CANCELLED
    }
}
