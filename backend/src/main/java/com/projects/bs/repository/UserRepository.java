package com.projects.bs.repository;

import com.projects.bs.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByLogin(String login);
}
