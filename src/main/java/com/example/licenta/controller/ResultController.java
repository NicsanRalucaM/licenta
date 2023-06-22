package com.example.licenta.controller;


import com.example.licenta.entities.Result;
import com.example.licenta.models.Results.AllResults;
import com.example.licenta.models.Results.ResultAdded;
import com.example.licenta.models.Results.ResultUpdated;
import com.example.licenta.models.Results.SingleResult;
import com.example.licenta.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/result")
public class ResultController {
    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping()
    public AllResults all() {
        AllResults result = resultService.findAll();
        return result;
    }

    @GetMapping("{id}")
    public SingleResult byId(@PathVariable Integer id) {

        SingleResult result = resultService.findById(id);
        return result;
    }

    @PostMapping("{create}")
    public ResultAdded create(@RequestBody Result res) {
        ResultAdded result = resultService.create(res);
        return result;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        resultService.deleteById(id);
    }

    @PutMapping("update/{id}")
    public ResultUpdated update(@PathVariable Integer id, @RequestBody Result res) {
        ResultUpdated result = resultService.update(id, res);
        return result;
    }

    @GetMapping("{archive_id}/results")
    public AllResults getTestQuestion(@PathVariable Integer archive_id) {
        if (resultService.checkArchiveExists(archive_id)) {

            List<Result> results = new ArrayList<>();
            for (Result result : resultService.findAll().getResults()) {
                if (result.getArchive().equals(archive_id)) {
                    results.add(result);
                }
            }
            return new AllResults(results, "", 200);
        }

        return new AllResults(new ArrayList<>(), "archive has no results", 200);
    }

}