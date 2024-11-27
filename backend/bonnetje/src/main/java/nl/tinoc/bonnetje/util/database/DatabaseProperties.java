package nl.tinoc.bonnetje.util.database;

import nl.tinoc.bonnetje.exception.DatabasePropertiesLoadException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperties {
    private final Properties properties;

    public DatabaseProperties() {
        this(new Properties());
    }

    public DatabaseProperties(Properties properties) {
        this.properties = properties;
        try {
            InputStream resourceStream = getClass().getClassLoader().getResourceAsStream("database.properties");
            if (resourceStream == null) {
                throw new IOException("Resource not found.");
            }
            properties.load(resourceStream);
        } catch (IOException e) {
            throw new DatabasePropertiesLoadException("Error loading database properties", e);
        }
    }

    public String connectionString() {
        return properties.getProperty("connectionstring");
    }

    public String getDatabaseDriver() {
        return properties.getProperty("driver");
    }
}