package com.jqyd.gmc.obd.util;

import com.jiuqi.bean.interfaceUser.InterfaceUserBean;
import com.jqyd.gmc.obd.contants.Contants;

public class AuthUtil {
	public static boolean checkIpAndPwd(String terminalCode, String pwd, String ip) {
		/*try {
			InterfaceUserBean positionBean = Contants.authService.findByAccountAndPasswordAndIp(terminalCode, pwd, ip);
			if(positionBean!=null){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}*/
		return true;
	}
}