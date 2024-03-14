package com.dtek.vote.voteapp.repositories;

import com.dtek.vote.voteapp.entities.Elector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ElectorRepository extends JpaRepository<Elector, Long> {
    Elector findByNin(String nin);
}
