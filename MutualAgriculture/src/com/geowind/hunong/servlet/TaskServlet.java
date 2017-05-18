package com.geowind.hunong.servlet;

import com.geowind.hunong.entity.SimTask;
import com.geowind.hunong.jpa.Block;
import com.geowind.hunong.jpa.BlockDAO;
import com.geowind.hunong.jpa.Center;
import com.geowind.hunong.jpa.CenterDAO;
import com.geowind.hunong.jpa.EntityManagerHelper;
import com.geowind.hunong.jpa.Farmland;
import com.geowind.hunong.jpa.FarmlandDAO;
import com.geowind.hunong.jpa.ITaskDAO;
import com.geowind.hunong.jpa.Machine;
import com.geowind.hunong.jpa.MachineDAO;
import com.geowind.hunong.jpa.Task;
import com.geowind.hunong.jpa.TaskDAO;
import com.geowind.hunong.jpa.User;
import com.geowind.hunong.jpa.UserDAO;
import com.geowind.hunong.service.TaskService;
import com.geowind.hunong.service.impl.TaskServiceImpl;
import com.geowind.hunong.util.JPushUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.Detail;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Kui on 2016/7/22.
 */
@WebServlet(name = "TaskServlet")
public class TaskServlet extends BasicServlet {

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String op = request.getParameter("op");
		
