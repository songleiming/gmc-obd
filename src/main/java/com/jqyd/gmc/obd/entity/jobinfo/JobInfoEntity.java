package com.jqyd.gmc.obd.entity.jobinfo;

import com.jqmobile.core.orm.DBColumn;
import com.jqmobile.core.orm.DBTable;
import com.jqmobile.core.orm.SimpleORMBean;

@DBTable(name = "gmc_jobinfo", version = 0)
public class JobInfoEntity extends SimpleORMBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3999504315277462170L;
	@DBColumn
	private String terminalImsi;
	@DBColumn
	private String plateNumber;
	@DBColumn
	private int jobType;
	@DBColumn(date = true)
	private long jobAppointDate;
	@DBColumn(date = true)
	private long jobBeginDate;
	@DBColumn(date = true)
	private long jobEndDate;
	@DBColumn
	private String jobPlace;
	@DBColumn
	private String jobTime;
	@DBColumn
	private String jobScale;
	@DBColumn
	private String jobLocation;
	@DBColumn
	private String jobDepth;
	@DBColumn
	private String jobPassPercent;
	@DBColumn
	private String jobEffectiveArea;
	@DBColumn
	private String jobCoordinateGroup;
	@DBColumn
	private String jobCheckEffectiveArea;
	@DBColumn
	private String jobCheckUserName;
	@DBColumn(date = true)
	private long jobCheckTime;
	@DBColumn
	private String jobCheckRemark;

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
	 * 作业日期
	 * 
	 * @return
	 */
	public long getJobAppointDate() {
		return jobAppointDate;
	}

	public void setJobAppointDate(long jobAppointDate) {
		this.jobAppointDate = jobAppointDate;
	}

	/**
	 * 作业开始时间
	 * 
	 * @return
	 */
	public long getJobBeginDate() {
		return jobBeginDate;
	}

	public void setJobBeginDate(long jobBeginDate) {
		this.jobBeginDate = jobBeginDate;
	}

	/**
	 * 作业结束时间
	 * 
	 * @return
	 */
	public long getJobEndDate() {
		return jobEndDate;
	}

	public void setJobEndDate(long jobEndDate) {
		this.jobEndDate = jobEndDate;
	}

	/**
	 * 作业地区经纬度
	 * 
	 * @return
	 */
	public String getJobPlace() {
		return jobPlace;
	}

	public void setJobPlace(String jobPlace) {
		this.jobPlace = jobPlace;
	}

	/**
	 * 作业地点详情
	 * @return
	 */
	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	/**
	 * 作业时长
	 * 
	 * @return
	 */
	public String getJobTime() {
		return jobTime;
	}

	public void setJobTime(String jobTime) {
		this.jobTime = jobTime;
	}

	/**
	 * 作业规模
	 * 
	 * @return
	 */
	public String getJobScale() {
		return jobScale;
	}

	public void setJobScale(String jobScale) {
		this.jobScale = jobScale;
	}

	/**
	 * 平均作业深度
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
	 * 作业合格率
	 * 
	 * @return
	 */
	public String getJobPassPercent() {
		return jobPassPercent;
	}

	public void setJobPassPercent(String jobPassPercent) {
		this.jobPassPercent = jobPassPercent;
	}

	/**
	 * 作业有效面积
	 * 
	 * @return
	 */
	public String getJobEffectiveArea() {
		return jobEffectiveArea;
	}

	public void setJobEffectiveArea(String jobEffectiveArea) {
		this.jobEffectiveArea = jobEffectiveArea;
	}

	/**
	 * 作业地块坐标组
	 * 
	 * @return
	 */
	public String getJobCoordinateGroup() {
		return jobCoordinateGroup;
	}

	public void setJobCoordinateGroup(String jobCoordinateGroup) {
		this.jobCoordinateGroup = jobCoordinateGroup;
	}

	/**
	 * 作业校验面积
	 * 
	 * @return
	 */
	public String getJobCheckEffectiveArea() {
		return jobCheckEffectiveArea;
	}

	public void setJobCheckEffectiveArea(String jobCheckEffectiveArea) {
		this.jobCheckEffectiveArea = jobCheckEffectiveArea;
	}

	/**
	 * 作业校验人
	 * 
	 * @return
	 */
	public String getJobCheckUserName() {
		return jobCheckUserName;
	}

	public void setJobCheckUserName(String jobCheckUserName) {
		this.jobCheckUserName = jobCheckUserName;
	}

	/**
	 * 作业校验时间
	 * 
	 * @return
	 */
	public long getJobCheckTime() {
		return jobCheckTime;
	}

	public void setJobCheckTime(long jobCheckTime) {
		this.jobCheckTime = jobCheckTime;
	}

	/**
	 * 作业备注
	 * 
	 * @return
	 */
	public String getJobCheckRemark() {
		return jobCheckRemark;
	}

	public void setJobCheckRemark(String jobCheckRemark) {
		this.jobCheckRemark = jobCheckRemark;
	}
}
