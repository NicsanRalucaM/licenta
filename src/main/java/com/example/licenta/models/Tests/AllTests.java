package com.example.licenta.models.Tests;

import com.example.licenta.entities.Test;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AllTests {
    private List<Test> tests = new ArrayList<Test>();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.example.licenta.models.Tests.AllTests AllTests = (com.example.licenta.models.Tests.AllTests) o;
        return statusCode == AllTests.statusCode && Objects.equals(tests, AllTests.tests) && Objects.equals(error, AllTests.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tests, error, statusCode);
    }
}
