package com.epam.esm.service.exception;

public enum ErrorCodes {

    CERTIFICATE_NOT_FOUND("404"),
    CERTIFICATE_ALREADY_EXISTS("409"),
    UNABLE_TO_DELETE_CERTIFICATE("409"),
    UNABLE_TO_UPDATE_CERTIFICATE("409"),
    INVALID_ENTERED_PARAMETERS("409"),
    INVALID_CERTIFICATE("409"),

    TAG_NOT_FOUND("404"),
    TAG_ALREADY_EXISTS("409"),
    UNABLE_TO_DELETE_TAG("409"),
    INVALID_TAG("409"),

    INTERNAL_SERVER_ERROR("0000");

    private final String errorCode;

    ErrorCodes(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getCode() {
        return errorCode;
    }

}
