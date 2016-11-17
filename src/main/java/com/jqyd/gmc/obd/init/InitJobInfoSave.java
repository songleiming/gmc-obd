package com.jqyd.gmc.obd.init;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jqyd.gmc.obd.bean.JobInfoBean;
import com.jqyd.gmc.obd.controller.jobinfo.JobInfoController;
import com.jqyd.gmc.obd.controller.jobinfo.JobInfoSave;
import com.jqyd.gmc.obd.util.LogPrintUtil;

public class InitJobInfoSave {
	private static Log logger = LogFactory.getLog("JOBINFOLOG");
	private static boolean init = true;
	public void init(){
		if(init){
			Thread upSendThread = new Thread() {
				public void run() {
					while (!isInterrupted()) {
						try {
							JobInfoBean bean = JobInfoController.delayQueue.take();
							JobInfoSave push = new JobInfoSave();
							if(!push.saveJobInfo(bean)){
								logger.info("JobInfo信息出错："+bean.getTerminalImsi()+"-"+bean.getJobType());
							}
							logger.info("线程JobInfo位置信息队列大小【"+JobInfoController.delayQueue.size()+"】");
						} catch (Throwable e) {
							logger.info("JobInfo:"+LogPrintUtil.LogExceptionStack(e));
						}
					}
				};
			};
			upSendThread.setDaemon(true);
			upSendThread.setName("作业信息线程");
			upSendThread.start();
			System.out.println("作业信息线程已启动");
			init = false;
		}
	}
}
