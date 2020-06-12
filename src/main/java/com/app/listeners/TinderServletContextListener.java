package com.app.listeners;

import com.app.connection.ConnectionFactory;
import com.app.connection.DB;
import com.app.controllers.UsersServlet;
import com.app.dao.UserDao;
import com.app.services.UserService;
import com.app.utils.Config;
import com.app.utils.MyLogger;
import freemarker.template.Configuration;
import freemarker.template.Version;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class TinderServletContextListener implements ServletContextListener {

    private Configuration configuration;
    private Properties properties;
    private UserService userService;

    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        MyLogger.info("context Initialized");

        ServletContext context=event.getServletContext();

        // Init basic servlet dependencies
        initJDBCDriver();
        initFreemarker();
        initProperties();

        // Init db connection and services
        DB db = new DB(new ConnectionFactory(properties));
        userService = new UserService(new UserDao(db));

        context.setAttribute("userService", userService);
        context.setAttribute("freemarker", configuration);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event)
    {
    }

    private void initFreemarker() {
        MyLogger.info("Init freemarker");

        configuration = new Configuration();
        configuration.setIncompatibleImprovements(new Version("2.3.23"));
        configuration.setClassForTemplateLoading(UsersServlet.class, "/");
        configuration.setDefaultEncoding("UTF-8");
    }

    private void initProperties() {
        MyLogger.info("Init app config file");

        try {
            properties = Config.INSTANCE.getProperties("/config.ini");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initJDBCDriver() {
        MyLogger.info("Init JDBC Driver");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
