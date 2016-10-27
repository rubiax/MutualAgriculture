package com.geowind.hunong.servlet;

import java.io.IOException;
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
import com.geowind.hunong.entities.UserDAO;
import com.geowind.hunong.entities.Zone;
import com.geowind.hunong.entities.ZoneDAO;
import com.geowind.hunong.util.FileUploadUtil;

public class BFarmlandServlet extends BasicServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String op = request.getParameter("op");
		
		switch (op) {
		case "searchAll":
			searchAll(request, response);
			break;
		case "detail":
			detail(request, response);
			break;
		case "editor":
			editor(request, response);
			break;
		case "delete":
			delete(request, response);
			break;
		case "add":
			add(request, response);
			break;
		case "uploadImage":
			uploadImage(request, response);
			break;
		default:
			break;
		}
	}

	/**
	 * 农田图片上传
	 * @param request
	 * @param response
	 */
	private void uploadImage(HttpServletRequest request,
			HttpServletResponse response) {
		ServletConfig servletConfig = this.getServletConfig();
        FileUploadUtil uploadUtil = new FileUploadUtil();
        Map<String, String> map = null;
		try {
			map = (Map<String, String>) uploadUtil.upload(servletConfig, request, response);
			if(map != null && map.size()>0) {
				FarmlandDAO farmlandDAO = new FarmlandDAO();
				Farmland farmland = (Farmland) request.getSession().getAttribute("currentFarmland");
				farmland.setPicture(map.get("pic1"));
				EntityManagerHelper.beginTransaction();
				farmlandDAO.update(farmland);
				EntityManagerHelper.commit();
				response.sendRedirect("back/addfarmland.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 添加农田
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
        FarmlandDAO farmlandDAO = new FarmlandDAO();
		String username = request.getParameter("username");
		String zonename = request.getParameter("zonename");
		String lal = request.getParameter("lal");
		String longitude = null;
		String latitude = null;
		if(!"".equals(lal) && lal != null) {
			lal = lal.substring(1, lal.length()-1);
			longitude = lal.split(",")[0];
			latitude = lal.split(",")[1];
		}
		double area = Double.parseDouble(request.getParameter("area"));
		String ph = request.getParameter("ph");
		String npk = request.getParameter("npk");
		String address = request.getParameter("address");
		String transtion = request.getParameter("transtion");
		UserDAO userDAO = new UserDAO();
		Farmland farmland = new Farmland();
		farmland.setUser(userDAO.findById(username));
		ZoneDAO zoneDAO = new ZoneDAO();
		farmland.setZone(zoneDAO.findByZonename(zonename).get(0));
		if(longitude != null && latitude != null) {
			farmland.setLatitude(Double.parseDouble(latitude));
			farmland.setLongitude(Double.parseDouble(longitude));
		} else {
			farmland.setLatitude(null);
			farmland.setLongitude(null);
		}
		farmland.setArea(area);
		farmland.setPh(ph);
		farmland.setNpk(npk);
		farmland.setAddress(address);
		farmland.setTranstion(transtion);
		farmland.setState(1);
		farmland.setValid(1);
		EntityManagerHelper.beginTransaction();
		try{
			farmlandDAO.save(farmland);
			EntityManagerHelper.commit();
			request.getSession().setAttribute("currentFarmland", farmland);
			this.out(response,"1");
		}catch(RuntimeException re){
			this.out(response, "0");
		}
		
	}

	/**
	 * 删除农田信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FarmlandDAO farmlandDAO = new FarmlandDAO();
		String farmlandId = request.getParameter("farmlandId");
		Farmland farmland = null;
		EntityManagerHelper.beginTransaction();
		
		try{
			farmland = farmlandDAO.findById(Integer.parseInt(farmlandId));
			farmland.setValid(0);
			farmlandDAO.update(farmland);
			EntityManagerHelper.commit();
			this.out(response,"1");
		}catch(RuntimeException re){
			this.out(response, "0");
		}
	}

	/**
	 * 编辑农田信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void editor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FarmlandDAO farmlandDAO = new FarmlandDAO();
		String username = request.getParameter("username");
		String zonename = request.getParameter("zonename");
		String lal = request.getParameter("lal");
		String longitude = null;
		String latitude = null;
		if(!"".equals(lal) && lal != null) {
			lal = lal.substring(1, lal.length()-1);
			longitude = lal.split(",")[0];
			latitude = lal.split(",")[1];
		}
		double area = Double.parseDouble(request.getParameter("area"));
		String ph = request.getParameter("ph");
		String npk = request.getParameter("npk");
		String address = request.getParameter("address");
		String transtion = request.getParameter("transtion");
		Farmland farmland = (Farmland) request.getSession().getAttribute("currentFarmland");
		UserDAO userDAO = new UserDAO();
		farmland.setUser(userDAO.findById(username));
		ZoneDAO zoneDAO = new ZoneDAO();
		farmland.setZone(zoneDAO.findByZonename(zonename).get(0));
		if(longitude != null && latitude != null) {
			farmland.setLatitude(Double.parseDouble(latitude));
			farmland.setLongitude(Double.parseDouble(longitude));
		} else {
			farmland.setLatitude(null);
			farmland.setLongitude(null);
		}
		farmland.setArea(area);
		farmland.setPh(ph);
		farmland.setNpk(npk);
		farmland.setAddress(address);
		farmland.setTranstion(transtion);
		EntityManagerHelper.beginTransaction();
		try{
			farmlandDAO.update(farmland);
			EntityManagerHelper.commit();
			request.getSession().setAttribute("currentFarmland", farmland);
			this.out(response,"1");
		}catch(RuntimeException re){
			this.out(response, "0");
		}
		
	}

	/**
	 * 查看农田详情
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FarmlandDAO farmlandDAO = new FarmlandDAO();
		try {
			Farmland farmland = farmlandDAO.findById(Integer.parseInt(request.getParameter("farmlandId")));
			request.getSession().setAttribute("currentFarmland",
					farmland);
			response.sendRedirect("back/editorfarmland.jsp");
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			//跳转至错误界面
		}
	}

	/**
	 * 查找所有农田信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void searchAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FarmlandDAO farmlandDAO = new FarmlandDAO();
		List<Farmland> farmlandList = farmlandDAO.findByValid(1);
		if(farmlandList != null && farmlandList.size()>0) {
			request.getSession().setAttribute("allFarmland", farmlandList);
			response.sendRedirect("back/farmland.jsp");
		} else {
			//跳转至错误页面
			
		}
	}
}
