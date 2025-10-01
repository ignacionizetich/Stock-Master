package org.example.backend.Repository;

import org.example.backend.Models.BillType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BillTypeRepository extends JpaRepository<BillType, Long> {
}
