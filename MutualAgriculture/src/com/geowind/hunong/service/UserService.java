package com.geowind.hunong.service;

import java.util.List;
import java.util.Map;

import com.geowind.hunong.entities.User;

/**
 * Created by Kui on 2016/7/20.
 */
public interface UserService {

    public String login(String userId, String password);
    
    public List<User> search(User user, String type);
}
