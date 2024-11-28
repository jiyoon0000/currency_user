package com.sparta.currency_user.validator;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

//애플리케이션 실행 시 currency 테이블의 환율 데이터를 검증
@Component
@RequiredArgsConstructor
@Slf4j
public class CurrencyValidator {

    private final CurrencyRepository currencyRepository;

    @PostConstruct
    public void validateExchangeRates(){
        List<Currency> currencies = currencyRepository.findAll();

        for(Currency currency : currencies){
        BigDecimal exchangeRate = currency.getExchangeRate();

            if(exchangeRate.compareTo(BigDecimal.ZERO) <= 0){
                log.warn("유효하지 않은 환율 발견. 통화 ID : {}, 환율 : {}", currency.getId(), exchangeRate);
            }else if(exchangeRate.compareTo(new BigDecimal("10000"))>0){
                log.warn("비정상적으로 높은 환율 발견. 통화 ID: {}, 환율 : {}", currency.getId(), exchangeRate);
            }
        }
    }
}
