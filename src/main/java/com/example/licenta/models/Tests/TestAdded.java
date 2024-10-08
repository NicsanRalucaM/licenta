package com.example.licenta.models.Tests;

import com.example.licenta.entities.Test;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class TestAdded {
    private Test test = new Test();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestAdded testAdded = (TestAdded) o;
        return statusCode == testAdded.statusCode && Objects.equals(test, testAdded.test) && Objects.equals(error, testAdded.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(test, error, statusCode);
    }
}