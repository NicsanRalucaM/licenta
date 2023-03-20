package com.example.licenta.controller;

import com.example.licenta.entities.User;
import com.example.licenta.models.Users.AllUsers;
import com.example.licenta.models.Users.SingleUser;
import com.example.licenta.models.Users.UserAdded;
import com.example.licenta.models.Users.UserUpdated;
import com.example.licenta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public AllUsers all() {
        AllUsers result = userService.findAll();
        return result;
    }

    @PostMapping("{register}")
    public UserAdded create(@RequestBody User user) {
        UserAdded result = userService.create(user);
        return result;
    }

    @GetMapping("{id}")
    public SingleUser byId(@PathVariable Integer id) {

        SingleUser result = userService.findById(id);
        return result;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteById(id);
    }

    @PutMapping("{id}")
    public UserUpdated update(@PathVariable Integer id, @RequestBody User user) {
        UserUpdated result = userService.update(id, user);
        return result;
    }

}