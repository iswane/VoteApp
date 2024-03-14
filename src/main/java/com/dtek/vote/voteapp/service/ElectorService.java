package com.dtek.vote.voteapp.service;

import com.dtek.vote.voteapp.entities.Elector;

import java.util.List;

public interface ElectorService {

    List<Elector> all();

    Elector add(Elector elector);

    Elector get(String nin);
}
