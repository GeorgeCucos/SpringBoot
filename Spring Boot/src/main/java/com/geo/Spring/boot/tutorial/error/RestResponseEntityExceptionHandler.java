package com.geo.Spring.boot.tutorial.error;

import com.geo.Spring.boot.tutorial.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorMessage> departmentNotFoundExeption(DepartmentNotFoundException
                                                           exeption, WebRequest request){
        ErrorMessage message=new ErrorMessage(HttpStatus.NOT_FOUND,exeption.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}