package com.example.licenta.controller;


import com.example.licenta.entities.Choice;
import com.example.licenta.entities.Question;
import com.example.licenta.models.Choices.AllChoices;
import com.example.licenta.models.Choices.ChoiceAdded;
import com.example.licenta.models.Choices.SingleChoice;
import com.example.licenta.models.Questions.AllQuestions;
import com.example.licenta.service.ChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/choice")
public class ChoiceController {
    private final ChoiceService choiceService;

    @Autowired
    public ChoiceController(ChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @GetMapping()
    public AllChoices all() {
        AllChoices result = choiceService.findAll();
        return result;
    }

    @GetMapping("{id}")
    public SingleChoice byId(@PathVariable Integer id) {
        SingleChoice result = choiceService.findById(id);
        return result;
    }

    @PostMapping("{create}")
    public ChoiceAdded create(@RequestBody Choice choice) {
        ChoiceAdded result = choiceService.create(choice);
        return result;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        choiceService.deleteById(id);
    }

    @GetMapping("{question_id}/choices")
    public AllChoices getQuestionChoices(@PathVariable Integer question_id) {
        if (choiceService.checkQuestionExists(question_id)) {

            List<Choice> choices = new ArrayList<>();
            for (Choice choice : choiceService.findAll().getChoices()) {
                if (choice.getQuestion().equals(question_id)) {
                    choices.add(choice);
                }
            }
            return new AllChoices(choices, "", 200);
        }

        return new AllChoices(new ArrayList<>(), "Question has no choice", 200);
    }


}