package com.app.dao;

import com.app.model.Like;
import com.app.model.Message;
import com.app.model.User;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProfileDao implements Dao {

    private Connection connection;

    public ProfileDao(Connection connection) {
        this.connection = connection;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        return users;
    }

    public void update(User current, User candidate) {

    }

    public List<Message> getAllMessagesByUser(User user) {
        List<Message> messages = new ArrayList<>();
        return messages;
    }

    public List<Like> getLikedProfilesByUser(User user) {
        List<Like> profiles = new ArrayList<>();
        return profiles;
    }

    public void createUser(User user) {
        try(Connection con = connection) {
            Statement statement = con.createStatement();
            String sql = String.format("INSERT INTO users(name, job, username, password) VALUES('%s', '%s', '%s', '%s')",
                    user.getName(),
                    user.getJob(),
                    user.getUsername(),
                    user.getPassword()
            );
            statement.execute(sql);
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
