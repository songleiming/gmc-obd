package com.jqyd.gmc.obd.entity.terminal;

import com.jqmobile.core.orm.DBColumn;
import com.jqmobile.core.orm.DBTable;
import com.jqmobile.core.orm.SimpleORMBean;

@DBTable(name = "gmc_farmer_machine", version = 0)
public class FarmerMachineEntity extends SimpleORMBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2862757410131515801L;
	@DBColumn
	private String farmerMachineBrand;
	@DBColumn
	private String farmerMachineHeading;
	@DBColumn
	private String farmerMachineModel;
	@DBColumn
	private String farmerMachineLicense;

	/**
	 * 农机品牌
	 * @return
	 */
	public String getFarmerMachineBrand() {
		return farmerMachineBrand;
	}

	public void setFarmerMachineBrand(String farmerMachineBrand) {
		this.farmerMachineBrand = farmerMachineBrand;
	}

	/**
	 * 农机品目
	 * @return
	 */
	public String getFarmerMachineHeading() {
		return farmerMachineHeading;
	}

	public void setFarmerMachineHeading(String farmerMachineHeading) {
		this.farmerMachineHeading = farmerMachineHeading;
	}

	/**
	 * 农机型号
	 * @return
	 */
	public String getFarmerMachineModel() {
		return farmerMachineModel;
	}

	public void setFarmerMachineModel(String farmerMachineModel) {
		this.farmerMachineModel = farmerMachineModel;
	}

	/**
	 * 农机号牌
	 * @return
	 */
	public String getFarmerMachineLicense() {
		return farmerMachineLicense;
	}

	public void setFarmerMachineLicense(String farmerMachineLicense) {
		this.farmerMachineLicense = farmerMachineLicense;
	}
}
