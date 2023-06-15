package com.example.licenta.service;


import com.example.licenta.entities.Choice;
import com.example.licenta.entities.Pair;
import com.example.licenta.models.Choices.ChoiceUpdated;
import com.example.licenta.models.Pairs.AllPairs;
import com.example.licenta.models.Pairs.PairAdded;
import com.example.licenta.models.Pairs.PairUpdated;
import com.example.licenta.models.Pairs.SinglePair;
import com.example.licenta.repository.PairRepository;
import com.example.licenta.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class PairService {

    private final PairRepository pairRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public PairService(PairRepository pairRepository, QuestionRepository questionRepository) {
        this.pairRepository = pairRepository;
        this.questionRepository = questionRepository;
    }

    public AllPairs findAll() {
        var it = pairRepository.findAll();

        if (it.isEmpty()) {
            return new AllPairs(new ArrayList<Pair>(), "", 204);
        }
        var pairs = new ArrayList<Pair>();
        it.forEach(e -> pairs.add(e));

        return new AllPairs(pairs, "", 200);
    }

    public SinglePair findById(Integer id) {

        var result = pairRepository.findById(id);

        if (result.isEmpty()) {
            return new SinglePair(null, "Pair not found", 404);
        }
        SinglePair pair = new SinglePair(result.get(), "", 200);

        return pair;
    }

    public PairAdded create(Pair pair) {
        PairAdded pairAdded = new PairAdded();
        var result = pairRepository.save(pair);

        if (result == null) {
            pairAdded.setError("Pair already Exists");
            pairAdded.setStatusCode(403);
            return pairAdded;
        } else {
            pairAdded.setPair(pair);
            pairAdded.setStatusCode(200);
            return pairAdded;
        }
    }

    public Long count() {

        return pairRepository.count();
    }

    public PairUpdated update(Integer id, Pair pair) {
        PairUpdated pairUpdated = new PairUpdated();
        Optional<Pair> result = pairRepository.findById(id);

        if (result.isEmpty()) {
            pairUpdated.setError("pair not found");
            pairUpdated.setStatusCode(404);

            return pairUpdated;
        } else {
            Pair entity = result.get();
            Integer identifier = entity.getId();
            entity = pair;
            entity.setId(identifier);

            pairUpdated.setPair(pairRepository.save(entity));
            pairUpdated.setStatusCode(202);

            return pairUpdated;
        }
    }


    public void deleteById(Integer testId) {
        pairRepository.deleteById(testId);
    }

    public boolean checkQuestionExists(Integer id) {
        return questionRepository.findById(id).isPresent();
    }


}
