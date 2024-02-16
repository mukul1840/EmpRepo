package com.Ems.EmployeeManagementSystem.repositories;

import com.Ems.EmployeeManagementSystem.entities.Department;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@Primary
public interface DepartmentRepository extends BaseRepository<Department, Long> {
    Optional<Department> findByName(String dName);

}