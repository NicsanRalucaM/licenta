package com.example.licenta.models.Answers;

import com.example.licenta.entities.Answer;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AllAnswers {
    private List<Answer> answers = new ArrayList<Answer>();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllAnswers allAnswers = (AllAnswers) o;
        return statusCode == allAnswers.statusCode && Objects.equals(answers, allAnswers.answers) && Objects.equals(error, allAnswers.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers, error, statusCode);
    }
}
