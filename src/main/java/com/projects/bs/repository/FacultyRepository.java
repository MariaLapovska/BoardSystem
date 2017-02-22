package com.projects.bs.repository;

import com.projects.bs.domain.Faculty;
import com.projects.bs.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByName(String name);
    List<Faculty> findBySubjects(Set<Subject> subjects);
}