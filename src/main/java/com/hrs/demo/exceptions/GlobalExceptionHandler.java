package com.hrs.demo.exceptions;

import com.hrs.demo.models.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * Custom response error
 * */
@Slf4j
@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Internal server error
     * */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        log.debug(ex.getLocalizedMessage());
        ex.printStackTrace();
        return commonHandlerException(Strings.EMPTY, "Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Bad request
     * */
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleBadRequest(Exception ex) {
        return commonHandlerException(ex.getLocalizedMessage(), "Bad Request", HttpStatus.BAD_REQUEST);
    }

    /**
     * Not Found
     * */
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleUserNotFoundException(NotFoundException ex) {
        return commonHandlerException(ex.getLocalizedMessage(), "Not Found", HttpStatus.NOT_FOUND);
    }

    /**
     * Common handler exception
     * @param exMessage (long message), message (short error message), httpStatus (errorCode)
     * */
    private ResponseEntity<Object> commonHandlerException(String exMessage, String message, HttpStatus httpStatus) {
        List<String> details = new ArrayList<>();

        //Save Message in Here
        details.add(exMessage);
        ErrorResponse error = new ErrorResponse(message, details);
        return new ResponseEntity(error, httpStatus);
    }

}