package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {
    private String currencyName;
    private BigDecimal exchangeRate;
    private String symbol;
    private int decimalPlaces;

    public Currency toEntity() {
        return new Currency(
                this.currencyName,
                this.exchangeRate,
                this.symbol,
                this.decimalPlaces
        );
    }
}
