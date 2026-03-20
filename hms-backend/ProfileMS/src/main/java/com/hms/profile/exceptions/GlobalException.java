package com.hms.profile.exceptions;


import com.hms.profile.dto.ErrorRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception exception,
                                                        WebRequest webRequest) {
        ErrorRespDto errorRespDTO = new ErrorRespDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorRespDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorRespDto> handleResourceNotFoundException(RuntimeException exception,
                                                                        WebRequest webRequest) {
        ErrorRespDto errorResponseDTO = new ErrorRespDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorRespDto> handlePatientNotFoundException(PatientNotFoundException exception,
                                                                       WebRequest webRequest) {
        ErrorRespDto errorResponseDTO = new ErrorRespDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PatientException.class)
    public ResponseEntity<ErrorRespDto> handlePatientNotFoundException(PatientException exception,
                                                                       WebRequest webRequest) {
        ErrorRespDto errorResponseDTO = new ErrorRespDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DoctorAlreadyFoundException.class)
    public ResponseEntity<ErrorRespDto> handleAlreadyFoundException(DoctorAlreadyFoundException exception,
                                                                    WebRequest webRequest) {
        ErrorRespDto errorResponseDTO = new ErrorRespDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception,
                                                                         WebRequest webRequest) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError ->
                        errors.put(
                                fieldError.getField(),
                                fieldError.getDefaultMessage()
                        )
                );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }
}
