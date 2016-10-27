package com.geowind.hunong.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geowind.hunong.entities.EntityManagerHelper;
import com.geowind.hunong.entities.Farmland;
import com.geowind.hunong.entities.FarmlandDAO;
import com.geowind.hunong.entities.Machine;
import com.geowind.hunong.entities.MachineDAO;
import com.geowind.hunong.entities.Machineowner;
import com.geowind.hunong.entities.MachineownerDAO;
import com.geowind.hunong.util.FileUploadUtil;

public class BMachineServlet extends BasicServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String op = request.getParameter("op");
		System.out.println(op);
		switch (op) {
		case "searchAll":
			searchAll(request, response);
			break;
		case "detail":
			detail(request, response);
			break;
		case "add":
			add(request, response);
			break;
		case "delete":
			delete(request, response);
			break;
		case "editor":
			editor(request, response);
			break;
		case "uploadImage":
			uploadImage(request, response);
			break;
		default:
			break;
		}
	}


	/**
	 * 编辑农机信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void editor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MachineDAO machineDAO = new MachineDAO();
		String ownername = request.getParameter("ownername");
		String plate = request.getParameter("plate");
		String type = request.getParameter("type");
		String brand = request.getParameter("brand");
		String horsepower = request.getParameter("horsepower");
		String overdate = request.getParameter("overdate");
		
		Machine machine = (Machine) request.getSession().getAttribute("currentMachine");
		MachineownerDAO machineownerDAO = new MachineownerDAO();
		List<Machineowner> machineowners = machineownerDAO.findByName(ownername);
		if(machineowners != null && machineowners.size()>0) {
			machine.setMachineowner(machineowners.get(0));
		}
		machine.setPlate(plate);
		machine.setType(type);
		machine.setBrand(brand);
		machine.setHorsepower(horsepower);
		try {
			machine.setOverdate(new SimpleDateFormat("yyyy-MM-dd").parse(overdate));
		} catch (ParseException e) {
			machine.setOverdate(null);
			e.printStackTrace();
		}
		try{
			machineDAO.update(machine);
			EntityManagerHelper.commit();
			this.out(response,"1");
		}catch(RuntimeException re){
			this.out(response, "0");
		}
	}


	/**
	 * 上传农机图片
	 * @param request
	 * @param response
	 */
	private void uploadImage(HttpServletRequest request, HttpServletResponse response) {
		ServletConfig servletConfig = this.getServletConfig();
        FileUploadUtil uploadUtil = new FileUploadUtil();
        Map<String, String> map = null;
		try {
			map = (Map<String, String>) uploadUtil.upload(servletConfig, request, response);
			if(map != null && map.size()>0) {
				MachineDAO machineDAO = new MachineDAO();
				Machine machine = (Machine) request.getSession().getAttribute("currentMachine");
				machine.setPicture(map.get("pic1"));
				EntityManagerHelper.beginTransaction();
				machineDAO.update(machine);
				EntityManagerHelper.commit();
				response.sendRedirect("back/addmachine.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 删除农机
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MachineDAO machineDAO = new MachineDAO();
		String machineId = request.getParameter("machineId");
		Machine machine = null;
		EntityManagerHelper.beginTransaction();
		
		try{
			machine = machineDAO.findById(Integer.parseInt(machineId));
			machine.setValid(0);
			machineDAO.update(machine);
			EntityManagerHelper.commit();
			this.out(response,"1");
		}catch(RuntimeException re){
			this.out(response, "0");
		}
	}


	/**
	 * 添加农机信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MachineDAO machineDAO = new MachineDAO();
		Machine machine = new Machine();
		String ownername = request.getParameter("ownername");
		String plate = request.getParameter("plate");
		String type = request.getParameter("type");
		String brand = request.getParameter("brand");
		String horsepower = request.getParameter("horsepower");
		String overdate = request.getParameter("overdate");
		
		System.out.println(ownername + " "+plate+ " "+ type+" "+brand + " " + horsepower+ " "+overdate);
		
		MachineownerDAO machineownerDAO = new MachineownerDAO();
		List<Machineowner> machineowners = machineownerDAO.findByName(ownername);
		if(machineowners != null && machineowners.size()>0) {
			machine.setMachineowner(machineowners.get(0));
		}
		machine.setPlate(plate);
		machine.setType(type);
		machine.setBrand(brand);
		machine.setHorsepower(horsepower);
		try {
			machine.setOverdate(new SimpleDateFormat("yyyy-MM-dd").parse(overdate));
		} catch (ParseException e) {
			machine.setOverdate(null);
			e.printStackTrace();
		}
		
		machine.setWorkstate(0);
		machine.setState(1);
		machine.setValid(1);
		EntityManagerHelper.beginTransaction();
		try{
			machineDAO.save(machine);
			EntityManagerHelper.commit();
			request.getSession().setAttribute("currentMachine", machine);
			this.out(response,"1");
		}catch(RuntimeException re){
			this.out(response, "0");
			re.printStackTrace();
		}
		
	}


	/**
	 * 农机详情
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MachineDAO machineDAO = new MachineDAO();
		try {
			Machine machine = machineDAO.findById(Integer.parseInt(request.getParameter("machineId")));
			request.getSession().setAttribute("currentMachine",
					machine);
			response.sendRedirect("back/editormachine.jsp");
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			//跳转至错误界面
		}
	}


	/**
	 * 查找所有农机信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void searchAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MachineDAO machineDAO = new MachineDAO();
		List<Machine> machineList = machineDAO.findByValid(1);
		if(machineList != null && machineList.size()>0) {
			request.getSession().setAttribute("allMachine", machineList);
			response.sendRedirect("back/machine.jsp");
		} else {
			//跳转至错误页面
			
		}
	}
}
