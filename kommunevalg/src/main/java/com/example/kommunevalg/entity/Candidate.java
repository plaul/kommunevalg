package com.example.kommunevalg.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Candidate {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  String name;
  String Commune;

  public Candidate(String name, String commune) {
    this.name = name;
    Commune = commune;
  }

  @ManyToOne()
  @JoinColumn(name = "party_id")
  private Party party;

}
