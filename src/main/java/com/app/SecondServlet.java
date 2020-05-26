package com.app;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "secondServlet", urlPatterns = {"/secondServlet", "/secondServletA"})
public class SecondServlet  extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("I am inside servlet 2");
        ServletOutputStream out = resp.getOutputStream();
        // resp.getWriter();
        String json = "{'name':'myName','age':'myage'}";
        byte[] data = json.getBytes();
        out.write(data);
    }

}
