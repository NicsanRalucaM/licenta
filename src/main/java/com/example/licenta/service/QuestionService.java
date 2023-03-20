package com.example.licenta.service;


import com.example.licenta.entities.Question;
import com.example.licenta.models.Questions.AllQuestions;
import com.example.licenta.models.Questions.QuestionUpdated;
import com.example.licenta.models.Questions.SingleQuestion;
import com.example.licenta.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {

        this.questionRepository = questionRepository;
    }

    public AllQuestions findAll() {
        var it = questionRepository.findAll();

        if (it.isEmpty()) {
            return new AllQuestions(new ArrayList<Question>(), "", 204);
        }
        var questions = new ArrayList<Question>();
        it.forEach(e -> questions.add(e));

        return new AllQuestions(questions, "", 200);
    }

    public SingleQuestion findById(Integer id) {

        var result = questionRepository.findById(id);

        if (result.isEmpty()) {
            return new SingleQuestion(null, "User not found", 404);
        }
        SingleQuestion question = new SingleQuestion(result.get(), "", 200);

        return question;
    }

    public Long count() {

        return questionRepository.count();
    }

    public void deleteById(Integer questionId) {
        questionRepository.deleteById(questionId);
    }

    public QuestionUpdated update(Integer id, Question question) {
        QuestionUpdated questionUpdated = new QuestionUpdated();
        Optional<Question> result = questionRepository.findById(id);

        if (result.isEmpty()) {
            questionUpdated.setError("User not found");
            questionUpdated.setStatusCode(404);

            return questionUpdated;
        } else {
            Question entity = result.get();
            Integer identifier = entity.getId();
            entity = question;
            entity.setId(identifier);

            questionUpdated.setQuestion(questionRepository.save(entity));
            questionUpdated.setStatusCode(202);

            return questionUpdated;
        }
    }
}
