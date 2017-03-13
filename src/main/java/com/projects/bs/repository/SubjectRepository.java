package com.projects.bs.repository;

import com.projects.bs.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findOneByName(String name);
}
