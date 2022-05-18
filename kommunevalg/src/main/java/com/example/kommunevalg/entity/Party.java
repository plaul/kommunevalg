package com.example.kommunevalg.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Party {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  String name;
  String letter;

  public Party(String name, String letter) {
    this.name = name;
    this.letter = letter;
  }

  @OneToMany(mappedBy = "party", cascade = CascadeType.PERSIST)
  Set<Candidate> candidates = new HashSet<>();

  public void addCandidate(Candidate candidate){
    candidates.add(candidate);
    candidate.setParty(this);
  }

}
