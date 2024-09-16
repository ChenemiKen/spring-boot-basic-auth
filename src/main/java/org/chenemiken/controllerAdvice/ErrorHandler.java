package org.chenemiken.controllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.chenemiken.exceptions.ModelAlreadyExistException;
import org.chenemiken.models.ErrorResponse;
import org.chenemiken.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse<Object>> handleException(HttpServletRequest request,
                                                                 Exception e){
        e.printStackTrace();
        return new ResponseEntity<>(ErrorResponse.DefaultErrorResponseBuilder()
                .message("GENERIC/UNKNOWN ERROR")
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse<Object>> handleException(HttpServletRequest request,
                                                                 HttpMessageNotReadableException e){
        return new ResponseEntity<>(ErrorResponse.ErrorResponseBuilder()
                .message("NULL/INVALID INPUT")
                .status(Response.ResponseStatus.ERROR).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<Object>> handleException(HttpServletRequest request,
                                                                 MethodArgumentNotValidException e){
        List<HashMap<String, String>> errorDataItems = new ArrayList<>();
        e.getFieldErrors().forEach(fieldError -> {
            HashMap<String, String> errorFieldItem = new HashMap<>();
            errorFieldItem.put("field", fieldError.getField());
            errorFieldItem.put("error", fieldError.getDefaultMessage());
            errorDataItems.add(errorFieldItem);
        });
        return new ResponseEntity<>(ErrorResponse.ErrorResponseBuilder()
                .message("INVALID INPUT")
                .data(errorDataItems)
                .status(Response.ResponseStatus.ERROR).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ModelAlreadyExistException.class)
    public ResponseEntity<ErrorResponse<Object>> handleException(HttpServletRequest request,
                                                                 ModelAlreadyExistException e){
        return new ResponseEntity<>(ErrorResponse.ErrorResponseBuilder()
                .message(e.getMessage())
                .status(Response.ResponseStatus.ERROR).build(), HttpStatus.BAD_REQUEST);
    }
}
