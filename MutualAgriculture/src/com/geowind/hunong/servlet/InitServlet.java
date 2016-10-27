package com.geowind.hunong.servlet;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.geowind.hunong.util.FileUploadUtil;



public class InitServlet extends HttpServlet {

	private String filePath = "HN_upload";
	
	@Override
	public void init() throws ServletException {
		String temp = this.getInitParameter("uploadPath");
		
		if(temp != null) {
			filePath = temp;
		}
		
		String path = this.getServletContext().getRealPath("/")+filePath;
		File file = new File(path);
		if(!file.exists()) {
			System.out.println(path + "路径创建成功");
			file.mkdirs();
		}
		
		FileUploadUtil.PATH = filePath;
		System.out.println(filePath);
		System.out.println(path);
	}
}
