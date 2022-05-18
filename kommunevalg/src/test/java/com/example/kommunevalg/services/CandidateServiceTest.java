package com.example.kommunevalg.services;

import com.example.kommunevalg.dto.CandidateDTO;
import com.example.kommunevalg.entity.Candidate;
import com.example.kommunevalg.entity.Party;
import com.example.kommunevalg.repositories.CandidateRepository;
import com.example.kommunevalg.repositories.PartyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;



@DataJpaTest
class CandidateServiceTest {

  @Autowired
  PartyRepository partyRepository;

  @Autowired
  CandidateRepository candidateRepository;

  CandidateService service;

  @BeforeEach
  void setUp() {
    Party partyA = new Party("Socialdemokratiet","A");
    Candidate c1 = new Candidate("Marcel Meijer","Samsø");
    Candidate c2 = new Candidate("Michael Kristensen","Samsø");
    partyA.addCandidate(c1);
    partyA.addCandidate(c2);
    partyRepository.save(partyA);
    service = new CandidateService(candidateRepository,partyRepository);
  }

  @Test
  void addCandidate() {
    CandidateDTO dto = new CandidateDTO("xxx","Samsø","A");
    CandidateDTO newCandidate = service.addCandidate(dto);
    System.out.println("-----> "+newCandidate.getId());
    assertTrue(newCandidate.getId()>0);
  }
}