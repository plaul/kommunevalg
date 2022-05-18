package com.example.kommunevalg.api;

import com.example.kommunevalg.dto.CandidateDTO;
import com.example.kommunevalg.services.CandidateService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
@RequestMapping("api/candidates")
public class CandidateController {

  CandidateService candidateService;

  public CandidateController(CandidateService candidateService) {
    this.candidateService = candidateService;
  }

  @PostMapping
  public CandidateDTO addPerson(@RequestBody CandidateDTO c ){
    return candidateService.addCandidate(c);
  }
  @PutMapping("/{id}")
  public CandidateDTO editPerson(@RequestBody CandidateDTO c,@PathVariable int id ){
    return candidateService.editCandidate(c,id);
  }

  @GetMapping()
  public List<CandidateDTO> getCandidates(@RequestParam(value = "party", required = false) String party){
    return candidateService.getCandidates(party);
  }
}
