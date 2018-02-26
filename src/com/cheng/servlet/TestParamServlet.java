package com.cheng.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * *  测试http传参
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
@WebServlet("/test")
public class TestParamServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("[TestParamServlet : init!]");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ip = req.getServletPath();
        int port = req.getRemotePort();
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("[TestParamServlet : doGet!] servletpath:"+ip + ",port:" + port + ",name:" + name + ",password:" + password);
        PrintWriter out = resp.getWriter();
        out.write(111);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("[TestParamServlet : destroy!]");
    }
}