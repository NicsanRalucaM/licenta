package com.example.licenta.models.Questions;

import com.example.licenta.entities.Question;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class QuestionAdded {
    private Question question = new Question();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionAdded QuestionAdded = (QuestionAdded) o;
        return statusCode == QuestionAdded.statusCode && Objects.equals(question, QuestionAdded.question) && Objects.equals(error, QuestionAdded.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, error, statusCode);
    }
}