package com.geowind.hunong.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.geowind.hunong.entities.Machineowner;
import com.geowind.hunong.service.impl.MachineOwnerServiceImpl;

public class MachineOwnerServiceTest {

	@Test
	public void testSearch() {
		MachineOwnerService machineOwnerService = new MachineOwnerServiceImpl();
		Machineowner machineowner = new Machineowner();
		machineowner.setName("李四");
		List<Machineowner> list = machineOwnerService.search(1);
		for(Machineowner m : list) {
			System.out.println(m);
		}
	}

}
