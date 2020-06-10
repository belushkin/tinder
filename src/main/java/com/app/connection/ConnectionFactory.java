package com.app.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private final Properties properties;

    public ConnectionFactory(Properties properties) {
        this.properties = properties;
    }

    public Connection createConnection() {
        String url = properties.get("DB_API") + ":" + properties.get("DB_DRIVER") +
                "://" + properties.get("DB_ENDPOINT") + ":" +
                properties.get("DB_PORT") + "/" + properties.get("DB_DATABASE");
        try {
            return DriverManager.getConnection(
                    url,
                    properties.get("DB_USER").toString(),
                    properties.get("DB_PASSWORD").toString()
            );
        } catch (SQLException throwables) {
            throw new RuntimeException("Can't connect to the database " + throwables.getMessage());
        }
    }
}
