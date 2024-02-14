package com.Ems.EmployeeManagementSystem.repositories;

import com.Ems.EmployeeManagementSystem.entities.Department;
import com.Ems.EmployeeManagementSystem.entities.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @MockBean
    private EmployeeRepository mockEmployeeRepository;

    @Test
    void findByActiveStatusTrue() {
        Employee activeEmployee1 = new Employee(1L, "John", new Department(1L, "HR", true), true);
        Employee activeEmployee2 = new Employee(2L, "Alice", new Department(2L, "IT", true), true);
        Employee inactiveEmployee = new Employee(3L, "Bob", new Department(3L, "Finance", false), false);
        when(mockEmployeeRepository.findByActiveStatusTrue()).thenReturn(Arrays.asList(activeEmployee1, activeEmployee2));
        List<Employee> activeEmployees = employeeRepository.findByActiveStatusTrue();
        System.out.println("activeEmployees : " +activeEmployees);
        assertEquals(2, activeEmployees.size());
        verify(mockEmployeeRepository, times(1)).findByActiveStatusTrue();
    }

    @Test
    void existsByDepartment_dId() {
        Long existingDepartmentId = 1L;
        Long nonExistingDepartmentId = 99L;
        when(mockEmployeeRepository.existsByDepartment_dId(existingDepartmentId)).thenReturn(true);
        when(mockEmployeeRepository.existsByDepartment_dId(nonExistingDepartmentId)).thenReturn(false);
        boolean existsForExistingDepartment = employeeRepository.existsByDepartment_dId(existingDepartmentId);
        boolean existsForNonExistingDepartment = employeeRepository.existsByDepartment_dId(nonExistingDepartmentId);
        assertTrue(existsForExistingDepartment);
        assertFalse(existsForNonExistingDepartment);
        verify(mockEmployeeRepository, times(1)).existsByDepartment_dId(existingDepartmentId);
        verify(mockEmployeeRepository, times(1)).existsByDepartment_dId(nonExistingDepartmentId);
    }
}
