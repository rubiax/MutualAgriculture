package com.geowind.hunong.servlet;

import com.geowind.hunong.service.UserService;
import com.geowind.hunong.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kui on 2016/7/20.
 */
@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String methodName = request.getParameter("method");
        String resultJson = "";
        System.out.println(methodName);
        if (methodName == null) {
            resultJson = null;
        } else {
            // 根据请求的方法，返回对应信息 resultJson
            resultJson = dealWithRequest(methodName, request);
        }
        //response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        System.out.println(resultJson);
        response.getWriter().write(resultJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    /**
     * 处理用户请求
     * @param methodName
     * @param request
     * @return
     */
    private String dealWithRequest(String methodName, HttpServletRequest request) {
        String resultJson = "";

        switch (methodName) {
            case "login":
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                resultJson = login(username, password);
                break;



        }
        return resultJson;

    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    private String login(String username, String password) {
        System.out.println(username + " " + password);
        UserService us = new UserServiceImpl();
        String result = us.login(username,password);
        return  result;
    }


}
