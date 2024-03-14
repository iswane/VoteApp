package com.dtek.vote.voteapp.service;

import com.dtek.vote.voteapp.entities.Elector;
import com.dtek.vote.voteapp.repositories.ElectorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ElectorServiceTest {

    @Mock
    private ElectorRepository repository;

    @InjectMocks
    private ElectorServiceImpl electorService;


    @Test
    void getAllElectorShouldReturnAll() {
        // given
        Elector elector = getElector();
        List<Elector> electorList = Arrays.asList(elector);
        given(repository.findAll()).willReturn(electorList);
        // when
        List<Elector> all = electorService.all();
        // then
        assertTrue(all.contains(elector));
    }

    @Test
    void addElectorShouldReturnElector() {
        // given
        Elector elector = getElector();
        given(repository.save(any(Elector.class))).willReturn(elector);
        // when
        Elector electorAdded = electorService.add(elector);
        // then
        assertEquals(elector.getFirstName(), electorAdded.getFirstName());
    }

    @Test
    void getElectorByNinShouldReturnElector() {
        // given
        Elector elector = getElector();
        given(repository.findByNin(anyString())).willReturn(elector);
        // when
        Elector electorAdded = electorService.get(elector.getNin());
        // then
        assertEquals(elector.getFirstName(), electorAdded.getFirstName());
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
}
