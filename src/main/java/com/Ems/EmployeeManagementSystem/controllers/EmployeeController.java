package com.Ems.EmployeeManagementSystem.controllers;

import com.Ems.EmployeeManagementSystem.constants.RestConstants;
import com.Ems.EmployeeManagementSystem.entities.Employee;
import com.Ems.EmployeeManagementSystem.exceptions.EmployeeNotFoundException;
import com.Ems.EmployeeManagementSystem.services.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(RestConstants.EMPLOYEE_API_PATH)
public class EmployeeController extends BaseController {
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @GetMapping(RestConstants.GET_ALL)
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employees = employeeServiceImpl.getAllEmployees();
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("Employee Not Found");
        } else {
            return ResponseEntity.ok(employees);
        }
    }

    @GetMapping(RestConstants.ACTIVE)
    public ResponseEntity<List<Employee>> getAllActiveEmployees() {
        List<Employee> activeEmployees = employeeServiceImpl.getAllActiveEmployees();
        if (activeEmployees.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(activeEmployees);
        }
    }

    @GetMapping(RestConstants.EMPLOYEE+RestConstants.ID)
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeServiceImpl.getEmployeeById(id);
    }

    @PostMapping(RestConstants.EMPLOYEE+RestConstants.CREAT)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeServiceImpl.addEmployee(employee);
    }


    @DeleteMapping(RestConstants.EMPLOYEE+RestConstants.DELETE+RestConstants.ID)
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeServiceImpl.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @PutMapping(RestConstants.EMPLOYEE+RestConstants.UPDATE+RestConstants.ID)
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        String updated = employeeServiceImpl.updateEmployee(id, updatedEmployee);
        if (updated == null) {
            throw new EmployeeNotFoundException("Employee Not Found");
        } else {
            return ResponseEntity.ok("Employee Details Updated SuccessFully");
        }
    }

}