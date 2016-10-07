package com.geowind.hunong.dao;

import java.util.List;

import com.geowind.hunong.entities.Machineowner;


/**
 * Created by Kui on 2016/9/3.
 */
public interface MachineOwnerDao {

    public int insertMachineOwner(Machineowner machineOwner);
    
    public List<Machineowner> search(Machineowner searchMachineowner);
}
