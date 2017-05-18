package com.geowind.hunong.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geowind.hunong.entity.SimConsult;
import com.geowind.hunong.entity.SimPestQuestion;
import com.geowind.hunong.jpa.Consult;
import com.geowind.hunong.jpa.ConsultDAO;
import com.geowind.hunong.jpa.EntityManagerHelper;
import com.geowind.hunong.jpa.Pestquestion;
import com.geowind.hunong.jpa.PestquestionDAO;
import com.geowind.hunong.util.JPushUtil;
import com.geowind.hunong.util.LogManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.jdbc.log.LogUtils;

public class PestQuestionServlet extends BasicServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String op = request.getParameter("op");
		
		switch(op){
		
			case "MapSearchAll":
				MapSearchAll(request,response);
				break;
			case "question":
				question(request, response);
				break;
			case "questioned":
				questioned(request, response);
				break;
			case "answer":
				answer(request, response);
				break;
			case "answeragain":
				answeragain(request, response);
				break;
			default:
				break;

				
		}
	
	}
	
	
	/**
	 * 鍥剧墖鏈哄櫒璇嗗埆鍚庝笓瀹跺啀娆¤ˉ鍏呰В绛�
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void answeragain(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int cid = Integer.parseInt(request.getParameter("qid"));
		String centent = request.getParameter("content");
		PestquestionDAO pestquestionDAO = new PestquestionDAO();
		Pestquestion pestquestion = pestquestionDAO.findById(cid);
		String answer = pestquestion.getAnswer();
		if (centent == null) {
			answer = "";
		}
		answer = answer.concat("\n").concat(centent);
		pestquestion.setAnswer(answer);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		pestquestion.setAtime(date);
		pestquestion.setStatus(1);
		EntityManagerHelper.beginTransaction();
		try {
			pestquestionDAO.update(pestquestion);
			EntityManagerHelper.commit();
			
			SimPestQuestion simPestQuestion = new SimPestQuestion();
			simPestQuestion = simPestQuestion.fromPestquestion(pestquestion);
			
			LogManager.logger.info(simPestQuestion.toString());
			
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			JsonObject jsonObject = new JsonParser().parse(gson.toJson(simPestQuestion)).getAsJsonObject();
			JPushUtil.sendPush(simPestQuestion.getUsername(), "虫害识别", jsonObject);
			this.out(response, "1");
		} catch (RuntimeException re) {
			this.out(response, "0");
		}
	}


	/**
	 * 鍥剧墖浜哄伐鍥炵瓟
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void answer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int cid = Integer.parseInt(request.getParameter("qid"));
		String centent = request.getParameter("content");
		PestquestionDAO pestquestionDAO = new PestquestionDAO();
		Pestquestion pestquestion = pestquestionDAO.findById(cid);
		pestquestion.setAnswer(centent);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		pestquestion.setAtime(date);
		pestquestion.setStatus(1);
		EntityManagerHelper.beginTransaction();
		try {
			pestquestionDAO.update(pestquestion);
			EntityManagerHelper.commit();
			
			SimPestQuestion simPestQuestion = new SimPestQuestion();
			simPestQuestion = simPestQuestion.fromPestquestion(pestquestion);
			
			LogManager.logger.info(simPestQuestion.toString());
			
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			JsonObject jsonObject = new JsonParser().parse(gson.toJson(simPestQuestion)).getAsJsonObject();
			JPushUtil.sendPush(simPestQuestion.getUsername(), "虫害识别", jsonObject);
			this.out(response, "1");
		} catch (RuntimeException re) {
			this.out(response, "0");
		}
	}

	/**
	 * 鏌ョ湅宸茬粡璇嗗埆鐨勫浘鐗�
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void questioned(HttpServletRequest request, HttpServletResponse response) throws IOException {
		EntityManager entityManager = EntityManagerHelper.getEntityManager();
		entityManager.getEntityManagerFactory().getCache().evictAll(); //濞撳懐鈹栨禍宀�楠囩紓鎾崇摠閿涳拷
		entityManager.clear(); //濞撳懐鈹栨稉锟界痪褏绱︾�涳拷
		
		PestquestionDAO pestquestionDAO = new PestquestionDAO();
		List<Pestquestion> pestquestionList = pestquestionDAO.findByStatus(1);
		request.getSession().setAttribute("historyquestions", pestquestionList);
		response.sendRedirect("manage/pestidentificated.jsp");
	}

	private void question(HttpServletRequest request, HttpServletResponse response) throws IOException {
		EntityManager entityManager = EntityManagerHelper.getEntityManager();
		entityManager.getEntityManagerFactory().getCache().evictAll(); //濞撳懐鈹栨禍宀�楠囩紓鎾崇摠閿涳拷
		entityManager.clear(); //濞撳懐鈹栨稉锟界痪褏绱︾�涳拷
		
		PestquestionDAO pestquestionDAO = new PestquestionDAO();
		List<Pestquestion> pestquestionList = pestquestionDAO.findByStatus(0);
		request.getSession().setAttribute("questions", pestquestionList);
		response.sendRedirect("manage/pestidentification.jsp");
	}

	private void MapSearchAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PestquestionDAO pestquestionDAO = new PestquestionDAO();
		List<Pestquestion> pestquestionList = pestquestionDAO.findAll();
		if(pestquestionList!=null&&pestquestionList.size()>0){
			this.out(response, pestquestionList);
		}else{
			this.out(response, 0);
		}
		
	}

}
