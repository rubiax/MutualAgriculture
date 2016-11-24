package com.geowind.hunong.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geowind.hunong.entity.FarmlandPoint;
import com.geowind.hunong.entity.Point;
import com.geowind.hunong.jpa.Center;
import com.geowind.hunong.jpa.EntityManagerHelper;
import com.geowind.hunong.jpa.Farmland;
import com.geowind.hunong.jpa.FarmlandDAO;
import com.geowind.hunong.jpa.Zone;
import com.geowind.hunong.jpa.ZoneDAO;
import com.geowind.hunong.service.ZoneService;
import com.geowind.hunong.service.impl.ZoneServiceImpl;
import com.geowind.hunong.util.PointSelector;

public class BZoneServlet extends BasicServlet {

	private static final long serialVersionUID = 8730822382741309731L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		// 删除
		case "delete":
			delete(request, response);
			break;
		case "isExistZone":
			isExistZone(request, response);
			break;
		case "MapSearchAll":
			MapSearchAll(request, response);
			break;
		case "MapSearchZonePoint":
			MapSearchZonePoint(request,response);
			break;
		default:
			break;
		}
	}

	/**
	 * 获得分区凸点并发送给前端
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void MapSearchZonePoint(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		ZoneDAO zoneDAO = new ZoneDAO();
		List<Zone> zoneList = zoneDAO.findAll();
		
		if(zoneList!=null&&zoneList.size()>0){
			
			List<FarmlandPoint> farmlandPointList = new ArrayList<FarmlandPoint>();

			//  根据分区数量遍历得出数据
			for(int i=0;i<zoneList.size();i++){
				Set<Farmland> farmlandList = new HashSet<Farmland>();
				//获得指定分区下的农田
				farmlandList = zoneList.get(i).getFarmlands();
				
				if(farmlandList!=null&&farmlandList.size()>0){
					List<Point> p = new ArrayList<Point>();
					
					Iterator<Farmland> f = farmlandList.iterator();
			
					while(f.hasNext()){
						
						Point p1 = new Point();
						Farmland tmp = f.next();
						System.out.println(tmp.getLongitude()+","+tmp.getLatitude());
						p1.setX(tmp.getLongitude());
						p1.setY(tmp.getLatitude());
						
						p.add(p1);
						
					}
					
					PointSelector ps = new PointSelector(p);
					p = ps.GetHullPoints();
					
					FarmlandPoint fp = new FarmlandPoint();
					fp.setPointList(p);
					farmlandPointList.add(fp);
				}else{
					System.out.println(zoneList.get(i).getZonename()+"没有农田");
				}
				
			}
			this.out(response, farmlandPointList);
			
		}else{
			this.out(response, 0);
		}
		
	
		
	}

	private void MapSearchAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ZoneDAO zoneDAO = new ZoneDAO();
		List<Zone> zoneList = zoneDAO.findAll();
		if(zoneList!=null&&zoneList.size()>0){
			this.out(response, zoneList);
		}else{
			this.out(response, 0);
		}
	}

	private void isExistZone(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String zonename = request.getParameter("zonename");
		ZoneDAO zoneDAO = new ZoneDAO();
		List<Zone> zoneList = zoneDAO.findByZonename(zonename);
		if (zoneList != null && zoneList.size() > 0) {
			Zone zone = zoneList.get(0);
			this.out(response, "{\"mark\":\"1\",\"type\":\"" + zone.getType() + "\"}");
		} else {
			this.out(response, "{\"mark\":\"0\"}");
		}
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ZoneDAO zoneDAO = new ZoneDAO();
		String zoneId = request.getParameter("zoneId");
		Zone zone = null;
		EntityManagerHelper.beginTransaction();

		try {
			zone = zoneDAO.findById(Integer.parseInt(zoneId));
			zone.setValid(0);
			zoneDAO.update(zone);
			EntityManagerHelper.commit();
			this.out(response, "1");
		} catch (RuntimeException re) {
			this.out(response, "0");
		}

	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ZoneDAO zoneDAO = new ZoneDAO();
		// List<Zone> list = (List<Zone>)
		// request.getSession().getAttribute("allZone");
		String zonename = request.getParameter("zonename");
		String type = request.getParameter("type");
		double area = Double.parseDouble(request.getParameter("area"));
		String address = request.getParameter("address");
		// System.out.println(zonename+" "+area+" "+type+" "+address);
		Zone zone = new Zone();
		zone.setZonename(zonename);
		zone.setAddress(address);
		zone.setArea(area);
		zone.setType(type);
		zone.setValid(1);
		Center center = new Center();
		center.setCenterId((int) request.getSession().getAttribute("currentCenterId"));
		zone.setCenter(center);
		EntityManagerHelper.beginTransaction();
		try {
			zoneDAO.save(zone);
			EntityManagerHelper.commit();
			// list.add(zone);
			// request.getSession().setAttribute("allZone", list);
			this.out(response, "1");
		} catch (Exception e) {
			this.out(response, "0");
		}

	}

	/**
	 * 修改
	 * 
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
		// System.out.println(zoneName+" "+area+" "+type+" "+address);
		Zone zone = (Zone) request.getSession().getAttribute("currentZone");
		zone.setZonename(zoneName);
		zone.setArea(area);
		zone.setType(type);
		zone.setAddress(address);
		EntityManagerHelper.beginTransaction();
		try {
			zoneDAO.update(zone);
			EntityManagerHelper.commit();
			/*
			 * List<Zone> list = (List<Zone>)
			 * request.getSession().getAttribute("allZone"); int temp = -1;
			 * for(int i=0; i<list.size(); i++) { if(list.get(i).getZoneId() ==
			 * zone.getZoneId()) { temp = i; break; } } if(temp != -1) {
			 * list.remove(temp); list.add(zone); }
			 * request.getSession().setAttribute("allZone", list);
			 */
			request.getSession().setAttribute("currentZone", zone);
			this.out(response, "1");
		} catch (RuntimeException re) {
			this.out(response, "0");
		}

	}

	/**
	 * 详情
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ZoneDAO zoneDAO = new ZoneDAO();
		try {
			Zone zone = zoneDAO.findById(Integer.parseInt(request.getParameter("zoneId")));
			request.getSession().setAttribute("currentZone", zone);
			response.sendRedirect("manage/editorzone.jsp");
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
		}

	}

	/**
	 * 查找所有
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void searchAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ZoneService zoneService = new ZoneServiceImpl();
		int centerId = (int) request.getSession().getAttribute("currentCenterId");
		List<Zone> zoneList = zoneService.search(centerId);
		if (zoneList != null && zoneList.size() > 0) {
			request.getSession().setAttribute("allZone", zoneList);
			response.sendRedirect("manage/zone.jsp");
		} else {
			// 跳转至错误页面

		}

	}

}
