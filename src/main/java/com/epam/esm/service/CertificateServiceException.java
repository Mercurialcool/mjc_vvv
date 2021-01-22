package com.epam.esm.service;

public class CertificateServiceException extends Exception {
    public CertificateServiceException() {
    }

    public CertificateServiceException(String message) {
        super(message);
    }

    public CertificateServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CertificateServiceException(Throwable cause) {
        super(cause);
    }
}
