package com.geowind.hunong.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.geowind.hunong.entity.Data;
import com.geowind.hunong.jpa.Machine;
import com.geowind.hunong.jpa.MachineDAO;
import com.geowind.hunong.jpa.Pestquestion;
import com.geowind.hunong.jpa.PestquestionDAO;
import com.geowind.hunong.util.DBHelper;
import com.google.gson.Gson;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

public class TestMachineJson {
	
	@Test
	public void test(){
//		String sql = "select count(machineid) as num,type from machine group by type";
//		List<Map<String,Object>> maps = DBHelper.doQuery(sql);
//		
//		
//		Gson gson = new Gson();
//		
//		System.out.println(gson.toJson(maps));
		
		PestquestionDAO qDao = new PestquestionDAO();
		List<Pestquestion> lsit = qDao.findAll();
		Pestquestion pestquestion = new Pestquestion();
		for ( Pestquestion p:lsit){
			System.out.println(p);
		}
	}

}
