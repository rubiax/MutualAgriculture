package com.geowind.hunong.service.impl;

import com.geowind.hunong.jpa.Machine;
import com.geowind.hunong.dao.MachineDao;
import com.geowind.hunong.dao.impl.MachineDaoImpl;
import com.geowind.hunong.service.MachineService;

/**
 * Created by Kui on 2016/9/4.
 */
public class MachineServiceImpl implements MachineService {

    private MachineDao machineDao;

    public MachineServiceImpl() {
        machineDao = new MachineDaoImpl();
    }

    @Override
    public int addMachineInfo(Machine machine) {
        return machineDao.insertMachine(machine);
    }
}
