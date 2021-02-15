package com.epam.esm.service.exception.certificate;

import com.epam.esm.service.exception.ServiceException;

public class UnableToUpdateCertificateException extends ServiceException {
    private static final long serialVersionUID = -1035738899521016567L;

    public UnableToUpdateCertificateException(String message) {
        super(message);
    }

    public UnableToUpdateCertificateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableToUpdateCertificateException(Throwable cause) {
        super(cause);
    }
}