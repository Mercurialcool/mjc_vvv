package com.epam.esm.service.exception;

public enum ErrorCodes {

    CERTIFICATE_NOT_FOUND("1001"),
    CERTIFICATE_ALREADY_EXISTS("1002"),
    UNABLE_TO_DELETE_CERTIFICATE("1003"),
    UNABLE_TO_UPDATE_CERTIFICATE("1004"),
    INVALID_ENTERED_PARAMETERS("1005"),
    INVALID_CERTIFICATE("1006"),

    TAG_NOT_FOUND("2001"),
    TAG_ALREADY_EXISTS("2002"),
    UNABLE_TO_DELETE_TAG("2003"),
    INVALID_TAG("2004"),

    INTERNAL_SERVER_ERROR("0000");

    private final String errorCode;

    ErrorCodes(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getCode() {
        return errorCode;
    }

}
