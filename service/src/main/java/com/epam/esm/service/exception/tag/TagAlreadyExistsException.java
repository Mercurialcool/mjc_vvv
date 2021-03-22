package com.epam.esm.service.exception.tag;

import com.epam.esm.service.exception.ServiceException;

public class TagAlreadyExistsException extends ServiceException {
    private static final long serialVersionUID = 2090395976066506109L;

    public TagAlreadyExistsException(String message) {
        super(message);
    }

    public TagAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}