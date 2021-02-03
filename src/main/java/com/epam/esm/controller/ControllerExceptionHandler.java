package com.epam.esm.controller;

import com.epam.esm.dto.ErrorDto;
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

@RestControllerAdvice
public class ControllerExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private static final String INTERNAL_SERVER_ERROR_CODE = "0000";

    private ErrorDto handle(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(code, null, locale);
        return new ErrorDto(message, code);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handlerSurprise(Exception e) {
        return new ErrorDto(e.getMessage(), INTERNAL_SERVER_ERROR_CODE);
    }

    @ExceptionHandler(value = CertificateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handlerGiftCertificateNotFoundException(CertificateNotFoundException e) {
        return handle(e.getMessage());
    }

    @ExceptionHandler(value = CertificateAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handlerGiftCertificateAlreadyExistException(CertificateAlreadyExistsException e) {
        return handle(e.getMessage());
    }

    @ExceptionHandler(value = UnableToDeleteCertificateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handlerUnableDeleteGiftCertificateException(UnableToDeleteCertificateException e) {
        return handle(e.getMessage());
    }

    @ExceptionHandler(value = UnableToUpdateCertificateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handlerUnableUpdateGiftCertificate(UnableToUpdateCertificateException e) {
        return handle(e.getMessage());
    }


    @ExceptionHandler(value = TagNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handlerTagNotFoundException(TagNotFoundException e) {
        return handle(e.getMessage());
    }

    @ExceptionHandler(value = TagAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handlerTagAlreadyExistException(TagAlreadyExistsException e) {
        return handle(e.getMessage());
    }

    @ExceptionHandler(value = UnableToDeleteTagException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handlerUnableDeleteTagException(UnableToDeleteTagException e) {
        return handle(e.getMessage());
    }
}
