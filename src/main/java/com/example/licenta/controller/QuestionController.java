package com.example.licenta.controller;


import com.example.licenta.entities.Question;
import com.example.licenta.entities.Test;
import com.example.licenta.models.Questions.AllQuestions;
import com.example.licenta.models.Questions.QuestionAdded;
import com.example.licenta.models.Questions.QuestionUpdated;
import com.example.licenta.models.Questions.SingleQuestion;
import com.example.licenta.models.Tests.TestAdded;
import com.example.licenta.service.QuestionService;
import com.example.licenta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping()
    public AllQuestions all() {
        AllQuestions result = questionService.findAll();
        return result;
    }

    @GetMapping("{id}")
    public SingleQuestion byId(@PathVariable Integer id) {

        SingleQuestion result = questionService.findById(id);
        return result;
    }

    @PostMapping("{create}")
    public QuestionAdded create(@RequestBody Question question) {
        QuestionAdded result = questionService.create(question);
        return result;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        questionService.deleteById(id);
    }

    @PutMapping("update/{id}")
    public QuestionUpdated update(@PathVariable Integer id, @RequestBody Question question) {
        QuestionUpdated result = questionService.update(id, question);
        return result;
    }

    @GetMapping("{test_id}/questions")
    public AllQuestions getTestQuestion(@PathVariable Integer test_id) {
        if (questionService.checkTestExists(test_id)) {

            List<Question> questions = new ArrayList<>();
            for (Question question : questionService.findAll().getQuestions()) {
                if (question.getTest().equals(test_id)) {
                    questions.add(question);
                }
            }
            return new AllQuestions(questions, "", 200);
        }

        return new AllQuestions(new ArrayList<>(), "Test has no Question", 200);
    }

}