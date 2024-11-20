package com.mygg.sb.statics.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateTimeUtils {
    // 시간을 Epoch(TimeStamp)으로
    public static long localDateTimeToEpoch(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    // Epoch(TimeStamp)를 시간으로
    public static LocalDateTime epochToLocalDateTime(long epochMillis) {
        Instant instant = Instant.ofEpochMilli(epochMillis);
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
}
