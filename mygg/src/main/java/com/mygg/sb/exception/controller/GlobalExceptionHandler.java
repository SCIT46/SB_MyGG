package com.mygg.sb.exception.controller;

import java.rmi.UnexpectedException;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.mygg.sb.exception.dto.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // --------------------------------------------------------- Compile Time Exception ---------------------------------------------------------
    @ExceptionHandler(BeanCreationException.class)
    public ResponseEntity<ErrorDTO> handleBeanCreationException(BeanCreationException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(500,HttpStatus.INTERNAL_SERVER_ERROR, "Bean 생성 오류가 발생했습니다.", e.getMessage()) );
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorDTO> handleIllegalStateException(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(500,HttpStatus.INTERNAL_SERVER_ERROR, "코드 오류가 발생했습니다.", e.getMessage()));
    }

    // --------------------------------------------------------- Runtime Exception ---------------------------------------------------------
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDTO> handleNullPointerException(NullPointerException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(500,HttpStatus.INTERNAL_SERVER_ERROR, "Null 오류가 발생했습니다.", e.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDTO> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(500,HttpStatus.INTERNAL_SERVER_ERROR, "런타임 오류가 발생했습니다.", e.getMessage()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorDTO> handleNoResourceFoundException(NoResourceFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(404,HttpStatus.NOT_FOUND, "존재하지 않는 리소스입니다.", e.getMessage()));
    }

    @ExceptionHandler(UnexpectedException.class)
    public ResponseEntity<ErrorDTO> handleUnexpectedException(UnexpectedException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(418,HttpStatus.I_AM_A_TEAPOT, "알 수 없는 오류가 발생했습니다.", e.getMessage()));
    }
    
    // 표준 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(500,HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 오류가 발생했습니다.", e.getMessage()));
    }
    
}

