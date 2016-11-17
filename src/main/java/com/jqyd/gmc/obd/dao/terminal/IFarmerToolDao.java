package com.jqyd.gmc.obd.dao.terminal;

import com.jqmobile.core.dao.DAO;
import com.jqmobile.core.server.db.dao.ISimpleDao;
import com.jqyd.gmc.obd.entity.terminal.FarmerToolEntity;

@DAO(impl="com.jqyd.gmc.obd.dao.terminal.FarmerToolDaoImpl")
public interface IFarmerToolDao extends ISimpleDao<FarmerToolEntity>{
	public FarmerToolEntity findByBrandHeadModel(String brand, String head, String model);
}
