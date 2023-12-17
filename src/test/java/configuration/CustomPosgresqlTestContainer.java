package configuration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;


public class CustomPosgresqlTestContainer extends PostgreSQLContainer<CustomPosgresqlTestContainer> {
    private static final String IMAGE_VERSION = "postgres:16.0";
    private static CustomPosgresqlTestContainer container;

    private CustomPosgresqlTestContainer() {
        super(IMAGE_VERSION);
    }

    public static CustomPosgresqlTestContainer getInstance() {
        if (container == null) {
            container = new CustomPosgresqlTestContainer();
            container.withReuse(true);
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.out.println("teste");
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> String.format("jdbc:postgresql://localhost:%d/prop", container.getFirstMappedPort()));
        registry.add("spring.datasource.username", () -> "postgres");
        registry.add("spring.datasource.password", () -> "pass");
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    static {
        getInstance().start();
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}