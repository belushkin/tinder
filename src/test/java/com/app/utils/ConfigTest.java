package com.app.utils;

import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertTrue;

public class ConfigTest {

    @Test
    public void test_read_config_ini() throws IOException {
        Properties properties = Config.INSTANCE.getProperties("/config.ini");
        assertTrue(properties.size() > 0);
    }
}
