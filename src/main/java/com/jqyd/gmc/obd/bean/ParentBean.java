package com.jqyd.gmc.obd.bean;

public class ParentBean {
	private String terminalCode;//第三方厂家工商注册号
	private String terminalPassword;//第三方登录密码
	private String infoGuid;
	public String getTerminalCode() {
		return terminalCode;
	}
	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}
	public String getTerminalPassword() {
		return terminalPassword;
	}
	public void setTerminalPassword(String terminalPassword) {
		this.terminalPassword = terminalPassword;
	}
	public String getInfoGuid() {
		return infoGuid;
	}
	public void setInfoGuid(String infoGuid) {
		this.infoGuid = infoGuid;
	}
}
