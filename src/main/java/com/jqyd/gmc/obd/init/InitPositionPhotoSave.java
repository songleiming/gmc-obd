package com.jqyd.gmc.obd.init;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jqyd.gmc.obd.bean.PhotoBean;
import com.jqyd.gmc.obd.controller.photo.PositionPhotoController;
import com.jqyd.gmc.obd.controller.photo.PositionPhotoSave;
import com.jqyd.gmc.obd.util.LogPrintUtil;

public class InitPositionPhotoSave {
	private static Log logger = LogFactory.getLog("PHOTOLOG");
	private static boolean init = true;
	public void init(){
		if(init){
			Thread upSendThread = new Thread() {
				public void run() {
					while (!isInterrupted()) {
						try {
							PhotoBean bean = PositionPhotoController.delayQueue.take();
							PositionPhotoSave push = new PositionPhotoSave();
							if (!push.savePositionPhoto(bean)) {
								logger.info("PositionPhoto信息出错："+bean.getTerminalImsi()+"-"+bean.getLongitude());
							}
							logger.info("线程PositionPhoto信息队列大小【"+PositionPhotoController.delayQueue.size()+"】");
						} catch (Throwable e) {
							logger.info("PositionPhoto"+LogPrintUtil.LogExceptionStack(e));
						}
					}
				};
			};
			upSendThread.setDaemon(true);
			upSendThread.setName("照片信息线程");
			upSendThread.start();
			System.out.println("照片信息线程已启动");
			init = false;
		}
	}
}
