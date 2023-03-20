package com.example.licenta.models.Users;

import com.example.licenta.entities.User;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class UserAdded {
    private User user = new User();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAdded userAdded = (UserAdded) o;
        return statusCode == userAdded.statusCode && Objects.equals(user, userAdded.user) && Objects.equals(error, userAdded.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, error, statusCode);
    }
}