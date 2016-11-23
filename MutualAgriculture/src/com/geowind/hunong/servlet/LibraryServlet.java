package com.geowind.hunong.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geowind.hunong.entity.ArticleSim;
import com.geowind.hunong.util.LibraryKeywordSearch;
import com.google.gson.Gson;

/**
 * Servlet implementation class LibraryServlet
 */
public class LibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// String method = request.getParameter("method");
		String keyword = request.getParameter("keyword");
		System.out.println("library serlvet op=" + keyword);
		// String keyword = request.getParameter("keyword");
		List<ArticleSim> ArticleMeg = LibraryKeywordSearch.GetMatchArticlesURL(keyword);
		String serverIP = request.getLocalAddr();
		System.out.println("IP=" + serverIP);
		for (int i = 0; i < ArticleMeg.size(); i++) {
			ArticleSim now = ArticleMeg.get(i);
			now.url = "http://" + serverIP + ":8080/MutualAgriculture/LibraryHTML/" + now.id + ".html";
		}

		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String msg = gson.toJson(ArticleMeg);

		out.print(msg);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
