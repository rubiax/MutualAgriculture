package com.geowind.hunong.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.geowind.hunong.entities.Article;
import com.geowind.hunong.entities.ArticleDAO;

public class LibraryKeywordSearch {
	// 匹配单词查询，简单模糊查询、反向查询
	static ArticleDAO articleDao = new ArticleDAO();
	// html文件储存地址
	static String HTMLFILESVAEPATH = PathUtil.Util_HTMLpath;

	public static List<String> ClearRepeatArticle(List<Article> list) {
		Set<String> set = new TreeSet<>();
		List<String> res = new ArrayList<>();
		for (Article i : list) {
			set.add(i.getArticleId().toString());
		}
		for (String string : set) {
			res.add(string);
		}
		return res;
	}

	public static List<String> ClearRepeatString(List<String> list) {
		Set<String> set = new TreeSet<>();
		List<String> res = new ArrayList<>();

		for (String string : list) {
			set.add(string);
		}
		for (String string : set) {
			res.add(string);
		}
		return res;
	}

	public static List<String> SearchMatchArticles(String keyword) {
		String sql = "select * from article where keyword like '" + keyword + "%" + "'";
		List<String> _article = DBHelperSim.findBykeyword(keyword + "%", sql);// 第一遍查询
		// System.out.println("article的长度="+_article.size());
		sql = "select * from article where keyword like '" + "%" + keyword + "%" + "'";
		_article.addAll(DBHelperSim.findBykeyword("%" + keyword + "%", sql)); // 第二遍查询
		// System.out.println("article的长度="+_article.size());
		// 反向查询
		/********* 暂时先不写 ********/
		// 推荐查询
		/********* 暂时先不写 ********/

		List<String> _articleId = ClearRepeatString(_article);
		// for (Article i : _article) {
		// _articleId.add(i.getArticleId().toString());
		// }

		// System.out.println(_articleId.size());
		return _articleId;
	}

	// 这个方法获得html的url
	public static List<String> GetMatchArticlesURL(String keyword) {
		List<String> _articleId = SearchMatchArticles(keyword);
		List<String> HTMLURL = new ArrayList<>();
		for (String string : _articleId) {

			HTMLURL.add(HTMLFILESVAEPATH + "\\" + string + ".html");
		}
		return HTMLURL;
	}
}
