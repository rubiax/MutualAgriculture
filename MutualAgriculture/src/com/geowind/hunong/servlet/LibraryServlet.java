package com.geowind.hunong.servlet;

import static com.geowind.hunong.util.PathUtil.ServerIP;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geowind.hunong.entity.ArticleSim;
import com.geowind.hunong.util.DBHelperSim;
import com.geowind.hunong.util.LibraryKeywordSearch;
import com.google.gson.Gson;

/**
 * Servlet implementation class LibraryServlet
 */
public class LibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String method = request.getParameter("method");
		// searchLib
		// getArticles

		if (method.equals("serchLib")) {
			SearchMethod(request, response);
		} else if (method.equals("getArticles")) {
			GetArticlesMethod(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void SearchMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String keyword = request.getParameter("keyword");
		System.out.println("library serlvet op=" + keyword);
		// String keyword = request.getParameter("keyword");
		List<ArticleSim> ArticleMeg = LibraryKeywordSearch.GetMatchArticlesURL(keyword);
		for (int i = 0; i < ArticleMeg.size(); i++) {
			ArticleSim now = ArticleMeg.get(i);
			now.url = "http://" + ServerIP + ":8080/MutualAgriculture/LibraryHTML/" + now.id + ".html";
		}
		// System.out.println("IP="+ServerIP);
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String msg = gson.toJson(ArticleMeg);

		out.print(msg);
		out.flush();
		out.close();
	}

	private void GetArticlesMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String category = request.getParameter("category");
		String nowPage = request.getParameter("nowPage");

		// 10001 10010
		int pagN = Integer.parseInt(nowPage);

		int begin_page = 10001 + pagN * 10;
		int end_page = 10001 + (pagN + 1) * 10 - 1;

		String sql = "select * from article";
		if (category.equals("0")) {
			sql += "where articleId between " + begin_page + " and " + end_page;
		} else {
			sql += "where classification like '%" + category + "%' and articleId between " + begin_page + " and "
					+ end_page;
		}
		// sql+= begin_page +" and "+end_page;

		System.out.println(sql);
		List<ArticleSim> res = DBHelperSim.GetArticleSimUseSql(sql);

		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String msg = gson.toJson(res);

		out.print(msg);
		out.flush();
		out.close();
	}

}
