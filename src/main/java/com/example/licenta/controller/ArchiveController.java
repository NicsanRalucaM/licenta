package com.example.licenta.controller;

import com.example.licenta.entities.Archive;
import com.example.licenta.models.Archives.AllArchives;
import com.example.licenta.models.Archives.ArchiveAdded;
import com.example.licenta.models.Archives.ArchiveUpdated;
import com.example.licenta.models.Archives.SingleArchive;
import com.example.licenta.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/archive")
public class ArchiveController {
    private final ArchiveService archiveService;

    @Autowired
    public ArchiveController(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @GetMapping()
    public AllArchives all() {
        AllArchives result = archiveService.findAll();
        return result;
    }

    @GetMapping("{test_id}/archives")
    public AllArchives getTestArchives(@PathVariable Integer test_id) {
        //check to see if user_id exists
        if (archiveService.checkTestExists(test_id)) {
            //creating the array of products that belong to user_id
            List<Archive> archives = new ArrayList<>();
            for (Archive archive : archiveService.findAll().getArchives()) {
                //verify that product belongs to user_id
                if (archive.getTest().equals(test_id)) {
                    archives.add(archive);
                }
            }
            return new AllArchives(archives, "", 200);
        }

        return new AllArchives(new ArrayList<>(), "Test has no Archives", 200);
    }
    @GetMapping("user/{user_id}/archives")
    public AllArchives getTestArchivess(@PathVariable Integer user_id) {
        //check to see if user_id exists
        if (archiveService.checkUserExists(user_id)) {
            List<Archive> archives = new ArrayList<>();
            for (Archive archive : archiveService.findAll().getArchives()) {
                if (archive.getUser().equals(user_id)) {
                    archives.add(archive);
                }
            }
            return new AllArchives(archives, "", 200);
        }

        return new AllArchives(new ArrayList<>(), "User has no Archives", 200);
    }

    @PostMapping("{create}")
    public ArchiveAdded create(@RequestBody Archive archive) {
        ArchiveAdded result = archiveService.create(archive);
        return result;
    }

    @GetMapping("{id}")
    public SingleArchive byId(@PathVariable Integer id) {

        SingleArchive result = archiveService.findById(id);
        return result;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        archiveService.deleteById(id);
    }

    @PostMapping("update/{id}")
    public ArchiveUpdated update(@PathVariable Integer id, @RequestBody Archive archive) {
        System.out.println(archive);
        ArchiveUpdated result = archiveService.update(id, archive);
        return result;
    }

}