package com.geowind.hunong.servlet;

import static com.geowind.hunong.util.PathUtil.ArticleBeginId;
import static com.geowind.hunong.util.PathUtil.ArticleEndId;
import static com.geowind.hunong.util.PathUtil.ArticleNumber;
import static com.geowind.hunong.util.PathUtil.Lib_PictureURL;
import static com.geowind.hunong.util.PathUtil.Util_HTMLpath;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.geowind.hunong.entity.ArticleSim;
import com.geowind.hunong.util.DBHelperSim;
import com.geowind.hunong.util.FileUploadUtil;
import com.geowind.hunong.util.LibraryHTMLBuilder;
import com.geowind.hunong.util.WeatherDataCrawler;
import com.geowind.hunong.util.WeatherDataTimerTask;

public class InitServlet extends HttpServlet {

	private String filePath = "HN_upload";

	@Override
	public void init() throws ServletException {
		String temp = this.getInitParameter("uploadPath");

		if (temp != null) {
			filePath = temp;
		}

		String path = this.getServletContext().getRealPath("/") + filePath;

		File file = new File(path);
		if (!file.exists()) {
//			System.out.println(path + "璺緞鍒涘缓鎴愬姛");
			//System.out.println("lalalallal");
			file.mkdirs();
		}

		path = this.getServletContext().getRealPath("/") + filePath + "/imgupload";
		file = new File(path);
		if (!file.exists()) {
//			System.out.println(path + "鍥剧墖鏂囦欢澶瑰垱寤烘垚鍔�");
			file.mkdirs();
		}

		path = this.getServletContext().getRealPath("/") + filePath + "/pestlib";
		file = new File(path);
		if (!file.exists()) {
//			System.out.println(path + "铏簱鍒涘缓鎴愬姛");
			file.mkdirs();
		}

		FileUploadUtil.PATH = filePath;
//		System.out.println(filePath);
//		System.out.println(path);

		/*
		 * ---------------------------------------------------------------------
		 * ----
		 */
		Util_HTMLpath = this.getServletContext().getRealPath("/") + Util_HTMLpath;

		// 灏嗗浘鐗囨斁鍒癴ilePath涓�
		//Lib_PictureURL = this.getServletContext().getRealPath("/") + filePath + Lib_PictureURL;
		List<ArticleSim> tmp = DBHelperSim.GetArticleSimUseSql("select * from article");
		ArticleNumber = tmp.size();
		if (ArticleNumber >= 1) {
			ArticleBeginId = Integer.parseInt(tmp.get(0).id);
			ArticleEndId = Integer.parseInt(tmp.get(ArticleNumber - 1).id);
		}

		LibraryHTMLBuilder lhb = new LibraryHTMLBuilder();
		lhb.CreateAllHTML();

//		System.out.println("鏂囧簱鍥剧墖璺緞" + Lib_PictureURL);
//		System.out.println("鎬绘枃绔犳暟" + ArticleNumber);
//		System.out.println("寮�濮婭D" + ArticleBeginId);
//		System.out.println("缁撴潫ID" + ArticleEndId);
		
		/********************************************************************/
		/**
		 * 鐢熸垚weather.json
		 */
		String p = '/'+WeatherDataTimerTask.class.getClassLoader().getResource("../../").getPath();
    	String weatherPath = null;
    	try {
    		weatherPath = URLDecoder.decode(p, "UTF-8").substring(1);
    		weatherPath += "jsonData";
//			System.out.println(path);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String json = new WeatherDataCrawler().getWeatherJson();
		File weatherFile = new File(weatherPath);
		
		if(!weatherFile.exists()) {
			weatherFile.mkdirs();
		}
		
		weatherPath += "/weather.json";
		System.out.println(weatherPath);
		weatherFile = new File(weatherPath);
		if(weatherFile.exists()) {
			weatherFile.delete();
		}
		if(!weatherFile.exists()) {
			try {
				boolean b =file.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(weatherFile, true));
				writer.write(json);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
