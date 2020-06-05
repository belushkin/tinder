package com.app.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.Statement;

public class DbConnectionTest {
    @InjectMocks
    private DbConnection dbConnection;
    @Mock
    private Connection mockConnection;
    @Mock
    private Statement mockStatement;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_mock_db_connection() throws Exception {
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockConnection.createStatement().execute((String) Mockito.any())).thenReturn(true);

        Assert.assertTrue(dbConnection.execute(""));
    }
}
