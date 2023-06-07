package com.example.licenta.models.Choices;

import com.example.licenta.entities.Choice;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AllChoices {
    private List<Choice> choices = new ArrayList<Choice>();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllChoices allChoices = (AllChoices) o;
        return statusCode == allChoices.statusCode && Objects.equals(choices, allChoices.choices) && Objects.equals(error, allChoices.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(choices, error, statusCode);
    }
}
