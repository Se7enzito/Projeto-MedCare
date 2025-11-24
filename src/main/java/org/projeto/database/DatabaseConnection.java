package org.projeto.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");

            // Diretório padrão do projeto (pasta "data")
            File dataFolder = new File("data");
            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }

            File databaseFile = new File(dataFolder, "database.db");
            String url = "jdbc:sqlite:" + databaseFile.getAbsolutePath();

            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url);
            }

        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found", e);
        }

        return connection;
    }

}
