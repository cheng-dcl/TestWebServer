package com.cheng.server;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * *
 * *  @author:dcl-Cheng
 * *  @blog:www.dcl-Cheng.com
 **/
public class AppStartUp implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Init Now!!!!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}