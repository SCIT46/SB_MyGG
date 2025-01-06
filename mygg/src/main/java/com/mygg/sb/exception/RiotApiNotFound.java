package com.mygg.sb.exception;

public class RiotApiNotFound extends RuntimeException {
    public RiotApiNotFound(String message) {
        super(message);
    }
}
