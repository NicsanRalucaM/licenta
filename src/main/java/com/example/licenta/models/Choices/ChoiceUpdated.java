package com.example.licenta.models.Choices;

import com.example.licenta.entities.Choice;
import com.example.licenta.entities.Question;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ChoiceUpdated {
    private Choice choice = new Choice();
    private String error = "";
    private int statusCode = 500;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChoiceUpdated that = (ChoiceUpdated) o;
        return statusCode == that.statusCode && Objects.equals(choice, that.choice) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(choice, error, statusCode);
    }
}