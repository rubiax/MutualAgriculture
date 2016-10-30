package com.geowind.hunong.entities;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class InsectcontrolDAOTest {

	@Test
	public void testSave() {
		InsectcontrolDAO insectcontrolDAO = new InsectcontrolDAO();
    	Insectcontrol insectcontrol = new Insectcontrol();
    	insectcontrol.setUploadImage("images");
    	insectcontrol.setDescr("describe");
    	UserDAO userDAO = new UserDAO();
    	insectcontrol.setUser(userDAO.findById("geowind"));
    	insectcontrol.setUploadtime(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date()));
    	EntityManagerHelper.beginTransaction();
    	try {
    		insectcontrolDAO.save(insectcontrol);
    		EntityManagerHelper.commit();
    	} catch (RuntimeException re) {
    		re.printStackTrace();
    	}
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}
