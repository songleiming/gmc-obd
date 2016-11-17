package com.jqyd.gmc.obd.entity.terminal;

import com.jqmobile.core.orm.DBColumn;
import com.jqmobile.core.orm.DBTable;
import com.jqmobile.core.orm.SimpleORMBean;

@DBTable(name = "gmc_farmer_tool", version = 0)
public class FarmerToolEntity extends SimpleORMBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2716311236092066080L;
	@DBColumn
	private String farmerMachineGuid;
	@DBColumn
	private String farmerToolBrand;
	@DBColumn
	private String farmerToolHeading;
	@DBColumn
	private String farmerToolModel;
	@DBColumn
	private String farmerToolWidth;

	/**
	 * 对应农机guid
	 * @return
	 */
	public String getFarmerMachineGuid() {
		return farmerMachineGuid;
	}

	public void setFarmerMachineGuid(String farmerMachineGuid) {
		this.farmerMachineGuid = farmerMachineGuid;
	}

	/**
	 * 农具品牌
	 * @return
	 */
	public String getFarmerToolBrand() {
		return farmerToolBrand;
	}

	public void setFarmerToolBrand(String farmerToolBrand) {
		this.farmerToolBrand = farmerToolBrand;
	}

	/**
	 * 农具品目
	 * @return
	 */
	public String getFarmerToolHeading() {
		return farmerToolHeading;
	}

	public void setFarmerToolHeading(String farmerToolHeading) {
		this.farmerToolHeading = farmerToolHeading;
	}

	/**
	 * 农具型号
	 * @return
	 */
	public String getFarmerToolModel() {
		return farmerToolModel;
	}

	public void setFarmerToolModel(String farmerToolModel) {
		this.farmerToolModel = farmerToolModel;
	}

	/**
	 * 农具幅宽
	 * @return
	 */
	public String getFarmerToolWidth() {
		return farmerToolWidth;
	}

	public void setFarmerToolWidth(String farmerToolWidth) {
		this.farmerToolWidth = farmerToolWidth;
	}
}
