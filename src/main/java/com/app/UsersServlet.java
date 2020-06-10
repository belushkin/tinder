package com.app;

import com.app.connection.ConnectionFactory;
import com.app.connection.DB;
import com.app.dao.UserDao;
import com.app.entities.User;
import com.app.utils.Config;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@WebServlet(name = "usersServlet", urlPatterns = {"/users"})
public class UsersServlet extends HttpServlet {

    private Configuration configuration;
    private Properties properties;
    private UserDao userDao;

    @Override
    public void init() {
        initFreemarker();
        initProperties();

        // init dao
        DB db = new DB(new ConnectionFactory(properties));
        userDao = new UserDao(db);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Template template = configuration.getTemplate("templates/people-list.ftl");

        User first = userDao.findFirst();

        Map<String, Object> input = new HashMap<>();

        PrintWriter pw = resp.getWriter();
        try (StringWriter out = new StringWriter()) {
            template.process(input, out);
            pw.append(out.getBuffer().toString());
            out.flush();
            pw.flush();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String submit = req.getParameter("submit");
        String userId = req.getParameter("user_id");

        System.out.println(submit);
        System.out.println(userId);
    }

    private void initFreemarker() {
        configuration = new Configuration();
        configuration.setIncompatibleImprovements(new Version("2.3.23"));
        configuration.setClassForTemplateLoading(UsersServlet.class, "/");
        configuration.setDefaultEncoding("UTF-8");
    }

    private void initProperties() {
        try {
            properties = Config.INSTANCE.getProperties("/config.ini");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
