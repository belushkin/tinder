package com.app.dao;

import com.app.model.Like;
import com.app.model.Message;
import com.app.model.User;
import java.util.List;

public interface Dao {

    public List<User> getUsers();

    public void update(User current, User candidate);

    public List<Message> getAllMessagesByUser(User user);

    public List<Like> getLikedProfilesByUser(User user);

    public void createUser(User user);

}
