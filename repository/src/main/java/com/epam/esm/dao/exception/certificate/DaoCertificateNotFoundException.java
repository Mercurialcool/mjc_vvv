package com.epam.esm.dao.exception.certificate;

import com.epam.esm.dao.exception.DaoException;

public class DaoCertificateNotFoundException extends DaoException {
    private static final long serialVersionUID = 5480895478668711729L;

    public DaoCertificateNotFoundException(String message) {
        super(message);
    }

    public DaoCertificateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoCertificateNotFoundException(Throwable cause) {
        super(cause);
    }
}
