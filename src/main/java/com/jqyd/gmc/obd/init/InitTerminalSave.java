package com.jqyd.gmc.obd.init;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jqyd.gmc.obd.bean.TerminalBean;
import com.jqyd.gmc.obd.controller.terminal.TerminalController;
import com.jqyd.gmc.obd.controller.terminal.TerminalSave;
import com.jqyd.gmc.obd.util.LogPrintUtil;

public class InitTerminalSave {
	private static Log logger = LogFactory.getLog("TERMINALLOG");
	private static boolean init = true;
	public void init(){
		if(init){
			Thread upSendThread = new Thread() {
				public void run() {
					while (!isInterrupted()) {
						try {
							TerminalBean bean = TerminalController.delayQueue.take();
							TerminalSave push = new TerminalSave();
							if(!push.saveTerminal(bean)){
								logger.info("Terminal信息出错："+bean.getTerminalImsi()+"-"+bean.getTerminalCode());
							}
							logger.info("线程Terminal位置信息队列大小【"+TerminalController.delayQueue.size()+"】");
						} catch (Throwable e) {
							logger.info("Terminal:"+LogPrintUtil.LogExceptionStack(e));
						}
					}
				};
			};
			upSendThread.setDaemon(true);
			upSendThread.setName("终端信息线程");
			upSendThread.start();
			System.out.println("终端信息线程已启动");
			init = false;
		}
	}
}
