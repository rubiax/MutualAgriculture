package com.geowind.hunong.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBHelperSim {
	public static List<String> findBykeyword(String keywords, String sql) {
		List<String> res = new ArrayList<>();
		try {
			Connection conn = DBHelper.getConn();
			Statement stmt = conn.createStatement(); // 创建Statement对象
			// System.out.println("成功连接到数据库！");
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);// 创建数据对象
			while (rs.next()) {
				res.add(rs.getString("articleId"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	// public static void main(String[] args) {
	// String keywords = "我是%";
	// findBykeyword(keywords, "select * from article where keyword like '" +
	// keywords + "'");
	// }
}
