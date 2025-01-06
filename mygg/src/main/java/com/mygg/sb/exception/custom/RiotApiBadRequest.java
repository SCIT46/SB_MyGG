package com.mygg.sb.exception.custom;

public class RiotApiBadRequest extends RuntimeException {
    public RiotApiBadRequest(String message) {
        super(message);
    }
}
