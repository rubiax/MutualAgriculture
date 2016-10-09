package com.geowind.test;

import java.util.List;

import com.geowind.hunong.entities.EntityManagerHelper;
import com.geowind.hunong.entities.Zone;
import com.geowind.hunong.entities.ZoneDAO;

public class ZoneTest {

	public static void main(String[] args) {
		ZoneDAO dao = new ZoneDAO();
		EntityManagerHelper.beginTransaction();
		List<Zone> list = dao.findByZonename("A区");
		EntityManagerHelper.commit();
		for(Zone zone : list) {
			System.out.println(zone);
		}
	}
	
}
