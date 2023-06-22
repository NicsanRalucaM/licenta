package com.example.licenta.service;

import com.example.licenta.entities.Result;
import com.example.licenta.models.Results.AllResults;
import com.example.licenta.models.Results.ResultAdded;
import com.example.licenta.models.Results.ResultUpdated;
import com.example.licenta.models.Results.SingleResult;
import com.example.licenta.repository.ArchiveRepository;
import com.example.licenta.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final ArchiveRepository archiveRepository;

    @Autowired
    public ResultService(ResultRepository resultRepository,ArchiveRepository archiveRepository) {
        this.resultRepository = resultRepository;
        this.archiveRepository = archiveRepository;
    }

    public AllResults findAll() {
        var it = resultRepository.findAll();

        if (it.isEmpty()) {
            return new AllResults(new ArrayList<Result>(), "", 204);
        }
        var results = new ArrayList<Result>();
        it.forEach(e -> results.add(e));

        return new AllResults(results, "", 200);
    }

    public SingleResult findById(Integer id) {

        var result = resultRepository.findById(id);

        if (result.isEmpty()) {
            return new SingleResult(null, "Result not found", 404);
        }
        SingleResult res = new SingleResult(result.get(), "", 200);

        return res;
    }

    public ResultAdded create(Result result) {
        ResultAdded resultAdded = new ResultAdded();
        var res = resultRepository.save(result);

        if (res == null) {
            resultAdded.setError("Test already Exists");
            resultAdded.setStatusCode(403);
            return resultAdded;
        } else {
            resultAdded.setResult(result);
            resultAdded.setStatusCode(200);
            return resultAdded;
        }
    }

    public Long count() {

        return resultRepository.count();
    }


    public void deleteById(Integer resultId) {
        resultRepository.deleteById(resultId);
    }

    public ResultUpdated update(Integer id, Result result) {
        ResultUpdated resultUpdated = new ResultUpdated();
        Optional<Result> res = resultRepository.findById(id);

        if (res.isEmpty()) {
            resultUpdated.setError("Test not found");
            resultUpdated.setStatusCode(404);

            return resultUpdated;
        } else {
            Result entity = res.get();
            Integer identifier = entity.getId();
            Integer archive = entity.getArchive();
            entity = result;
            entity.setId(identifier);
            entity.setScore(0);
            if (entity.getArchive() == null)
                entity.setArchive(archive);

            resultUpdated.setResult(resultRepository.save(entity));
            resultUpdated.setStatusCode(202);

            return resultUpdated;
        }
    }


    public boolean checkArchiveExists(Integer id) {
        return archiveRepository.findById(id).isPresent();
    }
}
