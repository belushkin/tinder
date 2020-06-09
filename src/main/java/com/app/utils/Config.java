package com.app.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum Config {
    INSTANCE;

    Properties properties;

    public Properties getProperties(String filename) throws IOException {
        properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream(filename);
        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("property file " + filename + " not found in the classpath");
        }
        return properties;
    }
}
