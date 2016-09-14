package com.geowind.hunong.service.impl;

import com.geowind.hunong.dao.LibraryDao;
import com.geowind.hunong.dao.impl.LibraryDaoImpl;
import com.geowind.hunong.entity.Article;
import com.geowind.hunong.entity.Library;
import com.geowind.hunong.service.LibraryService;
import com.geowind.hunong.util.FinalUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<Map<String, Object>> results = libraryDao.selectTitle(category);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        if(results.size() - begin >= 15) {
            for(int i=begin; i<begin+15; i++) {
                list.add(results.get(i));
            }
        } else {
            for(int i=begin; i<results.size(); i++) {
                list.add(results.get(i));
            }
        }
        List<Article> articles = new ArrayList<Article>();
        for(Map<String, Object> map : list) {
            Article article = new Article();
            article.setId((Integer)map.get("id"));
            article.setCategory(category);
            article.setTitle((String)map.get("title"));
            article.setUrl((String)map.get("url"));
            article.setHeadContent((String)map.get("headContent"));
            articles.add(article);
        }
        Library library = new Library();
        library.setArticleList(articles);
        return library;
    }

}