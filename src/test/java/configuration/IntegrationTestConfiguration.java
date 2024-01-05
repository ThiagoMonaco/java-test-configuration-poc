package configuration;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;

@DataJpaTest
public class IntegrationTestConfiguration implements BeforeAllCallback, AfterAllCallback {

    private PostgreSQLContainer<?> postgres;

    @Override
    public void beforeAll(ExtensionContext context) {
        postgres = new PostgreSQLContainer<>("postgres:16.0")
                .withDatabaseName("prop")
                .withUsername("postgres")
                .withPassword("pass")
                .withExposedPorts(5432);

        postgres.start();
        String jdbcUrl = String.format("jdbc:postgresql://localhost:%d/prop", postgres.getFirstMappedPort());
        System.setProperty("spring.datasource.url", jdbcUrl);
        System.setProperty("spring.datasource.username", "postgres");
        System.setProperty("spring.datasource.password", "pass");
        System.setProperty("spring.jpa.hibernate.ddl-auto", "create-drop");
    }

    @Override
    public void afterAll(ExtensionContext context) {
        // do nothing, Testcontainers handles container shutdown
    }
}
