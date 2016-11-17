package com.jqyd.gmc.obd.entity.terminal;

import com.jqmobile.core.orm.DBColumn;
import com.jqmobile.core.orm.DBTable;
import com.jqmobile.core.orm.SimpleORMBean;

@DBTable(name = "gmc_terminal_relation", version = 0)
public class TerminalRelationEntity extends SimpleORMBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2458478134333204207L;
	@DBColumn
	private String coopGuid;
	@DBColumn
	private String coopName;
	@DBColumn
	private String coopAreaCode;
	@DBColumn
	private String farmerMachineGuid;
	@DBColumn
	private String terminalImsi;
	@DBColumn
	private String terminalBrand;
	@DBColumn
	private String terminalModel;
	@DBColumn
	private String terminalToolNumber;

	/**
	 * 合作社guid
	 * @return
	 */
	public String getCoopGuid() {
		return coopGuid;
	}

	public void setCoopGuid(String coopGuid) {
		this.coopGuid = coopGuid;
	}

	/**
	 * 合作社名称
	 * @return
	 */
	public String getCoopName() {
		return coopName;
	}

	public void setCoopName(String coopName) {
		this.coopName = coopName;
	}

	/**
	 * 合作社归属地
	 * @return
	 */
	public String getCoopAreaCode() {
		return coopAreaCode;
	}

	public void setCoopAreaCode(String coopAreaCode) {
		this.coopAreaCode = coopAreaCode;
	}

	/**
	 * 农机guid
	 * @return
	 */
	public String getFarmerMachineGuid() {
		return farmerMachineGuid;
	}

	public void setFarmerMachineGuid(String farmerMachineGuid) {
		this.farmerMachineGuid = farmerMachineGuid;
	}

	/**
	 * 终端imsi号
	 * @return
	 */
	public String getTerminalImsi() {
		return terminalImsi;
	}

	public void setTerminalImsi(String terminalGuid) {
		this.terminalImsi = terminalGuid;
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
