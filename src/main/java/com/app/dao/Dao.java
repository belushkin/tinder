package com.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Dao <T> {

    public T findById(int id);
    public T hydrate(ResultSet resultSet) throws SQLException;
}
