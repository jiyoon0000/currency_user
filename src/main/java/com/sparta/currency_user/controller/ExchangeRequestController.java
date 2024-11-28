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

    //환전 요청 생성
    //valid로 dto에 대한 유효성 검사
    @PostMapping
    public ResponseEntity<ExchangeResponseDto> createExchangeRequest(@Valid @RequestBody ExchangeRequestDto dto){
        return ResponseEntity.ok(exchangeRequestService.createExchangeRequest(dto));
    }

    //특정 사용자의 모든 환전 요청 조회
    //사용자 아이디 -> 특정 사용자
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExchangeResponseDto>> findExchangeRequestsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(exchangeRequestService.findExchangeRequestsByUserId(userId));
    }

    //환전 요청 상태 업데이트
    //status : normal, cancelled
    @PutMapping("/{id}")
    public ResponseEntity<ExchangeResponseDto> updateExchangeRequestsStatus(@PathVariable Long id,
                                                                            @RequestParam ExchangeRequest.ExchangeStatus status){
        return ResponseEntity.ok(exchangeRequestService.updateExchangeRequestsStatus(id, status));
    }

    //삭제
    //고객이 삭제될 때 해당 고객이 수행한 모든 환전 요청도 삭제
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUserAndRequests(@PathVariable Long userId){
        userService.deleteUserById(userId);
        return ResponseEntity.ok("사용자와 관련 환전 요청이 삭제되었습니다.");
    }
}