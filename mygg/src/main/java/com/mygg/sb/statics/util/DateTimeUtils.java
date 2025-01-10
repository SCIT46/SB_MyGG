package com.mygg.sb.statics.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

// 시간 변환 유틸
// RIOT JSON으로 부터 받아오는 데이터 중 시간의 형식이 타임스탬프(Epoch)이므로 
// 자바시간(LocalDateTime)으로 변환해주는 유틸
public class DateTimeUtils {
    // 시간을 밀리미터초 Epoch(TimeStamp)으로
    public static long localDateTimeToMillisEpoch(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    // (밀리미터)초 Epoch(TimeStamp)를 시간으로
    public static LocalDateTime epochToLocalDateTime(long epochMillis) {
        Instant instant = Instant.ofEpochMilli(epochMillis);
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

    // timeStamp to LocalDateTime
    public static LocalDateTime epochToSecondLocalDateTime(long timeStamp)
    {
        Instant instant = Instant.ofEpochSecond(timeStamp);
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);    	
    }
    // 초 반환(timeStamp)
    public static long localDateTimeToSeconsEpoch(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli() / 1000;
    }
    
    // 밀리미터초 Epoch(TimeStamp)를 초 Epoch(TimeStamp)로
    public static long millisEpochToSecondEpoch(long millisEpoch) {
        return millisEpoch / 1000;
    }
}
