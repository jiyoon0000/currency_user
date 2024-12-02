package com.sparta.currency_user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExchangeSummaryDto {
    private Long userId;
    private Long count;
    private Double totalAmountExchange;
}
