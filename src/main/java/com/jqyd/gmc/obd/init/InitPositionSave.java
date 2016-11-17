package com.jqyd.gmc.obd.init;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jqyd.gmc.obd.bean.PositionBean;
import com.jqyd.gmc.obd.controller.position.PositionController;
import com.jqyd.gmc.obd.controller.position.PositionSave;
import com.jqyd.gmc.obd.util.LogPrintUtil;

public class InitPositionSave {
	private static Log logger = LogFactory.getLog("POSITIONLOG");
	private static boolean init = true;
	public void init(){
		if(init){
			Thread upSendThread = new Thread() {
				public void run() {
					while (!isInterrupted()) {
						try {
							PositionBean bean = PositionController.delayQueue.take();
							PositionSave push = new PositionSave();
							if (!push.savePositionBean(bean)) {
								logger.info("Position信息出错："+bean.getTerminalImsi()+"-"+bean.getLongitude());
							}
							logger.info("线程Position信息队列大小【"+PositionController.delayQueue.size()+"】");
						} catch (Throwable e) {
							logger.info("Position:"+LogPrintUtil.LogExceptionStack(e));
						}
					}
				};
			};
			upSendThread.setDaemon(true);
			upSendThread.setName("位置信息线程");
			upSendThread.start();
			System.out.println("位置信息线程已启动");
			init = false;
		}
	}
}
