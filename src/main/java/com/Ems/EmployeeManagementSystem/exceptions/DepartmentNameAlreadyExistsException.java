package com.Ems.EmployeeManagementSystem.exceptions;

public class DepartmentNameAlreadyExistsException extends RuntimeException {
    public DepartmentNameAlreadyExistsException(String message) {
        super(message);
    }
}
