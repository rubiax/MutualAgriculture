package com.geowind.test;

import java.util.List;
import java.util.Map;

import com.geowind.hunong.entities.User;
import com.geowind.hunong.service.UserService;
import com.geowind.hunong.service.impl.UserServiceImpl;

public class UserSearchTest {

	public static void main(String[] args) {
		UserService service = new UserServiceImpl();
		User user = new User();
		
		List<User> list = service.search(1, "v_farmer");
		for(User u : list) {
			System.out.println(u);
			System.out.println(u.getCenter().getCenterId());
		}
	}
}
