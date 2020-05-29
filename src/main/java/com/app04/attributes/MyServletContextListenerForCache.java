package com.app04.attributes;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class MyServletContextListenerForCache implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("start.listenerForCache");
        Map<String, Integer> cacheData = initCacheData();
        MyCacheData.getInstance().setCacheData(cacheData);
        Object value = servletContextEvent.getServletContext().getAttribute("cachData");
        if(value==null){
            servletContextEvent.getServletContext().setAttribute("cachData", cacheData);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private Map<String, Integer> initCacheData(){
        Map<String, Integer> map = new HashMap<>();
        map.put("UA",1);
        map.put("USA",2);
        map.put("UK",3);
        return map;
    }
}
