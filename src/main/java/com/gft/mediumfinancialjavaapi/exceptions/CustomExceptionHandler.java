/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gft.mediumfinancialjavaapi.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author lps10
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler implements HandlerExceptionResolver {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({InvalidDateException.class})
    public ResponseEntity<Object> handleInvalidDateException(InvalidDateException ex, WebRequest request) {

        String mensagemUsuario = ex.getMessage();
        String mensagemDev = ExceptionUtils.getRootCauseMessage(ex);

        List<Error> errors = Arrays.asList(new Error(mensagemUsuario, mensagemDev));

        return this.handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        List<Error> errors = this.createErrorList(ex.getBindingResult());

        return this.handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
            WebRequest request) {
        String mensagemUsuario = this.messageSource.getMessage("controller.not-allowed", null,
                LocaleContextHolder.getLocale());
        String mensagemDev = ExceptionUtils.getRootCauseMessage(ex);

        List<Error> erros = Arrays.asList(new Error(mensagemUsuario, mensagemDev));

        return this.handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error> errors = this.createErrorList(ex.getBindingResult());

        return this.handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensagemUsuario = messageSource.getMessage("controller.not-allowed", null,
                LocaleContextHolder.getLocale());
        String mensagemDev = ExceptionUtils.getRootCauseMessage(ex);

        List<Error> errors = Arrays.asList(new Error(mensagemUsuario, mensagemDev));

        return handleExceptionInternal(ex, errors, headers, status, request);
    }


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<Error> createErrorList(BindingResult bindingResult) {
        List<Error> errors = new ArrayList<>();

        bindingResult.getFieldErrors().forEach(fieldError -> {
            String mensagemUsuario = this.messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String mensagemDev = fieldError.toString();

            errors.add(new Error(mensagemUsuario, mensagemDev));
        });

        return errors;
    }
}
