package com.geowind.hunong.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geowind.hunong.entities.EntityManagerHelper;
import com.geowind.hunong.entities.Zone;
import com.geowind.hunong.entities.ZoneDAO;

public class BZoneServlet extends BasicServlet {

	private static final long serialVersionUID = 8730822382741309731L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

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
		// 新增
		case "add":
			add(request, response);
			break;
		//删除
		case "delete":
			delete(request,response);
			break;
		default:
			break;
		}
	}

	/**
	 * 删除
	 * @param request
	 * @param response
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		
		
	}

	/**
	 * 添加
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ZoneDAO zoneDAO = new ZoneDAO();
		String zonename = request.getParameter("zonename");
		String type = request.getParameter("type");
		Double area = Double.parseDouble(request.getParameter("area"));
		String address = request.getParameter("address");
		Zone zone = new Zone();
		zone.setZonename(zonename);
		zone.setAddress(address);
		zone.setArea(area);
		zone.setType(type);
		zone.setValid(1);
		EntityManagerHelper.beginTransaction();
		try{
			zoneDAO.save(zone);
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
		ZoneDAO zoneDAO = new ZoneDAO();
		String zoneName = request.getParameter("zonename");
		double area = Double.parseDouble(request.getParameter("area"));
		String type = request.getParameter("type");
		String address = request.getParameter("address");
		Zone zone = (Zone) request.getSession().getAttribute("currentZone");
		zone.setZonename(zoneName);
		zone.setArea(area);
		zone.setType(type);
		zone.setAddress(address);
		EntityManagerHelper.beginTransaction();
		try {
			zoneDAO.update(zone);
			EntityManagerHelper.commit();
			this.out(response, "1");
		} catch (RuntimeException re) {
			this.out(response, "0");
		}
		
	}

	/**
	 * 详情
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ZoneDAO zoneDAO = new ZoneDAO();
		Zone zone = zoneDAO.findById(Integer.parseInt(request
				.getParameter("zoneId")));
		request.getSession().setAttribute("currentZone", zone);
		response.sendRedirect("back/editorzone.jsp");
		
	}

	/**
	 * 查找所有
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void searchAll(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ZoneDAO zoneDAO = new ZoneDAO();
		List<Zone> zoneList = zoneDAO.findAll();
		request.getSession().setAttribute("allZone", zoneList);
		response.sendRedirect("back/zone.jsp");
		
	}

}
