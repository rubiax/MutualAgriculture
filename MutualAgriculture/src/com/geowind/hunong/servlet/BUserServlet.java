package com.geowind.hunong.servlet;

import java.io.IOException;
import java.util.List;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.geowind.hunong.dao.UserDao;
import com.geowind.hunong.entities.EntityManagerHelper;
import com.geowind.hunong.entities.User;
import com.geowind.hunong.entities.UserDAO;
import com.geowind.hunong.service.UserService;
import com.geowind.hunong.service.impl.UserServiceImpl;

public class BUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");

		String op = request.getParameter("op");

		UserService userService = new UserServiceImpl();
		String type = null;
		switch (op) {
		//查找所有
		case "searchAll":
			type = request.getParameter("type");
			if (type.equals("v_farmer")) {
				List<User> farmerList = userService.search(null, type);
				request.getSession().setAttribute("allFarmer", farmerList);
				response.sendRedirect("back/farmer.jsp");
			} else if (type.equals("v_machiner")) {
				List<User> farmerList = userService.search(null, type);
				request.getSession().setAttribute("allMachiner", farmerList);
				response.sendRedirect("back/machiner.jsp");
			}

			break;
		//编辑信息
		case "detail":
			type = request.getParameter("type");
			if (type.equals("v_farmer")) {
				UserDAO userDAO = new UserDAO();
				User currentFarmer = userDAO.findById(request
						.getParameter("username"));
				request.getSession().setAttribute("currentFarmer",
						currentFarmer);
				response.sendRedirect("back/editorfarmer.jsp");
			} else if (type.equals("v_machiner")) {
				UserDAO userDAO = new UserDAO();
				User currentMachiner = userDAO.findById(request
						.getParameter("username"));
				request.getSession().setAttribute("currentMachienr",
						currentMachiner);
				response.sendRedirect("back/editormachiner.jsp");
			}
			break;

		default:
			break;
		}
	}

}
