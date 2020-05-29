package com.app04.attributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "cacheServlet", urlPatterns = "/cacheServlet")
public class CacheServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Integer> cacheData = MyCacheData.getInstance().getCacheData();
        Map<String, Integer> cacheData2 =
                (Map<String, Integer>) req.getServletContext().getAttribute("cachData");

        resp.getWriter().append(cacheData.toString()+"\n");
        resp.getWriter().append("#########################"+"\n");
        resp.getWriter().append(cacheData2.toString()+"\n");


    }
}
