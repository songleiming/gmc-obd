package com.jqyd.gmc.obd.controller.jobinfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jqyd.gmc.obd.bean.JobInfoBean;
import com.jqyd.gmc.obd.util.AuthUtil;
import com.jqyd.gmc.obd.util.LogPrintUtil;
import com.jqyd.gmc.obd.util.TransUtil;

public class JobInfoSave {
	private static Log logger = LogFactory.getLog("JOBINFOLOG");
	public boolean saveJobInfo(JobInfoBean jobInfo){
		boolean authStatus = false;
		if (jobInfo != null) {
			if (jobInfo.getTerminalCode() != null) {
				try {
					authStatus = AuthUtil.checkIpAndPwd(jobInfo.getTerminalCode(), jobInfo.getTerminalPassword(), jobInfo.getJobInfoIp());
				} catch (Exception e) {
					logger.info("JobInfo推送对终端厂家鉴权失败："+LogPrintUtil.LogExceptionStack(e));
				}
				if(authStatus){
					// 作业信息保存
					boolean status = false;
					try {
						status = TransUtil.jobInfoTrans(jobInfo);
					} catch (Exception e) {
						logger.info("JobInfo推送信息错误:" + jobInfo.toString()+LogPrintUtil.LogExceptionStack(e));
					}
					if(status){
						logger.info("JobInfo推送成功，JobInfo工商注册号:" + jobInfo.toString());
						return true;
					}else{
						logger.info("JobInfo推送失败，JobInfo工商注册号:" + jobInfo.toString());
					}
				}else{
					logger.info("JobInfo推送数据鉴权失败" + jobInfo.toString());
				}
			} else {
				logger.info("JobInfo推送数据厂家工商注册号为空" + jobInfo.toString());
			}
		}
		return false;
	}
}
