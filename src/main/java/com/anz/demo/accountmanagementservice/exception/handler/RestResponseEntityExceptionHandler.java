package com.anz.demo.accountmanagementservice.exception.handler;

import com.anz.demo.accountmanagementservice.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.anz.demo.accountmanagementservice.util.AccountManagementConstants.TRANSACTION_ID;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllException(Exception ex, WebRequest request){
        String errorMsg = parseErrorMessage(ex);
        ErrorResponse errorResponse = ErrorResponse.builder().errorText(errorMsg)
                .responseTime(LocalDateTime.now().atZone(ZoneId.systemDefault())).transactionId(request.getHeader(TRANSACTION_ID)).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccountServiceException.class)
    public final ResponseEntity<ErrorResponse> handleAccountServiceException(Exception ex, WebRequest request){
        String errorMsg = parseErrorMessage(ex);
        ErrorResponse errorResponse = ErrorResponse.builder().errorText(errorMsg)
                .responseTime(LocalDateTime.now().atZone(ZoneId.systemDefault())).transactionId(request.getHeader(TRANSACTION_ID)).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ErrorResponse> handleTypeMismatchException(Exception ex, WebRequest request){
        String errorMsg = parseErrorMessage(ex);
        ErrorResponse errorResponse = ErrorResponse.builder().errorText(errorMsg)
                .responseTime(LocalDateTime.now().atZone(ZoneId.systemDefault())).transactionId(request.getHeader(TRANSACTION_ID)).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public final ResponseEntity<ErrorResponse> handleMissingHeaderException(Exception ex, WebRequest request){
        String errorMsg = parseErrorMessage(ex);
        ErrorResponse errorResponse = ErrorResponse.builder().errorText(errorMsg)
                .responseTime(LocalDateTime.now().atZone(ZoneId.systemDefault())).transactionId(request.getHeader(TRANSACTION_ID)).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private String parseErrorMessage(Exception ex){
        return ex.getLocalizedMessage();
    }
}
