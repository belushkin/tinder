package com.app.dao;

import com.app.entities.User;
import com.app.utils.MyLogger;

import java.util.ArrayList;
import java.util.List;


public class UserDao implements Dao<User> {

    @Override
    public List<User> getAll() {
        MyLogger.info("retrieve all users");

        List<User> users = new ArrayList<>();
        return users;
    }

    @Override
    public User add(User user) {
//        Logger.info("addUserToCollection");
        return user;
    }

    public User findById(int id) {
        return new User();
        //throw new UnsupportedOperationException();
    }

//    public UserDao(Connection connection) {
//        this.connection = connection;
//    }
//
//    public List<User> getUsers() {
//        List<User> users = new ArrayList<>();
//        return users;
//    }
//
//    public void update(User current, User candidate) {
//
//    }
//
//    public List<Message> getAllMessagesByUser(User user) {
//        List<Message> messages = new ArrayList<>();
//        return messages;
//    }
//
//    public List<Like> getLikedProfilesByUser(User user) {
//        List<Like> profiles = new ArrayList<>();
//        return profiles;
//    }
//
//    public void createUser(User user) {
//        try(Connection con = connection) {
//            Statement statement = con.createStatement();
//            String sql = String.format("INSERT INTO users(name, job, username, password) VALUES('%s', '%s', '%s', '%s')",
//                    user.getName(),
//                    user.getJob(),
//                    user.getUsername(),
//                    user.getPassword()
//            );
//            statement.execute(sql);
//        } catch (Exception ex){
//            throw new RuntimeException(ex);
//        }
//    }

}
