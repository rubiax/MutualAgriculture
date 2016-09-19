package test;

import com.geowind.entities.EntityManagerHelper;
import com.geowind.entities.User;
import com.geowind.entities.UserDAO;


/**
 * Created by Kui on 2016/7/21.
 */
public class Test {
	
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		
		EntityManagerHelper.beginTransaction();
		
		User user = new User();
		user.setUsername("ccc");
		user.setType(1);
		user.setPhone("111111111111");
		
		dao.save(user);
		
		EntityManagerHelper.commit();
	}
}
