package com.geowind.hunong.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geowind.hunong.entities.Center;
import com.geowind.hunong.entities.EntityManagerHelper;
import com.geowind.hunong.entities.MachineDAO;
import com.geowind.hunong.entities.Machineowner;
import com.geowind.hunong.entities.MachineownerDAO;
import com.geowind.hunong.entities.User;
import com.geowind.hunong.entities.UserDAO;
import com.geowind.hunong.entities.Zone;
import com.geowind.hunong.entities.ZoneDAO;
import com.geowind.hunong.service.MachineOwnerService;
import com.geowind.hunong.service.MachineService;
import com.geowind.hunong.service.UserService;
import com.geowind.hunong.service.impl.MachineOwnerServiceImpl;
import com.geowind.hunong.service.impl.MachineServiceImpl;
import com.geowind.hunong.service.impl.UserServiceImpl;

import javafx.scene.input.DataFormat;

public class BMachineOwnerServlet extends BasicServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

	

		String op = request.getParameter("op");

		switch (op) {
		case "searchAll":
			searchAll(request,response);
			break;

		// 详情
		case "detail":
			detail(request,response);
			break;
		// 修改
		case "editor":
			editor(request,response);
			break;

		//新增
		case "add":
			add(request,response);
			break;
		//删除
		case "delete":
			delete(request,response);
		default:
			break;
		}
	}

	/**
	 * 删除
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MachineownerDAO machineOwnerDAO = new MachineownerDAO();
		int ownerId = Integer.parseInt(request.getParameter("ownerId"));
		Machineowner machineOwner = machineOwnerDAO.findById(ownerId);
		EntityManagerHelper.beginTransaction();
		try{
			machineOwnerDAO.delete(machineOwner);
			EntityManagerHelper.commit();
			this.out(response,"1");
		}catch(Exception e){
			this.out(response, "0");
		}

		
	}

	/**
	 * 农机详情
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MachineownerDAO machineownerDAO = new MachineownerDAO();
		Machineowner machineowner = machineownerDAO.findById(Integer.parseInt(request.getParameter("ownerId")));
		request.getSession().setAttribute("currentMachineOwner",
				machineowner);
		response.sendRedirect("back/editormachineowner.jsp");
		
	}

	/**
	 * 搜索所有农机拥有者
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void searchAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MachineOwnerService machineService = new MachineOwnerServiceImpl();
		List<Machineowner> machinerOwnerList = machineService.search(null);
		request.getSession().setAttribute("allMachinerOwner",
				machinerOwnerList);
		response.sendRedirect("back/machineowner.jsp");
		
	}

	/**
	 * 添加
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MachineownerDAO machineOwnerDAOAdd = new MachineownerDAO();
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		Machineowner machineOwner = new Machineowner();
		machineOwner.setName(name);
		machineOwner.setAddress(address);
		machineOwner.setSex(sex);
		machineOwner.setPhone(phone);
		Date date = new Date();
		machineOwner.setBirthday(date);
		Center center = new Center();
		center.setCenterId(1);
		machineOwner.setValid(1);
		machineOwner.setCenter(center);
		EntityManagerHelper.beginTransaction();
		try{
			machineOwnerDAOAdd.save(machineOwner);
			EntityManagerHelper.commit();
			this.out(response,"1");
		}catch(Exception e){
			this.out(response,"0");
		}
		
	}

	/**
	 * 修改
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void editor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MachineownerDAO machineOwnerDAOEditor = new MachineownerDAO();
		String username = request.getParameter("username");
		String sex = request.getParameter("sex");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		Machineowner machineOwner = (Machineowner) request.getSession().getAttribute("currentMachineOwner");
		machineOwner.setName(username);
		machineOwner.setAddress(address);
		machineOwner.setSex(sex);
		machineOwner.setPhone(phone);
		EntityManagerHelper.beginTransaction();
		try{
			machineOwnerDAOEditor.update(machineOwner);
			EntityManagerHelper.commit();
			this.out(response,"1");
		}catch(RuntimeException re){
			this.out(response, "0");
		}
		
		
	}

}
