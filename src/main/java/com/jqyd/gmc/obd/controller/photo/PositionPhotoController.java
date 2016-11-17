package com.jqyd.gmc.obd.controller.photo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jqiyd.encdec.DES;
import com.jqyd.gmc.obd.bean.NJZTCResult;
import com.jqyd.gmc.obd.bean.PhotoBean;
import com.jqyd.gmc.obd.util.LogPrintUtil;
import com.jqyd.gmc.obd.util.PushUtils;
import com.jqyd.gmc.obd.util.TransUtil;

@Controller
public class PositionPhotoController {

	public final static DelayQueue<PhotoBean> delayQueue = new DelayQueue<PhotoBean>();
	private static Log logger = LogFactory.getLog("PHOTOLOG");
	private static DES des = new DES();
	@RequestMapping(value = "/positionPhoto")
	@ResponseBody
	public String position(String json ,HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<PhotoBean> list = new ArrayList<PhotoBean>();
		List<NJZTCResult> resultList = new ArrayList<NJZTCResult>();
		Gson gson = new Gson();
		// 获取IP进行鉴权
		String photoIp = "";
		try {
			photoIp = PushUtils.getIpAddress(req);
		} catch (IOException e) {
			logger.info("positionPhoto===IP获取失败"+LogPrintUtil.LogExceptionStack(e));
		}
		// 读取数据
		json = des.decrypt(json);
		logger.info(photoIp + "PositionPhoto推送：" + json);
		if (json != null && !"".equals(json)) {
			try {
				//单条数据传输
				//photo = gson.fromJson(json, PhotoBean.class);
				//批量传输
				list = (List<PhotoBean>) TransUtil.strToArray(json,PhotoBean.class);
			} catch (Exception e) {
				logger.info(photoIp + "PositionPhoto推送报错：" +LogPrintUtil.LogExceptionStack(e));
				e.printStackTrace();// 上线后删除
				NJZTCResult result = new NJZTCResult();
				result.setNStatus(2);
				result.setNDesc("必填信息错误");
				resultList.add(result);
				return des.encrypt(gson.toJson(resultList));
			}
		} else {
			logger.info(photoIp + "PositionPhoto推送信息错误:" + json);
			NJZTCResult result = new NJZTCResult();
			result.setNStatus(2);
			result.setNDesc("必填信息错误");
			resultList.add(result);
			return des.encrypt(gson.toJson(resultList));
		}
		if(list.size()>0){
			for(int i = 0;i<list.size();i++){
				PhotoBean photo = list.get(i);
				photo.setPositionPhotoIp(photoIp);
				delayQueue.put(photo);
				NJZTCResult result = new NJZTCResult();
				result.setTerminalCode(photo.getTerminalCode());
				result.setTerminalImsi(photo.getTerminalImsi());
				result.setNStatus(0);
				result.setNDesc("成功");
				resultList.add(result);
			}
			return des.encrypt(gson.toJson(resultList));
		}
		logger.info(photoIp + "PositionPhoto推送信息错误:" + json);
		return des.encrypt(gson.toJson(resultList));
	}
}
