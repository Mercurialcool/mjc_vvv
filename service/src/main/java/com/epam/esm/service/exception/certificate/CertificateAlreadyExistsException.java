package com.epam.esm.service.exception.certificate;

import com.epam.esm.service.exception.ServiceException;

public class CertificateAlreadyExistsException extends ServiceException {
    private static final long serialVersionUID = -8892115154572538648L;

    public CertificateAlreadyExistsException(String message) {
        super(message);
    }

    public CertificateAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CertificateAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}