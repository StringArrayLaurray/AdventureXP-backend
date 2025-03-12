package org.example.adventurexp.controller;

import org.example.adventurexp.model.Activity;
import org.example.adventurexp.model.Booking;
import org.example.adventurexp.repository.ActivityRepository;
import org.example.adventurexp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("activity")
@CrossOrigin(origins = "*")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/all")
    public List<Activity> getALlActivities(){
        return activityRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getById(@PathVariable int id){
        return activityRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity){
//        System.out.println(student.isActive());
        Activity savedActivity = activityRepository.save(activity);
        return new ResponseEntity<>(savedActivity, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        if (!activityRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        List<Booking> bookings = bookingRepository.findByActivities_Id(id);

        for (Booking booking : bookings) {
            booking.getActivities().removeIf(activity -> activity.getId() == id);
            bookingRepository.save(booking);
        }

        activityRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // lav update færdig
    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable int id, @RequestBody Activity updatedActivity) {
        return activityRepository.findById(id)
                .map(existingActivity -> {
                    existingActivity.setName(updatedActivity.getName());
                    existingActivity.setDescription(updatedActivity.getDescription());
                    existingActivity.setMinAge(updatedActivity.getMinAge());
                    existingActivity.setDuration(updatedActivity.getDuration());
                    existingActivity.setMinHeight(updatedActivity.getMinHeight());
                    existingActivity.setInstructors(updatedActivity.getInstructors());

                    activityRepository.save(existingActivity);
                    return ResponseEntity.ok(existingActivity);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
