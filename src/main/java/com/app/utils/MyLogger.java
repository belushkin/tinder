package com.app.utils;

import org.apache.log4j.Logger;

public class MyLogger {

    private static final Logger logger = Logger.getLogger("tinder");

    public static void info(String message) {
        logger.info(message);
    }

}
