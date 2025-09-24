package org.example.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.backend.Models.BillType;

import java.util.List;
import java.util.Optional;


@Repository
public interface BillTypeRepository extends JpaRepository<BillType, Long> {
}
