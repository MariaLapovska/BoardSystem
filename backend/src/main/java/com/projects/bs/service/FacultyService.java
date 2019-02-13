package com.projects.bs.service;

import com.projects.bs.domain.Faculty;
import com.projects.bs.domain.Subject;
import com.projects.bs.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private SubjectService subjectService;

    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }

    public Faculty findByName(String name) {
        return facultyRepository.findOneByNameIgnoreCase(name);
    }

    public List<Faculty> findByIsAvailable(boolean isAvailable) {
        return facultyRepository.findByIsAvailable(isAvailable);
    }

    public List<Faculty> findBySubjects(Set<Subject> subjects) {
        return facultyRepository.findBySubjects(subjects);
    }

    public Faculty saveFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findOne(long id) {
        return facultyRepository.findById(id).get();
    }

    public void delete(long id) {
        facultyRepository.deleteById(id);
    }
}
