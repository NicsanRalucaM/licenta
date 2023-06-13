package com.example.licenta.controller;


import com.example.licenta.entities.Answer;
import com.example.licenta.models.Answers.AllAnswers;
import com.example.licenta.models.Answers.AnswerAdded;
import com.example.licenta.models.Answers.AnswerUpdated;
import com.example.licenta.models.Answers.SingleAnswer;
import com.example.licenta.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/answer")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping()
    public AllAnswers all() {
        AllAnswers result = answerService.findAll();
        return result;
    }

    @GetMapping("{id}")
    public SingleAnswer byId(@PathVariable Integer id) {

        SingleAnswer result = answerService.findById(id);
        return result;
    }

    @PostMapping("{create}")
    public AnswerAdded create(@RequestBody Answer answer) {
        AnswerAdded result = answerService.create(answer);
        return result;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        answerService.deleteById(id);
    }

    @PutMapping("update/{id}")
    public AnswerUpdated update(@PathVariable Integer id, @RequestBody Answer answer) {
        AnswerUpdated result = answerService.update(id, answer);
        return result;
    }


}