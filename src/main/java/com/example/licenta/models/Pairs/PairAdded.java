package com.example.licenta.models.Pairs;
import com.example.licenta.entities.Pair;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class PairAdded {
    private Pair pair = new Pair();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairAdded pairAdded = (PairAdded) o;
        return statusCode == pairAdded.statusCode && Objects.equals(pair, pairAdded.pair) && Objects.equals(error, pairAdded.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pair, error, statusCode);
    }
}