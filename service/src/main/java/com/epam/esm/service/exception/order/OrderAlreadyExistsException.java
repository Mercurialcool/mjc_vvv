package com.epam.esm.service.exception.order;

import com.epam.esm.service.exception.ServiceException;

public class OrderAlreadyExistsException extends ServiceException{
        private static final long serialVersionUID = -8892115154572538648L;

        public OrderAlreadyExistsException(String message) {
            super(message);
        }

        public OrderAlreadyExistsException(String message, Throwable cause) {
            super(message, cause);
        }

        public OrderAlreadyExistsException(Throwable cause) {
            super(cause);
        }
}
