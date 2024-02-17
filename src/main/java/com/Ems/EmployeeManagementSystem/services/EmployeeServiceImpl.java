package com.Ems.EmployeeManagementSystem.services;

import com.Ems.EmployeeManagementSystem.entities.Department;
import com.Ems.EmployeeManagementSystem.entities.Employee;
import com.Ems.EmployeeManagementSystem.repositories.DepartmentRepository;
import com.Ems.EmployeeManagementSystem.repositories.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class EmployeeServiceImpl extends BaseService<Employee> implements EmployeeService<Employee> {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Employee> getAllEmployees() {
        return getAllResources();
    }
    public List<Employee> getAllActiveEmployees() {
        return employeeRepository.findByActiveStatusTrue();
    }
    public Employee addEmployee(Employee employee) {
        Optional<Department> existingDepartment = departmentRepository.findByName(employee.getDepartment().getName());

        if (existingDepartment != null) {
            employee.setDepartment(existingDepartment.get());
        } else {
            Department newDepartment = new Department();
            newDepartment.setName(employee.getDepartment().getName());
            newDepartment.setActiveStatus(true);
            departmentRepository.save(newDepartment);
            employee.setDepartment(newDepartment);
        }
        return  createResource(employee);
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = getResourceById(id);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            throw new RuntimeException("Employee not found with ID: " + id);
        }
    }
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        employee.setActiveStatus(false);
        employeeRepository.save(employee);
    }

    public String updateEmployee(Long id, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);
        if (existingEmployee != null) {
            BeanUtils.copyProperties(updatedEmployee, existingEmployee, "e_id");
            employeeRepository.save(existingEmployee);
            return "Employee Details Updated SuccessFully";
        } else {
            return null;
        }
    }
}