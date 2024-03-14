package com.dtek.vote.voteapp.repositories;

import com.dtek.vote.voteapp.entities.Votes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Votes, Long> {
}
