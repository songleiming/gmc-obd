package com.jqyd.gmc.obd.controller.terminal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jqyd.gmc.obd.bean.TerminalBean;
import com.jqyd.gmc.obd.util.AuthUtil;
import com.jqyd.gmc.obd.util.LogPrintUtil;
import com.jqyd.gmc.obd.util.TransUtil;

public class TerminalSave {
	private static Log logger = LogFactory.getLog("TERMINALLOG");
	public boolean saveTerminal(TerminalBean terminal){
		//IP、密码对终端厂家进行鉴权
		try {
			boolean authStatus = false;
			try {
				authStatus = AuthUtil.checkIpAndPwd(terminal.getTerminalCode(), terminal.getTerminalPassword(), terminal.getTerminalIp());
			} catch (Exception e) {
				logger.info("Terminal推送对终端厂家鉴权失败："+LogPrintUtil.LogExceptionStack(e));
			}
			if (authStatus) {
				int num = TransUtil.terminalTrans(terminal);
				switch (num){
					case 5:logger.info("Terminal推送失败，推送数据省市县错误"+terminal.toString()); break;
					case 6:logger.info("Terminal推送失败，推送终端关系已存在"+terminal.toString()); break;
					default:logger.info("Terminal推送成功"+terminal.toString()); break;
				}
				return true;
			} else {
				logger.info("Terminal推送数据鉴权失败" + terminal.toString());
			}
		} catch (Exception e) {
			logger.info("Terminal对终端厂家鉴权接口有误"+terminal.toString()+LogPrintUtil.LogExceptionStack(e));
		}
		return false;
	}
}
