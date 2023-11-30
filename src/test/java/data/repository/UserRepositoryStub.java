package data.repository;

import com.testconfigurationpoc.data.repository.UserRepository;
import com.testconfigurationpoc.domain.entity.User;

public class UserRepositoryStub implements UserRepository {

    @Override
    public User save(User user) {
        return user;
    }
}
