package data.repository;

import configuration.AbstractIntegrationTest;
import configuration.CustomPosgresqlTestContainer;
import configuration.CustomTestConfiguration;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CustomTestConfiguration.class)
public class UserRepositoryIntegrationTestDois {

    @ClassRule
    public static PostgreSQLContainer<CustomPosgresqlTestContainer> postgreSQLContainer = CustomPosgresqlTestContainer.getInstance();


    @Test
    public void test() {
        System.out.println("DB_URL2: " + postgreSQLContainer.getJdbcUrl());
        System.out.println("DB_USERNAME2: " + postgreSQLContainer.getUsername());
        System.out.println("DB_PASSWORD2: " + postgreSQLContainer.getPassword());
    }
}
