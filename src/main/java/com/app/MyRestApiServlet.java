package com.app;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "myRestApiServlet", urlPatterns = {"/myRestApiServlet"})
public class MyRestApiServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        super.service(req, resp);
        long timeUsed = System.currentTimeMillis()-start;
        System.out.println("timeUsed: "+timeUsed);
//        resp.getOutputStream().write(("timeUsed: "+timeUsed).getBytes());
        resp.getWriter().append("timeUsed: "+timeUsed);


        /*String method = req.getMethod();
        System.out.println("service()"+method);
        resp.getWriter().append("service()"+method);*/

        // super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("I am in get");
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
//        Person person = new Person();
//        person.setAge(age);
//        person.setName(name);
//        resp.getWriter().append(person.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("I am in POST");
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));

        resp.getOutputStream().write(("SUCCESS REQUEST STATUS; name="+name+"; age="+age+"").getBytes());
    }
}
