package com.example.licenta.service;

import com.example.licenta.entities.Choice;
import com.example.licenta.models.Choices.AllChoices;
import com.example.licenta.models.Choices.ChoiceAdded;
import com.example.licenta.models.Choices.ChoiceUpdated;
import com.example.licenta.models.Choices.SingleChoice;
import com.example.licenta.repository.ChoiceRepository;
import com.example.licenta.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class ChoiceService {

    private final ChoiceRepository choiceRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public ChoiceService(ChoiceRepository choiceRepository,QuestionRepository questionRepository) {
        this.choiceRepository = choiceRepository;
        this.questionRepository = questionRepository;
    }

    public AllChoices findAll() {
        var it = choiceRepository.findAll();

        if (it.isEmpty()) {
            return new AllChoices(new ArrayList<Choice>(), "", 204);
        }
        var choices = new ArrayList<Choice>();
        it.forEach(e -> choices.add(e));

        return new AllChoices(choices, "", 200);
    }

    public SingleChoice findById(Integer id) {

        var result = choiceRepository.findById(id);

        if (result.isEmpty()) {
            return new SingleChoice(null, "Choice not found", 404);
        }
        SingleChoice choice = new SingleChoice(result.get(), "", 200);

        return choice;
    }

    public ChoiceAdded create(Choice choice) {
        ChoiceAdded choiceAdded = new ChoiceAdded();
        var result = choiceRepository.save(choice);

        if (result == null) {
            choiceAdded.setError("Choice already Exists");
            choiceAdded.setStatusCode(403);
            return choiceAdded;
        } else {
            choiceAdded.setChoice(choice);
            choiceAdded.setStatusCode(200);
            return choiceAdded;
        }
    }

    public Long count() {

        return choiceRepository.count();
    }


    public void deleteById(Integer choiceId) {
        choiceRepository.deleteById(choiceId);
    }
    public boolean checkQuestionExists(Integer id) {
        return questionRepository.findById(id).isPresent();
    }

}
