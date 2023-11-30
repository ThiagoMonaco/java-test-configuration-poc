package com.testconfigurationpoc.data.repository;


import com.testconfigurationpoc.domain.entity.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {
    User save(User user);
}
