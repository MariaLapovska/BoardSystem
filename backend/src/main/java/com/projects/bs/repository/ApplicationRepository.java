package com.projects.bs.repository;

import com.projects.bs.domain.Application;
import com.projects.bs.domain.Faculty;
import com.projects.bs.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Application findOneByUser(User user);

    Application findByCertificateNumber(String certificateNumber);

    List<Application> findByCertificateNumberContaining(String certificateNumber);

    List<Application> findByFacultyOrderByTotalGradeDesc(Faculty faculty);

    Page<Application> findByFaculty(Faculty faculty, Pageable pageable);
}