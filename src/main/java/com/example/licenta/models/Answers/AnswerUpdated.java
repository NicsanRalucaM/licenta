package com.example.licenta.models.Answers;


import com.example.licenta.entities.Answer;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AnswerUpdated {
    private Answer answer = new Answer();
    private String error = "";
    private int statusCode = 500;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerUpdated that = (AnswerUpdated) o;
        return statusCode == that.statusCode && Objects.equals(answer, that.answer) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer, error, statusCode);
    }
}