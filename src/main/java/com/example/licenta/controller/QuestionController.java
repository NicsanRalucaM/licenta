package com.example.licenta.controller;


import com.example.licenta.entities.Question;
import com.example.licenta.models.Questions.AllQuestions;
import com.example.licenta.models.Questions.QuestionUpdated;
import com.example.licenta.models.Questions.SingleQuestion;
import com.example.licenta.service.QuestionService;
import com.example.licenta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path="/question")
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

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        questionService.deleteById(id);
    }

    @PutMapping("{id}")
    public QuestionUpdated update(@PathVariable Integer id, @RequestBody Question question) {
        QuestionUpdated result = questionService.update(id, question);
        return result;
    }

}