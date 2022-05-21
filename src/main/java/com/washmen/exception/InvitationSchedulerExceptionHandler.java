package com.washmen.exception;

import com.washmen.util.ErrorConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class InvitationSchedulerExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handleBadRequestException(Exception exception) {
        log.error("Invalid or Bad request: ", exception);
        return new ErrorInfo(ErrorConstants.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public Object handleException(Exception exception) {
        log.error("Something went wrong: ", exception);
        return new ErrorInfo(ErrorConstants.SERVICE_EXCEPTION, ErrorConstants.SERVICE_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler({PartnerNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Object handleCustomException(PartnerNotFoundException ex) {
        log.error("ApiException thrown: ", ex);
        return new ErrorInfo(ErrorConstants.PARTNER_NOT_FOUND, ErrorConstants.PARTNER_NOT_FOUND_MESSAGE);
    }

}
