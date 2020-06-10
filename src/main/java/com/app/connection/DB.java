package com.app.connection;

import java.sql.*;

public class DB {

    private final ConnectionFactory connectionFactory;

    public DB(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public boolean execute(String sql) {
        try(Connection con = connectionFactory.createConnection()) {
            return con.createStatement().execute(sql);
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public ResultSet executeQuery(String sql) {
        try(Connection con = connectionFactory.createConnection()) {
            return con.createStatement().executeQuery(sql);
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
