package com.epam.esm.service.exception.tag;

import com.epam.esm.service.exception.ServiceException;

public class UnableToDeleteTagException extends ServiceException {
    private static final long serialVersionUID = -6076577728053221733L;

    public UnableToDeleteTagException(String message) {
        super(message);
    }

    public UnableToDeleteTagException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableToDeleteTagException(Throwable cause) {
        super(cause);
    }
}