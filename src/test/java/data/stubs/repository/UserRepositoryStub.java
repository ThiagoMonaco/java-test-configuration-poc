package data.stubs.repository;

import com.testconfigurationpoc.data.repository.UserRepository;
import com.testconfigurationpoc.domain.entity.User;
import org.springframework.stereotype.Repository;


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
}
