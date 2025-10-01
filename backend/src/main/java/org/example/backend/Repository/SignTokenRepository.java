package org.example.backend.Repository;

import org.example.backend.Models.SignToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SignTokenRepository extends JpaRepository<SignToken, Long> {

}
