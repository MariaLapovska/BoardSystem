package com.projects.bs.web.controller;

import com.projects.bs.domain.Faculty;
import com.projects.bs.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultiesController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public List<Faculty> getFaculties() {
        return facultyService.findAll();
    }

    @GetMapping("{id}")
    public Faculty getFaculty(@PathVariable long id) {
        return facultyService.findOne(id);
    }

    @GetMapping("search/{name}")
    public Faculty getFacultyByName(@PathVariable String name) {
        return facultyService.findByName(name);
    }

    @PostMapping
    public Faculty addFaculty(@Valid @RequestBody Faculty faculty) {
        return facultyService.saveFaculty(faculty);
    }

    @PutMapping("{id}")
    public Faculty editFaculty(@PathVariable long id, @Valid @RequestBody Faculty faculty) {
        Faculty old = facultyService.findOne(id);
        old.setName(faculty.getName());
        return facultyService.saveFaculty(old);
    }

    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable long id) {
        facultyService.delete(id);
    }
}
