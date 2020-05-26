package com.app;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse resp){
        System.out.println("Inside servlet");
        try {
            PrintWriter pw = resp.getWriter();
            pw.append("This is my API!!!");
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
