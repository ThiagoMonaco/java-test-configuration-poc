package configuration;

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
            container.withInitScript();
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

    static {
        getInstance().start();
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}