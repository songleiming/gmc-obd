package com.jqyd.gmc.obd.service.position;


import com.jqmobile.core.service.ISimpleService;
import com.jqmobile.core.service.Service;
import com.jqyd.gmc.obd.entity.position.PositionEntity;

@Service(impl="com.jqyd.gmc.obd.service.impl.position.PositionServiceImpl")
public interface IPositionService extends ISimpleService<PositionEntity> {
	
}
