package com.example.licenta.service;


import com.example.licenta.entities.Archive;
import com.example.licenta.entities.Test;
import com.example.licenta.models.Archives.AllArchives;
import com.example.licenta.models.Archives.ArchiveAdded;
import com.example.licenta.models.Archives.ArchiveUpdated;
import com.example.licenta.models.Archives.SingleArchive;
import com.example.licenta.models.Tests.TestUpdated;
import com.example.licenta.repository.ArchiveRepository;
import com.example.licenta.repository.TestRepository;
import com.example.licenta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class ArchiveService {

    private final ArchiveRepository archiveRepository;
    private final TestRepository testRepository;
    private final UserRepository userRepository;

    @Autowired
    public ArchiveService(ArchiveRepository archiveRepository, TestRepository testRepository, UserRepository userRepository) {
        this.archiveRepository = archiveRepository;
        this.testRepository = testRepository;
        this.userRepository = userRepository;
    }

    public AllArchives findAll() {
        var it = archiveRepository.findAll();

        if (it.isEmpty()) {
            return new AllArchives(new ArrayList<Archive>(), "", 204);
        }
        var archives = new ArrayList<Archive>();
        it.forEach(e -> archives.add(e));

        return new AllArchives(archives, "", 200);
    }

    public SingleArchive findById(Integer id) {

        var result = archiveRepository.findById(id);

        if (result.isEmpty()) {
            return new SingleArchive(null, "Arhive not found", 404);
        }
        SingleArchive archive = new SingleArchive(result.get(), "", 200);

        return archive;
    }

    public ArchiveAdded create(Archive archive) {
        ArchiveAdded archiveAdded = new ArchiveAdded();
        var result = archiveRepository.save(archive);

        if (result == null) {
            archiveAdded.setError("Choice already Exists");
            archiveAdded.setStatusCode(403);
            return archiveAdded;
        } else {
            archiveAdded.setArchive(archive);
            archiveAdded.setStatusCode(200);
            return archiveAdded;
        }
    }

    public ArchiveUpdated update(Integer id, Archive archive) {
        ArchiveUpdated archiveUpdated = new ArchiveUpdated();
        Optional<Archive> result = archiveRepository.findById(id);

        if (result.isEmpty()) {
            archiveUpdated.setError("Test not found");
            archiveUpdated.setStatusCode(404);

            return archiveUpdated;
        } else {
            Archive entity = result.get();
            Integer identifier = entity.getId();
            Integer test = entity.getTest();
            entity = archive;
            entity.setId(identifier);
            if (entity.getTest() == null)
                entity.setTest(test);

            archiveUpdated.setArchive(archiveRepository.save(entity));
            archiveUpdated.setStatusCode(202);

            return archiveUpdated;
        }
    }

    public Long count() {

        return archiveRepository.count();
    }


    public void deleteById(Integer choiceId) {
        archiveRepository.deleteById(choiceId);
    }

    public boolean checkTestExists(Integer id) {
        return testRepository.findById(id).isPresent();
    }

    public boolean checkUserExists(Integer id) {
        return userRepository.findById(id).isPresent();
    }
}
