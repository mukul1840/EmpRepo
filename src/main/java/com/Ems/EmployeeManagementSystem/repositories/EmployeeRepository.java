package com.Ems.EmployeeManagementSystem.repositories;

import com.Ems.EmployeeManagementSystem.entities.Department;
import com.Ems.EmployeeManagementSystem.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Long> {

    List<Employee> findByActiveStatusTrue();
    boolean existsByDepartment_dId(Long departmentId);

}