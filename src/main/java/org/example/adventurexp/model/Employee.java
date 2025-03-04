package com.example.adventurexp.model;

import jakarta.persistence.*;
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;

    public Employee(){}

    public Employee(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getID() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;

    }
}
