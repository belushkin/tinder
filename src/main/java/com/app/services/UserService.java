package com.app.services;

import com.app.dao.Dao;
import com.app.dao.UserDao;
import com.app.entities.User;

import java.util.List;

public class UserService {

    private Dao<User> userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public User findById(int id) {
        return userDao.findById(id);
    }

}
