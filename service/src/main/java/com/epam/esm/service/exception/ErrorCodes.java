package com.epam.esm.service.exception;

public enum ErrorCodes {

    CERTIFICATE_NOT_FOUND("40401"),
    CERTIFICATE_ALREADY_EXISTS("40902"),
    UNABLE_TO_DELETE_CERTIFICATE("40903"),
    UNABLE_TO_UPDATE_CERTIFICATE("40904"),
    INVALID_ENTERED_PARAMETERS("40905"),
    INVALID_CERTIFICATE("40906"),

    TAG_NOT_FOUND("40401"),
    TAG_ALREADY_EXISTS("40902"),
    UNABLE_TO_DELETE_TAG("40903"),
    INVALID_TAG("40904"),

    USER_NOT_FOUND("40403"),

    ORDER_NOT_FOUND("40404"),

    INTERNAL_SERVER_ERROR("0000");

    private final String errorCode;

    ErrorCodes(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getCode() {
        return errorCode;
    }

}
