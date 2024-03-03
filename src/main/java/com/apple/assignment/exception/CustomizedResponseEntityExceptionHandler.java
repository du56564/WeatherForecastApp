package com.apple.assignment.exception;

import com.apple.assignment.constants.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), CommonConstants.FAILURE + ": " + ex.getMessage(), request.getDescription(false));
        LOGGER.error("code:{} :: message:{}", HttpStatus.INTERNAL_SERVER_ERROR, exceptionResponse.getMessage());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WeatherForcastBusinessException.class)
    public final ResponseEntity<Object> handleWeatherForcastBusinessException(WeatherForcastBusinessException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), CommonConstants.FAILURE + ": " + ex.getErrMessage(), request.getDescription(false));
        LOGGER.error("code:{} :: message:{}", HttpStatus.NOT_FOUND, exceptionResponse.getMessage());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
