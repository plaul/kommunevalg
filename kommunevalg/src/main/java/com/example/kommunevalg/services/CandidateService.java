package com.example.kommunevalg.services;

import com.example.kommunevalg.dto.CandidateDTO;
import com.example.kommunevalg.entity.Candidate;
import com.example.kommunevalg.entity.Party;
import com.example.kommunevalg.repositories.CandidateRepository;
import com.example.kommunevalg.repositories.PartyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {
  CandidateRepository candidateRepository;
  PartyRepository partyRepository;

  public CandidateService(CandidateRepository candidateRepository, PartyRepository partyRepository) {
    this.candidateRepository = candidateRepository;
    this.partyRepository = partyRepository;
  }

  public CandidateDTO addCandidate(CandidateDTO c){
    Candidate candidate = new Candidate(c.getName(),c.getCommune());
    Party party = partyRepository.findByLetter(c.getPartyLetter()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Party not found"));
    party.addCandidate(candidate);
    //partyRepository.save(party);
    candidateRepository.save(candidate);
    return new CandidateDTO(candidate);
  }

  public CandidateDTO findCandidate(int id){
    Candidate candidate =  candidateRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Candidate not found"));
    return new CandidateDTO(candidate);
  }

  public CandidateDTO editCandidate(CandidateDTO c,int id){
    Candidate candidate = candidateRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Candidate with provided id not found"));
    candidate.setCommune(c.getCommune());
    candidate.setName(c.getName());
    if(!c.getPartyLetter().equals(candidate.getParty().getLetter())){
      candidate.getParty().getCandidates().remove(candidate);
      Party newParty = partyRepository.findByLetter(c.getPartyLetter()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"No party found for given letter" ));
      newParty.addCandidate(candidate);
    }
    candidateRepository.save(candidate);
    return new CandidateDTO(candidate);
  }

  public List<CandidateDTO> getCandidates(String party){
    List<Candidate> candidates;
    if(party != null){
      candidates = candidateRepository.findCandidatesByParty_Letter(party);
    } else {
      candidates = candidateRepository.findAll();
    }
    return candidates.stream().map(CandidateDTO::new).collect(Collectors.toList());
    //return candidates.stream().map(c-> new CandidateDTO(c)).collect(Collectors.toList());
  }
}
