package com.dtek.vote.voteapp.service;

import com.dtek.vote.voteapp.controller.VoterRequest;
import com.dtek.vote.voteapp.entities.Voter;

public interface VoterService {
    Voter toVote(VoterRequest voterRequest);
}
