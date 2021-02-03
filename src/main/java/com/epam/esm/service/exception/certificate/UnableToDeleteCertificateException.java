package com.epam.esm.service.exception.certificate;

import com.epam.esm.service.exception.ServiceException;

public class UnableToDeleteCertificateException extends ServiceException {
    private static final long serialVersionUID = 4829675206020157517L;

    public UnableToDeleteCertificateException(String message) {
        super(message);
    }

    public UnableToDeleteCertificateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableToDeleteCertificateException(Throwable cause) {
        super(cause);
    }
}