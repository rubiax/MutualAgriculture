package com.geowind.hunong.service.impl;

import java.util.List;

import com.geowind.hunong.entities.Machineowner;
import com.geowind.hunong.dao.MachineOwnerDao;
import com.geowind.hunong.dao.impl.MachineOwnerDaoImpl;
import com.geowind.hunong.service.MachineOwnerService;

/**
 * Created by Kui on 2016/9/3.
 */
public class MachineOwnerServiceImpl implements MachineOwnerService {

    private MachineOwnerDao machineOwnerDao;

    public MachineOwnerServiceImpl() {
        machineOwnerDao = new MachineOwnerDaoImpl();
    }

    @Override
    public int addMachineOwnerInfo(Machineowner machineOwner) {
        return  machineOwnerDao.insertMachineOwner(machineOwner);
    }

	@Override
	public List<Machineowner> search(Machineowner searchMachineowner) {
		return machineOwnerDao.search(searchMachineowner);
	}
}
