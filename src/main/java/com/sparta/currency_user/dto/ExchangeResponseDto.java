package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.ExchangeRequest;
import com.sparta.currency_user.enums.ExchangeStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Getter
public class ExchangeResponseDto {
    private Long id;
    private Long userId;
    private Long currencyId;
    private BigDecimal beforeExchange;
    private BigDecimal afterExchange;
    private String formattedAfterExchange;
    private ExchangeStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ExchangeResponseDto(ExchangeRequest exchangeRequest){
        this.id = exchangeRequest.getId();
        this.userId = exchangeRequest.getUser().getId();
        this.currencyId = exchangeRequest.getCurrency().getId();
        this.beforeExchange = exchangeRequest.getBeforeExchange();
        this.afterExchange = exchangeRequest.getAfterExchange();
        this.formattedAfterExchange = formatAfterExchange(exchangeRequest);
        this.status = exchangeRequest.getStatus();
        this.createdAt = exchangeRequest.getCreatedAt();
        this.modifiedAt = exchangeRequest.getModifiedAt();
    }

    private String formatAfterExchange(ExchangeRequest exchangeRequest){
        int decimalPlaces = exchangeRequest.getCurrency().getDecimalPlaces();
        StringBuilder pattern = new StringBuilder("#");

        if(decimalPlaces>0){
            pattern.append(".");
            for(int i=0; i<decimalPlaces; i++){
                pattern.append("#");
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat(pattern.toString());
        String formattedValue = decimalFormat.format(exchangeRequest.getAfterExchange());
        return formattedValue + exchangeRequest.getCurrency().getSymbol();
    }

}
