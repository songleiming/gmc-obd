package com.jqyd.gmc.obd.dao.terminal;

import com.jqmobile.core.server.db.dao.SimpleDAO;
import com.jqyd.gmc.obd.entity.terminal.FarmerToolEntity;

public class FarmerToolDaoImpl extends SimpleDAO<FarmerToolEntity> implements IFarmerToolDao {

	@Override
	public FarmerToolEntity findByBrandHeadModel(String brand, String head, String model) {
		FarmerToolEntity entity = null;
		try {
			entity = getORM().queryFirst("farmerToolBrand = ? and farmerToolHeading = ? and farmerToolModel = ?", brand, head, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

}
