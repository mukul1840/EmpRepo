package com.Ems.EmployeeManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
@NoRepositoryBean

public interface BaseRepository<T, Long> extends JpaRepository<T,Long> {

}
