package com.example.licenta.models.Results;

import com.example.licenta.entities.Result;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AllResults {
    private List<Result> results = new ArrayList<Result>();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllResults allResults = (AllResults) o;
        return statusCode == allResults.statusCode && Objects.equals(results, allResults.results) && Objects.equals(error, allResults.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results, error, statusCode);
    }
}
