package com.dtek.vote.voteapp.controller;

import com.dtek.vote.voteapp.entities.Voter;
import com.dtek.vote.voteapp.service.VoterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/voter")
public class VoterController {
    private final VoterService voterService;

    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @PostMapping
    public ResponseEntity<Voter> toVote(@RequestBody VoterRequest voter) {
        Voter vote = voterService.toVote(voter);
        return new ResponseEntity<>(vote, HttpStatus.CREATED);
    }
}
