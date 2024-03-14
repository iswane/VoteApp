package com.dtek.vote.voteapp.controller;

import com.dtek.vote.voteapp.entities.Elector;
import com.dtek.vote.voteapp.service.ElectorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ElectorController.class)
class ElectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ElectorService electorService;

    private final String urlTemplate = "/api/v1/elector";

    private static Elector getElector() {
        return Elector.builder()
                .firstName("Emmanuel")
                .lastName("MACRON")
                .address("Quai dorsay")
                .dateBirth("12/12/1992")
                .nin("123456789")
                .build();
    }

    private static String getContent() {
        return """
                    {
                        "fistName": "Emmanuel",
                        "lastName": "MACRON",
                        "address": "Quai dorsay",
                        "dateBirth": "12/12/1992",
                        "nin": "123456789"
                    }
                """;
    }

    @Test
    void getAllElectorShouldReturnAll() throws Exception {
        List<Elector> electors = List.of(getElector());
        given(electorService.all()).willReturn(electors);

        //formatter:off
        mockMvc.perform(get(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", equalTo("Emmanuel")))
                .andExpect(jsonPath("$[0].lastName", equalTo("MACRON")));

    }

    @Test
    void addElectorShouldReturnElector() throws Exception {
        Elector elector = getElector();
        given(electorService.add(any(Elector.class))).willReturn(elector);

        mockMvc.perform(post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getContent()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Emmanuel"))
                .andExpect(jsonPath("$.lastName").value("MACRON"));
    }

    @Test
    void getElectorByNinShouldReturnElector() throws Exception {
        Elector elector = getElector();
        given(electorService.get(anyString())).willReturn(elector);

        mockMvc.perform(get(urlTemplate + "/{nin}", "123456789")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Emmanuel"))
                .andExpect(jsonPath("$.lastName").value("MACRON"));
    }
    //formatter:on
}
