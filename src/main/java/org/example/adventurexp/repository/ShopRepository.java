package org.example.adventurexp.repository;

import org.example.adventurexp.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    }

