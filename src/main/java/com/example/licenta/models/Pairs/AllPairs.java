package com.example.licenta.models.Pairs;
import com.example.licenta.entities.Pair;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AllPairs {
    private List<Pair> pairs = new ArrayList<Pair>();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllPairs allPairs = (AllPairs) o;
        return statusCode == allPairs.statusCode && Objects.equals(pairs, allPairs.pairs) && Objects.equals(error, allPairs.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pairs, error, statusCode);
    }
}
