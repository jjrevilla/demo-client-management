package com.client.management.enums;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
public enum DocumentType {
    DNI("1", "DNI"),
    CEX("2", "Alien card"),
    PSP("3", "Passport"),
    OTH("4", "Other");

    private final String value;
    private final String description;

    private final static Map<String, DocumentType> LOOKUP = new HashMap<>();

    static {
        for (final DocumentType documentType : DocumentType.values()) {
            LOOKUP.put(documentType.value, documentType);
        }
    }

    public static DocumentType fromValue(final String documentValue) {
        return LOOKUP.get(documentValue);
    }

    public static Set<String> getValues() {
        return LOOKUP.keySet();
    }

    @Override
    public String toString() {
        return description;
    }
}