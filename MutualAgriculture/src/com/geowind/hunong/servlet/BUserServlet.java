package com.geowind.hunong.servlet;

import java.io.IOException;
import java.util.List;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geowind.hunong.entities.User;
import com.geowind.hunong.service.UserService;
import com.geowind.hunong.service.impl.UserServiceImpl;

public class BUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");;
		response.setCharacterEncoding("utf-8");
		
		String op = request.getParameter("op");
		
		UserService userService = new UserServiceImpl(); 
		
		switch (op) {
		case "search":
			String type = request.getParameter("type");
			if(type.equals("v_farmer")) {
				if(request.getAttribute("allFarmer") == null) {
					List<User> farmerList = userService.search(null, type);
					request.getSession().setAttribute("allFarmer", farmerList);
				}
				response.sendRedirect("back/farmer.jsp");
			} else if(type.equals("v_machiner")) {
				if(request.getAttribute("allMachiner") == null) {
					List<User> farmerList = userService.search(null, type);
					request.getSession().setAttribute("allMachiner", farmerList);
				}
				response.sendRedirect("back/machiner.jsp");
			}
			
			break;

		default:
			break;
		}
	}

}
