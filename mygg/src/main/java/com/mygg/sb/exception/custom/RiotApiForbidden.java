package com.mygg.sb.exception.custom;

public class RiotApiForbidden extends RuntimeException {
    public RiotApiForbidden(String message) {
        super(message);
    }
}
