package com.snapped.web.exception;

import com.snapped.web.enums.StatusCodes;
import com.snapped.web.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.Date;

@RestControllerAdvice
public class ExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleAuthenticationException(AuthenticationException ex){

        ErrorResponse response = new ErrorResponse(
                new ErrorResponse.SnappedError(
                        StatusCodes.AuthError.getCode(),
                        StatusCodes.AuthError.getMessage(),
                        new Date()
                )
        );
        logger.info("response - {}", response);
        return response;
    }

}
