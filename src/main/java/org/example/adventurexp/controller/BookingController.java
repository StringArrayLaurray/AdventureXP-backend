package org.example.adventurexp.controller;

import org.example.adventurexp.model.Activity;
import org.example.adventurexp.model.Booking;
import org.example.adventurexp.repository.ActivityRepository;
import org.example.adventurexp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping("/all")
    public List<Booking> getALlBookings(){
        return bookingRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getById(@PathVariable int id){
        return bookingRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        System.out.println("📥 Modtager booking request: " + booking);

        List<Activity> matchedActivities = new ArrayList<>();


        for (Activity a : booking.getActivities()) {
            Activity foundActivity = activityRepository.findByNameIgnoreCase(a.getName());
            if (foundActivity != null) {
                matchedActivities.add(foundActivity);
            }
        }


        if (matchedActivities.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Fejl: Ingen gyldige aktiviteter fundet.");
        }


        booking.setActivities(matchedActivities);

        Booking savedBooking = bookingRepository.save(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }



    // deletemapping
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Booking> deleteById(@PathVariable int id){
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // updatemapping
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateActivity(@PathVariable int id, @RequestBody Booking updatedBooking) {
        return bookingRepository.findById(id)
                .map(existingActivity -> {
                    existingActivity.setDate(updatedBooking.getDate());
                    existingActivity.setTime(updatedBooking.getTime());
                    existingActivity.setBusinessBooking(updatedBooking.isBusinessBooking());
                    existingActivity.setParticipants(updatedBooking.getParticipants());
                    existingActivity.setActivities(updatedBooking.getActivities());

                    bookingRepository.save(existingActivity);
                    return ResponseEntity.ok(existingActivity);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
