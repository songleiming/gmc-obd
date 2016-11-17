package com.jqyd.gmc.obd.controller.position;

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
import com.jqyd.gmc.obd.bean.PositionBean;
import com.jqyd.gmc.obd.util.LogPrintUtil;
import com.jqyd.gmc.obd.util.PushUtils;
import com.jqyd.gmc.obd.util.TransUtil;

@Controller
public class PositionController {

	public final static DelayQueue<PositionBean> delayQueue = new DelayQueue<PositionBean>();
	private static Log logger = LogFactory.getLog("POSITIONLOG");
	private static DES des = new DES();
	@RequestMapping(value = "/position")
	@ResponseBody
	public String position(String json ,HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		List<PositionBean> list = new ArrayList<PositionBean>();
		List<NJZTCResult> resultList = new ArrayList<NJZTCResult>();
		Gson gson = new Gson();
		// 获取IP进行鉴权
		String positionIp = "";
		try {
			positionIp = PushUtils.getIpAddress(req);
		} catch (IOException e) {
			logger.info("Position获取IP失败："+LogPrintUtil.LogExceptionStack(e));
			e.printStackTrace();
		}
		// 读取数据
		json = des.decrypt(json);
		logger.info(positionIp + "Position推送：" + json);
		if (json != null && !"".equals(json)) {
			try {
				//单条数据传输
				//position = gson.fromJson(json, PositionBean.class);
				//批量传输
				list = (List<PositionBean>) TransUtil.strToArray(json,PositionBean.class);
			} catch (Exception e) {
				logger.info(positionIp + "Position推送报错：" + e);
				e.printStackTrace();// 上线后删除
				NJZTCResult result = new NJZTCResult();
				result.setNStatus(2);
				result.setNDesc("必填信息错误");
				resultList.add(result);
				return des.encrypt(gson.toJson(resultList));
			}
		} else {
			logger.info(positionIp + "Position推送信息错误:" + json);
			NJZTCResult result = new NJZTCResult();
			result.setNStatus(2);
			result.setNDesc("必填信息错误");
			resultList.add(result);
			return des.encrypt(gson.toJson(resultList));
		}
		if(list.size()>0){
			for(int i = 0;i<list.size();i++){
				PositionBean position = list.get(i);
				position.setPositionIp(positionIp);
				delayQueue.put(position);
				NJZTCResult result = new NJZTCResult();
				result.setTerminalCode(position.getTerminalCode());
				result.setTerminalImsi(position.getTerminalImsi());
				result.setNStatus(0);
				result.setNDesc("成功");
				resultList.add(result);
			}
			return des.encrypt(gson.toJson(resultList));
		}
		logger.info(positionIp + "Position推送信息错误:" + json);
		return des.encrypt(gson.toJson(resultList));
	}
}
