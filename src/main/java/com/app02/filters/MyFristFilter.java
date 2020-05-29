package com.app02.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "MyFirstFilter", urlPatterns = {"/originalServlet"})
public class MyFristFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        PrintWriter pw = resp.getWriter();
        pw.append("This is first filter\n");
        System.out.println("This is first filter");

        int age = Integer.parseInt(req.getParameter("age"));
        if(20<=age && age<=100){
            System.out.println("@Before call to orginal servlet");
            pw.append("@Before call to orginal servlet\n");
            filterChain.doFilter(servletRequest, servletResponse);
            pw.append("@After call to orginal servlet\n");
            System.out.println("@After call to orginal servlet");
        } else {
            resp.getWriter().append("NOT PERMITTED age="+age+"\n");
        }

    }

    @Override
    public void destroy() {

    }
}
