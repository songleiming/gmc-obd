package com.jqyd.gmc.obd.service.terminal;

import com.jqmobile.core.service.ISimpleService;
import com.jqmobile.core.service.Service;
import com.jqyd.gmc.obd.entity.terminal.FarmerToolEntity;

@Service(impl="com.jqyd.gmc.obd.service.impl.terminal.FarmerToolServiceImpl")
public interface IFarmerToolService extends ISimpleService<FarmerToolEntity>{
	public FarmerToolEntity findByBrandHeadModel(String brand,String head,String model);
}
