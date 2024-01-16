package com.testconfigurationpoc.repository;
import com.testconfigurationpoc.domain.entity.User;
import com.testconfigurationpoc.domain.projection.BasicUserData;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    User findByUsername(String username);

    Optional<BasicUserData> findUserById(long id);

    List<BasicUserData> findAllBy();

    @Modifying
    @Query("UPDATE User SET username = :username WHERE id = :id")
    void  updateUsernameById(@Param("username") String username, @Param("id") Long id);
}