		if("pulishTask".equals(op)) {
			pulishTask(request, response);
		} else if("listTask".equals(op)) {
			listTask(request, response);
		} else if("tasked".equals(op)) {
			tasked(request, response);
		} else if("finishTask".equals(op)) {
			finishTask(request, response);
		} else if("detail".equals(op)) {
			detail(request, response);
		} else if("historyTask".equals(op)) {
			historyTask(request, response);
		} else if("MapSearchAll".equals(op)){
			MapSearchAll(request,response);
		} else if("getTaskNum".equals(op)){
			getTaskNum(request,response);
		}
	}
	
	
	/**
	 * 获得任务数量
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getTaskNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
		TaskDAO taskDAO =new TaskDAO();
		List<Task> taskList = taskDAO.findAll();
		String zoneNum = request.getParameter("zone");
		int taskNum = 0;
		if(taskList!=null&&taskList.size()>0){
			taskNum = countTaskNum(taskList,zoneNum);
			this.out(response,taskNum);
		}else{
			this.out(response, 0);
		}
	}

	/**
	 * 计算任务数量
	 * @param taskList
	 * @return
	 */
	private int countTaskNum(List<Task> taskList, String zoneNum) {
		int num = 0;
		if (zoneNum.equals("0")) {
			Iterator<Task> it = taskList.iterator();
			while (it.hasNext()) {

				if(it.next().getBlock().getZone().getZonename().equals("A区")){
					num++;
				}
				

			}
		} else if (zoneNum.equals("1")) {
			
			Iterator<Task> it = taskList.iterator();
			while (it.hasNext()) {

				if(it.next().getBlock().getZone().getZonename().equals("B区")){
					num++;
				}
				

			}
		}

		return num;
	}


	/**
	 * 鍦板浘鏌ユ壘鎵�鏈変换鍔�
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void MapSearchAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		TaskDAO taskDAO =new TaskDAO();
		List<Task> taskList = taskDAO.findAll();
		if(taskList!=null&&taskList.size()>0){
			this.out(response,taskList);
		}else{
			this.out(response, 0);
		}
	}

	private void historyTask(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
//		System.out.println("---------------historyTask----------------");
		String username = request.getParameter("username");
//		System.out.println(username);
		/*int centerId = (int) request.getSession().getAttribute("currentCenterId");
		TaskService taskService = new TaskServiceImpl();
		List<Task> tasks = taskService.historyTaskByUser(centerId, 1, username);*/
		TaskDAO taskDAO = new TaskDAO();
		List<Task> tasks = taskDAO.findByFinished(1);
		List<SimTask> simTasks = new ArrayList<SimTask>();
		for(Task t : tasks) {
			SimTask simTask = new SimTask();
			simTasks.add(simTask.fromTask(t));
		}
		this.out(response, simTasks);
	}

	private void detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String taskId = request.getParameter("taskId");
		TaskDAO taskDAO = new TaskDAO();
		Task task = taskDAO.findById(Integer.parseInt(taskId));
		request.getSession().setAttribute("currentTask", task);
		response.sendRedirect("manage/taskdetail.jsp");
	}

	/**
	 * 浠诲姟缁撴潫
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void finishTask(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String taskId = request.getParameter("taskId");
//		System.out.println(taskId);
		TaskDAO taskDAO = new TaskDAO();
		UserDAO userDAO = new UserDAO();
		MachineDAO machineDAO = new MachineDAO();
		EntityManagerHelper.beginTransaction();
		Task task = null;
		try {
			task = taskDAO.findById(Integer.parseInt(taskId));
			task.setFinished(1);
			task.setFinishdate(new Date());
			
			User user = userDAO.findById(task.getUser().getUsername());
			user.setStatus(0);
			task.setUser(user);
			
			Machine machine = machineDAO.findById(task.getMachine().getMachineId());
			machine.setWorkstate(0);
			task.setMachine(machine);

			userDAO.update(user);
			machineDAO.update(machine);
			taskDAO.update(task);
			EntityManagerHelper.commit();
			this.out(response, "1");
		} catch (RuntimeException re) {
			this.out(response, "0");
		}

	}

	/**
	 * 宸茬粡瀹屾垚鐨勪换鍔�
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void tasked(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int centerId = (int) request.getSession().getAttribute("currentCenterId");
		TaskService taskService = new TaskServiceImpl();
		List<Task> tasks = taskService.getTaskInfo(centerId, 1);
		request.getSession().setAttribute("tasked", tasks);
		response.sendRedirect("manage/tasked.jsp");
	}

	/**
	 * 浠诲姟娓呭崟
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void listTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int centerId = (int) request.getSession().getAttribute("currentCenterId");
		TaskService taskService = new TaskServiceImpl();
		List<Task> tasks = taskService.getTaskInfo(centerId, 0);
		request.getSession().setAttribute("tasking", tasks);
		List<Task> tasks2 = taskService.getTaskInfo(centerId, 1);
		request.getSession().setAttribute("tasked", tasks2);
		response.sendRedirect("manage/task.jsp");
	}

	/**
	 * 鍙戝竷浠诲姟
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void pulishTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String machineId = request.getParameter("machineId");
		String bid = request.getParameter("bid");
		String workdate = request.getParameter("workdate");
		String descr = request.getParameter("descr");
		//System.out.println(username+" "+machineId+" "+bid+" "+workdate+" "+descr);
		TaskDAO taskDAO = new TaskDAO();
		Task task = new Task();
		User user = new UserDAO().findById(username);
		//user.setStatus(1);
		task.setUser(user);
		Machine machine = new MachineDAO().findById(Integer.parseInt(machineId));
		//machine.setWorkstate(1);
		task.setMachine(machine);
		Block block = new BlockDAO().findById(Integer.parseInt(bid));
		task.setBlock(block);
		Center center = new CenterDAO().findById((int)request.getSession().getAttribute("currentCenterId"));
		task.setCenter(center);
		try {
			task.setWorkdate(new SimpleDateFormat("yyyy-MM-dd").parse(workdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		task.setDescr(descr);
		task.setFinished(0);
		task.setPublishdate(new Date());
		try {
			EntityManagerHelper.beginTransaction();
			taskDAO.save(task);
			EntityManagerHelper.commit();
		
			
			SimTask simTask = new SimTask();
			simTask = simTask.fromTask(task);
			System.out.println("=====================================");
			//System.out.println(simTask.toString());
			
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			JsonObject jsonObject = new JsonParser().parse(gson.toJson(simTask)).getAsJsonObject();
			System.out.println(jsonObject.toString());
			JPushUtil.sendPush(username, "任务提醒", jsonObject);
			this.out(response, "1");
		} catch (RuntimeException re) {
			this.out(response, "0");
		}
	}
	
	
	
	
}

