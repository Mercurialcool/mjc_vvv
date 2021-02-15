package com.epam.esm.service.exception.certificate;

import com.epam.esm.service.exception.ServiceException;

public class CertificateNotFoundException extends ServiceException {
    private static final long serialVersionUID = 5480895478668711729L;

    public CertificateNotFoundException(String message) {
        super(message);
    }

    public CertificateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CertificateNotFoundException(Throwable cause) {
        super(cause);
    }

}