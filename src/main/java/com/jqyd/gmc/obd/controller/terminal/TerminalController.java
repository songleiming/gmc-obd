package com.jqyd.gmc.obd.controller.terminal;

import java.io.IOException;
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
import com.jqyd.gmc.obd.bean.TerminalBean;
import com.jqyd.gmc.obd.util.LogPrintUtil;
import com.jqyd.gmc.obd.util.PushUtils;

@Controller
public class TerminalController {

	public final static DelayQueue<TerminalBean> delayQueue = new DelayQueue<TerminalBean>();
	private static Log logger = LogFactory.getLog("TERMINALLOG");
	private static DES des = new DES();
	@RequestMapping(value = "/terminal")
	@ResponseBody
	public String terminalPush(String json ,HttpServletRequest req, HttpServletResponse resp) {
		TerminalBean terminal = new TerminalBean();
		NJZTCResult result = new NJZTCResult();
		Gson gson = new Gson();
		// 获取IP进行鉴权
		String terminalIp = "";
		try {
			terminalIp = PushUtils.getIpAddress(req);
		} catch (IOException e) {
			logger.info("Terminal获取终端厂家IP失败："+LogPrintUtil.LogExceptionStack(e));
			e.printStackTrace();
		}
		// 读取数据
		json=des.decrypt(json);
		logger.info(terminalIp + "Terminal推送：" + json);
		if (json != null && !"".equals(json)) {
			try {
				terminal = gson.fromJson(json, TerminalBean.class);
			} catch (Exception e) {
				logger.info(terminalIp + "Terminal推送报错：" +LogPrintUtil.LogExceptionStack(e));
				e.printStackTrace();// 上线后删除
				result.setNStatus(2);
				result.setNDesc("必填信息错误");
				return des.encrypt(gson.toJson(result));
			}
		} else {
			logger.info(terminalIp + "Terminal推送信息错误:" + json);
			result.setNStatus(2);
			result.setNDesc("必填信息错误");
			return des.encrypt(gson.toJson(result));
		}
		if (terminal != null) {
			delayQueue.put(terminal);
			result.setNStatus(0);
			result.setNDesc("成功");
			return des.encrypt(gson.toJson(result));
		}
		logger.info(terminalIp + "Terminal推送信息错误:" + json);
		result.setNStatus(2);
		result.setNDesc("必填信息错误");
		return des.encrypt(gson.toJson(result));
	}
}
