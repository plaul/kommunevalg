package com.example.kommunevalg.dto;

import com.example.kommunevalg.entity.Candidate;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateDTO {
  int id;
  String name;
  String commune;
  String partyLetter;


  public CandidateDTO(int id, String name, String commune,String partyLetter) {
    this.id = id;
    this.name = name;
    this.commune = commune;
    this.partyLetter = partyLetter;
  }

  public CandidateDTO(String name, String commune,String partyLetter) {
    this.name = name;
    this.commune = commune;
    this.partyLetter = partyLetter;
  }

  public CandidateDTO(Candidate c){
    this.id = c.getId();
    this.name = c.getName();
    this.commune = c.getCommune();
    this.partyLetter = c.getParty().getLetter();
  }
}
