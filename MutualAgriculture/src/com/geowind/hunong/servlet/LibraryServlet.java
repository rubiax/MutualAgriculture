package com.geowind.hunong.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geowind.hunong.util.LibraryKeywordSearch;
import com.google.gson.Gson;

/**
 * Servlet implementation class LibraryServlet
 */
//@WebServlet("/LibraryServlet")
public class LibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String op = request.getParameter("op");

		System.out.println("library serlvet op=" + op);
		String keyword = request.getParameter("keyword");
		List<String> ArticleURLS = LibraryKeywordSearch.GetMatchArticlesURL(keyword);
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String msg = gson.toJson(ArticleURLS);

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
