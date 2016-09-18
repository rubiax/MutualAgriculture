package com.geowind.hunong.service.impl;

import com.geowind.entities.Machineowner;
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
}
