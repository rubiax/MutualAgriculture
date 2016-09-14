package com.geowind.hunong.dao.impl;


import com.geowind.hunong.dao.MachineOwnerDao;
import com.geowind.hunong.entity.MachineOwner;
import com.geowind.hunong.util.DBHelper;

/**
 * Created by Kui on 2016/9/3.
 */
public class MachineOwnerDaoImpl implements MachineOwnerDao {


    @Override
    public int insertMachineOwner(MachineOwner machineOwner) {
        String sql = "insert machineowner values(null, ?, ?, ?)";
        return DBHelper.doUpdate(sql, machineOwner.getPhone(), machineOwner.getName(), machineOwner.getAddress());
    }
}
