package com.Ems.EmployeeManagementSystem.controllers;

import com.Ems.EmployeeManagementSystem.entities.Employee;
import com.Ems.EmployeeManagementSystem.exceptions.EmployeeNotFoundException;
import com.Ems.EmployeeManagementSystem.services.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/ems")
public class EmployeeController extends BaseController{
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employees = employeeServiceImpl.getAllEmployees();
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("Employee Not Found");
        } else {
            return ResponseEntity.ok(employees);
        }
    }

    @GetMapping("/employees/active")
    public ResponseEntity<List<Employee>> getAllActiveEmployees() {
        List<Employee> activeEmployees = employeeServiceImpl.getAllActiveEmployees();
        if (activeEmployees.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(activeEmployees);
        }
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeServiceImpl.getEmployeeById(id);
    }

    @PostMapping("/employee/create")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeServiceImpl.addEmployee(employee);
    }


    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeServiceImpl.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @PutMapping("/employee/update{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        String updated = employeeServiceImpl.updateEmployee(id, updatedEmployee);
        if (updated== null) {
            throw new EmployeeNotFoundException("Employee Not Found");
        } else {
            return ResponseEntity.ok("Employee Details Updated SuccessFully");
        }
    }

}