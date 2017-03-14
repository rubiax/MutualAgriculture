package com.geowind.hunong.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geowind.hunong.entity.FarmlandPoint;
import com.geowind.hunong.entity.Point;
import com.geowind.hunong.jpa.Farmland;
import com.geowind.hunong.jpa.FarmlandDAO;
import com.geowind.hunong.jpa.Pestzone;
import com.geowind.hunong.jpa.PestzoneDAO;
import com.geowind.hunong.jpa.Zone;
import com.geowind.hunong.util.PointSelector;

public class PestZoneServlet extends BasicServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String op = request.getParameter("op");
		
		switch(op){
		
			case "MapSearchAll":
				MapSearchAll(request,response);
				break;
				
			case "MapSearchAffectedArea":		
				MapSearchAffectedArea(request,response);
				break;
			default:
				break;
		}
 	
	
	}

	
	
	
	private void MapSearchAffectedArea(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PestzoneDAO pestzoneDAO = new PestzoneDAO();
		Integer in = 0;
		List<Pestzone> affectedAreaList = pestzoneDAO.findByStatus(in);
		if(affectedAreaList!=null&&affectedAreaList.size()>0){			
			this.out(response, getAffectedFarmlandPoint(affectedAreaList));
		}else{
			this.out(response, 0);
		}
		
	}

	
	/**
	 * 获得受灾区凸点经纬度
	 * @param affectedAreaList
	 * @return
	 */
	private List<FarmlandPoint> getAffectedFarmlandPoint(List<Pestzone> affectedAreaList) {
		
		//获得分区的集合，保证不重复
		Set<Zone> zoneNumber = new HashSet<Zone>();
		int b =0;
		for(int i=0;i<affectedAreaList.size();i++){
				b++;
				zoneNumber.add(affectedAreaList.get(i).getZone());
				
		} 
		System.out.println("b is:"+b);
//		FarmlandDAO farmlandDAO =new FarmlandDAO();
		Iterator<Zone> i =zoneNumber.iterator(); 
		
		List<FarmlandPoint> farmlandPointList = new ArrayList<FarmlandPoint>();
		
		Set<Farmland> farmlandList = new HashSet<Farmland>();
	
		//遍历受灾的农田分区
		while(i.hasNext()){
		
			//获得该指定分区下的所有农田
//			farmlandList = i.next().getBlocks().;
			
			System.out.println(farmlandList.iterator().next().getLatitude());
			
			System.out.println("meile");
			
			List<Point> p = new ArrayList<Point>();
			
			Iterator<Farmland> f = farmlandList.iterator();
			
			int count = 0;
			while(f.hasNext()){
				
				count++;
				Point p1 = new Point();
				Farmland tmp = f.next();
				System.out.println(tmp.getLongitude()+","+tmp.getLatitude());
				p1.setX(tmp.getLongitude());
				p1.setY(tmp.getLatitude());
				
				p.add(p1);
				
			}
			System.out.println("count is" + count);
			
			PointSelector ps = new PointSelector(p);
			p = ps.GetHullPoints();
			
			FarmlandPoint fp = new FarmlandPoint();
			fp.setPointList(p);
			farmlandPointList.add(fp);
		}
		
		for(int l=0;l<farmlandPointList.size();l++){
			System.out.println(farmlandPointList.get(l).getPointList().get(l));
		}
		return farmlandPointList;
	}




	private void MapSearchAll(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PestzoneDAO pestzoneDAO = new PestzoneDAO();
		List<Pestzone> pestzoneList = pestzoneDAO.findAll();
		if(pestzoneList!=null&&pestzoneList.size()>0){
			this.out(response,pestzoneList);
		}else{
			this.out(response, 0);
		}
		
		
	}

}
