package com.Ems.EmployeeManagementSystem.controllers;

import com.Ems.EmployeeManagementSystem.entities.Department;
import com.Ems.EmployeeManagementSystem.exceptions.DepartmentNameAlreadyExistsException;
import com.Ems.EmployeeManagementSystem.services.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController extends BaseController {
    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @GetMapping("/getall")
    public List<Department> getAllDepartments() {
        return departmentServiceImpl.getAllDepartments();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createDepartment(@RequestBody Department department) {
        try {
            Department createdDepartment = departmentServiceImpl.createDepartment(department);
            return new ResponseEntity<>("Department Created Successfully", HttpStatus.CREATED);
        } catch (DepartmentNameAlreadyExistsException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

@DeleteMapping("/departments/delete/{id}")
public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
    try {
        departmentServiceImpl.deleteDepartment(id);
        return ResponseEntity.ok("Department deleted successfully");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Department contains employees, cannot be deleted");
    }
}

}
