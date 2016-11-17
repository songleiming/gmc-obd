package com.jqyd.gmc.obd.bean;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 作业照片推送类
 * 
 * @author songleiming
 *
 */
public class PhotoBean extends ParentBean implements Delayed{
	private String positionPhotoIp;
	private String terminalImsi;
	private String plateNumber;
	private String longitude;
	private String latitude;
	private String photoDateTime;
	private String photoRoute;
	private long nextWorkTime;// 下次重发执行时间

	public String getPositionPhotoIp() {
		return positionPhotoIp;
	}

	public void setPositionPhotoIp(String positionPhotoIp) {
		this.positionPhotoIp = positionPhotoIp;
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

	public String getPhotoDateTime() {
		return photoDateTime;
	}

	public void setPhotoDateTime(String photoDateTime) {
		this.photoDateTime = photoDateTime;
	}

	public String getPhotoRoute() {
		return photoRoute;
	}

	public void setPhotoRoute(String photoRoute) {
		this.photoRoute = photoRoute;
	}
	@Override
	public int compareTo(Delayed o) {
		if (o == null || !(o instanceof PositionBean))
			return 1;
		if (o == this)
			return 0;
		PhotoBean s = (PhotoBean) o;
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
