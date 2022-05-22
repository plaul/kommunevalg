package com.example.kommunevalg.configuration;


import com.example.kommunevalg.entity.Candidate;
import com.example.kommunevalg.entity.Party;
import com.example.kommunevalg.repositories.CandidateRepository;
import com.example.kommunevalg.repositories.PartyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

@Controller
@Profile("!test")
public class MakeTestData implements CommandLineRunner {

  CandidateRepository candidateRepository;
  PartyRepository partyRepository;

  public MakeTestData(CandidateRepository candidateRepository, PartyRepository partyRepository) {
    this.candidateRepository = candidateRepository;
    this.partyRepository = partyRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    Party partyA = new Party("Socialdemokratiet","A");
    Party partyC = new Party("Det Konservative Folkeparti","C");

    Candidate c1 = new Candidate("Marcel Meijer","Samsø");
    Candidate c2 = new Candidate("Michael Kristensen","Samsø");
    partyA.addCandidate(c1);
    partyA.addCandidate(c2);

    Candidate c3 = new Candidate("Per Urban Olsen","Samsø");
    Candidate c4 = new Candidate("Peter Askjær","Samsø");
    partyC.addCandidate(c3);
    partyC.addCandidate(c4);

    partyRepository.save(partyA);
    partyRepository.save(partyC);





  }
}
