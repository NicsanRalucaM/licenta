package com.example.licenta.controller;

import com.example.licenta.entities.Choice;
import com.example.licenta.entities.Pair;
import com.example.licenta.models.Choices.AllChoices;
import com.example.licenta.models.Choices.ChoiceUpdated;
import com.example.licenta.models.Pairs.AllPairs;
import com.example.licenta.models.Pairs.PairAdded;
import com.example.licenta.models.Pairs.PairUpdated;
import com.example.licenta.models.Pairs.SinglePair;
import com.example.licenta.service.PairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/pair")
public class PairController {
    private final PairService pairService;

    @Autowired
    public PairController(PairService pairService) {
        this.pairService = pairService;
    }

    @GetMapping()
    public AllPairs all() {
        AllPairs result = pairService.findAll();
        return result;
    }

    @GetMapping("{id}")
    public SinglePair byId(@PathVariable Integer id) {
        SinglePair result = pairService.findById(id);
        return result;
    }

    @PostMapping("{create}")
    public PairAdded create(@RequestBody Pair pair) {
        PairAdded result = pairService.create(pair);
        return result;
    }

    @PutMapping("update/{id}")
    public PairUpdated update(@PathVariable Integer id, @RequestBody Pair pair) {
        PairUpdated result = pairService.update(id, pair);
        return result;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        pairService.deleteById(id);
    }

    @GetMapping("{question_id}/pairs")
    public AllPairs getQuestionPairs(@PathVariable Integer question_id) {
        if (pairService.checkQuestionExists(question_id)) {

            List<Pair> pairs = new ArrayList<>();
            for (Pair pair : pairService.findAll().getPairs()) {
                if (pair.getQuestion().equals(question_id)) {
                    pairs.add(pair);
                }
            }
            return new AllPairs(pairs, "", 200);
        }

        return new AllPairs(new ArrayList<>(), "Question has no pairs", 200);
    }


}