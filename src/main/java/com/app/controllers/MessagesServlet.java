package com.app.controllers;

import com.app.entities.User;
import com.app.services.MessageService;
import com.app.services.UserService;
import com.app.utils.MyLogger;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "messagesServlet", urlPatterns = {"/messages"})
public class MessagesServlet extends HttpServlet {

    private Configuration freemarker;
    private UserService userService;
    private MessageService messageService;

    @Override
    public void init() {
        MyLogger.info("Init messages servlet");

        userService = (UserService) getServletContext().getAttribute("userService");
        messageService = (MessageService) getServletContext().getAttribute("messageService");
        freemarker = (Configuration) getServletContext().getAttribute("freemarker");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User current = userService.findById((Integer) session.getAttribute("user_id"));

        String userId = req.getParameter("user_id");
        User messageUser = userService.findById(Integer.parseInt(userId));

        Template template = freemarker.getTemplate("templates/chat.ftl");

//        List<User> likedUsers = userService.getAllLikedProfilesByUser(current);

        Map<String, Object> templateData = new HashMap<>();
//        templateData.put("users", likedUsers);

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
