package com.app02.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "MySecondFilter", urlPatterns = {"/originalServlet"})
public class MySecondFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        PrintWriter pw = resp.getWriter();
        pw.append("This is second filter\n");
        System.out.println("This is second filter");

        String city = req.getParameter("city");
        if("Kyiv".equals(city) || "NY".equals(city)){
            System.out.println("@Before call to orginal servlet");
            pw.append("@Before call to orginal servlet\n");
            chain.doFilter(servletRequest, servletResponse);
            pw.append("@After call to orginal servlet\n");
            System.out.println("@After call to orginal servlet");
        } else {
            resp.getWriter().append("NOT PERMITTED CITY="+city+"\n");
        }

    }



    public void destroy() {
    }

}
