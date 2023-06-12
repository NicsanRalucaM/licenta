package com.example.licenta.models.Archives;
import com.example.licenta.entities.Archive;
import lombok.*;

import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ArchiveAdded {
    private Archive archive = new Archive();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArchiveAdded archiveAdded = (ArchiveAdded) o;
        return statusCode == archiveAdded.statusCode && Objects.equals(archive, archiveAdded.archive) && Objects.equals(error, archiveAdded.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(archive, error, statusCode);
    }
}