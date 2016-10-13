package com.geowind.hunong.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geowind.hunong.entities.Admin;
import com.geowind.hunong.entities.AdminDAO;

public class AdminServlet extends BasicServlet {

	private static final long serialVersionUID = -1653380860409961910L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String op = request.getParameter("op");
		
		switch (op) {
		case "login":
			login(request, response);
			break;

		default:
			break;
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		AdminDAO adminDAO = new AdminDAO();
		List<Admin> admins = adminDAO.findByAname(username);
		if(admins != null && admins.size()>0) {
			Admin admin = admins.get(0);
			if(admin.getPwd().equals(pwd)) {
				request.getSession().setAttribute("currentAdmin", admin);
				request.getSession().setAttribute("currentCenter", admin.getCenter());
				request.getSession().setAttribute("currentCenterId", admin.getCenter().getCenterId());
				this.out(response, "1");
			} else {
				this.out(response, "0");
			}
		}
	}
}
