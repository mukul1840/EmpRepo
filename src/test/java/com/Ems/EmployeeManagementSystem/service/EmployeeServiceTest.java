package com.Ems.EmployeeManagementSystem.service;

import com.Ems.EmployeeManagementSystem.entities.Department;
import com.Ems.EmployeeManagementSystem.entities.Employee;
import com.Ems.EmployeeManagementSystem.repositories.EmployeeRepository;
import com.Ems.EmployeeManagementSystem.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee(1L, "Ankit", new Department(),true));
        mockEmployees.add(new Employee(1L, "Roy", new Department(),true));
        mockEmployees.add(new Employee(1L, "Ashish", new Department(),true));
        when(employeeRepository.findAll()).thenReturn(mockEmployees);
        List<Employee> allEmployees = employeeService.getAllEmployees();
        assertEquals(3, allEmployees.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteEmployee() {
        Long employeeId = 1L;
        Employee mockEmployee = new Employee(employeeId, "Mukul", new Department(), true);
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(mockEmployee));
        employeeService.deleteEmployee(employeeId);
        assertFalse(mockEmployee.isActiveStatus());
        verify(employeeRepository, times(1)).save(mockEmployee);
    }

}
