package com.example.kommunevalg.repositories;

import com.example.kommunevalg.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartyRepository extends JpaRepository<Party,Integer> {

  Optional<Party> findByLetter(String letter);
}
