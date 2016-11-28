package com.geowind.hunong.jpa;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class PestlibDAOTest {

	@Test
	public void test() {
		PestlibDAO pestlibDAO = new PestlibDAO();
		
		List<Pestlib> pests = pestlibDAO.findByPestname("稻飞虱");
		System.out.println(pests);
	}

}
