package com.jqyd.gmc.obd.bean;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 作业信息推送类
 * 
 * @author songleiming
 *
 */
public class JobInfoBean extends ParentBean implements Delayed{
	private String jobInfoIp;
	private String terminalImsi;
	private String plateNumber;
	private String jobType;
	private String jobAppointDate;
	private String jobBeginDate;
	private String jobEndDate;
	private String jobPlace;
	private String jobLocation;
	private String jobTime;
	private String jobScale;
	private String jobDepth;
	private String jobPassPercent;
	private String jobEffectiveArea;
	private String jobCoordinateGroup;
	private long nextWorkTime;// 下次重发执行时间
	
	public String getJobInfoIp() {
		return jobInfoIp;
	}

	public void setJobInfoIp(String jobInfoIp) {
		this.jobInfoIp = jobInfoIp;
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

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobAppointDate() {
		return jobAppointDate;
	}

	public void setJobAppointDate(String jobAppointDate) {
		this.jobAppointDate = jobAppointDate;
	}

	public String getJobBeginDate() {
		return jobBeginDate;
	}

	public void setJobBeginDate(String jobBeginDate) {
		this.jobBeginDate = jobBeginDate;
	}

	public String getJobEndDate() {
		return jobEndDate;
	}

	public void setJobEndDate(String jobEndDate) {
		this.jobEndDate = jobEndDate;
	}

	public String getJobPlace() {
		return jobPlace;
	}

	public void setJobPlace(String jobPlace) {
		this.jobPlace = jobPlace;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public String getJobTime() {
		return jobTime;
	}

	public void setJobTime(String jobTime) {
		this.jobTime = jobTime;
	}

	public String getJobScale() {
		return jobScale;
	}

	public void setJobScale(String jobScale) {
		this.jobScale = jobScale;
	}

	public String getJobDepth() {
		return jobDepth;
	}

	public void setJobDepth(String jobDepth) {
		this.jobDepth = jobDepth;
	}

	public String getJobPassPercent() {
		return jobPassPercent;
	}

	public void setJobPassPercent(String jobPassPercent) {
		this.jobPassPercent = jobPassPercent;
	}

	public String getJobEffectiveArea() {
		return jobEffectiveArea;
	}

	public void setJobEffectiveArea(String jobEffectiveArea) {
		this.jobEffectiveArea = jobEffectiveArea;
	}

	public String getJobCoordinateGroup() {
		return jobCoordinateGroup;
	}

	public void setJobCoordinateGroup(String jobCoordinateGroup) {
		this.jobCoordinateGroup = jobCoordinateGroup;
	}
	@Override
	public int compareTo(Delayed o) {
		if (o == null || !(o instanceof PositionBean))
			return 1;
		if (o == this)
			return 0;
		JobInfoBean s = (JobInfoBean) o;
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
