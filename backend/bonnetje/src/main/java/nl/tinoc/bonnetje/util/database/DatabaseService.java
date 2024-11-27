package nl.tinoc.bonnetje.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseService {
    protected static DatabaseProperties databaseProperties = new DatabaseProperties();

    private DatabaseService() {
        throw new IllegalStateException("Utility class");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseProperties.connectionString());
    }
}