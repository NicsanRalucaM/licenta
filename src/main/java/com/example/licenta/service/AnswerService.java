package com.example.licenta.service;


import com.example.licenta.entities.Answer;
import com.example.licenta.entities.Choice;
import com.example.licenta.entities.Question;
import com.example.licenta.entities.Result;
import com.example.licenta.models.Answers.AllAnswers;
import com.example.licenta.models.Answers.AnswerAdded;
import com.example.licenta.models.Answers.AnswerUpdated;
import com.example.licenta.models.Answers.SingleAnswer;
import com.example.licenta.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final ArchiveRepository archiveRepository;
    private final ResultRepository resultRepository;
    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository, ChoiceRepository choiceRepository, ArchiveRepository archiveRepository, ResultRepository resultRepository, QuestionRepository questionRepository) {

        this.answerRepository = answerRepository;
        this.archiveRepository = archiveRepository;
        this.resultRepository = resultRepository;
        this.questionRepository = questionRepository;
        this.choiceRepository = choiceRepository;

    }

    public AllAnswers findAll() {
        var it = answerRepository.findAll();

        if (it.isEmpty()) {
            return new AllAnswers(new ArrayList<Answer>(), "", 204);
        }
        var answers = new ArrayList<Answer>();
        it.forEach(e -> answers.add(e));

        return new AllAnswers(answers, "", 200);
    }

    public SingleAnswer findById(Integer id) {

        var result = answerRepository.findById(id);

        if (result.isEmpty()) {
            return new SingleAnswer(null, "Answer not found", 404);
        }
        SingleAnswer answer = new SingleAnswer(result.get(), "", 200);

        return answer;
    }

    public Long count() {

        return answerRepository.count();
    }

    public void deleteById(Integer answerId) {
        answerRepository.deleteById(answerId);
    }

    public AnswerUpdated update(Integer id, Answer answer) {
        AnswerUpdated answerUpdated = new AnswerUpdated();
        Optional<Answer> result = answerRepository.findById(id);

        if (result.isEmpty()) {
            answerUpdated.setError("Answer not found");
            answerUpdated.setStatusCode(404);

            return answerUpdated;
        } else {
            Answer entity = result.get();
            Integer identifier = entity.getId();
            entity = answer;
            entity.setId(identifier);

            answerUpdated.setAnswer(answerRepository.save(entity));
            answerUpdated.setStatusCode(202);

            return answerUpdated;
        }
    }

    public AnswerAdded create(Answer answer) {
        AnswerAdded answerAdded = new AnswerAdded();
        var result = answerRepository.save(answer);

        if (result == null) {
            answerAdded.setError("Answer already Exists");
            answerAdded.setStatusCode(403);
            return answerAdded;
        } else {
            answerAdded.setAnswer(answer);
            answerAdded.setStatusCode(200);
            Optional<Question> question = questionRepository.findById(answer.getQuestion());
            Optional<Result> res = resultRepository.findById(answer.getResult());
            if (question.isPresent() && question.get().getIsmultiple()) {
                String[] options = answer.getText().split(",");
                List<String> correctChoices = new ArrayList<>();

                Iterable<Choice> allChoices = choiceRepository.findAll();
                for (Choice choice : allChoices) {
                    if (choice.getQuestion().equals(question.get().getId())) {
                        if (choice.getType() == true)
                            correctChoices.add(choice.getLetter());
                    }
                }
                System.out.println("correct:" + correctChoices);
                System.out.println("current:" + options);
                if (new HashSet<>(Arrays.asList(options)).equals(new HashSet<>(correctChoices))) {

                    var score = res.get().getScore();
                    res.get().setScore(score + question.get().getScore());
                    resultRepository.save(res.get());
                }
            }
            return answerAdded;
        }
    }
}
