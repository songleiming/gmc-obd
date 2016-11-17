package com.jqyd.gmc.obd.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.jqiyd.encdec.DES;
import com.jqmobile.core.server.service.ServiceFactory;
import com.jqyd.gmc.obd.bean.NJZTCResult;
import com.jqyd.gmc.obd.entity.handle.TerminalHandleEntity;
import com.jqyd.gmc.obd.service.handle.IHandleService;

public class HandleUtil {
	private static DES des = new DES();
	private static Logger logger = LoggerFactory.getLogger(HandleUtil.class);
	private static IHandleService service = ServiceFactory.instance(IHandleService.class);
	private static Object o = new Object();
	private static Map<String, TerminalHandleEntity> map = new HashMap<String, TerminalHandleEntity>();
	public static void main(String[] args) {
		NJZTCResult result = new NJZTCResult();
		result.setGuid("526b9159c725407ca2e25609464d6796");
		result.setInterfaceType(5);
		result.setNStatus(6);
		result.setNDesc("终端关联关系已存在");
		result.setTerminalCode("bochuang");
		System.out.println(handle("bochuang", result));
	}
	public static boolean handle(String terminalCode,NJZTCResult result){
		TerminalHandleEntity entity = getTerminalHandleEntity(terminalCode);
		if(entity!=null){
			if(entity.getHandleUrl()!=null && !"".equals(entity.getHandleUrl())){
				int code = 0;
				switch (entity.getHandleType()){
					case 1:code = reqGet(entity.getHandleUrl());break;
					case 2:code = reqPost(entity.getHandleUrl(),"POST",result,entity.getIsDes());break;
					default:break;
				}
				if(200==code){
					return true;
				}
			}
		}
		return false;
	}
	
	private static TerminalHandleEntity getTerminalHandleEntity(String terminalCode){
		TerminalHandleEntity entity = map.get(terminalCode);
		if(entity!=null){
			return entity;
		}else{
			entity = service.findByCode(terminalCode);
			if(entity!=null){
				setMap(terminalCode,entity);
				return entity;
			}
			return null;
		}
	}
	
	private static void setMap(String terminalCode,TerminalHandleEntity entity){
		synchronized (o) {
			map.put(terminalCode, entity);
		}
	}
	
	private static int reqGet(String url) {
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestProperty("Charset", "utf8");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStream httpInput = null;
				BufferedReader httpReader = null;
				// 读取数据
				httpInput = conn.getInputStream();
				httpReader = new BufferedReader(new InputStreamReader(httpInput, "utf8"));
				String line = null;
				StringBuilder sResult = new StringBuilder();
				while ((line = httpReader.readLine()) != null) {
					if (sResult.length() > 0) {
						sResult.append("\n");
					}
					sResult.append(line);
				}
				String respMsg = sResult.toString();
				System.out.println(respMsg);
			}else{
				System.out.println(conn.getResponseCode());
			}
			return responseCode;
		} catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	private static int reqPost(String url, String reqMethod, Object params,int isDes) {
		String respMsg = "";
		URL _url = null;
		try {
			_url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpURLConnection conn = null;
		//
		try {
			Gson gson = new Gson();
			conn = (HttpURLConnection) _url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod(reqMethod);
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Charset", "utf8");
			// POST请求
			if ("POST".equalsIgnoreCase(reqMethod)) {
				conn.connect();
				DataOutputStream out = new DataOutputStream(conn.getOutputStream());
				String json = gson.toJson(params);
				String str = "json="+URLEncoder.encode(json,"utf-8");
				if(1==isDes){
					str = "json="+des.encrypt(json);
				}
				out.writeBytes(str);
				out.flush();
				out.close();
			}
			StringBuilder sResult = new StringBuilder();
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStream httpInput = null;
				BufferedReader httpReader = null;
				// 读取数据
				httpInput = conn.getInputStream();
				httpReader = new BufferedReader(new InputStreamReader(httpInput, "utf8"));
				String line = null;
				while ((line = httpReader.readLine()) != null) {
					if (sResult.length() > 0) {
						sResult.append("\n");
					}
					sResult.append(line);
				}
				respMsg = sResult.toString();
				System.out.println(respMsg);
				logger.info("Response:"+respMsg+"-----"+gson.toJson(params));
			}else{
				System.out.println(responseCode);
				logger.info("处理结果推送失败----------"+responseCode);
			}
			return responseCode;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	private static String getResp(String url) {
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestProperty("Charset", "utf8");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			if (200 == conn.getResponseCode()) {
				InputStream httpInput = null;
				BufferedReader httpReader = null;
				// 读取数据
				httpInput = conn.getInputStream();
				httpReader = new BufferedReader(new InputStreamReader(httpInput, "utf8"));
				String line = null;
				StringBuilder sResult = new StringBuilder();
				while ((line = httpReader.readLine()) != null) {
					if (sResult.length() > 0) {
						sResult.append("\n");
					}
					sResult.append(line);
				}
				String respMsg = sResult.toString();
				return respMsg;
			}else{
				System.out.println(conn.getResponseCode());
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return "0";
	}
	
	
	private static HttpURLConnection httpRequest(String url) throws IOException {
		HttpURLConnection conn = connect(url);
		conn.setConnectTimeout(10000);
		conn.setReadTimeout(10000);
		conn.connect();
		// request
		PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
		Gson gson = new Gson();
		String requestString = gson.toJson("");
		out.print(requestString);
		out.flush();
		out.close();
		return conn;
	}
	public static HttpURLConnection connect(String url) throws IOException {
		URL _url = null;
		try {
			_url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpURLConnection conn = null;
		//
		conn = (HttpURLConnection) _url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.setUseCaches(false);
		conn.setRequestProperty("Content-type", "text/html");
		conn.setRequestProperty("Charset", "utf-8");
		//
		return conn;
	}
	/**
	 * 获取相应信息
	 * 
	 * @param conn
	 *            链接
	 * @return 响应
	 * @throws IOException
	 */
	private static String getResponseMsg(HttpURLConnection conn) throws IOException {
		StringBuilder sResult = new StringBuilder();
		InputStream httpInput = null;
		BufferedReader httpReader = null;
		// 读取数据
		httpInput = conn.getInputStream();
		httpReader = new BufferedReader(new InputStreamReader(httpInput, "utf-8"));
		String line = null;
		while ((line = httpReader.readLine()) != null) {
			if (sResult.length() > 0) {
				sResult.append("\n");
			}
			sResult.append(line);
		}
		return sResult.toString();
	}
}
