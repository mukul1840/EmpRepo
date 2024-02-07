package com.Ems.EmployeeManagementSystem.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DepartmentNameAlreadyExistsException.class)
    public ResponseEntity<String> handleDepartmentNameAlreadyExistsException(DepartmentNameAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
