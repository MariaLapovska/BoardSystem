package com.projects.bs.controller;

import com.projects.bs.domain.Subject;
import com.projects.bs.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectsController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<Subject> getSubjects() {
        return subjectService.findAll();
    }

    @GetMapping("{id}")
    public Subject getSubject(@PathVariable long id) {
        return subjectService.findOne(id);
    }

    @GetMapping("search/{name}")
    public List<Subject> getSubjectByName(@PathVariable String name) {
        return subjectService.findByName(name);
    }

    @PostMapping
    public Subject addSubject(@Valid Subject subject) {
        subjectService.saveSubject(subject);
        return subject;
    }

    @PutMapping("{id}")
    public Subject editSubject(@PathVariable int id, @Valid Subject subject) {
        subjectService.saveSubject(subject);
        return subject;
    }

    @DeleteMapping("{id}")
    public void deleteSubject(@PathVariable int id) {
        subjectService.delete(id);
    }
}
