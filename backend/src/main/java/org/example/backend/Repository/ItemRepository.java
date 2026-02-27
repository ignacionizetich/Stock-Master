package org.example.backend.Repository;


import java.util.Optional;

import org.example.backend.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByItemName(String itemName);
}
