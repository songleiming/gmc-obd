package com.jqyd.gmc.obd.entity.terminal;

import com.jqmobile.core.orm.DBColumn;
import com.jqmobile.core.orm.DBTable;
import com.jqmobile.core.orm.SimpleORMBean;

@DBTable(name = "gmc_terminal", version = 0)
public class TerminalEntity extends SimpleORMBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -597602325830308537L;

	@DBColumn
	private String terminalCode;
	@DBColumn
	private String terminalBrand;
	@DBColumn
	private String terminalModel;
	@DBColumn
	private String terminalImsi;
	@DBColumn
	private String terminalToolNumber;

	/**
	 * 第三方厂家工商注册号
	 * @return
	 */
	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	/**
	 * 终端品牌
	 * @return
	 */
	public String getTerminalBrand() {
		return terminalBrand;
	}

	public void setTerminalBrand(String terminalBrand) {
		this.terminalBrand = terminalBrand;
	}

	/**
	 * 终端型号
	 * @return
	 */
	public String getTerminalModel() {
		return terminalModel;
	}

	public void setTerminalModel(String terminalModel) {
		this.terminalModel = terminalModel;
	}

	/**
	 * 终端IMSI号
	 * @return
	 */
	public String getTerminalImsi() {
		return terminalImsi;
	}

	public void setTerminalImsi(String terminalImsi) {
		this.terminalImsi = terminalImsi;
	}

	/**
	 * 终端设备号
	 * @return
	 */
	public String getTerminalToolNumber() {
		return terminalToolNumber;
	}

	public void setTerminalToolNumber(String terminalToolNumber) {
		this.terminalToolNumber = terminalToolNumber;
	}
}
