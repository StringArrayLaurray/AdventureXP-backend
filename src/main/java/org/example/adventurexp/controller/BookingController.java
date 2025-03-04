package org.example.adventurexp.controller;

import org.example.adventurexp.model.Booking;
import org.example.adventurexp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

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
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking){
        // System.out.println(booking.isActive());
        Booking savedBooking = bookingRepository.save(booking);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }
}
