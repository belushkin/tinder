package com.app.controllers;

import com.app.entities.Message;
import com.app.entities.User;
import com.app.services.MessageService;
import com.app.services.UserService;
import com.app.utils.MyLogger;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.RequestDispatcher;
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

        List<Message> messages = messageService.getMessages(current, messageUser);

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("messages", messages);
        templateData.put("currentUserId", current.getId());
        templateData.put("messageUserName", messageUser.getName());
        templateData.put("messageUserId", messageUser.getId());

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        String message = req.getParameter("message");

        User fromUser = userService.findById(Integer.parseInt(from));
        User toUser = userService.findById(Integer.parseInt(to));

        messageService.addMessage(fromUser, toUser, message);

        resp.sendRedirect("/tinder/messages?user_id=" + Integer.parseInt(to));
    }
}
