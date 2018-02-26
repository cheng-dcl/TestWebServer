package com.cheng.servlet;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * *
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
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