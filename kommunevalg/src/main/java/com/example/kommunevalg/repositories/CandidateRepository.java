package com.example.kommunevalg.repositories;

import com.example.kommunevalg.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
  List<Candidate> findCandidatesByParty_Letter(String letter);
}
