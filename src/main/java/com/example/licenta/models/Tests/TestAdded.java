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
        TestAdded TestAdded = (TestAdded) o;
        return statusCode == TestAdded.statusCode && Objects.equals(test, TestAdded.test) && Objects.equals(error, TestAdded.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(test, error, statusCode);
    }
}