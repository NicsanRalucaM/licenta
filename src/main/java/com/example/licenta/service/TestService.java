package com.example.licenta.service;

import com.example.licenta.entities.Test;
import com.example.licenta.entities.Test;
import com.example.licenta.models.Tests.AllTests;
import com.example.licenta.models.Tests.SingleTest;
import com.example.licenta.models.Tests.TestAdded;
import com.example.licenta.models.Tests.TestUpdated;
import com.example.licenta.models.Tests.TestAdded;
import com.example.licenta.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class TestService {

    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public AllTests findAll() {
        var it = testRepository.findAll();

        if (it.isEmpty()) {
            return new AllTests(new ArrayList<Test>(), "", 204);
        }
        var tests = new ArrayList<Test>();
        it.forEach(e -> tests.add(e));

        return new AllTests(tests, "", 200);
    }

    public SingleTest findById(Integer id) {

        var result = testRepository.findById(id);

        if (result.isEmpty()) {
            return new SingleTest(null, "Test not found", 404);
        }
        SingleTest test = new SingleTest(result.get(), "", 200);

        return test;
    }

    public TestAdded create(Test test) {
        TestAdded testAdded = new TestAdded();
        var result = testRepository.save(test);

        if (result == null) {
            testAdded.setError("Test already Exists");
            testAdded.setStatusCode(403);
            return testAdded;
        } else {
            testAdded.setTest(test);
            testAdded.setStatusCode(200);
            return testAdded;
        }
    }

    public Long count() {

        return testRepository.count();
    }



    public void deleteById(Integer testId) {
        testRepository.deleteById(testId);
    }

    public TestUpdated update(Integer id, Test test) {
        TestUpdated testUpdated = new TestUpdated();
        Optional<Test> result = testRepository.findById(id);

        if (result.isEmpty()) {
            testUpdated.setError("Test not found");
            testUpdated.setStatusCode(404);

            return testUpdated;
        } else {
            Test entity = result.get();
            Integer identifier = entity.getId();
            Integer user=entity.getUser();
            entity = test;
            entity.setId(identifier);
            if(entity.getUser()==null)
                entity.setUser(user);

            testUpdated.setTest(testRepository.save(entity));
            testUpdated.setStatusCode(202);

            return testUpdated;
        }
    }
}
