package org.example.adventurexp.model;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "instructors")
    List<Activity> activitiesToInstruct = new ArrayList<>();

    public Instructor(){}

    public Instructor(String name) {
        this.name = name;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Activity> getActivitiesToInstruct() {
        return activitiesToInstruct;
    }

    public void setActivitiesToInstruct(List<Activity> activities) {
        this.activitiesToInstruct = activities;
    }
}
