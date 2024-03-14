package com.dtek.vote.voteapp.service;

import com.dtek.vote.voteapp.controller.VoterRequest;
import com.dtek.vote.voteapp.entities.Choice;
import com.dtek.vote.voteapp.entities.Elector;
import com.dtek.vote.voteapp.entities.Voter;
import com.dtek.vote.voteapp.entities.Votes;
import com.dtek.vote.voteapp.repositories.VoteRepository;
import com.dtek.vote.voteapp.repositories.VoterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class VoterServiceTest {

    @Mock
    private VoterRepository repository;
    @Mock
    private VoteRepository voteRepository;
    @InjectMocks
    private VoterServiceImpl voterService;

    private static Elector getElector() {
        return Elector.builder()
                .firstName("Emmanuel")
                .lastName("MACRON")
                .address("Quai Dorsay")
                .dateBirth("12/12/1992")
                .nin("123456789")
                .toVote(true)
                .build();
    }

    @Test
    void toVoteWhileElectorShouldMarkVoter() {
        // given
        Voter voter = Voter.builder()
                .elector(getElector())
                .electionDate(LocalDate.now())
                .build();
        Votes votes = Votes.builder()
                .id(1L)
                .choice(Choice.MELANCHON)
                .build();
        given(repository.save(any(Voter.class))).willReturn(voter);
        given(voteRepository.save(any(Votes.class))).willReturn(votes);
        // when
        Voter vote = voterService.toVote(voterRequest());
        // then
        assertTrue(voter.getElector().isToVote());
        assertEquals(vote.getElector().getFirstName(), voter.getElector().getFirstName());
    }

    private static VoterRequest voterRequest() {
        return new VoterRequest("Emmanuel",
                "MACRON",
                "Quai dorsay",
                "123456789",
                "12/12/1990",
                true,
                "MELANCHON"
                );
    }
}
