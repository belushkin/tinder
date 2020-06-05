package com.app.dao;

import com.app.entities.Message;
import com.app.entities.User;
import java.util.List;

public interface Dao <T> {

    public List<T> getAll();

    public void add(T value);

    public T findById(int id);

//    public List<User> list();
//
//    public void update(User current, User candidate);
//
//    public List<Message> getAllMessagesByUser(User user);
//
//    public List<Like> getLikedProfilesByUser(User user);
//
//    public void createUser(User user);

}
