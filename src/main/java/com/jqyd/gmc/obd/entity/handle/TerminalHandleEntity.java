package com.jqyd.gmc.obd.entity.handle;

import com.jqmobile.core.orm.DBColumn;
import com.jqmobile.core.orm.DBTable;
import com.jqmobile.core.orm.SimpleORMBean;

@DBTable(name = "gmc_terminalhandle", version = 0)
public class TerminalHandleEntity extends SimpleORMBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7910656364399726246L;
	@DBColumn
	private String terminalCode;
	@DBColumn
	private String handleUrl;
	@DBColumn
	private int handleType;
	@DBColumn
	private int isDes;

	/**
	 * 厂家工商注册号
	 * @return
	 */
	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	/**
	 * 厂家接收处理结果接口URL
	 * @return
	 */
	public String getHandleUrl() {
		return handleUrl;
	}

	public void setHandleUrl(String handleUrl) {
		this.handleUrl = handleUrl;
	}

	/**
	 * 厂家接收处理接口接口类型
	 * @return
	 */
	public int getHandleType() {
		return handleType;
	}

	public void setHandleType(int handleType) {
		this.handleType = handleType;
	}

	/**
	 * 是否采用加密字符串返回
	 * 0:未加密    1:加密
	 * @return
	 */
	public int getIsDes() {
		return isDes;
	}

	public void setIsDes(int isDes) {
		this.isDes = isDes;
	}
	
}
