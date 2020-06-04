package com.app.dao;

import com.app.Config;
import com.app.model.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ProfileDao implements Dao {

    private Properties properties;

    public ProfileDao(Properties properties) {
        this.properties = properties;
    }

    public void create(Person p) {
        try(Connection con = getConnection();) {
            Statement statement = con.createStatement();
            String sql = String.format("INSERT INTO persons(name, age) VALUES('%s', %s)",
                    p.getName(), p.getAge());

            statement.execute(sql);
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgres://database-1.cl2n834aix67.us-east-1.rds.amazonaws.com:3306/myjdbc?useSSL=false","root","11111111");
    }

}
