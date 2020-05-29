package com.app01.dispatcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyServletA", urlPatterns = {"/myServletA"})
public class MyServletA extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.append("This is servletASOS\n");
        System.out.println("This is servletA");
        RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher("/mySyervletB");
         requestDispatcher.forward(req, resp);
//        requestDispatcher.include(req, resp);
        pw.append("After dispatcher to B\n");
        System.out.println("After dispatcher to B");
    }
}
