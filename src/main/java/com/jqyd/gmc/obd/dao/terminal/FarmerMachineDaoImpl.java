package com.jqyd.gmc.obd.dao.terminal;

import com.jqmobile.core.server.db.dao.SimpleDAO;
import com.jqmobile.core.server.service.ServiceFactory;
import com.jqyd.gmc.obd.entity.terminal.FarmerMachineEntity;
import com.jqyd.gmc.obd.service.terminal.IFarmerMachineService;

public class FarmerMachineDaoImpl extends SimpleDAO<FarmerMachineEntity> implements IFarmerMachineDao {

	@Override
	public FarmerMachineEntity findByBrandHeadModel(String brand, String head, String model,String license) {
		FarmerMachineEntity entity = null;
		try {
			entity = getORM().queryFirst("farmerMachineBrand = ? and farmerMachineHeading = ? and farmerMachineModel = ? and farmerMachineLicense = ?", brand, head, model,license);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	public static void main(String[] args) {
		IFarmerMachineService service = ServiceFactory.instance(IFarmerMachineService.class);
		FarmerMachineEntity entity = service.findByBrandHeadModel("11", "22", "33","44");
		System.out.println(entity);
	}
}
