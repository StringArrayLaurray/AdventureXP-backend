package org.example.adventurexp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int minAge;
    private int duration;
    private int minHeight;

    @OneToMany
    @JoinTable(
            name = "activity_instructor",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private List<Employee> instructors = new ArrayList<>();

    public Activity(){}
    public Activity(String name, int minAge, int duration, int minHeight) {
        this.name = name;
        this.minAge = minAge;
        this.duration = duration;
        this.minHeight = minHeight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public List<Employee> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Employee> instructors) {
        this.instructors = instructors;
    }
}
