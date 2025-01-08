package com.mygg.sb.exception.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
    
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private int responseCode;
    private HttpStatus responseText;
    private String message;
    private String errorData;
}
