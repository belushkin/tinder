package com.app.controllers;

import com.app.entities.User;
import com.app.services.UserService;
import com.app.utils.MyLogger;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "loginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private Configuration freemarker;
    private UserService userService;

    @Override
    public void init() {
        MyLogger.info("Init login servlet");

        userService = (UserService) getServletContext().getAttribute("userService");
        freemarker = (Configuration) getServletContext().getAttribute("freemarker");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Template template = freemarker.getTemplate("templates/login.ftl");
        Map<String, Object> templateData = new HashMap<>();

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.isValid(username, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user_id", user.getId());

            // Storing last login time
            userService.addLastLoginTime(user);

            resp.sendRedirect("/users");
        } else {
            Template template = freemarker.getTemplate("templates/login.ftl");
            Map<String, Object> templateData = new HashMap<>();

            templateData.put("error", "Invalid username or password");

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
    }

}
