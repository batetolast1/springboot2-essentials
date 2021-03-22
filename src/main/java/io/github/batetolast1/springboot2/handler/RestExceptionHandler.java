package io.github.batetolast1.springboot2.handler;

import io.github.batetolast1.springboot2.exception.ResourceNotFoundDetails;
import io.github.batetolast1.springboot2.exception.ResourceNotFoundException;
import io.github.batetolast1.springboot2.exception.ValidationExceptionDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDetails> handleResourceNorFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<>(
                ResourceNotFoundDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Resource not found.")
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handleResourceNorFoundException(MethodArgumentNotValidException exception) {

        log.info("Fields error {}", exception.getBindingResult().getFieldErrors());

        String fields = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(", "));
        String fieldsMessages = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Field validation error.")
                        .details("Check the fields below.")
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMessages(fieldsMessages)
                        .build(),
                HttpStatus.BAD_REQUEST);
    }
}
