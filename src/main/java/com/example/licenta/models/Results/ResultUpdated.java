package com.example.licenta.models.Results;

import com.example.licenta.entities.Result;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ResultUpdated {
    private Result result = new Result();
    private String error = "";
    private int statusCode = 500;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultUpdated that = (ResultUpdated) o;
        return statusCode == that.statusCode && Objects.equals(result, that.result) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, error, statusCode);
    }
}