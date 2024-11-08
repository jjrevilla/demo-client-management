package com.client.management.utils;

import com.client.management.enums.DeviceType;
import com.client.management.enums.DocumentType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidatorUtil {

    public static boolean checkDocumentType(final String documentType) {
        return DocumentType.getValues().contains(documentType);
    }

    public static boolean checkDeviceType(final String deviceType) {
        return DeviceType.getValues().contains(deviceType);
    }
}