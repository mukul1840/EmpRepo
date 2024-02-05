package com.Ems.EmployeeManagementSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long e_id;

    @Column(nullable = false)
    private String e_name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "d_id")
    @JsonIgnoreProperties("employees")
    private Department department;
    public Employee(){

    }
    public Employee(Long e_id, String e_name, Department department, boolean activeStatus) {
        this.e_id = e_id;
        this.e_name = e_name;
        this.department = department;
        this.activeStatus = activeStatus;
    }
    @Column(nullable = false)
    private boolean activeStatus;

    public void setActiveStatus(boolean status){
        this.activeStatus=status;
    }
    public boolean isActiveStatus(){
        return this.activeStatus;
    }

}
