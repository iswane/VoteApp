package com.dtek.vote.voteapp.repositories;

import com.dtek.vote.voteapp.entities.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {
}
