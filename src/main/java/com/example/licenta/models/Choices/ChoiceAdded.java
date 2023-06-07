package com.example.licenta.models.Choices;
import com.example.licenta.entities.Choice;
import lombok.*;

import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ChoiceAdded {
    private Choice choice = new Choice();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       ChoiceAdded choiceAdded = (ChoiceAdded) o;
        return statusCode == choiceAdded.statusCode && Objects.equals(choice, choiceAdded.choice) && Objects.equals(error, choiceAdded.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(choice, error, statusCode);
    }
}