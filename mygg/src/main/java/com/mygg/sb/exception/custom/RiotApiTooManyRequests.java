package com.mygg.sb.exception.custom;

public class RiotApiTooManyRequests extends RuntimeException {
    public RiotApiTooManyRequests(String message) {
        super(message);
    }
}
