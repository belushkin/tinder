package com.app.connection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.Statement;

@RunWith(MockitoJUnitRunner.class)
public class DBTest {
    @InjectMocks
    private DB db;
    @Mock
    private Connection mockConnection;
    @Mock
    private Statement mockStatement;
    @Mock
    private ConnectionFactory connectionFactory;

    @Test
    public void test_execute_sql_by_db_class() throws Exception {
        Mockito.when(connectionFactory.createConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockConnection.createStatement().execute(Mockito.any())).thenReturn(true);

        Assert.assertTrue(db.execute(""));
    }
}
