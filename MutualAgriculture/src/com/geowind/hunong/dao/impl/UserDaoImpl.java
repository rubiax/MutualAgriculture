package com.geowind.hunong.dao.impl;

import com.geowind.hunong.entities.Center;
import com.geowind.hunong.entities.User;
import com.geowind.hunong.dao.UserDao;
import com.geowind.hunong.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Kui on 2016/7/20.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public Map<String, Object> selectAccounts(String userId, String password) {
        String sql = "select * from user where username=? and password=?";
        return DBHelper.doQueryOne(sql, userId, password);
    }
    
    @Override
    public List<User> search(User searchUser, String type) {
    	List<User> list = new ArrayList<User>();
    	StringBuffer sql = new StringBuffer();
    	sql.append("select *, year(now())-year(birthday) as age from " + type + " where 1=1 and valid=1");
    	if(searchUser != null) {
    		if(null != searchUser.getUsername() && !"".equals(searchUser.getUsername())) {
        		sql.append(" and username like '%").append(searchUser.getUsername()).append("%'");
        	}
        	if(null != searchUser.getRealname() && !"".equals(searchUser.getRealname())) {
        		sql.append(" and realname like '%").append(searchUser.getRealname()).append("%'");
        	}
        	if(null != searchUser.getSex() && !"".equals(searchUser.getSex())) {
        		sql.append(" and sex like '%").append(searchUser.getSex()).append("%'");
        	}
        	if(null != searchUser.getPhone() && !"".equals(searchUser.getPhone())) {
        		sql.append(" and phone like '%").append(searchUser.getPhone()).append("%'");
        	}
        	if(null != searchUser.getAddress() && !"".equals(searchUser.getAddress())) {
        		sql.append(" and address like '%").append(searchUser.getAddress()).append("%'");
        	}
    	}
    	
    	Connection con = DBHelper.getConn();
    	ResultSet rs = null;
    	try {
    		rs = con.createStatement().executeQuery(sql.toString());
			while(rs.next()) {
				User user = new User();
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setRealname(rs.getString(3));
				user.setSex(rs.getString(4));
				user.setBirthday(rs.getDate(5));
				user.setPhone(rs.getString(6));
				user.setType(rs.getInt(7));
				user.setPicture(rs.getString(8));
				user.setAddress(rs.getString(9));
				user.setCredit(rs.getString(10));
				Center center = new Center();
				center.setCenterId(rs.getInt(11));
				user.setCenter(center);
				user.setValid(rs.getInt(12));
				user.setAge(rs.getString(13));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.close(con, null, rs);
		return list;
    	
    }
}
