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
        // Arrange
        Employee activeEmployee1 = new Employee(1L, "John", new Department(1L, "HR", true), true);
        Employee activeEmployee2 = new Employee(2L, "Alice", new Department(2L, "IT", true), true);
        Employee inactiveEmployee = new Employee(3L, "Bob", new Department(3L, "Finance", false), false);

        when(mockEmployeeRepository.findByActiveStatusTrue()).thenReturn(Arrays.asList(activeEmployee1, activeEmployee2));

        // Act
        List<Employee> activeEmployees = employeeRepository.findByActiveStatusTrue();
        System.out.println("activeEmployees : " +activeEmployees);
        // Assert
        assertEquals(2, activeEmployees.size());
        //assertTrue(activeEmployees.stream().allMatch(Employee::isActiveStatus));

        // Verify that the mock method was called
        verify(mockEmployeeRepository, times(1)).findByActiveStatusTrue();
    }

    @Test
    void existsByDepartment_dId() {
        // Arrange
        Long existingDepartmentId = 1L;
        Long nonExistingDepartmentId = 99L;

        when(mockEmployeeRepository.existsByDepartment_dId(existingDepartmentId)).thenReturn(true);
        when(mockEmployeeRepository.existsByDepartment_dId(nonExistingDepartmentId)).thenReturn(false);

        // Act
        boolean existsForExistingDepartment = employeeRepository.existsByDepartment_dId(existingDepartmentId);
        boolean existsForNonExistingDepartment = employeeRepository.existsByDepartment_dId(nonExistingDepartmentId);

        // Assert
        assertTrue(existsForExistingDepartment);
        assertFalse(existsForNonExistingDepartment);

        // Verify that the mock methods were called
        verify(mockEmployeeRepository, times(1)).existsByDepartment_dId(existingDepartmentId);
        verify(mockEmployeeRepository, times(1)).existsByDepartment_dId(nonExistingDepartmentId);
    }
}
