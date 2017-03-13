package com.projects.bs.service;

import com.projects.bs.domain.Application;
import com.projects.bs.domain.Faculty;
import com.projects.bs.domain.User;
import com.projects.bs.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    public List<Application> findByFaculty(Faculty faculty) {
        return applicationRepository.findByFaculty(faculty);
    }

    public Application findByCertificateNumber(String certificateNumber) {
        return applicationRepository.findOneByCertificateNumber(certificateNumber);
    }

    public Application findByUser(User user) {
        return applicationRepository.findOneByUser(user);
    }

    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }

    public Application findOne(long id) {
        return applicationRepository.findOne(id);
    }

    public void delete(long id) {
        applicationRepository.delete(id);
    }

    //TODO: pageable + sort
}
