package com.Ems.EmployeeManagementSystem.services;

import com.Ems.EmployeeManagementSystem.entities.Department;

import java.util.List;

public interface DepartmentService<Department> {

    List<Department> getAllDepartments();

    Department createDepartment(Department department);

    void deleteDepartment(Long id);
}
