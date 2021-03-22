package com.epam.esm.dao.exception.tag;

import com.epam.esm.dao.exception.DaoException;

public class DaoTagNotFoundException extends DaoException {
    private static final long serialVersionUID = 5480895478668711729L;

    public DaoTagNotFoundException(String message) {
        super(message);
    }

    public DaoTagNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoTagNotFoundException(Throwable cause) {
        super(cause);
    }
}
