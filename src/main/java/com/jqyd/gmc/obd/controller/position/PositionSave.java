package com.jqyd.gmc.obd.controller.position;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jqyd.gmc.obd.bean.PositionBean;
import com.jqyd.gmc.obd.util.AuthUtil;
import com.jqyd.gmc.obd.util.LogPrintUtil;
import com.jqyd.gmc.obd.util.PushUtils;
import com.jqyd.gmc.obd.util.TransUtil;

public class PositionSave {
	private static Log logger = LogFactory.getLog("POSITIONLOG");
	public boolean savePositionBean(PositionBean position){
		boolean authStatus = false;
		if (position.getTerminalCode() != null) {
			try {
				authStatus = AuthUtil.checkIpAndPwd(position.getTerminalCode(), position.getTerminalPassword(), position.getPositionIp());
			} catch (Exception e) {
				logger.info("Position推送对终端厂家鉴权失败："+LogPrintUtil.LogExceptionStack(e));
			}
			if(authStatus){
				boolean status = false;
				try {
					status = TransUtil.positionTrans(position);
					//推送位置信息到位置系统
					if(status){
						PushUtils.positionPush(position);
					}
				} catch (Exception e) {
					logger.info("Position推送信息错误:" + position.toString() + LogPrintUtil.LogExceptionStack(e));
				}
				if(status){
					logger.info("Position推送成功:" + position.toString());
					return true;
				}else{
					logger.info("Position推送失败:" + position.toString());
				}
			}else{
				logger.info("Position推送数据鉴权失败" + position.toString());
			}
		} else {
			logger.info("Position推送数据厂家工商注册号为空" + position.toString());
		}
		return false;
	}
}
