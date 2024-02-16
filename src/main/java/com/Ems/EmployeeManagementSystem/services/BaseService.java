package com.Ems.EmployeeManagementSystem.services;


import com.Ems.EmployeeManagementSystem.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BaseService<T>{
    @Autowired
    private BaseRepository<T,Long> repository;

    public List<T> getAllResources() {
        return repository.findAll();
    }
    public T createResource(T entity) {
        return repository.save(entity);
    }

    public Optional<T> getResourceById(Long id) {
        return repository.findById(id);
    }

}
