package com.example.licenta.models.Archives;

import com.example.licenta.entities.Archive;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class SingleArchive {
    private Archive archive = new Archive();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleArchive that = (SingleArchive) o;
        return statusCode == that.statusCode && Objects.equals(archive, that.archive) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(archive, error, statusCode);
    }
}