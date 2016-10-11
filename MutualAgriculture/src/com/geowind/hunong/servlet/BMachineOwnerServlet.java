package com.geowind.hunong.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geowind.hunong.entities.Machineowner;
import com.geowind.hunong.entities.MachineownerDAO;
import com.geowind.hunong.entities.User;
import com.geowind.hunong.entities.UserDAO;
import com.geowind.hunong.entity.MachineOwner;
import com.geowind.hunong.service.MachineOwnerService;
import com.geowind.hunong.service.MachineService;
import com.geowind.hunong.service.UserService;
import com.geowind.hunong.service.impl.MachineOwnerServiceImpl;
import com.geowind.hunong.service.impl.MachineServiceImpl;
import com.geowind.hunong.service.impl.UserServiceImpl;

public class BMachineOwnerServlet extends BasicServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		MachineOwnerService machineService = new MachineOwnerServiceImpl();

		String op = request.getParameter("op");

		switch (op) {
		case "searchAll":
			List<Machineowner> machinerOwnerList = machineService.search(null);
			request.getSession().setAttribute("allMachinerOwner",
					machinerOwnerList);
			response.sendRedirect("back/machineowner.jsp");
			break;

		// 详情
		case "detail":
			MachineownerDAO machineownerDAO = new MachineownerDAO();
			Machineowner machineowner = machineownerDAO.findById(Integer
					.parseInt(request.getParameter("machineOwnerId")));
			request.getSession().setAttribute("currentMachineOwner",
					machineowner);
			response.sendRedirect("back/editormachineowner.jsp");
			break;
		// 修改
		case "editor":
			break;

		//新增
		case "add":
			break;
		default:
			break;
		}
	}

}
