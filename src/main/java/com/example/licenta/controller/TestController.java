package com.example.licenta.controller;


import com.example.licenta.entities.Test;
import com.example.licenta.entities.User;
import com.example.licenta.models.Tests.AllTests;
import com.example.licenta.models.Tests.SingleTest;
import com.example.licenta.models.Tests.TestAdded;
import com.example.licenta.models.Tests.TestUpdated;
import com.example.licenta.models.Users.UserAdded;
import com.example.licenta.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/test")
public class TestController {
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping()
    public AllTests all() {
        AllTests result = testService.findAll();
        return result;
    }

    @PostMapping("{create}")
    public TestAdded create(@RequestBody Test test) {
        TestAdded result = testService.create(test);
        return result;
    }

    @GetMapping("{id}")
    public SingleTest byId(@PathVariable Integer id) {

        SingleTest result = testService.findById(id);
        return result;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        testService.deleteById(id);
    }

    @PostMapping("update/{id}")
    public TestUpdated update(@PathVariable Integer id, @RequestBody Test test) {
        System.out.println(test);
        TestUpdated result = testService.update(id, test);
        return result;
    }

}