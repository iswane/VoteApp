package com.dtek.vote.voteapp.service;

import com.dtek.vote.voteapp.controller.VoterRequest;
import com.dtek.vote.voteapp.entities.Choice;
import com.dtek.vote.voteapp.entities.Elector;
import com.dtek.vote.voteapp.entities.Voter;
import com.dtek.vote.voteapp.entities.Votes;
import com.dtek.vote.voteapp.repositories.VoteRepository;
import com.dtek.vote.voteapp.repositories.VoterRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VoterServiceImpl implements VoterService {

    private final VoterRepository voterRepository;
    private final VoteRepository voteRepository;

    public VoterServiceImpl(VoterRepository voterRepository, VoteRepository voteRepository) {
        this.voterRepository = voterRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public Voter toVote(VoterRequest voterRequest) {
        Votes savedVotes = getVotes(voterRequest.choice());
        if (savedVotes.getId() == null) {
            throw new RuntimeException("Votes no saved !");
        }
        Voter voter = Voter(voterRequest);
        return voterRepository.save(voter);
    }

    private static Voter Voter(VoterRequest voterRequest) {
        return Voter.builder()
                .elector(
                        Elector.builder()
                                .firstName(voterRequest.firstName())
                                .lastName(voterRequest.lastName())
                                .address(voterRequest.address())
                                .dateBirth(voterRequest.dateBirth())
                                .nin(voterRequest.nin())
                                .toVote(true)
                                .build())
                .electionDate(LocalDate.now())
                .build();
    }

    private Votes getVotes(String choice) {
        Votes votes = Votes.builder()
                .choice(Choice.valueOf(choice))
                .build();
        return voteRepository.save(votes);
    }
}
