package com.jqyd.gmc.obd.service.photo;


import com.jqmobile.core.service.ISimpleService;
import com.jqmobile.core.service.Service;
import com.jqyd.gmc.obd.entity.photo.PositionPhotoEntity;
import com.jqyd.gmc.obd.entity.position.PositionEntity;

@Service(impl="com.jqyd.gmc.obd.service.impl.photo.PositionPhotoServiceImpl")
public interface IPositionPhotoService extends ISimpleService<PositionPhotoEntity> {
	
}
