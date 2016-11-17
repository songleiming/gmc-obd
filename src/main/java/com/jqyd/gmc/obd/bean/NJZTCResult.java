package com.jqyd.gmc.obd.bean;

public class NJZTCResult {
	private int NStatus;
	private String NDesc;
	private int InterfaceType;
	private String Guid;
	private String terminalCode;
	private String terminalImsi;
	public int getNStatus() {
		return NStatus;
	}
	public void setNStatus(int nStatus) {
		NStatus = nStatus;
	}
	public String getNDesc() {
		return NDesc;
	}
	public void setNDesc(String nDesc) {
		NDesc = nDesc;
	}
	public String getTerminalCode() {
		return terminalCode;
	}
	public int getInterfaceType() {
		return InterfaceType;
	}
	public void setInterfaceType(int interfaceType) {
		InterfaceType = interfaceType;
	}
	public String getGuid() {
		return Guid;
	}
	public void setGuid(String guid) {
		Guid = guid;
	}
	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}
	public String getTerminalImsi() {
		return terminalImsi;
	}
	public void setTerminalImsi(String terminalImsi) {
		this.terminalImsi = terminalImsi;
	}
}
