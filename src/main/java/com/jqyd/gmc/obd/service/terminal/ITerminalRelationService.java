package com.jqyd.gmc.obd.service.terminal;

import com.jqmobile.core.service.ISimpleService;
import com.jqmobile.core.service.Service;
import com.jqyd.gmc.obd.entity.terminal.FarmerToolEntity;
import com.jqyd.gmc.obd.entity.terminal.TerminalRelationEntity;

@Service(impl="com.jqyd.gmc.obd.service.impl.terminal.TerminalRelationServiceImpl")
public interface ITerminalRelationService extends ISimpleService<TerminalRelationEntity> {
	public TerminalRelationEntity findByTerminalimsi(String imsi);
}
