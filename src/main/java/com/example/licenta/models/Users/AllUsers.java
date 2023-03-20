package com.example.licenta.models.Users;

import com.example.licenta.entities.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AllUsers {
    private List<User> users = new ArrayList<User>();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllUsers allUsers = (AllUsers) o;
        return statusCode == allUsers.statusCode && Objects.equals(users, allUsers.users) && Objects.equals(error, allUsers.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users, error, statusCode);
    }
}