package com.example.licenta.models.Pairs;
import com.example.licenta.entities.Pair;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class PairUpdated {
    private Pair pair = new Pair();
    private String error = "";
    private int statusCode = 500;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairUpdated that = (PairUpdated) o;
        return statusCode == that.statusCode && Objects.equals(pair, that.pair) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pair, error, statusCode);
    }
}