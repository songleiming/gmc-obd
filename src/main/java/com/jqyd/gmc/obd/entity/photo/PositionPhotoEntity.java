package com.jqyd.gmc.obd.entity.photo;

import com.jqmobile.core.orm.DBColumn;
import com.jqmobile.core.orm.DBTable;
import com.jqmobile.core.orm.SimpleORMBean;

@DBTable(name = "gmc_position_photo", version = 0)
public class PositionPhotoEntity extends SimpleORMBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -597602325830308537L;

	@DBColumn
	private String terminalImsi;
	@DBColumn
	private String plateNumber;
	@DBColumn
	private String longitude;
	@DBColumn
	private String latitude;
	@DBColumn
	private String photoDateTime;
	@DBColumn
	private String photoRoute;

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
	 * @return
	 */
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * 照片拍摄时间
	 * @return
	 */
	public String getPhotoDateTime() {
		return photoDateTime;
	}

	public void setPhotoDateTime(String photoDateTime) {
		this.photoDateTime = photoDateTime;
	}

	/**
	 * 照片路径
	 * @return
	 */
	public String getPhotoRoute() {
		return photoRoute;
	}

	public void setPhotoRoute(String photoRoute) {
		this.photoRoute = photoRoute;
	}
}
