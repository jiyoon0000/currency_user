package com.sparta.currency_user.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

//공통 에러 응답
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
}
