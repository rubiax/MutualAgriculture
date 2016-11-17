package com.geowind.hunong.servlet;

import com.geowind.hunong.entities.EntityManagerHelper;
import com.geowind.hunong.entities.Farmland;
import com.geowind.hunong.entities.FarmlandDAO;
import com.geowind.hunong.entities.ITaskDAO;
import com.geowind.hunong.entities.Machine;
import com.geowind.hunong.entities.MachineDAO;
import com.geowind.hunong.entities.TaskDAO;
import com.geowind.hunong.entities.Task;
import com.geowind.hunong.entities.User;
import com.geowind.hunong.entities.UserDAO;
import com.geowind.hunong.service.TaskService;
import com.geowind.hunong.service.impl.TaskServiceImpl;
import com.geowind.hunong.util.JPushUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.Detail;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Kui on 2016/7/22.
 */
@WebServlet(name = "TaskServlet")
public class TaskServlet extends BasicServlet {

    /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String methodName = request.getParameter("method");
        String resultJson = "";
        System.out.println(methodName);
        if (methodName == null) {
            resultJson = null;
        } else {
            // 根据请求的方法，返回对应信息 resultJson
            resultJson = dealWithRequest(methodName, request);
        }
        //response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        System.out.println(resultJson);
        response.getWriter().write(resultJson);
    }

    private String dealWithRequest(String methodName, HttpServletRequest request) {
        String resultJson = "";
        TaskService ts = new TaskServiceImpl();
        String userName;
        List<Task> taskList;

        switch (methodName) {
            case "getTaskInfo":
                userName = request.getParameter("username");
                taskList = ts.getTaskInfo(userName, 0);
                resultJson = new Gson().toJson(taskList);
                break;
            case "getHistoryTaskInfo":
                userName = request.getParameter("username");
                taskList = ts.getTaskInfo(userName, 1);
                resultJson = new Gson().toJson(taskList);
                break;
        }
        return resultJson;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }*/
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String op = request.getParameter("op");
		
		if("pulishTask".equals(op)) {
			pulishTask(request, response);
		} else if("tasking".equals(op)) {
			tasking(request, response);
		} else if("tasked".equals(op)) {
			tasked(request, response);
		} else if("finishTask".equals(op)) {
			finishTask(request, response);
		} else if("detail".equals(op)) {
			detail(request, response);
		}
	}

	private void detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String taskId = request.getParameter("taskId");
		TaskDAO taskDAO = new TaskDAO();
		Task task = taskDAO.findById(Integer.parseInt(taskId));
		request.getSession().setAttribute("currentTask", task);
		response.sendRedirect("manage/taskdetail.jsp");
	}

	private void finishTask(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String taskId = request.getParameter("taskId");
		TaskDAO taskDAO = new TaskDAO();
		EntityManagerHelper.beginTransaction();
		Task task = null;
		try {
			task = taskDAO.findById(Integer.parseInt(taskId));
			task.setFinished(1);
			taskDAO.update(task);
			EntityManagerHelper.commit();
			this.out(response, "1");
		} catch (RuntimeException re) {
			this.out(response, "0");
		}

	}

	/**
	 * 已经完成的任务
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
	 * 正在进行的任务
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void tasking(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int centerId = (int) request.getSession().getAttribute("currentCenterId");
		TaskService taskService = new TaskServiceImpl();
		List<Task> tasks = taskService.getTaskInfo(centerId, 0);
		request.getSession().setAttribute("tasking", tasks);
		response.sendRedirect("manage/tasking.jsp");
	}

	/**
	 * 发布任务
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void pulishTask(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String musername = request.getParameter("musername");
		String machineplate = request.getParameter("machineplate");
		String tasktype = request.getParameter("tasktype");
		String taskdate = request.getParameter("taskdate");
		int farmlandId = Integer.parseInt(request.getParameter("farmlandId"));
		int workload = Integer.parseInt(request.getParameter("workload"));
		String descr = request.getParameter("descr");
		TaskDAO taskDAO = new TaskDAO();
		Task task = new Task();
		User user = new UserDAO().findById(musername);
		task.setUser(user);
		Machine machine = new MachineDAO().findByPlate(machineplate).get(0);
		task.setMachine(machine);
		Farmland farmland = new FarmlandDAO().findById(farmlandId);
		task.setFarmland(farmland);
		task.setType(tasktype);
		task.setWorkdate(taskdate);
		task.setWorkload(workload);
		task.setDesrc(descr);
		task.setFinished(0);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String publishdate = sdf.format(new Date());
		task.setPublishdate(publishdate);
		try {
			EntityManagerHelper.beginTransaction();
			taskDAO.save(task);
			EntityManagerHelper.commit();
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); 
			JPushUtil.sendPush(musername, "任务来了", gson.toJson(task));
			this.out(response, "1");
		} catch (RuntimeException re) {
			this.out(response, "0");
		}
	}
}
