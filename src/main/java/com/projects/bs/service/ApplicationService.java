package com.projects.bs.service;

import com.projects.bs.domain.Application;
import com.projects.bs.domain.Faculty;
import com.projects.bs.domain.User;
import com.projects.bs.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparingInt;

@Service
@Transactional
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public Page<Application> findAll(int pageNumber, int pageSize) {
        return applicationRepository.findAll(new PageRequest(pageNumber - 1, pageSize));
    }

    public Page<Application> findByFaculty(Faculty faculty, int pageNumber, int pageSize) {
        return applicationRepository.findByFaculty(faculty, new PageRequest(pageNumber - 1, pageSize));
    }

    public List<Application> findByParameter(String parameter) {
        return applicationRepository.findByNameContainingOrSurnameContainingOrCertificateNumberContaining(parameter, parameter, parameter);
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
}
