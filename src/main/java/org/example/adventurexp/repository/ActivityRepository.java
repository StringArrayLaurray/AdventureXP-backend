package org.example.adventurexp.repository;

import org.example.adventurexp.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    Activity findByNameIgnoreCase(String name);
}
