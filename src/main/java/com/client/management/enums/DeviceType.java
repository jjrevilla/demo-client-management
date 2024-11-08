package com.client.management.enums;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
public enum DeviceType {
    IOS("IOS", "Apple iOS"),
    AND("AND", "Google Android");

    private final String value;
    private final String description;

    private final static Map<String, DeviceType> LOOKUP = new HashMap<>();

    static {
        for (final DeviceType deviceType : DeviceType.values()) {
            LOOKUP.put(deviceType.value, deviceType);
        }
    }

    public static DeviceType fromValue(final String deviceType) {
        return LOOKUP.get(deviceType);
    }

    public static Set<String> getValues() {
        return LOOKUP.keySet();
    }

    @Override
    public String toString() {
        return description;
    }
}