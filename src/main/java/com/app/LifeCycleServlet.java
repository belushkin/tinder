package com.app;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "lifeCycleServlet", urlPatterns = {"/lifeCycleServlet"})
public class LifeCycleServlet extends HttpServlet {

    /*static {
        System.out.println("Static1{}");
    }

    static {
        System.out.println("Static2{}");
    }

    {
        System.out.println("init1 {}");
    }

    {
        System.out.println("init2 {}");
    }

    public LifeCycleServlet() {
        System.out.println("LifeCycleServlet()");
    }*/

    @Override
    public void init(){
        System.out.println("init(); hashCode()"+hashCode());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service(); hashCode()"+hashCode());
    }

    @Override
    public void destroy() {
        System.out.println("destroy(); hashCode()"+hashCode());
    }
}
