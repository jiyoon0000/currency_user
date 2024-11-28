package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.ExchangeRequest;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.ExchangeRequestRepository;
import com.sparta.currency_user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExchangeRequestService {

    private final ExchangeRequestRepository exchangeRequestRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    @Transactional
    public ExchangeResponseDto createExchangeRequest(ExchangeRequestDto dto){
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Currency currency = currencyRepository.findById(dto.getCurrencyId())
                .orElseThrow(() -> new IllegalArgumentException("통화를 찾을 수 없습니다."));

        BigDecimal afterExchange = dto.getBeforeExchange()
                .divide(currency.getExchangeRate(),2, RoundingMode.HALF_UP);

        ExchangeRequest exchangeRequest = new ExchangeRequest(user, currency, dto.getBeforeExchange(), afterExchange);
        exchangeRequestRepository.save(exchangeRequest);

        return new ExchangeResponseDto(exchangeRequest);
    }

    @Transactional
    public List<ExchangeResponseDto> findExchangeRequestsByUserId(Long userId){
        userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return exchangeRequestRepository.findAllByUserId(userId)
                .stream()
                .map(ExchangeResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ExchangeResponseDto updateExchangeRequestsStatus(Long id, ExchangeRequest.ExchangeStatus status){
        ExchangeRequest exchangeRequest = exchangeRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("요청을 찾을 수 없습니다."));

        exchangeRequest.updateStatus(status);

        return new ExchangeResponseDto(exchangeRequest);
    }
}
