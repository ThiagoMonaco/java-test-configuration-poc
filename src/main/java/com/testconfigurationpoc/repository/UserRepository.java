package com.testconfigurationpoc.repository;
import com.testconfigurationpoc.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    User findByUsername(String username);
}
