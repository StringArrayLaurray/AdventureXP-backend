package org.example.adventurexp.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    private LocalTime time;
    private boolean isBusinessBooking = false;

    @ElementCollection
    @CollectionTable(name = "booking_participants", joinColumns = @JoinColumn(name = "booking_id"))
    @Column(name = "participants")
    private List<String> participants = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "booking_activity",
            joinColumns = @JoinColumn(name = "booking_id"), // Foreign key for Booking
            inverseJoinColumns = @JoinColumn(name = "activity_id") // Foreign key for Activity
    )
    private List<Activity> activities = new ArrayList<>();

    public Booking(){}

    public Booking(LocalDate date, LocalTime time, boolean isBusinessBooking, List<String> participants, List<Activity> activities) {
        this.date = date;
        this.time = time;
        this.isBusinessBooking = isBusinessBooking;
        this.participants = participants;
        this.activities = activities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public boolean isBusinessBooking() {
        return isBusinessBooking;
    }

    public void setBusinessBooking(boolean businessBooking) {
        isBusinessBooking = businessBooking;
    }
}