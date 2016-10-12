package com.geowind.hunong.servlet;

import com.geowind.hunong.entity.Task;
import com.geowind.hunong.service.TaskService;
import com.geowind.hunong.service.impl.TaskServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Kui on 2016/7/22.
 */
@WebServlet(name = "TaskServlet")
public class TaskServlet extends HttpServlet {

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

    private String dealWithRequest(String methodName, HttpServletRequest request) {
        String resultJson = "";
        TaskService ts = new TaskServiceImpl();
        String userName;
        List<Task> taskList;

        switch (methodName) {
            case "getTaskInfo":
                userName = request.getParameter("username");
                taskList = ts.getTaskInfo(userName, 0);
                resultJson = new Gson().toJson(taskList);
                break;
            case "getHistoryTaskInfo":
                userName = request.getParameter("username");
                taskList = ts.getTaskInfo(userName, 1);
                resultJson = new Gson().toJson(taskList);
                break;
        }
        return resultJson;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
