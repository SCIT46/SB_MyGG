package com.mygg.sb.exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mygg.sb.exception.custom.DataNotFoundException;
import com.mygg.sb.exception.custom.RiotApiBadRequest;
import com.mygg.sb.exception.custom.RiotApiForbidden;
import com.mygg.sb.exception.custom.RiotApiNotFound;
import com.mygg.sb.exception.custom.RiotApiTooManyRequests;
import com.mygg.sb.exception.dto.ErrorDTO;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(RiotApiNotFound.class)
    public ResponseEntity<ErrorDTO> handleRiotApiNotFound(RiotApiNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(404,HttpStatus.NOT_FOUND, "존재하지 않는 데이터입니다.", e.getMessage()));
    }

    @ExceptionHandler(RiotApiForbidden.class)
    public ResponseEntity<ErrorDTO> handleRiotApiForbidden(RiotApiForbidden e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDTO(403,HttpStatus.FORBIDDEN, "API 권한이(키가) 만료되었습니다.", e.getMessage()));
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleDataNotFoundException(DataNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(404,HttpStatus.NOT_FOUND, "존재하지 않는 데이터입니다.", e.getMessage()));
    }

    @ExceptionHandler(RiotApiBadRequest.class)
    public ResponseEntity<ErrorDTO> handleRiotApiBadRequest(RiotApiBadRequest e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(400,HttpStatus.BAD_REQUEST, "API 요청에 문제가 있습니다.", e.getMessage()));
    }

    @ExceptionHandler(RiotApiTooManyRequests.class)
    public ResponseEntity<ErrorDTO> handleRiotApiTooManyRequests(RiotApiTooManyRequests e) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(new ErrorDTO(429,HttpStatus.TOO_MANY_REQUESTS, "API의 요청 제한을 초과했습니다.", e.getMessage()));
    }
}
