package com.jqyd.gmc.obd.entity.position;

import com.jqmobile.core.orm.DBColumn;
import com.jqmobile.core.orm.DBTable;
import com.jqmobile.core.orm.SimpleORMBean;

@DBTable(name = "gmc_position", version = 0)
public class PositionEntity extends SimpleORMBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1179330958098394356L;
	@DBColumn
	private String terminalImsi;
	@DBColumn
	private String plateNumber;
	@DBColumn
	private String longitude;
	@DBColumn
	private String latitude;
	@DBColumn(date = true)
	private long positionDate;
	@DBColumn
	private int positionType;
	@DBColumn
	private int jobStatus;
	@DBColumn
	private int jobType;
	@DBColumn
	private int jobPassStatus;
	@DBColumn
	private String jobDepth;
	@DBColumn
	private int jobStartStatus;
	@DBColumn
	private String farmerMachineDerection;
	@DBColumn
	private String farmerMachineSpeed;
	@DBColumn
	private String farmerMachineAltitude;

	/**
	 * 终端IMSI号
	 * 
	 * @return
	 */
	public String getTerminalImsi() {
		return terminalImsi;
	}

	public void setTerminalImsi(String terminalImsi) {
		this.terminalImsi = terminalImsi;
	}

	/**
	 * 车牌号
	 * @return
	 */
	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	/**
	 * 经度
	 * 
	 * @return
	 */
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * 纬度
	 * 
	 * @return
	 */
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * 定位时间
	 * 
	 * @return
	 */
	public long getPositionDate() {
		return positionDate;
	}

	public void setPositionDate(long positionDate) {
		this.positionDate = positionDate;
	}

	/**
	 * 定位方式
	 * 
	 * @return
	 */
	public int getPositionType() {
		return positionType;
	}

	public void setPositionType(int positionType) {
		this.positionType = positionType;
	}

	/**
	 * 作业状态
	 * 
	 * @return
	 */
	public int getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(int jobStatus) {
		this.jobStatus = jobStatus;
	}

	/**
	 * 作业类型
	 * 
	 * @return
	 */
	public int getJobType() {
		return jobType;
	}

	public void setJobType(int jobType) {
		this.jobType = jobType;
	}

	/**
	 * 是否合格
	 * 
	 * @return
	 */
	public int getJobPassStatus() {
		return jobPassStatus;
	}

	public void setJobPassStatus(int jobPassStatus) {
		this.jobPassStatus = jobPassStatus;
	}

	/**
	 * 作业深度
	 * 
	 * @return
	 */
	public String getJobDepth() {
		return jobDepth;
	}

	public void setJobDepth(String jobDepth) {
		this.jobDepth = jobDepth;
	}

	/**
	 * 启动状态
	 * 
	 * @return
	 */
	public int getJobStartStatus() {
		return jobStartStatus;
	}

	public void setJobStartStatus(int jobStartStatus) {
		this.jobStartStatus = jobStartStatus;
	}

	/**
	 * 方向
	 * 
	 * @return
	 */
	public String getFarmerMachineDerection() {
		return farmerMachineDerection;
	}

	public void setFarmerMachineDerection(String farmerMachineDerection) {
		this.farmerMachineDerection = farmerMachineDerection;
	}

	/**
	 * 速度
	 * 
	 * @return
	 */
	public String getFarmerMachineSpeed() {
		return farmerMachineSpeed;
	}

	public void setFarmerMachineSpeed(String farmerMachineSpeed) {
		this.farmerMachineSpeed = farmerMachineSpeed;
	}

	/**
	 * 海拔
	 * 
	 * @return
	 */
	public String getFarmerMachineAltitude() {
		return farmerMachineAltitude;
	}

	public void setFarmerMachineAltitude(String farmerMachineAltitude) {
		this.farmerMachineAltitude = farmerMachineAltitude;
	}
}
