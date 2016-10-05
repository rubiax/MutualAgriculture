package com.geowind.hunong.dao;


import java.util.List;
import java.util.Map;

import com.geowind.hunong.entities.User;

/**
 * Created by Kui on 2016/7/19.
 */
public interface UserDao {

    public Map<String, Object> selectAccounts(String userId, String password);
    
    public List<User> search(User searchUser, String type); 

}
