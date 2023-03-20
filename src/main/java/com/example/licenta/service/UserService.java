package com.example.licenta.service;

import com.example.licenta.entities.User;
import com.example.licenta.models.Users.AllUsers;
import com.example.licenta.models.Users.SingleUser;
import com.example.licenta.models.Users.UserAdded;
import com.example.licenta.models.Users.UserUpdated;
import com.example.licenta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public AllUsers findAll() {
        var it = userRepository.findAll();

        if(it.isEmpty())
        {
            return new AllUsers(new ArrayList<User>(), "", 204);
        }
        var users = new ArrayList<User>();
        it.forEach(e -> users.add(e));

        return new AllUsers(users, "", 200);
    }

    public SingleUser findById(Integer id) {

        var result = userRepository.findById(id);

        if(result.isEmpty()){
            return new SingleUser(null, "User not found", 404);
        }
        SingleUser user = new SingleUser(result.get(), "", 200);

        return user;
    }
    public UserAdded create(User user){
        UserAdded userAdded=new UserAdded();
        var result= userRepository.save(user);

        if (result==null) {
            userAdded.setError("User already Exists");
            userAdded.setStatusCode(403);
            return userAdded;
        } else {
            userAdded.setUser(user);
            userAdded.setStatusCode(200);
            return userAdded;
        }
    }

    public Long count() {

        return userRepository.count();
    }

    public void deleteById(Integer userId) {
        userRepository.deleteById(userId);
    }

    public UserUpdated update(Integer id, User user) {
        UserUpdated userUpdated = new UserUpdated();
        Optional<User> result = userRepository.findById(id);

        if (result.isEmpty()) {
            userUpdated.setError("User not found");
            userUpdated.setStatusCode(404);

            return userUpdated;
        } else {
            User entity = result.get();
            Integer identifier = entity.getId();
            entity = user;
            entity.setId(identifier);

            userUpdated.setUser(userRepository.save(entity));
            userUpdated.setStatusCode(202);

            return userUpdated;
        }
    }
}
