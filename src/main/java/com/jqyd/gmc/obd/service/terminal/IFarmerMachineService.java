package com.jqyd.gmc.obd.service.terminal;

import com.jqmobile.core.service.ISimpleService;
import com.jqmobile.core.service.Service;
import com.jqyd.gmc.obd.entity.terminal.FarmerMachineEntity;

@Service(impl="com.jqyd.gmc.obd.service.impl.terminal.FarmerMachineServiceImpl")
public interface IFarmerMachineService extends ISimpleService<FarmerMachineEntity>{
	public FarmerMachineEntity findByBrandHeadModel(String brand,String head,String model,String license);
}
