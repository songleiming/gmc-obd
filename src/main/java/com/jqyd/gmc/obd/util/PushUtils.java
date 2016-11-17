package com.jqyd.gmc.obd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.jiuqi.njztc.pos.bean.LcResult;
import com.jiuqi.njztc.pos.bean.obd.LcObdPositionBean;
import com.jiuqi.njztc.pos.bean.obd.LcObdPositionImageBean;
import com.jqmobile.core.utils.plain.StringUtils;
import com.jqyd.gmc.obd.bean.NJZTCResult;
import com.jqyd.gmc.obd.bean.PhotoBean;
import com.jqyd.gmc.obd.bean.PositionBean;
import com.jqyd.gmc.obd.contants.Contants;

/**
 * 推送接口工具类
 * 
 * @author songleiming
 *
 */
public class PushUtils {

	private static Logger logger = Logger.getLogger(PushUtils.class);
	private static Gson gson = new Gson();
	private static String UTF_8 = "utf-8";
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	/**
	 * 获取真实IP地址
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public final static String getIpAddress(HttpServletRequest request) throws IOException {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

		String ip = request.getHeader("X-Forwarded-For");
		if (logger.isInfoEnabled()) {
			logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
				}
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}

	/**
	 * 返回json字符串
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@Deprecated
	public static String returnJson(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 设置request和response编码
		req.setCharacterEncoding(UTF_8);
		resp.setCharacterEncoding(UTF_8);
		// 第二步，获取IP进行鉴权
		String terminalIp = req.getLocalAddr();
		// TODO IP鉴权还未做
		// 获取流
		InputStream httpInput = null;
		BufferedReader httpReader = null;
		httpInput = req.getInputStream();
		httpReader = new BufferedReader(new InputStreamReader(httpInput, UTF_8));
		StringBuffer sb = new StringBuffer();
		String line = null;
		while ((line = httpReader.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}

	/**
	 * 返回response信息
	 * 
	 * @param resp
	 * @param result
	 * @throws IOException
	 */
	@Deprecated
	public static void returnResp(ServletResponse resp, Object result) throws IOException {
		if (null == result) {
			result = new NJZTCResult();
		}
		Gson gson = new Gson();
		PrintWriter writer = resp.getWriter();
		writer.write(gson.toJson(result));
		writer.flush();
		writer.close();
	}

	/**
	 * 推送作业位置信息到位置系统
	 * 
	 * @param bean
	 * @return
	 */
	public static boolean positionPush(PositionBean bean) {
		LcObdPositionBean t = new LcObdPositionBean();
		boolean result = false;
		t.setGuid();
		t.setLbsMode(0);
		t.setLbsType(7);
		//设置来源
		t.setSource(5);
		if (StringUtils.isNotEmpty(bean.getPositionDate(), true)) {
			try {
				t.setCreateDate(new Date(sdf.parse(bean.getPositionDate()).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(bean.getLongitude(), true)) {
			try {
				t.setLongitude(Double.parseDouble(bean.getLongitude()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(bean.getLatitude(), true)) {
			try {
				t.setLatitude(Double.parseDouble(bean.getLatitude()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*if (StringUtils.isNotEmpty(bean.getPositionType(), true)) {
			t.setLbsType(Integer.parseInt(bean.getPositionType()));
		}*/
		if (StringUtils.isNotEmpty(bean.getTerminalImsi(), true)) {
			t.setImsi(bean.getTerminalImsi());
		}
		if (StringUtils.isNotEmpty(bean.getFarmerMachineDerection(), true)) {
			try {
				t.setHeight(Double.parseDouble(bean.getFarmerMachineDerection()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(bean.getFarmerMachineSpeed(), true)) {
			try {
				t.setSpeed(Double.parseDouble(bean.getFarmerMachineSpeed()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(bean.getJobDepth(), true)) {
			try {
				t.setJobDepth(Double.parseDouble(bean.getJobDepth()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(bean.getPlateNumber(), true)) {
			t.setOperLicensePlateNumber(bean.getPlateNumber());
		}
		LcResult lcresult =Contants.positionService.saveObdPosition(t);
		if(lcresult.getCode()==1){
			result = true;
		}
		logger.info("Position推送到位置系统【"+lcresult.getCode()+"】"+gson.toJson(t));
		System.out.println("Position推送到位置系统【"+lcresult.getCode()+"——"+lcresult.getDesc()+"】"+gson.toJson(t));
		return result;
	}

	/**
	 * 推送作业照片信息到位置系统
	 * 
	 * @param bean
	 * @return
	 */
	public static boolean positionPhotoPush(PhotoBean bean) {
		LcObdPositionImageBean t = new LcObdPositionImageBean();
		t.setGuid();
		if (StringUtils.isNotEmpty(bean.getPhotoDateTime(), true)) {
			try {
				t.setCreateDate(new Date(sdf.parse(bean.getPhotoDateTime()).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(bean.getTerminalImsi(), true)) {
			t.setImsi(bean.getTerminalImsi());
		}
		if (StringUtils.isNotEmpty(bean.getLongitude(), true)) {
			try {
				t.setLongitude(Double.parseDouble(bean.getLongitude()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(bean.getLatitude(), true)) {
			try {
				t.setLatitude(Double.parseDouble(bean.getLatitude()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(StringUtils.isNotEmpty(bean.getPhotoRoute(), true)){
			t.setPhotoRoute(bean.getPhotoRoute());
		}
		boolean result = Contants.positionPhotoService.addObdPositionImage(t);
		logger.info("PositionPhoto推送到位置系统【"+result+"】"+gson.toJson(t));
		System.out.println("PositionPhoto推送到位置系统【"+result+"】"+gson.toJson(t));
		return result;
	}
}
