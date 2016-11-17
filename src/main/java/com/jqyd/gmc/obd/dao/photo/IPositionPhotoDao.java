package com.jqyd.gmc.obd.dao.photo;

import com.jqmobile.core.dao.DAO;
import com.jqmobile.core.server.db.dao.ISimpleDao;
import com.jqyd.gmc.obd.entity.photo.PositionPhotoEntity;

@DAO(impl="com.jqyd.gmc.obd.dao.photo.PositionPhotoDaoImpl")
public interface IPositionPhotoDao extends ISimpleDao<PositionPhotoEntity> {
	
}
