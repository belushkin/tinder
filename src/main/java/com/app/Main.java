package com.app;

import com.app.MyProcessor;

import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) {
        MyProcessor myProcessor = new MyProcessor();
        String dataContent = myProcessor.readConfig();
        System.out.println(dataContent);
        System.out.println("---------------------");
        String dataAContent = myProcessor.readConfigA();
        System.out.println(dataAContent);
    }
}
