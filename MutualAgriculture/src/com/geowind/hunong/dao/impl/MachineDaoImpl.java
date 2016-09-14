package com.geowind.hunong.dao.impl;

import com.geowind.hunong.dao.MachineDao;
import com.geowind.hunong.entity.Machine;
import com.geowind.hunong.util.DBHelper;

/**
 * Created by Kui on 2016/9/4.
 */
public class MachineDaoImpl implements MachineDao {

    @Override
    public int insertMachine(Machine machine) {
        String sql = "insert into values (null,?,?,?,?,?,?,?,?,?)";
        return DBHelper.doUpdate(sql, machine.getPlate(), machine.getStyle(), machine.getBrand(), machine.getHp(),
                machine.getRetirementDate(), machine.getPicUrl(), machine.getNo(), machine.getState(), machine.getWorkState());
    }
}


