package stubs.repository;

import com.testconfigurationpoc.repository.UserRepository;
import com.testconfigurationpoc.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepositoryStub extends UserRepository {
    @Override
    default User save(User user) {
        return User.builder()
                .username(user.getUsername())
                .favoriteTechs(user.getFavoriteTechs())
                .password(user.getPassword())
                .birthDate(user.getBirthDate())
                .id(1L)
                .build();
    }

    @Override
    default List<User> findAll() {
        return List.of(User.builder()
                .username("username")
                .favoriteTechs(List.of())
                .password("password")
                .birthDate(null)
                .id(1L)
                .build());
    }
}
