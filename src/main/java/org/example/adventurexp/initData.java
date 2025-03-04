package org.example.adventurexp;

import org.example.adventurexp.model.Activity;
import org.example.adventurexp.model.Booking;
import org.example.adventurexp.repository.ActivityRepository;
import org.example.adventurexp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class initData implements CommandLineRunner {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void run(String... args) throws Exception {
        Activity gokart = activityRepository.save(new Activity("Gokart", 12, 60, 160));
        Activity paintball = activityRepository.save(new Activity("Paintball", 15, 60, 0));
        Activity lasertag = activityRepository.save(new Activity("Lasertag", 0, 120, 0));
        Activity sumo = activityRepository.save(new Activity("Sumo wrestling", 10, 190, 130));
        Activity minigolf = activityRepository.save(new Activity("Minigolf", 0, 30, 0));
        Activity archery = activityRepository.save(new Activity("Archery", 8, 45, 100));

        ArrayList<Booking> bookingList = new ArrayList<>();
        Collections.addAll(bookingList,
                new Booking(LocalDate.of(2025, 3, 10), LocalTime.of(14, 30),
                        List.of("Alice", "Bob", "Charlie"),
                        List.of(paintball, minigolf)
                ),
                new Booking(LocalDate.of(2025, 3, 11), LocalTime.of(10, 15),
                        List.of("David", "Eve"),
                        List.of(gokart)
                ),
                new Booking(LocalDate.of(2025, 3, 12), LocalTime.of(16, 45),
                        List.of("Frank", "Grace", "Hannah", "Ian"),
                        List.of(sumo, paintball)
                ),
                new Booking(LocalDate.of(2025, 3, 13), LocalTime.of(9, 0),
                        List.of("Jack", "Karen"),
                        List.of(archery, gokart)
                ),
                new Booking(LocalDate.of(2025, 3, 14), LocalTime.of(18, 20),
                        List.of("Liam", "Mia", "Noah"),
                        List.of(minigolf, sumo, archery)
                )
        );
        bookingRepository.saveAll(bookingList);
    }
}

