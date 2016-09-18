package com.geowind.hunong.dao.impl;

import com.geowind.hunong.dao.UserDao;
import com.geowind.hunong.util.DBHelper;

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
}
