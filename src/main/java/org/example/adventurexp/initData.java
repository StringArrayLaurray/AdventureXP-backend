package org.example.adventurexp;

import org.example.adventurexp.model.*;
import org.example.adventurexp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

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
    private ShopRepository shopRepository;

    @Autowired
    private ShopItemsRepository shopItemsRepository;

    @Override
    public void run(String... args) throws Exception {

        // Tilføj description i constructor of initData før aflevering
        Activity gokart = new Activity("Gokart", 12, 60, 160);
        Activity paintball = new Activity("Paintball", 15, 60, 0);
        Activity lasertag = new Activity("Lasertag", 0, 120, 0);
        Activity sumo = new Activity("Sumo wrestling", 10, 190, 130);
        Activity minigolf = new Activity("Minigolf", 0, 30, 0);
        Activity archery = new Activity("Archery", 8, 45, 100);

        gokart.setDescription("some desciption about gokart. wroomwroom - very fast");

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


        //Shop
        Shop shop = new Shop();
        shop.setName("AdventureXP Shop");
        shop.setDescription("I denne shop kan du købe alt fra t-shirts til slik!");

        ShopItems tShirt = new ShopItems();
        tShirt.setName("AdventureXP merch T-shirt");
        tShirt.setPrice(150.00);
        tShirt.setItemType("Clothing");
        tShirt.setDescription("Lavet af 100% bomuld og 100% vinder");
        tShirt.setQuantity(50);
        tShirt.setSize("Unisex");
        tShirt.setFlavor(null);

        ShopItems soda = new ShopItems();
        soda.setName("Coca Cola");
        soda.setPrice(25.00);
        soda.setItemType("Beverage");
        soda.setDescription("Forfriskende læskedrik");
        soda.setQuantity(200);
        soda.setSize("330ml");
        soda.setFlavor("Coca Cola");

        ShopItems candy = new ShopItems();
        candy.setName("Click Mix");
        candy.setPrice(45.00);
        candy.setItemType("Candy");
        candy.setDescription("Alles favorit!");
        candy.setQuantity(200);
        candy.setSize(null);
        candy.setFlavor("Sweet and sour");


        //Tilføjer shopItems til shop
        Set<ShopItems> items = new HashSet<>();
        items.add(tShirt);
        items.add(soda);
        items.add(candy);

        shop.setShopItems(items);

        shopRepository.save(shop);

}

}
