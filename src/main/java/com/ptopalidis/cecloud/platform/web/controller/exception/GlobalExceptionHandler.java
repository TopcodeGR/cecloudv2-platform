package com.ptopalidis.cecloud.platform.web.controller.exception;


import com.ptopalidis.cecloud.platform.common.exception.GlobalException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    protected ApiError handleConflict(
            GlobalException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.getMessage(),ex.getError().getHttpStatus().value(), new Date(),ex.getError().getCode(),ex.getData());
        return apiError;
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    protected ApiError handleConflict(
            Exception ex, WebRequest request) {
        ApiError apiError  = new ApiError(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(),request.getDescription(false),null);
        return  apiError;
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ApiError handleConflict(
            AccessDeniedException ex, WebRequest request) {
        ApiError apiError  = new ApiError(ex.getMessage(),HttpStatus.FORBIDDEN.value(), new Date(),request.getDescription(false),null);
        return  apiError;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiError handleConflict(
            MethodArgumentNotValidException ex, WebRequest request) {
        return new ApiError(ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(". ")), HttpStatus.BAD_REQUEST.value(), new Date(),request.getDescription(false),null);
    }


    @ExceptionHandler(value = DataAccessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiError handleConflict(
            DataAccessException ex, WebRequest request) {
        return new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), new Date(),request.getDescription(false),null);
    }

    @ExceptionHandler(value = UnsupportedOperationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ApiError handleConflict(
            UnsupportedOperationException ex, WebRequest request) {
        ApiError apiError  = new ApiError(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(),request.getDescription(false),null);
        return  apiError;
    }
}
