package org.example.adventurexp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private int shopId;
    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    @JsonBackReference
    private Set<ShopItems> shopItems = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public Set<ShopItems> getShopItems() {
        return shopItems;
    }

    public void setShopItems(Set<ShopItems> shopItems) {
        this.shopItems = shopItems;
    }
}



