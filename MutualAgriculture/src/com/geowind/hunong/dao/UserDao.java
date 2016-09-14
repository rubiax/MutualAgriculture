package com.geowind.hunong.dao;

import com.geowind.hunong.util.DBHelper;

import java.util.Map;

/**
 * Created by Kui on 2016/7/19.
 */
public interface UserDao {

    public Map<String, Object> selectAccounts(String userId, String password);

}
