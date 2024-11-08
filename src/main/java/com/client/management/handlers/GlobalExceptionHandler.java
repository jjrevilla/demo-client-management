package com.client.management.handlers;

import com.client.management.dto.error.ErrorDetails;
import com.client.management.exceptions.ResourceClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.server.MissingRequestValueException;

import java.util.List;

import static com.client.management.utils.Constants.BAD_INPUT_CODE;
import static com.client.management.utils.Constants.BAD_INPUT_MESSAGE;
import static com.client.management.utils.Constants.INTERNAL_ERROR_CODE;
import static com.client.management.utils.Constants.INTERNAL_ERROR_MESSAGE;
import static com.client.management.utils.Constants.RESOURCE_ERROR_CODE;
import static com.client.management.utils.Constants.RESOURCE_ERROR_MESSAGE;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<?> handleWebExchangeBindException(final WebExchangeBindException ex) {
        log.error(ex.getMessage(), ex);
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .code(BAD_INPUT_CODE)
                .message(BAD_INPUT_MESSAGE)
                .details(ex.getFieldErrors().stream()
                        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                        .toList())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestValueException.class)
    public ResponseEntity<?> handleMissingRequestValueException(final MissingRequestValueException ex) {
        log.error(ex.getMessage(), ex);
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .code(BAD_INPUT_CODE)
                .message(ex.getReason())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<?> handleMethodValidationException(final HandlerMethodValidationException ex) {
        log.error(ex.getMessage(), ex);
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .code(BAD_INPUT_CODE)
                .message(BAD_INPUT_MESSAGE)
                .details(ex.getAllValidationResults().stream()
                        .map(ParameterValidationResult::getResolvableErrors)
                        .flatMap(List::stream)
                        .map(MessageSourceResolvable::getDefaultMessage)
                        .toList())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceClientException.class)
    public ResponseEntity<?> handleResourceClientException(final ResourceClientException ex) {
        log.error(ex.getMessage(), ex);
        final ErrorDetails errorDetails =
                ErrorDetails.builder().code(RESOURCE_ERROR_CODE).message(RESOURCE_ERROR_MESSAGE).build();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(final Exception ex) {
        log.error(ex.getMessage(), ex);
        final ErrorDetails errorDetails =
                ErrorDetails.builder().code(INTERNAL_ERROR_CODE).message(INTERNAL_ERROR_MESSAGE).build();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}