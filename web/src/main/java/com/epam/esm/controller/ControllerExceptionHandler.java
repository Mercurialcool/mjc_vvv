package com.epam.esm.controller;

import com.epam.esm.service.dto.ErrorDto;
import com.epam.esm.service.exception.certificate.CertificateAlreadyExistsException;
import com.epam.esm.service.exception.certificate.CertificateNotFoundException;
import com.epam.esm.service.exception.certificate.UnableToDeleteCertificateException;
import com.epam.esm.service.exception.certificate.UnableToUpdateCertificateException;
import com.epam.esm.service.exception.tag.TagAlreadyExistsException;
import com.epam.esm.service.exception.tag.TagNotFoundException;
import com.epam.esm.service.exception.tag.UnableToDeleteTagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

import static com.epam.esm.service.exception.ErrorCodes.*;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private static final String INTERNAL_SERVER_ERROR_CODE = "0000";

    private ErrorDto handle(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("error.not.found", null, locale);
        return new ErrorDto(message, code);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handlerInternal(Exception e) {
        return new ErrorDto(e.getMessage(), INTERNAL_SERVER_ERROR_CODE);
    }

    @ExceptionHandler(value = CertificateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handlerCertificateNotFoundException(CertificateNotFoundException e) {
        return new ErrorDto(e.getMessage(), CERTIFICATE_NOT_FOUND.getCode());
    }

    @ExceptionHandler(value = CertificateAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handlerCertificateAlreadyExistException(CertificateAlreadyExistsException e) {
        return new ErrorDto(e.getMessage(), CERTIFICATE_ALREADY_EXISTS.getCode());
    }

    @ExceptionHandler(value = UnableToDeleteCertificateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handlerUnableToDeleteCertificateException(UnableToDeleteCertificateException e) {
        return new ErrorDto(e.getMessage(), UNABLE_TO_DELETE_CERTIFICATE.getCode());
    }

    @ExceptionHandler(value = UnableToUpdateCertificateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handlerUnableToUpdateCertificate(UnableToUpdateCertificateException e) {
        return new ErrorDto(e.getMessage(), UNABLE_TO_UPDATE_CERTIFICATE.getCode());
    }

    @ExceptionHandler(value = TagNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handlerTagNotFoundException(TagNotFoundException e) {
        return new ErrorDto(e.getMessage(), TAG_NOT_FOUND.getCode());
    }

    @ExceptionHandler(value = TagAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handlerTagAlreadyExistsException(TagAlreadyExistsException e) {
        return new ErrorDto(e.getMessage(), TAG_ALREADY_EXISTS.getCode());
    }

    @ExceptionHandler(value = UnableToDeleteTagException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handlerUnableToDeleteTagException(UnableToDeleteTagException e) {
        return new ErrorDto(e.getMessage(), UNABLE_TO_DELETE_TAG.getCode());
    }
}
