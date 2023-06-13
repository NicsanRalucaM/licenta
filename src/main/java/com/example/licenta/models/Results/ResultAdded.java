package com.example.licenta.models.Results;

import com.example.licenta.entities.Result;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ResultAdded {
    private Result result = new Result();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultAdded resultAdded = (ResultAdded) o;
        return statusCode == resultAdded.statusCode && Objects.equals(result, resultAdded.result) && Objects.equals(error, resultAdded.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, error, statusCode);
    }
}