package com.geowind.hunong.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geowind.hunong.jpa.Aiplan;
import com.geowind.hunong.jpa.AiplanDAO;

public class AiPlanningServlet extends BasicServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String op = request.getParameter("op");
		
		switch (op) {
		case "getPlan":
			getPlan(request, response);
			break;

		default:
			break;
		}
	}

	private void getPlan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AiplanDAO aiplanDAO = new AiplanDAO();
		List<Aiplan> list = aiplanDAO.findAll();
		Iterator<Aiplan> it = list.iterator();
		while(it.hasNext()){
			Aiplan ai = it.next();
		    if(ai.getBname() == null){
		        it.remove();
		    }
		}
		this.out(response, list);
	}
	
}
