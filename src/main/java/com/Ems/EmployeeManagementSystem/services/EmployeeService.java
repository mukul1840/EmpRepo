package com.Ems.EmployeeManagementSystem.services;

import java.util.List;

public interface EmployeeService<Employee> {
    List<Employee> getAllEmployees();
    Employee addEmployee(Employee entity);

    Employee getEmployeeById(Long id);

    void deleteEmployee(Long id);

    String updateEmployee(Long id, Employee employee);


}
