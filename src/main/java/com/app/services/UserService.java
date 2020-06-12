package com.app.services;

import com.app.dao.UserDao;
import com.app.entities.User;

import java.time.LocalDateTime;
import java.util.List;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User findById(int id) {
        return userDao.findById(id);
    }

    public LocalDateTime getLastLoginTime(User user) {
        return userDao.getLastLoginTime(user);
    }

    public void addLastLoginTime(User user) {
        userDao.addLastLoginTime(user);
    }

    public User getUserByArgId(String userId) {
        if (userId == null) {
            return userDao.findFirst();
        }

        User user = userDao.findById(Integer.parseInt(userId));
        if (user == null) {
            return userDao.findFirst();
        }
        return user;
    }
}
