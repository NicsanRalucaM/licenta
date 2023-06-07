package com.example.licenta.models.Questions;

import com.example.licenta.entities.Question;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AllQuestions {
    private List<Question> questions = new ArrayList<Question>();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllQuestions allQuestions = (AllQuestions) o;
        return statusCode == allQuestions.statusCode && Objects.equals(questions, allQuestions.questions) && Objects.equals(error, allQuestions.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questions, error, statusCode);
    }
}
