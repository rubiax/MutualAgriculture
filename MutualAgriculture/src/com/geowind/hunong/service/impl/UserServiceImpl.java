package com.geowind.hunong.service.impl;

import com.geowind.hunong.dao.UserDao;
import com.geowind.hunong.dao.impl.UserDaoImpl;
import com.geowind.hunong.entities.User;
import com.geowind.hunong.service.UserService;
import com.geowind.hunong.util.Encrypt;

import java.util.List;
import java.util.Map;

/**
 * Created by Kui on 2016/7/20.
 */
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public String login(String userId, String password) {
		// String encryptPassword = Encrypt.md5AndSha(password);
		Map<String, Object> result = userDao.selectAccounts(userId, password);
		if (result != null) {
			return "登录成功";
		}
		return "登录失败";
	}
	
	
	@Override
	public List<User> search(User searchUser, String type) {
		List<User> users = userDao.search(searchUser, type);
		return users;
	}
}
