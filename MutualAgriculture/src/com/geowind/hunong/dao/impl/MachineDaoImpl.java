package com.geowind.hunong.dao.impl;

import com.geowind.hunong.entities.Machine;
import com.geowind.hunong.dao.MachineDao;
import com.geowind.hunong.util.DBHelper;

/**
 * Created by Kui on 2016/9/4.
 */
public class MachineDaoImpl implements MachineDao {

    @Override
    public int insertMachine(Machine machine) {
        String sql = "insert into machine values (null,?,?,?,?,?,?,?,?,?)";
        return DBHelper.doUpdate(sql, machine.getPlate(), machine.getType(), machine.getBrand(), machine.getHorsepower(),
                machine.getOverdate(), machine.getPicture(), machine.getMachineowner().getOwnerId(), machine.getState(), machine.getWorkstate());
    	
    }
}


