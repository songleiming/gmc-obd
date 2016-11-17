package com.jqyd.gmc.obd.controller.photo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jqyd.gmc.obd.bean.PhotoBean;
import com.jqyd.gmc.obd.util.AuthUtil;
import com.jqyd.gmc.obd.util.LogPrintUtil;
import com.jqyd.gmc.obd.util.PushUtils;
import com.jqyd.gmc.obd.util.TransUtil;

public class PositionPhotoSave {
	private static Log logger = LogFactory.getLog("PHOTOLOG");
	public boolean savePositionPhoto(PhotoBean photo){
		boolean authStatus = false;
		if (photo != null) {
			if (photo.getTerminalCode() != null) {
				try {
					authStatus = AuthUtil.checkIpAndPwd(photo.getTerminalCode(), photo.getTerminalPassword(), photo.getPositionPhotoIp());
				} catch (Exception e) {
					logger.info("PositionPhoto推送对终端厂家鉴权失败："+LogPrintUtil.LogExceptionStack(e));
				}
				if(authStatus){
					// 位置信息保存
					boolean status = false;
					try {
						status = TransUtil.positionPhotoTrans(photo);
						//推送照片信息到位置系统
						if(status){
							PushUtils.positionPhotoPush(photo);
						}
					} catch (Exception e) {
						logger.info("PositionPhoto推送信息错误:" + photo.toString()+LogPrintUtil.LogExceptionStack(e));
						e.printStackTrace();
					}
					if(status){
						logger.info("PositionPhoto推送成功，PositionPhoto工商注册号:" + photo.toString());
						return true;
					}else{
						logger.info("PositionPhoto推送失败，PositionPhoto工商注册号:" + photo.toString());
					}
				}else{
					logger.info("PositionPhoto推送数据鉴权失败" + photo.toString());
				}
			} else {
				logger.info("PositionPhoto推送数据厂家工商注册号为空" + photo.toString());
			}
		}
		return false;
	}
}
