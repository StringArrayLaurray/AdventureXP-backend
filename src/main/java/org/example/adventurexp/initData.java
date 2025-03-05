package org.example.adventurexp;

import org.example.adventurexp.model.*;
import org.example.adventurexp.repository.*;
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
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public void run(String... args) throws Exception {
        Instructor instructor1 = new Instructor("Johan");
        Instructor instructor2 = new Instructor("Marie");
        Instructor instructor3 = new Instructor("Tessa");

        Activity gokart = new Activity("Gokart", 12, 60, 160);
        Activity paintball = new Activity("Paintball", 15, 60, 0);
        Activity lasertag = new Activity("Lasertag", 0, 120, 0);
        Activity sumo = new Activity("Sumo wrestling", 10, 190, 130);
        Activity minigolf = new Activity("Minigolf", 0, 30, 0);
        Activity archery = new Activity("Archery", 8, 45, 100);

        instructor1.setActivitiesToInstruct(List.of(gokart, sumo));
        instructor2.setActivitiesToInstruct(List.of(paintball, minigolf));
        instructor3.setActivitiesToInstruct(List.of(archery, lasertag, paintball));

        gokart.setInstructors(List.of(instructor1));
        paintball.setInstructors(List.of(instructor2));
        lasertag.setInstructors(List.of(instructor3));
        sumo.setInstructors(List.of(instructor1));
        minigolf.setInstructors(List.of(instructor2));
        archery.setInstructors(List.of(instructor3));

        instructorRepository.save(instructor1);
        instructorRepository.save(instructor2);
        instructorRepository.save(instructor3);

        activityRepository.save(gokart);
        activityRepository.save(paintball);
        activityRepository.save(lasertag);
        activityRepository.save(sumo);
        activityRepository.save(minigolf);
        activityRepository.save(archery);


        ArrayList<Booking> bookingList = new ArrayList<>();
        Collections.addAll(bookingList,
                new Booking(LocalDate.of(2025, 3, 10), LocalTime.of(14, 30),
                        true,
                        List.of("Alice", "Bob", "Charlie"),
                        List.of(paintball, minigolf)
                ),
                new Booking(LocalDate.of(2025, 3, 11), LocalTime.of(10, 15),
                        false,
                        List.of("David", "Eve"),
                        List.of(gokart)
                ),
                new Booking(LocalDate.of(2025, 3, 12), LocalTime.of(16, 45),
                        true,
                        List.of("Frank", "Grace", "Hannah", "Ian"),
                        List.of(sumo, paintball)
                ),
                new Booking(LocalDate.of(2025, 3, 13), LocalTime.of(9, 0),
                        true,
                        List.of("Jack", "Karen"),
                        List.of(archery, gokart)
                ),
                new Booking(LocalDate.of(2025, 3, 14), LocalTime.of(18, 20),
                        true,
                        List.of("Liam", "Mia", "Noah"),
                        List.of(minigolf, sumo, archery)
                )
        );
        bookingRepository.saveAll(bookingList);


        employeeRepository.save(new Employee("Jimmy Carter"));
        employeeRepository.save(new Employee("Appelsina"));
        employeeRepository.save(new Employee("Viski"));
        employeeRepository.save(new Employee("Michael Jackson"));
        employeeRepository.save(new Employee("David Bowie"));
        employeeRepository.save(new Employee("Tessa Okay"));
        employeeRepository.save(new Employee("Peter Parker"));
        employeeRepository.save(new Employee("Bella Swan"));

        adminRepository.save(new Admin("admin1", "password123"));
        adminRepository.save(new Admin("admin2", "123password"));



}

}
