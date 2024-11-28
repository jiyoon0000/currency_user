package com.sparta.currency_user.dto;

import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Getter
public class ExchangeRequestDto {

    @NotNull(message = "사용자 ID는 필수입니다.")
    private Long userId;

    @NotNull(message = "통화 ID는 필수입니다.")
    private Long currencyId;

    @DecimalMin(value = "0.01", message = "환전 금액은 0.01 이상이어야 합니다.")
    private BigDecimal beforeExchange;
}
