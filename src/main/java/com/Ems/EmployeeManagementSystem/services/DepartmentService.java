package com.Ems.EmployeeManagementSystem.services;

import com.Ems.EmployeeManagementSystem.entities.Department;
import com.Ems.EmployeeManagementSystem.exceptions.DepartmentNameAlreadyExistsException;
import com.Ems.EmployeeManagementSystem.repositories.DepartmentRepository;
import com.Ems.EmployeeManagementSystem.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import  java.util.*;
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Department> getAllDepartments() {

        return departmentRepository.findAll();
    }

    public Department createDepartment(Department department) {
        String departmentName = department.getDname();
        // Check if a department with the given name already exists
        if (departmentRepository.findByName(departmentName).isPresent()) {
            throw new DepartmentNameAlreadyExistsException("Department with name " + departmentName + " already exists");
        }
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                . orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));

        // Check if the department has associated employees
        if (employeeRepository.existsByDepartment_dId(id)) {
            throw new RuntimeException("Department contains employees, cannot be deleted");
        }

        // Delete the department if no associated employees
        departmentRepository.delete(department);
    }
}