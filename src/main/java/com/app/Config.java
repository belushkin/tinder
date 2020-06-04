package com.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public enum Config {
    INSTANCE;

    Properties properties;

    public Properties getProperties() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("config.ini"));
        } catch (IOException ignored) {

        }
        return properties;
    }
}
