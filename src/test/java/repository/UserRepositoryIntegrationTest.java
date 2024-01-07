package repository;

import com.testconfigurationpoc.JavaTestConfigurationPocApplication;
import com.testconfigurationpoc.repository.UserRepository;
import com.testconfigurationpoc.domain.entity.User;
import configuration.CustomPosgresqlTestContainer;
import configuration.IntegrationTestConfiguration;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({SpringExtension.class, IntegrationTestConfiguration.class})
@SpringBootTest(classes = JavaTestConfigurationPocApplication.class)
public class UserRepositoryIntegrationTest{

    @ClassRule
    public static PostgreSQLContainer<CustomPosgresqlTestContainer> postgreSQLContainer = CustomPosgresqlTestContainer.getInstance();

    @Autowired
    private UserRepository userRepository;


    // I know this test is useless, but i just wanted simulate how a integration test would be
    @Test
    public void shouldSaveUserCorrectly() {
        User user = User.builder()
                        .birthDate(LocalDate.now())
                        .username("username")
                        .password("password")
                        .favoriteTechs(List.of())
                        .build();

        userRepository.save(user);

        User result = userRepository.findByUsername("username");

        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(user.getBirthDate(), result.getBirthDate());
        assertEquals(user.getFavoriteTechs().size(), result.getFavoriteTechs().size());
        assertNotNull(result.getId());
    }
}
