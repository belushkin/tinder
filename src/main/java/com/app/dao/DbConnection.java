package com.app.dao;

import java.sql.*;

public class DbConnection {

    private Connection dbConnection;

    private void getConnection() throws SQLException {
        dbConnection =  DriverManager.getConnection(
                "jdbc:postgresql://database-1.cl2n834aix67.us-east-1.rds.amazonaws.com:5432/tinder",
                "postgres",
                "LhlyOD1JvL2XnLHO2xoE");//?useSSL=false
    }

    public boolean execute(String sql) {
        try(Connection con = dbConnection) {
            return con.createStatement().execute(sql);
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }

//        try(Connection con = dbConnection) {
//            return con.createStatement().execute(sql);
//        } catch (Exception ex){
//            throw new RuntimeException(ex);
//        }
    }

//    public ResultSet executeQuery(String sql) {
//        try(Connection con = getConnection()) {
//            Statement statement = con.createStatement();
//            return statement.executeQuery(sql);
//        } catch (Exception ex){
//            throw new RuntimeException(ex);
//        }
//    }

}
