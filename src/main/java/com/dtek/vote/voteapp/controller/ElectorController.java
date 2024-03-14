package com.dtek.vote.voteapp.controller;

import com.dtek.vote.voteapp.entities.Elector;
import com.dtek.vote.voteapp.service.ElectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/elector")
public class ElectorController {

    private final ElectorService electorService;

    public ElectorController(ElectorService electorService) {
        this.electorService = electorService;
    }

    @GetMapping
    public ResponseEntity<List<Elector>> all() {
        return new ResponseEntity<>(electorService.all(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Elector> add(@RequestBody Elector elector) {
        return new ResponseEntity<>(electorService.add(elector), HttpStatus.CREATED);
    }

    @GetMapping("/{nin}")
    public ResponseEntity<Elector> get(@PathVariable String nin) {
        return new ResponseEntity<>(electorService.get(nin), HttpStatus.OK);
    }
}
