package com.app.dao;

import java.sql.*;
import java.util.Properties;

public class DbConnection {

    private Connection dbConnection;
    private Properties properties;

    public DbConnection(Properties properties) {
        this.properties = properties;
    }

    public void getConnection() {
        try {
            dbConnection =  DriverManager.getConnection(
                    properties.get("DB_API") +
                            ":" +
                            properties.get("DB_DRIVER") +
                            "://" +
                            properties.get("DB_ENDPOINT") +
                            ":" +
                            properties.get("DB_PORT") +
                            "/" +
                            properties.get("DB_DATABASE"),
                    properties.get("DB_USER").toString(),
                    properties.get("DB_PASSWORD").toString()
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean execute(String sql) {
        try(Connection con = dbConnection) {
            return con.createStatement().execute(sql);
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public ResultSet executeQuery(String sql) {
        try(Connection con = dbConnection) {
            return con.createStatement().executeQuery(sql);
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
