package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.ExchangeRequest;
import com.sparta.currency_user.service.ExchangeRequestService;
import com.sparta.currency_user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchange-requests")
@RequiredArgsConstructor
public class ExchangeRequestController {

    private final ExchangeRequestService exchangeRequestService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ExchangeResponseDto> createExchangeRequest(@Valid @RequestBody ExchangeRequestDto dto){
        return ResponseEntity.ok(exchangeRequestService.createExchangeRequest(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExchangeResponseDto>> findExchangeRequestsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(exchangeRequestService.findExchangeRequestsByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExchangeResponseDto> updateExchangeRequestsStatus(@PathVariable Long id,
                                                                            @RequestParam ExchangeRequest.ExchangeStatus status){
        return ResponseEntity.ok(exchangeRequestService.updateExchangeRequestsStatus(id, status));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUserAndRequests(@PathVariable Long userId){
        userService.deleteUserById(userId);
        return ResponseEntity.ok("사용자와 관련 환전 요청이 삭제되었습니다.");
    }
}