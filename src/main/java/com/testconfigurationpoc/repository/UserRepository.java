package com.testconfigurationpoc.repository;
import com.testconfigurationpoc.domain.entity.User;
import com.testconfigurationpoc.domain.projection.BasicUserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    User findByUsername(String username);

    Optional<BasicUserData> findUserById(long id);

    List<BasicUserData> findAllBy();
}
