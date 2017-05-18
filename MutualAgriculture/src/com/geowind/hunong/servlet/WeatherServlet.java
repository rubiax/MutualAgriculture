package com.geowind.hunong.servlet;

import com.geowind.hunong.entity.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WeatherServlet extends BasicServlet{

	
//	/**
//	 * 鍙戦�佷竴涓〃绀虹缁欏鏈嶇
//	 * @param response
//	 * @param result锛氱粨鏋�
//	 * @throws IOException 
//	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String weather = getWeather("衡阳");
		
		String a = "\\d{1,2}.gif";
		Pattern p = Pattern.compile(a);
		Matcher m = p.matcher(weather);
		List<Weather> weatherList = new ArrayList<Weather>();
		Weather detail = new Weather();
		int i = 1;
		while(m.find()){
			switch(i){
				case 1:
					detail.setFirst1(m.group());
					i++;
					break;
				case 2:
					detail.setFirst2(m.group());
					i++;
					break;
				case 3:
					detail.setSecond1(m.group());
					i++;
					break;
				case 4:
					detail.setSecond2(m.group());
					i++;
					break;
				case 5:
					detail.setThird1(m.group());
					i++;
					break;
				case 6:
					detail.setThird2(m.group());
					i++;
					break;
				case 7:
					detail.setFour1(m.group());
					i++;
					break;
				case 8:
					detail.setFour2(m.group());
					i++;
					break;
				case 9:
					detail.setFive1(m.group());
					i++;
					break;
				case 10:
					detail.setFive2(m.group());
					i++;
					break;
				default:
					break;
					
			}
			
		}	
		
		String after = weather.replaceAll("\\d{1,2}.gif", "");
//		System.out.println("after is"+after);
		detail.setDetail(after);
		weatherList.add(detail);

//		System.out.println("detail is"+weatherList.get(0).getDetail());

//		System.out.println("i= "+i+" first is: "+weatherList.get(0).getFirst1()+" five is :"+weatherList.get(0).getFive2());
			
		this.out(response,weatherList);
//		System.out.println("pao chu shu ju");
	}
	/** 
     * 鑾峰彇SOAP鐨勮姹傚ご锛屽苟鏇挎崲鍏朵腑鐨勬爣蹇楃鍙蜂负鐢ㄦ埛杈撳叆鐨勫煄甯� 
     *  
     * @param city 鐢ㄦ埛杈撳叆鐨勫煄甯傚悕绉� 
     * @return 瀹㈡埛灏嗚鍙戦�佺粰鏈嶅姟鍣ㄧ殑SOAP璇锋眰 
     */  
    private static String getSoapRequest(String city) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>"  
                    + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "  
                    + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "  
                    + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"  
                    + "<soap:Body>    <getWeather xmlns=\"http://WebXml.com.cn/\">"  
                    + "<theCityCode>" + city  
                    + "</theCityCode>    </getWeather>"  
                    + "</soap:Body>"
                    + "</soap:Envelope>");  
        return sb.toString();  
    }  
  
    /** 
     * 鐢ㄦ埛鎶奡OAP璇锋眰鍙戦�佺粰鏈嶅姟鍣ㄧ锛屽苟杩斿洖鏈嶅姟鍣ㄧ偣杩斿洖鐨勮緭鍏ユ祦 
     *  
     * @param city 鐢ㄦ埛杈撳叆鐨勫煄甯傚悕绉� 
     * @return 鏈嶅姟鍣ㄧ杩斿洖鐨勮緭鍏ユ祦锛屼緵瀹㈡埛绔鍙� 
     * @throws Exception 
     */  
    private static InputStream getSoapInputStream(String city) throws Exception {  
        try {  
            String soap = getSoapRequest(city);  
            if (soap == null) {  
                return null;  
            }  
            URL url = new URL(  
                    "http://www.webxml.com.cn/WebServices/WeatherWS.asmx");  
            URLConnection conn = url.openConnection();  
            conn.setUseCaches(false);  
            conn.setDoInput(true);  
            conn.setDoOutput(true);  
  
            conn.setRequestProperty("Content-Length", Integer.toString(soap  
                    .length()));  
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");  
            conn.setRequestProperty("SOAPAction",  
                    "http://WebXml.com.cn/getWeather");  
  
            OutputStream os = conn.getOutputStream();  
            OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");  
            osw.write(soap);  
            osw.flush();  
            osw.close();  
  
            InputStream is = conn.getInputStream();  
            return is;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
  
    /** 
     * 瀵规湇鍔″櫒绔繑鍥炵殑XML杩涜瑙ｆ瀽 
     *  
     * @param city 
     *            鐢ㄦ埛杈撳叆鐨勫煄甯傚悕绉� 
     * @return 瀛楃涓� 鐢�#鍒嗗壊 
     */  
    public static String getWeather(String city) {  
        try {  
            Document doc;  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
            dbf.setNamespaceAware(true);  
            DocumentBuilder db = dbf.newDocumentBuilder();  
            InputStream is = getSoapInputStream(city);  
            doc = db.parse(is);  
            NodeList nl = doc.getElementsByTagName("string");  
            StringBuffer sb = new StringBuffer();  
            for (int count = 0; count < nl.getLength(); count++) {  
                if(count!=6&&count!=2)
                {
	            	Node n = nl.item(count);  
	                if(n.getFirstChild().getNodeValue().equals("鏌ヨ缁撴灉涓虹┖锛�")) {  
	                    sb = new StringBuffer(" ") ;  
	                    break ;  
	                }  
	                sb.append(n.getFirstChild().getNodeValue() + " "); 
                }
            }  
            is.close();  
            return sb.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  

}