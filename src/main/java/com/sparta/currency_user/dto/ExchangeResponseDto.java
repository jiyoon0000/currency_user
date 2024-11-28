package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.ExchangeRequest;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ExchangeResponseDto {
    private Long id;
    private Long userId;
    private Long currencyId;
    private BigDecimal beforeExchange;
    private BigDecimal afterExchange;
    private ExchangeRequest.ExchangeStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ExchangeResponseDto(ExchangeRequest exchangeRequest){
        this.id = exchangeRequest.getId();
        this.userId = exchangeRequest.getUser().getId();
        this.currencyId = exchangeRequest.getCurrency().getId();
        this.beforeExchange = exchangeRequest.getBeforeExchange();
        this.afterExchange = exchangeRequest.getAfterExchange();
        this.status = exchangeRequest.getStatus();
        this.createdAt = exchangeRequest.getCreatedAt();
        this.modifiedAt = exchangeRequest.getModifiedAt();
    }
}
