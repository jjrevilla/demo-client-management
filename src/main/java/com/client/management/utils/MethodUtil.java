package com.client.management.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MethodUtil {

    private static final String DASH = "-";

    public static String getTraceId(final String traceParent) {
        return traceParent.split(DASH)[1];
    }

    public static Long getCurrentTimeStamp() {
        return System.currentTimeMillis();
    }

    public static String getFormattedCurrentTimeStamp(final Long timestamp) {
        return Instant.ofEpochMilli(timestamp).toString();
    }

}