package com.app;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class MyProcessorTest {

    MyProcessor myProcessor = new MyProcessor();

    @Test
    public void readConfigTest() {
        String data = myProcessor.readConfig();
        assertEquals("Some useful information...", data);
    }
}
