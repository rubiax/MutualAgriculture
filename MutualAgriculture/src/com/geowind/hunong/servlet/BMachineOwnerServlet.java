package com.geowind.hunong.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geowind.hunong.entities.Center;
import com.geowind.hunong.entities.EntityManagerHelper;
import com.geowind.hunong.entities.Machineowner;
import com.geowind.hunong.entities.MachineownerDAO;
import com.geowind.hunong.service.MachineOwnerService;
import com.geowind.hunong.service.impl.MachineOwnerServiceImpl;


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
		MachineownerDAO machineownerDAO = new MachineownerDAO();
		String ownerId = request.getParameter("ownerId");
		//List<Machineowner> list = (List<Machineowner>) request.getSession().getAttribute("allMachinerOwner");
		Machineowner machineowner = null;
		EntityManagerHelper.beginTransaction();
		
		try{
			machineowner = machineownerDAO.findById(Integer.parseInt(ownerId));
			/*for(int i=0; i<list.size(); i++) {
				if(list.get(i).getOwnerId() == Integer.parseInt(ownerId)) {
					machineowner = list.get(i);
					list.remove(i);
					break;
				}
			}*/
			machineowner.setValid(0);
			machineownerDAO.update(machineowner);
			EntityManagerHelper.commit();
			//request.getSession().setAttribute("allMachinerOwner", list);
			this.out(response,"1");
		}catch(RuntimeException re){
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
		try {
			Machineowner machineowner = machineownerDAO.findById(Integer.parseInt(request.getParameter("ownerId")));
			request.getSession().setAttribute("currentMachineOwner",
					machineowner);
			response.sendRedirect("back/editormachineowner.jsp");
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
		}
		
		
	}

	/**
	 * 搜索所有农机拥有者
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void searchAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MachineOwnerService machineService = new MachineOwnerServiceImpl();
		int centerId = (int) request.getSession().getAttribute("currentCenterId");
		System.out.println(centerId);
		List<Machineowner> machinerOwnerList = machineService.search(centerId);
		if(machinerOwnerList != null && machinerOwnerList.size()>0) {
			request.getSession().setAttribute("allMachinerOwner",
					machinerOwnerList);
			response.sendRedirect("back/machineowner.jsp");
		} else {
			//跳转出错页面
		}
		
		
	}

	/**
	 * 添加
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//List<Machineowner> list = (List<Machineowner>) request.getSession().getAttribute("allMachinerOwner");
		int centerId = (int)request.getSession().getAttribute("currentCenterId");
		MachineownerDAO machineOwnerDAOAdd = new MachineownerDAO();
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String birthday = request.getParameter("birthday");
		Machineowner machineOwner = new Machineowner();
		machineOwner.setName(name);
		machineOwner.setAddress(address);
		machineOwner.setSex(sex);
		machineOwner.setPhone(phone);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			machineOwner.setBirthday(sdf.parse(birthday));
		} catch (ParseException e1) {
			machineOwner.setBirthday(null);
		}
		Center center = new Center();
		center.setCenterId(centerId);
		machineOwner.setCenter(center);
		machineOwner.setValid(1);
		EntityManagerHelper.beginTransaction();
		try{
			machineOwnerDAOAdd.save(machineOwner);
			EntityManagerHelper.commit();
			//list.add(machineOwner);
			//request.getSession().setAttribute("allMachinerOwner", list);
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
		MachineownerDAO machineOwnerDAO = new MachineownerDAO();
		String username = request.getParameter("username");
		String sex = request.getParameter("sex");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String birthday = request.getParameter("birthday");
		Machineowner machineOwner = (Machineowner) request.getSession().getAttribute("currentMachineOwner");
		machineOwner.setName(username);
		machineOwner.setAddress(address);
		machineOwner.setSex(sex);
		machineOwner.setPhone(phone);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			machineOwner.setBirthday(sdf.parse(birthday));
		} catch (ParseException e) {
			machineOwner.setBirthday(null);
		}
		EntityManagerHelper.beginTransaction();
		try{
			machineOwnerDAO.update(machineOwner);
			EntityManagerHelper.commit();
			/*List<Machineowner> list = (List<Machineowner>) request.getSession().getAttribute("allMachinerOwner");
			int temp = -1;
			for(int i=0; i<list.size(); i++) {
				if(list.get(i).getOwnerId() == machineOwner.getOwnerId()) {
					temp = i;
					break;
				}
			}
			if(temp != -1) {
				list.remove(temp);
				list.add(machineOwner);
			}
			request.getSession().setAttribute("allMachinerOwner", list);*/
			request.getSession().setAttribute("currentMachineOwner", machineOwner);
			this.out(response,"1");
		}catch(RuntimeException re){
			this.out(response, "0");
		}
		
		
	}

}
