package com.geowind.hunong.servlet;

import com.geowind.hunong.entity.Library;
import com.geowind.hunong.service.LibraryService;
import com.geowind.hunong.service.impl.LibraryServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kui on 2016/7/23.
 */
@WebServlet(name = "LibraryServlet")
public class LibraryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String methodName = request.getParameter("method");
        System.out.println(methodName);
        String resultJson = "";
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

    private String dealWithRequest(String methodName, HttpServletRequest request) {
        String resultJson = "";
        LibraryService ls = new LibraryServiceImpl();
        switch (methodName) {
            case "getTitles":
                int category = Integer.valueOf(request.getParameter("category"));
                int begin = Integer.valueOf(request.getParameter("begin"));
                System.out.println(category + " " + begin);
                Library library = ls.getTitles(category, begin);
                resultJson = new Gson().toJson(library);
                break;

        }
        return resultJson;

    }
}
