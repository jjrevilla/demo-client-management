package com.client.management.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

    public static final String CLIENTS_PATH = "clients";
    public static final String CONSUMER_ID_HEADER = "consumerId";
    public static final String CONSUMER_ID_PATTERN = "^[A-Z]{3}$";
    public static final String TRACE_PARENT_HEADER = "traceparent";
    public static final String TRACE_PARENT_PATTERN = "^[0-9a-f]{2}-[0-9a-f]{32}-[0-9a-f]{16}-[0-9a-f]{2}$";
    public static final String DEVICE_TYPE_HEADER = "deviceType";
    public static final String DEVICE_ID_HEADER = "deviceId";
    public static final String NAME_INVALID_MESSAGE = "Name value is mandatory.";
    public static final String FATHER_LAST_NAME_REQUIRED_MESSAGE = "Father LastName value is mandatory.";
    public static final String MOTHER_LAST_NAME_REQUIRED_MESSAGE = "Mother LastName value must not be empty.";
    public static final String DOCUMENT_TYPE_REQUIRED_MESSAGE = "Document Type value is mandatory.";
    public static final String DOCUMENT_TYPE_INVALID_MESSAGE = "Document Type value is not valid.";
    public static final String DOCUMENT_NUMBER_PATTERN = "^[0-9]{6,10}$";
    public static final String DOCUMENT_NUMBER_REQUIRED_MESSAGE = "Document Number value is mandatory.";
    public static final String DOCUMENT_NUMBER_INVALID_MESSAGE = "Document Number value is not valid.";
    public static final String CONSUMER_ID_INVALID_MESSAGE = "Consumer Id doest not match the expected format.";
    public static final String TRACE_PARENT_INVALID_MESSAGE = "Traceparent doest not match the expected format.";
    public static final String DEVICE_TYPE_INVALID_MESSAGE = "Device Type value is not valid.";
    public static final String DEVICE_ID_INVALID_MESSAGE = "Device Id value is mandatory.";
    public static final String CONTAINER_CLIENT_NAME = "ClientData";
    public static final String CONTAINER_UNIQUE_KEY_PATH = "/documentNumber";

    public static final String INTERNAL_ERROR_CODE = "E001";
    public static final String INTERNAL_ERROR_MESSAGE = "An unexpected error occurred.";
    public static final String BAD_INPUT_CODE = "E002";
    public static final String BAD_INPUT_MESSAGE = "Validation failed for input received.";
    public static final String RESOURCE_ERROR_CODE = "E003";
    public static final String RESOURCE_ERROR_MESSAGE = "There was a problem while querying internal resources.";
}