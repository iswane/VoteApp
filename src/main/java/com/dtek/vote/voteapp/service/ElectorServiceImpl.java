package com.dtek.vote.voteapp.service;

import com.dtek.vote.voteapp.entities.Elector;
import com.dtek.vote.voteapp.repositories.ElectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectorServiceImpl implements ElectorService {

    private final ElectorRepository electorRepository;

    public ElectorServiceImpl(ElectorRepository electorRepository) {
        this.electorRepository = electorRepository;
    }

    public List<Elector> all() {
        return electorRepository.findAll();
    }

    @Override
    public Elector add(Elector elector) {
        elector.setToVote(false);
        return electorRepository.save(elector);
    }

    @Override
    public Elector get(String nin) {
        return electorRepository.findByNin(nin);
    }
}
