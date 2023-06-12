package com.example.licenta.models.Archives;

import com.example.licenta.entities.Archive;
import com.example.licenta.entities.Choice;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AllArchives {
    private List<Archive> archives = new ArrayList<Archive>();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllArchives allArchives = (AllArchives) o;
        return statusCode == allArchives.statusCode && Objects.equals(allArchives, allArchives.archives) && Objects.equals(error, allArchives.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(archives, error, statusCode);
    }
}
