package com.example.kommunevalg.api;


import com.example.kommunevalg.dto.CandidateDTO;
import com.example.kommunevalg.entity.Candidate;
import com.example.kommunevalg.entity.Party;
import com.example.kommunevalg.repositories.CandidateRepository;
import com.example.kommunevalg.repositories.PartyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CandidateApiTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  CandidateRepository candidateRepository;

  @Autowired
  PartyRepository partyRepository;

  @Autowired
  ObjectMapper objectMapper;

  public Candidate c1;

  @BeforeEach
  public void setupCandidateData(){
    candidateRepository.deleteAll();
    partyRepository.deleteAll();
    Party partyA = new Party("Socialdemokratiet","A");

    c1 = new Candidate("Marcel Meijer","Samsø");
    Candidate c2 = new Candidate("Michael Kristensen","Samsø");
    partyA.addCandidate(c1);
    partyA.addCandidate(c2);
    partyRepository.save(partyA);
  }

  @Test
  void testFindCandidate() throws Exception {
    int id = c1.getId();
    mockMvc.perform(MockMvcRequestBuilders
            .get("/api/candidates/"+id)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(c1.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(c1.getName()));
  }

  @Test
  void testCandidateNotFound() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
            .get("/api/candidates/"+7834785)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }


  @Test
  void testGetCandidates() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
            .get("/api/candidates")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
            .andExpect(MockMvcResultMatchers.content().string(containsString("Marcel Meijer")));
  }
  @Test
  void testAddCandidate() throws Exception {
    CandidateDTO candidateNew = new CandidateDTO("AAA","BBB","A");
    mockMvc.perform(MockMvcRequestBuilders
            .post("/api/candidates")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(candidateNew)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    assertEquals(3,candidateRepository.count());
  }

}
