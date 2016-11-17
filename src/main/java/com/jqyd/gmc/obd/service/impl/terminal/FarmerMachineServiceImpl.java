package com.jqyd.gmc.obd.service.impl.terminal;

import com.jqmobile.core.server.service.SimpleServiceImpl;
import com.jqyd.gmc.obd.dao.terminal.IFarmerMachineDao;
import com.jqyd.gmc.obd.entity.terminal.FarmerMachineEntity;
import com.jqyd.gmc.obd.service.terminal.IFarmerMachineService;

public class FarmerMachineServiceImpl extends SimpleServiceImpl<FarmerMachineEntity, IFarmerMachineDao> implements IFarmerMachineService{

	@Override
	public FarmerMachineEntity findByBrandHeadModel(String brand, String head, String model,String license) {
		return getDao().findByBrandHeadModel(brand, head, model,license);
	}

}
