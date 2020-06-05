package com.app.dao;

import java.sql.*;
import java.util.Properties;

public class ConnectionFactory {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgres://database-1.cl2n834aix67.us-east-1.rds.amazonaws.com:5432/myjdbc","postgres","LhlyOD1JvL2XnLHO2xoE");//?useSSL=false
    }

    public boolean execute(String sql) {
        try(Connection con = getConnection()) {
            Statement statement = con.createStatement();
            return statement.execute(sql);
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public ResultSet executeQuery(String sql) {
        try(Connection con = getConnection()) {
            Statement statement = con.createStatement();
            return statement.executeQuery(sql);
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
