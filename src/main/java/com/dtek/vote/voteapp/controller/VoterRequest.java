package com.dtek.vote.voteapp.controller;

public record VoterRequest(String firstName,
                           String lastName,
                           String address,
                           String dateBirth,
                           String nin,
                           boolean toVote,
                           String choice) { }