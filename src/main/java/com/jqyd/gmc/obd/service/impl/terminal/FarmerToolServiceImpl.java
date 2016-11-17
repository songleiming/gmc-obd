package com.jqyd.gmc.obd.service.impl.terminal;

import com.jqmobile.core.server.service.SimpleServiceImpl;
import com.jqyd.gmc.obd.dao.terminal.IFarmerToolDao;
import com.jqyd.gmc.obd.entity.terminal.FarmerToolEntity;
import com.jqyd.gmc.obd.service.terminal.IFarmerToolService;

public class FarmerToolServiceImpl extends SimpleServiceImpl<FarmerToolEntity, IFarmerToolDao> implements IFarmerToolService{

	@Override
	public FarmerToolEntity findByBrandHeadModel(String brand, String head, String model) {
		return getDao().findByBrandHeadModel(brand, head, model);
	}

}
