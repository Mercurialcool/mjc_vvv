package com.epam.esm.dao.exception;

public class DaoException extends RuntimeException {
    private static final long serialVersionUID = -3950237039619962604L;

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException() {

    }
}