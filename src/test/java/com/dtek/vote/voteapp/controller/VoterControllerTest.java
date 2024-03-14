package com.dtek.vote.voteapp.controller;

import com.dtek.vote.voteapp.entities.Elector;
import com.dtek.vote.voteapp.entities.Voter;
import com.dtek.vote.voteapp.service.VoterServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VoterController.class)
class VoterControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VoterServiceImpl voterService;

    private final String urlTemplate = "/api/v1/voter";

    private static String getContent() {
        return """
                    {
                        "id": 1L,
                        "fistName": "Emmanuel",
                        "lastName": "MACRON",
                        "address": "Quai dorsay",
                        "dateBirth": "12/12/1992",
                        "nin": "123456789",
                        "toVote": true,
                        "electionDate": 2017-01-13
                    }
                """;
    }

    private static Elector getElector() {
        return Elector.builder()
                .firstName("Emmanuel")
                .lastName("MACRON")
                .address("Quai dorsay")
                .dateBirth("12/12/1992")
                .nin("123456789")
                .build();
    }

    @Disabled
    @Test
    void toVoteWhileElectorShouldMarkVoter() throws Exception {
        Voter voter = Voter.builder()
                .id(1L)
                .elector(getElector())
                .electionDate(LocalDate.now())
                .build();
        given(voterService.toVote(any(VoterRequest.class))).willReturn(voter);

        mockMvc.perform(post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON).
                        content(getContent()))
                .andExpect(status().isCreated());
    }
}
