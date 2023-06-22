package com.example.licenta.controller;


import com.example.licenta.entities.Answer;
import com.example.licenta.entities.Archive;
import com.example.licenta.models.Answers.AllAnswers;
import com.example.licenta.models.Answers.AnswerAdded;
import com.example.licenta.models.Answers.AnswerUpdated;
import com.example.licenta.models.Answers.SingleAnswer;
import com.example.licenta.models.Archives.AllArchives;
import com.example.licenta.service.AnswerService;
import com.example.licenta.service.ResultService;
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

    @GetMapping("{result_id}/answers")
    public AllAnswers getResultAnswers(@PathVariable Integer result_id) {
        if (answerService.checkResultExists(result_id)) {
            List<Answer> answers = new ArrayList<>();
            for (Answer answer : answerService.findAll().getAnswers()) {
                if (answer.getResult().equals(result_id)) {
                    answers.add(answer);
                }
            }
            return new AllAnswers(answers, "", 200);
        }

        return new AllAnswers(new ArrayList<>(), "Result has no Answers", 200);
    }


}