package org.example.adventurexp.controller;


import org.example.adventurexp.model.ShopItems;
import org.example.adventurexp.repository.ShopItemsRepository;
import org.example.adventurexp.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
@CrossOrigin(origins = "*")
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopItemsRepository shopItemsRepository;

    @GetMapping("/all")
    public List<ShopItems> getAllShopItems() {
        return shopItemsRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopItems> getShopItemsById(@PathVariable int id) {
        return shopItemsRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<ShopItems> createShopItem(@RequestBody ShopItems shopItem){
        return shopRepository.findById(1) // der er kun én shop ergo id 1
                .map(shop -> { //lambda funtion der tager shop som para og udfører blokken inde i turborgparenteserne
                    shopItem.setShop(shop);
                    shopItemsRepository.save(shopItem);
                    return ResponseEntity.ok(shopItem);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShopItems> updateShopItem(@PathVariable int id, @RequestBody ShopItems updatedShopItem){
        return shopItemsRepository.findById(id)
                .map(existingShopItem -> {
                    existingShopItem.setName(updatedShopItem.getName());
                    existingShopItem.setPrice(updatedShopItem.getPrice());
                    existingShopItem.setItemType(updatedShopItem.getItemType());
                    existingShopItem.setDescription(updatedShopItem.getDescription());
                    existingShopItem.setQuantity(updatedShopItem.getQuantity());
                    existingShopItem.setSize(updatedShopItem.getSize());
                    existingShopItem.setFlavor(updatedShopItem.getFlavor());
                    shopItemsRepository.save(existingShopItem);
                    return ResponseEntity.ok(existingShopItem);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        if (shopItemsRepository.existsById(id)) {
            shopItemsRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
