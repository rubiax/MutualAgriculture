package com.geowind.hunong.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

public class ImageClassify {

	public static Properties props = null;

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
		System.out.println(classifyImagePath);
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
	
	public static void classifyImage(String originImage) {
		String path = createClassifyImage(originImage);
		if(path==null) {
			return;
		}
		
		//调用python模块：tensorflow识别图片
		try {
			System.out.println("start");
			Process pr = Runtime.getRuntime().exec("python python_ImageRecognation/tensorflow/tutorials/image/imagenet/target.py");
			BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			in.close();
			pr.waitFor();
			System.out.println("end");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		//new ImageClassify().createClassifyImage("../HN_upload/imgupload/c1.jpeg");
		try {
			System.out.println("start");
			Process pr = Runtime.getRuntime().exec("python python_ImageRecognation/tensorflow/tutorials/image/imagenet/target.py");
			BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			in.close();
			pr.waitFor();
			System.out.println("end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
