package com.app.controllers;

import com.app.entities.User;
import com.app.services.UserService;
import com.app.utils.MyLogger;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class UsersServlet extends HttpServlet {

    private Configuration freemarker;
    private UserService userService;

    @Override
    public void init() {
        MyLogger.info("Init users servlet");

        userService = (UserService) getServletContext().getAttribute("userService");
        freemarker = (Configuration) getServletContext().getAttribute("freemarker");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();

        User user = userService.getUserByArgId(req.getParameter("id"));
        User current = userService.findById((Integer) session.getAttribute("user_id"));

        // Preventing voting by current user for yourself
        if (user.getId() == current.getId()) {
            user = userService.findById(user.getNext());
        }

        Template template = freemarker.getTemplate("templates/people-list.ftl");

        // get last login time
        LocalDateTime lastLoginTime = userService.getLastLoginTime(user);

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("picture", user.getPicture());
        templateData.put("name", user.getName());
        templateData.put("username", user.getUsername());
        templateData.put("job", user.getJob());
        templateData.put("user_id", user.getId());
        templateData.put("current_user_name", current.getName());

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

        HttpSession session = req.getSession();
        User current = userService.findById((Integer) session.getAttribute("user_id"));
        User voted = userService.findById(Integer.parseInt(userId));
        userService.like(current, voted, submit);
        resp.sendRedirect("/tinder/users?id="+voted.getNext());
    }
}
