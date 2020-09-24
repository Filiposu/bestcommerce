package com.bestcommerce.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value
            = { ProductNotFoundException.class })
    protected ResponseEntity<Object> handleProductException(
            ProductNotFoundException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), bodyOfResponse);
        return  new  ResponseEntity<Object>(apiError,
                new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), bodyOfResponse);
        return  new  ResponseEntity<Object>(apiError,
                new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(value
            = {AccessDeniedException.class})
    protected ResponseEntity<Object> handleForbidden(
            AccessDeniedException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), bodyOfResponse);
        return  new  ResponseEntity<Object>(apiError,
                new HttpHeaders(), apiError.getStatus());
    }


    @ExceptionHandler(value
            = {UsernameNotFoundException.class })
    protected ResponseEntity<Object> handleGeneric(
            UsernameNotFoundException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

//    @ExceptionHandler({ Exception.class })
//    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
//
//        ApiError apiError = new ApiError(
//                HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
//        return new ResponseEntity<Object>(
//                apiError, new HttpHeaders(), apiError.getStatus());
//    }












}
