package com.geowind.hunong.dao.impl;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.geowind.hunong.entities.Center;
import com.geowind.hunong.entities.Machineowner;
import com.geowind.hunong.entities.User;
import com.geowind.hunong.dao.MachineOwnerDao;
import com.geowind.hunong.util.DBHelper;

/**
 * Created by Kui on 2016/9/3.
 */
public class MachineOwnerDaoImpl implements MachineOwnerDao {


    @Override
    public int insertMachineOwner(Machineowner machineOwner) {
        String sql = "insert into machineowner values(null, ?, ?, ?)";
        return DBHelper.doUpdate(sql, machineOwner.getPhone(), machineOwner.getName(), machineOwner.getAddress());
    }
    
    @Override
    public List<Machineowner> search(Machineowner searchMachineowner) {
    	List<Machineowner> list = new ArrayList<Machineowner>();
    	StringBuffer sql = new StringBuffer();
    	sql.append("select * from machineowner where 1=1 and valid=1");
    	if(null != searchMachineowner.getName() && !"".equals(searchMachineowner.getName())) {
    		sql.append(" and name like '%").append(searchMachineowner.getName()).append("%'");
    	}
    	if(null != searchMachineowner.getPhone() && !"".equals(searchMachineowner.getPhone())) {
    		sql.append(" and phone like '%").append(searchMachineowner.getPhone()).append("%'");
    	}
    	if(null != searchMachineowner.getSex() && !"".equals(searchMachineowner.getSex())) {
    		sql.append(" and sex like '%").append(searchMachineowner.getSex()).append("%'");
    	}
    	if(null != searchMachineowner.getAddress() && !"".equals(searchMachineowner.getAddress())) {
    		sql.append(" and address like '%").append(searchMachineowner.getAddress()).append("%'");
    	}
    	
    	
    	Connection con = DBHelper.getConn();
    	ResultSet rs = null;
    	try {
    		rs = con.createStatement().executeQuery(sql.toString());
			while(rs.next()) {
				Machineowner machineowner = new Machineowner();
				machineowner.setOwnerId(rs.getInt(1));
				machineowner.setName(rs.getString(2));
				machineowner.setSex(rs.getString(3));
				machineowner.setBirthday(rs.getDate(4));
				machineowner.setPhone(rs.getString(5));
				machineowner.setAddress(rs.getString(6));
				Center center = new Center();
				center.setCenterId(rs.getInt(7));
				machineowner.setCenter(center);
				machineowner.setValid(rs.getInt(8));
				list.add(machineowner);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.close(con, null, rs);
		return list;
    	
    }
}
