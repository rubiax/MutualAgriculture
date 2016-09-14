package com.geowind.hunong.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.geowind.hunong.dao.LibraryDao;
import com.geowind.hunong.dao.impl.LibraryDaoImpl;
import com.geowind.hunong.entity.Article;
import com.geowind.hunong.entity.Library;
import com.geowind.hunong.service.LibraryService;

/**
 * Created by Kui on 2016/7/23.
 */
public class LibraryServiceImpl implements LibraryService {

	private LibraryDao libraryDao;

	public LibraryServiceImpl() {
		libraryDao = new LibraryDaoImpl();
	}

	@Override
	public Library getTitles(int category, int begin) {
		List<Map<String, Object>> results = libraryDao.selectTitle(category, begin);
		return dealResults(results);
	}

	@Override
	public Library getTitles(int begin) {
		List<Map<String, Object>> results = libraryDao.selectTitle(begin);
		return dealResults(results);
	}

	public Library dealResults(List<Map<String, Object>> results) {
		List<Article> articles = new ArrayList<Article>();
		for (Map<String, Object> map : results) {
			Article article = new Article();
			article.setId((Integer) map.get("id"));
			article.setCategory((Integer) map.get("category"));
			article.setTitle((String) map.get("title"));
			article.setUrl((String) map.get("url"));
			article.setHeadContent((String) map.get("headContent"));
			articles.add(article);
		}
		Library library = new Library();
		library.setArticleList(articles);
		return library;
	}

}