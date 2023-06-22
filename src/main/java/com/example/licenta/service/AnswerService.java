package com.example.licenta.service;


import com.example.licenta.entities.*;
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
    private final PairRepository pairRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository, PairRepository pairRepository, ChoiceRepository choiceRepository, ArchiveRepository archiveRepository, ResultRepository resultRepository, QuestionRepository questionRepository) {

        this.answerRepository = answerRepository;
        this.archiveRepository = archiveRepository;
        this.resultRepository = resultRepository;
        this.questionRepository = questionRepository;
        this.choiceRepository = choiceRepository;
        this.pairRepository = pairRepository;

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

            Optional<Question> question = questionRepository.findById(answer.getQuestion());
            Optional<Result> res = resultRepository.findById(answer.getResult());
            System.out.println("res=" + res.get().getScore());
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
            } else if (question.isPresent() && question.get().getIsfree()) {
                try {
                    var score = Integer.parseInt(answer.getText());
                    res.get().setScore(score + res.get().getScore());
                    resultRepository.save(res.get());
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            } else if (question.isPresent() && question.get().getIsbinar()) {
                System.out.println("raspuns:" + question.get().getValue());
                System.out.println("answer:" + answer.getText() == "T");
                if (answer.getText().equals("T") && question.get().getValue() == true) {
                    System.out.println("da");
                    var score = res.get().getScore();
                    res.get().setScore(score + question.get().getScore());
                    resultRepository.save(res.get());
                } else if (answer.getText().equals("F") && question.get().getValue() == false) {
                    var score = res.get().getScore();
                    res.get().setScore(score + question.get().getScore());
                    resultRepository.save(res.get());
                }
            }
            if (question.isPresent() && question.get().getIspair()) {
                try {
                    List<List<String>> pairs = new ArrayList<>();

                    Iterable<Pair> allPairs = pairRepository.findAll();
                    for (Pair pair : allPairs) {
                        if (pair.getQuestion().equals(question.get().getId()))
                            pairs.add(Arrays.asList(pair.getLetterClue(), pair.getNumberCorrect().toString()));
                    }
                    List<List<String>> pairsAnswer = new ArrayList<>();

                    String[] options = answer.getText().split(",");
                    for (var pair : options) {
                        var clue = pair.split("-")[0];
                        var correct = pair.split("-")[1];
                        pairsAnswer.add(Arrays.asList(correct, clue));

                    }
                    // Print pairs
                    System.out.println("Pairs:");
                    for (List<String> pair : pairs) {
                        for (String element : pair) {
                            System.out.print(element + " ");
                        }
                        System.out.println();
                    }
                    Set<List<String>> pairsAnswerSet = new HashSet<>(pairsAnswer);
                    Set<List<String>> pairsSet = new HashSet<>(pairs);

                    System.out.println(pairsSet.equals(pairsAnswerSet));

// Print pairsAnswer
                    System.out.println("PairsAnswer:");
                    for (List<String> pair : pairsAnswer) {
                        for (String element : pair) {
                            System.out.print(element + " ");
                        }
                        System.out.println();
                    }

                    if (pairsAnswerSet.equals(pairsSet)) {
                        var score = res.get().getScore();
                        res.get().setScore(score + question.get().getScore());
                        resultRepository.save(res.get());
                    }
                } catch (Exception e) {
                    System.err.println("Error occurred while adding pair: " + e.getMessage());
                }

            }
            System.out.println("res1=" + res.get().getScore());


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
            System.out.println("res=" + res.get().getScore());
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
            } else if (question.isPresent() && question.get().getIsfree()) {
                try {
                    var score = Integer.parseInt(answer.getText());
                    res.get().setScore(score + res.get().getScore());
                    resultRepository.save(res.get());
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            } else if (question.isPresent() && question.get().getIsbinar()) {
                System.out.println("raspuns:" + question.get().getValue());
                System.out.println("answer:" + answer.getText() == "T");
                if (answer.getText().equals("T") && question.get().getValue() == true) {
                    System.out.println("da");
                    var score = res.get().getScore();
                    res.get().setScore(score + question.get().getScore());
                    resultRepository.save(res.get());
                } else if (answer.getText().equals("F") && question.get().getValue() == false) {
                    var score = res.get().getScore();
                    res.get().setScore(score + question.get().getScore());
                    resultRepository.save(res.get());
                }
            }
            if (question.isPresent() && question.get().getIspair()) {
                try {
                    List<List<String>> pairs = new ArrayList<>();

                    Iterable<Pair> allPairs = pairRepository.findAll();
                    for (Pair pair : allPairs) {
                        if (pair.getQuestion().equals(question.get().getId()))
                            pairs.add(Arrays.asList(pair.getLetterClue(), pair.getNumberCorrect().toString()));
                    }
                    List<List<String>> pairsAnswer = new ArrayList<>();

                    String[] options = answer.getText().split(",");
                    for (var pair : options) {
                        var clue = pair.split("-")[0];
                        var correct = pair.split("-")[1];
                        pairsAnswer.add(Arrays.asList(correct, clue));

                    }
                    // Print pairs
                    System.out.println("Pairs:");
                    for (List<String> pair : pairs) {
                        for (String element : pair) {
                            System.out.print(element + " ");
                        }
                        System.out.println();
                    }
                    Set<List<String>> pairsAnswerSet = new HashSet<>(pairsAnswer);
                    Set<List<String>> pairsSet = new HashSet<>(pairs);

                    System.out.println(pairsSet.equals(pairsAnswerSet));

// Print pairsAnswer
                    System.out.println("PairsAnswer:");
                    for (List<String> pair : pairsAnswer) {
                        for (String element : pair) {
                            System.out.print(element + " ");
                        }
                        System.out.println();
                    }

                    if (pairsAnswerSet.equals(pairsSet)) {
                        var score = res.get().getScore();
                        res.get().setScore(score + question.get().getScore());
                        resultRepository.save(res.get());
                    }
                } catch (Exception e) {
                    System.err.println("Error occurred while adding pair: " + e.getMessage());
                }

            }
            System.out.println("res1=" + res.get().getScore());


            return answerAdded;
        }
    }

    public boolean checkResultExists(Integer id) {
        return resultRepository.findById(id).isPresent();
    }
}
