package data.repository;

import com.testconfigurationpoc.data.repository.UserRepository;
import com.testconfigurationpoc.domain.entity.User;
import configuration.AbstractIntegrationTest;
import configuration.CustomPosgresqlTestContainer;
import configuration.CustomTestConfiguration;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = CustomTestConfiguration.class)
public class UserRepositoryIntegrationTest {

    @ClassRule
    public static PostgreSQLContainer<CustomPosgresqlTestContainer> postgreSQLContainer = CustomPosgresqlTestContainer.getInstance();

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        System.out.println("DB_URL1: " + postgreSQLContainer.getJdbcUrl());
        System.out.println("DB_USERNAME1: " + postgreSQLContainer.getUsername());
        System.out.println("DB_PASSWORD1: " + postgreSQLContainer.getPassword());

        User user = User.builder()
                        .birthDate(LocalDate.now())
                        .username("username")
                        .password("password")
                        .build();

        userRepository.save(user);

        System.out.println(userRepository.findByUsername("username").getUsername());
    }
}
