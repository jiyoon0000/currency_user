package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currencyName;
    private BigDecimal exchangeRate;
    private String symbol;
    private int decimalPlaces;

    @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExchangeRequest> exchangeRequests = new ArrayList<>();

    public Currency(String currencyName, BigDecimal exchangeRate, String symbol, int decimalPlaces) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
        this.decimalPlaces = decimalPlaces;
    }


}
