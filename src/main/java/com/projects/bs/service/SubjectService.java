package com.projects.bs.service;

import com.projects.bs.domain.Subject;
import com.projects.bs.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Subject findByName(String name) {
        return subjectRepository.findOneByNameIgnoreCase(name);
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject findOne(long id) {
        return subjectRepository.findOne(id);
    }

    public void delete(long id) {
        subjectRepository.delete(id);
    }
}
