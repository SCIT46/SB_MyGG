package com.mygg.sb.exception.custom;

import lombok.Getter;

@Getter
public class RiotApiTooManyRequests extends RuntimeException 
{
    private String xAppRateLimit;       // API 요청 제한 정보 ex) 2:1, 100:120 1초당 20회, 2분당 100회
    private String retryAfter;          // (초 단위) 다시 요청 가능할 때까지 남은 시간
    private String xAppRateLimitCount; // 현재 요청량 ex) 15:1, 80:120 -> 현재 1초에 1 5회 2분 동안 80회 요청

    public RiotApiTooManyRequests(String message)
    {
        super(message);
    }
    public RiotApiTooManyRequests(String message, String xAppRateLimit, String retryAfter, String xAppRateLimitCount)
    {
        super(message);
        this.xAppRateLimit = xAppRateLimit;
        this.retryAfter = retryAfter;
        this.xAppRateLimitCount = xAppRateLimitCount;
    }
}
