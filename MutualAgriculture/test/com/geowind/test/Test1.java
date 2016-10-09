package com.geowind.test;

import com.geowind.hunong.entities.*;


/**
 * Created by Kui on 2016/7/21.
 */
public class Test1 {
	
	public static void main(String[] args) {
		ArticleDAO dao = new ArticleDAO();
		EntityManagerHelper.beginTransaction();
		
		Article article = new Article();
		article.setTitle("helloworld");
		dao.save(article);
		
		EntityManagerHelper.commit();
	}
}
