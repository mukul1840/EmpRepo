package com.Ems.EmployeeManagementSystem.controllers;

import com.Ems.EmployeeManagementSystem.entities.Employee;
import com.Ems.EmployeeManagementSystem.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/ems")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        if (employees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employees found");
        } else {
            return ResponseEntity.ok(employees);
        }
    }

    @GetMapping("/employees/active")
    public ResponseEntity<List<Employee>> getAllActiveEmployees() {
        List<Employee> activeEmployees = employeeService.getAllActiveEmployees();

        if (activeEmployees.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(activeEmployees);
        }
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employee/create")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }


    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @PutMapping("/employee/update{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        String updated = employeeService.updateEmployee(id, updatedEmployee);

        if (updated== null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Found");
        } else {
            return ResponseEntity.ok("Employee Details Updated SuccessFully");
        }
    }

}