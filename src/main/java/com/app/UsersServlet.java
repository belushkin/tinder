package com.app;

import com.app.connection.ConnectionFactory;
import com.app.connection.DB;
import com.app.dao.UserDao;
import com.app.entities.User;
import com.app.services.UserService;
import com.app.utils.Config;
import com.app.utils.MyLogger;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@WebServlet(name = "usersServlet", urlPatterns = {"/users"})
public class UsersServlet extends HttpServlet {

    private Configuration configuration;
    private Properties properties;
    private UserService userService;

    @Override
    public void init() {
        initJDBCDriver();
        initFreemarker();
        initProperties();

        // init user service
        DB db = new DB(new ConnectionFactory(properties));
        userService = new UserService(new UserDao(db));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = userService.getUserByArgId(req.getParameter("id"));
        Template template = configuration.getTemplate("templates/people-list.ftl");


        // get last login time
        LocalDateTime lastLoginTime = userService.getLastLoginTime(user);

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("picture", user.getPicture());
        templateData.put("name", user.getName());
        templateData.put("job", user.getJob());
        templateData.put("user_id", user.getId());

        if (lastLoginTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            templateData.put("last_login_time", lastLoginTime.format(formatter));
            templateData.put("last_login_time_passed", ChronoUnit.DAYS.between(lastLoginTime, LocalDateTime.now()));
        }

        PrintWriter pw = resp.getWriter();
        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            pw.append(out.getBuffer().toString());
            out.flush();
            pw.flush();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String submit = req.getParameter("submit");
        String userId = req.getParameter("user_id");

        User user = userService.findById(Integer.parseInt(userId));
        if (user != null) {
            resp.sendRedirect("/tinder/users?id="+user.getNext());
        }
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
