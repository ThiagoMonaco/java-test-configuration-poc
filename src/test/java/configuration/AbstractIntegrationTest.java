package configuration;

import org.junit.ClassRule;
import org.springframework.boot.test.context.TestComponent;
import org.testcontainers.containers.PostgreSQLContainer;

@TestComponent
public class AbstractIntegrationTest {
    @ClassRule
    public static PostgreSQLContainer<CustomPosgresqlTestContainer> postgreSQLContainer = CustomPosgresqlTestContainer.getInstance();


    static {
        postgreSQLContainer.start();
    }
}
