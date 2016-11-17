package com.jqyd.gmc.obd.controller.jobinfo;

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
import com.jqyd.gmc.obd.bean.JobInfoBean;
import com.jqyd.gmc.obd.bean.NJZTCResult;
import com.jqyd.gmc.obd.util.LogPrintUtil;
import com.jqyd.gmc.obd.util.PushUtils;
import com.jqyd.gmc.obd.util.TransUtil;

@Controller
public class JobInfoController {

	public final static DelayQueue<JobInfoBean> delayQueue = new DelayQueue<JobInfoBean>();
	private static Log logger = LogFactory.getLog("JOBINFOLOG");
	private static DES des = new DES();
	@RequestMapping(value = "/jobInfo")
	@ResponseBody
	public String jobInfo(String json ,HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<JobInfoBean> list = new ArrayList<JobInfoBean>();
		List<NJZTCResult> resultList = new ArrayList<NJZTCResult>();
		Gson gson = new Gson();
		// 获取IP进行鉴权
		String jobInfoIp = "";
		try {
			jobInfoIp = PushUtils.getIpAddress(req);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 读取数据
		json = des.decrypt(json);
		logger.info(jobInfoIp + "JobInfo推送：" + json);
		if (json != null && !"".equals(json)) {
			try {
				//单条数据传输
				//jobInfo = gson.fromJson(json, JobInfoBean.class);
				//批量传输
				list = (List<JobInfoBean>) TransUtil.strToArray(json,JobInfoBean.class);
			} catch (Exception e) {
				logger.info(jobInfoIp + "JobInfo推送报错：" +LogPrintUtil.LogExceptionStack(e));
				e.printStackTrace();// 上线后删除
				NJZTCResult result = new NJZTCResult();
				result.setNStatus(2);
				result.setNDesc("必填信息错误");
				resultList.add(result);
				return des.encrypt(gson.toJson(resultList));
			}
		} else {
			logger.info(jobInfoIp + "JobInfo推送信息错误:" + json);
			NJZTCResult result = new NJZTCResult();
			result.setNStatus(2);
			result.setNDesc("必填信息错误");
			resultList.add(result);
			return des.encrypt(gson.toJson(resultList));
		}
		if(list.size()>0){
			for(int i = 0;i<list.size();i++){
				JobInfoBean jobInfo = list.get(i);
				jobInfo.setJobInfoIp(jobInfoIp);
				delayQueue.put(jobInfo);
				NJZTCResult result = new NJZTCResult();
				result.setTerminalCode(jobInfo.getTerminalCode());
				result.setTerminalImsi(jobInfo.getTerminalImsi());
				result.setNStatus(0);
				result.setNDesc("成功");
				resultList.add(result);
			}
			return des.encrypt(gson.toJson(resultList));
		}
		logger.info(jobInfoIp + "JobInfo推送信息错误:" + json);
		return des.encrypt(gson.toJson(resultList));
	}
}
