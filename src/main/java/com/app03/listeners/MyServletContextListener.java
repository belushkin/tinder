package com.app03.listeners;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("@@@@start");
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis()-start<=3000){
        }
        System.out.println("!!!!!!!!!!!!MyServletContextListener.contextInitialized()!!!!!!!!!!!!!!!!!!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("!!!!!!!!!!!!MyServletContextListener.contextDestroyed()!!!!!!!!!!!!!!!!!!");
    }

}
