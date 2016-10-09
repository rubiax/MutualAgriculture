package com.geowind.test;

import java.util.List;

import com.geowind.hunong.entities.EntityManagerHelper;
import com.geowind.hunong.entities.User;
import com.geowind.hunong.entities.UserDAO;

public class UserTest {

	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		EntityManagerHelper.beginTransaction();
		List<User> list = dao.findAll();
		EntityManagerHelper.commit();
		for(User user : list) {
			System.out.println(user.toString());
		}
	}
}
