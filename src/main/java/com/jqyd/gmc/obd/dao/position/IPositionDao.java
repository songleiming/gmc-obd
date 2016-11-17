package com.jqyd.gmc.obd.dao.position;

import com.jqmobile.core.dao.DAO;
import com.jqmobile.core.server.db.dao.ISimpleDao;
import com.jqyd.gmc.obd.entity.position.PositionEntity;

@DAO(impl="com.jqyd.gmc.obd.dao.position.PositionDaoImpl")
public interface IPositionDao extends ISimpleDao<PositionEntity> {
	
}
