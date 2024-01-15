package stubs.repository;

import com.testconfigurationpoc.domain.projection.BasicUserData;
import com.testconfigurationpoc.repository.UserRepository;
import com.testconfigurationpoc.domain.entity.User;
import org.springframework.stereotype.Repository;
import stubs.domain.projection.BasicUserDataStub;

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
    default List<BasicUserData> findAllBy() {
        return List.of(BasicUserDataStub.builder()
                .username("username")
                .id(1L)
                .build());
    }
}
