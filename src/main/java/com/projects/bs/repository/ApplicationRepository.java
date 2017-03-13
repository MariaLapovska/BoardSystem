package com.projects.bs.repository;

import com.projects.bs.domain.Application;
import com.projects.bs.domain.Faculty;
import com.projects.bs.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Application findOneByUser(User user);
    Application findOneByCertificateNumber(String certificateNumber);
    List<Application> findByFaculty(Faculty faculty);
}