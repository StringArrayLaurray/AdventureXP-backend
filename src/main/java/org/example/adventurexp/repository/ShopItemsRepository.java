package org.example.adventurexp.repository;

import org.example.adventurexp.model.ShopItems;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShopItemsRepository extends JpaRepository<ShopItems, Integer> {
    }

