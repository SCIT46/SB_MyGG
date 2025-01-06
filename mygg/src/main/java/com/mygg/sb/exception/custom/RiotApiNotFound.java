package com.mygg.sb.exception.custom;

public class RiotApiNotFound extends RuntimeException {
    public RiotApiNotFound(String message) {
        super(message);
    }
}
