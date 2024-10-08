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
        QuestionAdded questionAdded = (QuestionAdded) o;
        return statusCode == questionAdded.statusCode && Objects.equals(question, questionAdded.question) && Objects.equals(error, questionAdded.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, error, statusCode);
    }
}