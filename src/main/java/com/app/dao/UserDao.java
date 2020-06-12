package com.app.dao;

import com.app.connection.DB;
import com.app.entities.User;
import com.app.utils.MyLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class UserDao implements Dao<User> {

    private final DB db;

    public UserDao(DB db) {
        this.db = db;
    }

    @Override
    public List<User> getAll() {
        MyLogger.info("Find all users");

        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try {
            ResultSet resultSet = this.db.executeQuery(sql);
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("picture"),
                        resultSet.getString("job"),
                        resultSet.getString("username"),
                        resultSet.getInt("next")
                ));
            }
            return users;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public User isValid(String username, String password) {
        MyLogger.info("Checking is valid user with " + username + ", " + password);
        String sql = String.format(
                "SELECT * FROM users WHERE username = '%s' AND password = md5('%s')",
                username,
                password
        );
        try {
            ResultSet resultSet = this.db.executeQuery(sql);
            if (resultSet.next()) {
                return hydrate(resultSet);
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void add(User user) {
        MyLogger.info("Adding new user to the database : " + user.getName());

        String sql = String.format(
                "INSERT INTO users (name, job, username, password, picture) VALUES ('%s', '%s', '%s', md5('%s'), '%s')",
                user.getName(),
                user.getJob(),
                user.getUsername(),
                user.getPassword(),
                user.getPicture()
        );
        this.db.execute(sql);
    }

    public void like(User who, User whom, String value) {
        MyLogger.info("User " + who.getName() + " liked user " + whom.getUsername());

        String delete = String.format(
                "DELETE FROM liked WHERE user_id = '%s' AND user_liked_id = '%s'",
                who.getId(),
                whom.getId()
        );
        String insert = String.format(
                "INSERT INTO liked (user_id, user_liked_id, value) VALUES ('%s', '%s', '%s')",
                who.getId(),
                whom.getId(),
                value
        );
        this.db.execute(delete);
        this.db.execute(insert);
    }

    public void addLastLoginTime(User user) {
        MyLogger.info("Adding last login time for user : " + user.getName());

        String sql = String.format(
                "INSERT INTO login_time (user_id) VALUES (%s)", user.getId()
        );
        this.db.execute(sql);
    }

    public LocalDateTime getLastLoginTime(User user) {
        MyLogger.info("Get last login time for user: " + user.getName());

        String sql = String.format(
                "SELECT timestamp FROM login_time WHERE user_id = %s ORDER BY id DESC LIMIT 1", user.getId()
        );
        try {
            ResultSet resultSet = this.db.executeQuery(sql);
            if (resultSet.next()) {
                resultSet.getTimestamp("timestamp").toLocalDateTime();
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User findById(int id) {
        MyLogger.info("Find user by id: " + id);

        String sql = String.format("SELECT * FROM users WHERE id = %s", id);
        try {
            ResultSet resultSet = this.db.executeQuery(sql);
            if (resultSet.next()) {
                return hydrate(resultSet);
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public User findFirst() {
        MyLogger.info("Find first user");

        String sql = String.format("SELECT * FROM users LIMIT 1");
        try {
            ResultSet resultSet = this.db.executeQuery(sql);
            if (resultSet.next()) {
                return hydrate(resultSet);
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private User hydrate(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("picture"),
                resultSet.getString("job"),
                resultSet.getString("username"),
                resultSet.getInt("next")
        );
    }
}
