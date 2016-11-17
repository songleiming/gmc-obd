package com.jqyd.gmc.obd.util;

import java.util.HashMap;
import java.util.Map;

import com.jqyd.gmc.obd.contants.Contants;

import client.NaispWsContext;
import emc.client.NaispWsContextEmc;

/**
 * 接口调用工厂
 * @author songleiming
 *
 */
public class IntfServiceFactory {

	private static int timeOut = 6000;
	private final static Object lock = new Object();
	private final static Map<String, Object> map = new HashMap<String, Object>();

	private static IntfServiceFactory intf = new IntfServiceFactory();

	public static <T> T getInstance(Class<T> c, EnumUrl type) {
		return intf.instanceService(c, type);
	}

	/**
	 * 实例化接口方法
	 * @param c  接口类Class
	 * @param type  接口所在服务器
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T instanceService(Class<T> c, EnumUrl type) {
		T t = (T) map.get(c.getName());
		if (null == t) {
			synchronized (lock) {
				t = (T) map.get(c.getName());
				if (null == t) {
					try {
						String url = getUrl(type);
						if(type == EnumUrl.njuc){
							t = NaispWsContext.getContext(url).getManager(c, timeOut);
						}else if (type == EnumUrl.emc) {
							t = NaispWsContextEmc.getContext(url).getManager(c, timeOut);
						}else if(type == EnumUrl.postion){
							t = pos.client.NaispWsContextEmcSms.getContext(url).getManager(c, timeOut);
						}
						map.put(c.getName(), t);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return t;
	}
	
	private static String getUrl(EnumUrl type){
		String url = "";
		switch(type){
			case njuc:url=Contants.njuc;break;
			case emc:url=Contants.emc;break;
			case postion:url=Contants.postion;break;
			default:break;
		}
		return url;
	}
}
