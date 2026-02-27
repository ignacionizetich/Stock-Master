package org.example.backend.Repository;

import org.example.backend.Models.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType, Long> {
}
