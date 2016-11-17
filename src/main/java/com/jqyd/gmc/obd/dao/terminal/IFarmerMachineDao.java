package com.jqyd.gmc.obd.dao.terminal;

import com.jqmobile.core.dao.DAO;
import com.jqmobile.core.server.db.dao.ISimpleDao;
import com.jqyd.gmc.obd.entity.terminal.FarmerMachineEntity;

@DAO(impl="com.jqyd.gmc.obd.dao.terminal.FarmerMachineDaoImpl")
public interface IFarmerMachineDao extends ISimpleDao<FarmerMachineEntity>{
	public FarmerMachineEntity findByBrandHeadModel(String brand,String head,String model,String license);
}
