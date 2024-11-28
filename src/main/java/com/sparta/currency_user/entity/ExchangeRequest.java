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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    private BigDecimal beforeExchange;

    private BigDecimal afterExchange;

    @Enumerated(EnumType.STRING)
    private ExchangeStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public ExchangeRequest(User user, Currency currency, BigDecimal beforeExchange, BigDecimal afterExchange) {
        this.user = user;
        this.currency = currency;
        this.beforeExchange = beforeExchange;
        this.afterExchange = afterExchange;
        this.status = ExchangeStatus.NORMAL;
    }

    public void updateStatus(ExchangeStatus newStatus){
        this.status = newStatus;
    }

    public enum ExchangeStatus{
        NORMAL,
        CANCELLED
    }
}
