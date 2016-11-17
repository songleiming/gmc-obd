package com.jqyd.gmc.obd.bean;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 位置信息推送类
 * 
 * @author songleiming
 *
 */
public class PositionBean extends ParentBean implements Delayed{
	private String positionIp;
	private String terminalImsi;
	private String plateNumber;
	private String longitude;
	private String latitude;
	private String positionDate;
	private String positionType;
	private String jobStatus;
	private String jobType;
	private String jobPassStatus;
	private String jobDepth;
	private String jobStartStatus;
	private String farmerMachineDerection;
	private String farmerMachineSpeed;
	private String farmerMachineAltitude;
	private long nextWorkTime;// 下次重发执行时间
	public String getPositionIp() {
		return positionIp;
	}

	public void setPositionIp(String positionIp) {
		this.positionIp = positionIp;
	}

	public String getTerminalImsi() {
		return terminalImsi;
	}

	public void setTerminalImsi(String terminalImsi) {
		this.terminalImsi = terminalImsi;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getPositionDate() {
		return positionDate;
	}

	public void setPositionDate(String positionDate) {
		this.positionDate = positionDate;
	}

	public String getPositionType() {
		return positionType;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobPassStatus() {
		return jobPassStatus;
	}

	public void setJobPassStatus(String jobPassStatus) {
		this.jobPassStatus = jobPassStatus;
	}

	public String getJobDepth() {
		return jobDepth;
	}

	public void setJobDepth(String jobDepth) {
		this.jobDepth = jobDepth;
	}

	public String getJobStartStatus() {
		return jobStartStatus;
	}

	public void setJobStartStatus(String jobStartStatus) {
		this.jobStartStatus = jobStartStatus;
	}

	public String getFarmerMachineDerection() {
		return farmerMachineDerection;
	}

	public void setFarmerMachineDerection(String farmerMachineDerection) {
		this.farmerMachineDerection = farmerMachineDerection;
	}

	public String getFarmerMachineSpeed() {
		return farmerMachineSpeed;
	}

	public void setFarmerMachineSpeed(String farmerMachineSpeed) {
		this.farmerMachineSpeed = farmerMachineSpeed;
	}

	public String getFarmerMachineAltitude() {
		return farmerMachineAltitude;
	}

	public void setFarmerMachineAltitude(String farmerMachineAltitude) {
		this.farmerMachineAltitude = farmerMachineAltitude;
	}

	@Override
	public int compareTo(Delayed o) {
		if (o == null || !(o instanceof PositionBean))
			return 1;
		if (o == this)
			return 0;
		PositionBean s = (PositionBean) o;
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
