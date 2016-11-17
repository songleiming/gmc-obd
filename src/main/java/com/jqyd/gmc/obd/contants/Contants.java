package com.jqyd.gmc.obd.contants;

import com.jiuqi.njztc.emc.service.emcuser.EmcUserCenterServiceI;
import com.jiuqi.njztc.pos.service.obd.LcObdPositionImageServiceI;
import com.jiuqi.njztc.pos.service.obd.LcObdPositionServiceI;
import com.jiuqi.service.interfaceUser.InterfaceUserServiceI;

import emc.client.NaispWsContextEmc;
import pos.client.NaispWsContextEmcSms;

public class Contants {
	public static String pUrl = "http://113.57.230.245:9768/wsController/req";
	public static String  postion = "http://emc.njztc.com:9702/wsController/req";
	public static String njuc = "http://njuc.njztc.com:9759/wsController/req";
	public static String emc = "http://emc.njztc.com/WebService/request";
	// public static String njuc = "http://10.144.37.64:9759/wsController/req";
	// private static String emc = "http://10.144.37.64/WebService/request";
	/** emc用户中心接口，查询行政区划、注册以及查询工商注册号 */
	public static EmcUserCenterServiceI userCenterService = NaispWsContextEmc.getContext(emc).getManager(EmcUserCenterServiceI.class, 60000);
	/** 终端厂家IP、密码鉴权接口 */
	public static InterfaceUserServiceI authService = NaispWsContextEmc.getContext(njuc).getManager(InterfaceUserServiceI.class, 60000);
	/** 位置系统推送接口 */
	public static LcObdPositionServiceI positionService = NaispWsContextEmcSms.getContext(pUrl).getManager(LcObdPositionServiceI.class, 60000);
	/** 位置系统推送接口 */
	public static LcObdPositionImageServiceI positionPhotoService = NaispWsContextEmcSms.getContext(pUrl).getManager(LcObdPositionImageServiceI.class, 60000);
}