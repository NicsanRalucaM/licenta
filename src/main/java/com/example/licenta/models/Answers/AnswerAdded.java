package com.example.licenta.models.Answers;

import com.example.licenta.entities.Answer;
import lombok.*;

import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AnswerAdded {
    private Answer answer = new Answer();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerAdded answerAdded = (AnswerAdded) o;
        return statusCode == answerAdded.statusCode && Objects.equals(answer, answerAdded.answer) && Objects.equals(error, answerAdded.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer, error, statusCode);
    }
}