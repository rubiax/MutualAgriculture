package com.geowind.hunong.util;

import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.geowind.hunong.entity.SimConsult;
import com.geowind.hunong.entity.SimPestQuestion;
import com.geowind.hunong.jpa.EntityManagerHelper;
import com.geowind.hunong.jpa.Pestquestion;
import com.geowind.hunong.jpa.PestquestionDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ImageClassify implements Runnable {

	public static Properties props = null;
	private Pestquestion pestquestion;

	static {
		try { // Class.forName(DRIVER_CLASS_NAME);
			props = new Properties();
			InputStream in = DBHelper.class.getClassLoader().getResourceAsStream("global.properties");
			props.load(in);
			LogManager.logger.debug("加载global.properties成功");
		} catch (Exception e) {
			LogManager.logger.error("加载global.properties失败", e);
		}
	}

	public ImageClassify() {
		// TODO Auto-generated constructor stub
	}
	
	public ImageClassify(Pestquestion pestquestion) {
		this.pestquestion = pestquestion;
	}

	private static String createClassifyImage(String originImage) {
		if (originImage == null) {
			LogManager.logger.debug("图片不存在...");
			return null;
		}
		String pestImagePath = props.getProperty("pestImageUpload");
		String huUploadPath = props.getProperty("HN_upload");
		String temp = originImage.split("HN_upload")[1];
		String originImageAbsolutePath = huUploadPath + temp;
		File pestImageUplaodFile = new File(pestImagePath);
		if (!pestImageUplaodFile.exists()) {
			pestImageUplaodFile.mkdirs();
		}
		String classifyImagePath = pestImagePath + File.separator + "classifyimage.jpeg";
		//System.out.println(classifyImagePath);
		File classifyFile = new File(classifyImagePath);
		if (classifyFile.exists()) {
			classifyFile.delete();
		}
		copyFile(originImageAbsolutePath, classifyImagePath);
		return classifyImagePath;
	}

	private static void copyFile(String origin, String target) {
		File originFile = new File(origin);
		File targetFile = new File(target);
		if (!originFile.isFile()) {
			LogManager.logger.debug("只能拷贝文件");
			try {
				throw new IOException("只能拷贝文件");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			// 选择流
			InputStream is = new FileInputStream(originFile);
			OutputStream os = new FileOutputStream(targetFile);

			// 文件拷贝 循环+读取+写出
			byte[] data = new byte[1024];
			int len = 0;
			while (-1 != (len = is.read(data))) {
				os.write(data, 0, len);// 写出
			}
			os.flush();// 强制刷出
			// 关闭流 先打开的后关闭
			os.close();
			is.close();
			LogManager.logger.debug("拷贝成功");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 图片识别过程
	 * @param originImage 待识别图片路径
	 */
	public static String classifyImage(String originImage) {
		String path = createClassifyImage(originImage);
		if(path==null) {
			return null;
		}
		
		//调用python模块：tensorflow识别图片
		try {
			String target = props.getProperty("target");
			Process pr = Runtime.getRuntime().exec("python "+target);
			BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line+"\n");
			}
			in.close();
			pr.waitFor();
			return sb.toString();
			//System.out.println("end");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		String string = new ImageClassify().classifyImage("../HN_upload/imgupload/c5.jpg");
		System.out.println(string);
//		try {
//			System.out.println("start");
//			Process pr = Runtime.getRuntime().exec("python python_ImageRecognation/tensorflow/tutorials/image/imagenet/target.py");
//			BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//			String line;
//			while ((line = in.readLine()) != null) {
//				System.out.println(line);
//			}
//			in.close();
//			pr.waitFor();
//			System.out.println("end");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void run() {
		LogManager.logger.info("run.................");
		PestquestionDAO pestquestionDAO = new PestquestionDAO();
		String string = classifyImage(pestquestion.getUploadPic());
		if(string != null && !"".equals(string)) {
			LogManager.logger.info(string);
			pestquestion.setAnswer(string);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
			pestquestion.setAtime(date);
			pestquestion.setStatus(1);
//			System.out.println(pestquestion.toString());
			EntityManagerHelper.beginTransaction();
			try {
	    		pestquestionDAO.update(pestquestion);
	    		EntityManagerHelper.commit();
	    		
	    		SimPestQuestion simPestQuestion = new SimPestQuestion();
	    		simPestQuestion = simPestQuestion.fromPestquestion(pestquestion);
	    		LogManager.logger.info("图片识别返回结果："+simPestQuestion.toString());
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				JsonObject jsonObject = new JsonParser().parse(gson.toJson(simPestQuestion)).getAsJsonObject();
				JPushUtil.sendPush(simPestQuestion.getUsername(), "虫害识别", jsonObject);
	    		
	    	} catch (RuntimeException re) {
	    		re.printStackTrace();
	    	}
		}
		
	}
}
