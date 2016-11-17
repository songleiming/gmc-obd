package com.jqyd.gmc.obd.bean;

import java.util.ArrayList;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 终端推送类
 * @author songleiming
 *
 */
public class TerminalBean extends ParentBean implements Delayed{
	private String terminalIp;
	private String cooperCode;//合作社工商注册号
	private String cooperName;//合作社名称
	private String cooperContacts;//合作社联系人
	private String cooperContactsTel;//合作社联系人电话
	private String cooperProvince;//合作社省份
	private String cooperCity;//合作社市区
	private String cooperCounty;//合作社县区
	private String farmerMachineLicense;//农机号牌
	private String farmerMachineBrand;//农机品牌
	private String farmerMachineHeading;//农机品目
	private String farmerMachineModel;//农机型号
	private ArrayList<FarmerToolBean> farmerTools;//农具列表
	private String terminalBrand;//终端品牌
	private String terminalModel;//终端型号
	private String terminalImsi;//终端IMSI号
	private String terminalToolNumber;//终端设备号
	private long nextWorkTime;// 下次重发执行时间
	public String getTerminalIp() {
		return terminalIp;
	}
	public void setTerminalIp(String terminalIp) {
		this.terminalIp = terminalIp;
	}
	public String getCooperCode() {
		return cooperCode;
	}
	public void setCooperCode(String cooperCode) {
		this.cooperCode = cooperCode;
	}
	public String getCooperName() {
		return cooperName;
	}
	public void setCooperName(String cooperName) {
		this.cooperName = cooperName;
	}
	public String getCooperContacts() {
		return cooperContacts;
	}
	public void setCooperContacts(String cooperContacts) {
		this.cooperContacts = cooperContacts;
	}
	public String getCooperContactsTel() {
		return cooperContactsTel;
	}
	public void setCooperContactsTel(String cooperContactsTel) {
		this.cooperContactsTel = cooperContactsTel;
	}
	public String getCooperProvince() {
		return cooperProvince;
	}
	public void setCooperProvince(String cooperProvince) {
		this.cooperProvince = cooperProvince;
	}
	public String getCooperCity() {
		return cooperCity;
	}
	public void setCooperCity(String cooperCity) {
		this.cooperCity = cooperCity;
	}
	public String getCooperCounty() {
		return cooperCounty;
	}
	public void setCooperCounty(String cooperCounty) {
		this.cooperCounty = cooperCounty;
	}
	public String getFarmerMachineLicense() {
		return farmerMachineLicense;
	}
	public void setFarmerMachineLicense(String farmerMachineLicense) {
		this.farmerMachineLicense = farmerMachineLicense;
	}
	public String getFarmerMachineBrand() {
		return farmerMachineBrand;
	}
	public void setFarmerMachineBrand(String farmerMachineBrand) {
		this.farmerMachineBrand = farmerMachineBrand;
	}
	public String getFarmerMachineHeading() {
		return farmerMachineHeading;
	}
	public void setFarmerMachineHeading(String farmerMachineHeading) {
		this.farmerMachineHeading = farmerMachineHeading;
	}
	public String getFarmerMachineModel() {
		return farmerMachineModel;
	}
	public void setFarmerMachineModel(String farmerMachineModel) {
		this.farmerMachineModel = farmerMachineModel;
	}
	public ArrayList<FarmerToolBean> getFarmerTools() {
		return farmerTools;
	}
	public void setFarmerTools(ArrayList<FarmerToolBean> farmerTools) {
		this.farmerTools = farmerTools;
	}
	public String getTerminalBrand() {
		return terminalBrand;
	}
	public void setTerminalBrand(String terminalBrand) {
		this.terminalBrand = terminalBrand;
	}
	public String getTerminalModel() {
		return terminalModel;
	}
	public void setTerminalModel(String terminalModel) {
		this.terminalModel = terminalModel;
	}
	public String getTerminalImsi() {
		return terminalImsi;
	}
	public void setTerminalImsi(String terminalImsi) {
		this.terminalImsi = terminalImsi;
	}
	public String getTerminalToolNumber() {
		return terminalToolNumber;
	}
	public void setTerminalToolNumber(String terminalToolNumber) {
		this.terminalToolNumber = terminalToolNumber;
	}
	@Override
	public int compareTo(Delayed o) {
		if (o == null || !(o instanceof PositionBean))
			return 1;
		if (o == this)
			return 0;
		TerminalBean s = (TerminalBean) o;
		if (this.nextWorkTime > s.nextWorkTime) {
			return 1;
		} else if (this.nextWorkTime == s.nextWorkTime) {
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(getNextWorkTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}
	public void setNextWorkTime(long nextWorkTime) {
		this.nextWorkTime = nextWorkTime;
	}

	public long getNextWorkTime() {
		return nextWorkTime;
	}

	public int i = 0;

	public void freshenNextWorkTime() {
		long nextTime;
		if (i < 6) {
			nextTime = 60000 * (2 ^ i);
			i++;
		} else {
			nextTime = 3600000;
		}
		setNextWorkTime(nextTime + System.currentTimeMillis());
	}
}
