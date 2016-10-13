package com.geowind.hunong.dao.impl;

import com.geowind.hunong.dao.UserDao;
import com.geowind.hunong.util.DBHelper;

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
    public List<Map<String, Object>> search(int centerId, String type) {
    	//List<User> list = new ArrayList<User>();
    	String sql = "select * from " + type + " where centerId=? and valid=1";
    	return DBHelper.doQuery(sql ,centerId);
    	
    	/*Connection con = DBHelper.getConn();
    	PreparedStatement ps = null;
    	ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, centerId);
			rs = ps.executeQuery();
			
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
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBHelper.close(con, null, rs);
		return list;
    	*/
    }
}
