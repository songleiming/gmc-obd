package com.jqyd.gmc.obd.service.impl.terminal;

import com.jqmobile.core.server.service.SimpleServiceImpl;
import com.jqyd.gmc.obd.dao.terminal.ITerminalRelationDao;
import com.jqyd.gmc.obd.entity.terminal.FarmerToolEntity;
import com.jqyd.gmc.obd.entity.terminal.TerminalRelationEntity;
import com.jqyd.gmc.obd.service.terminal.ITerminalRelationService;

public class TerminalRelationServiceImpl extends SimpleServiceImpl<TerminalRelationEntity, ITerminalRelationDao> implements ITerminalRelationService{
	@Override
	public TerminalRelationEntity findByTerminalimsi(String imsi) {
		return getDao().findByTerminalimsi(imsi);
	}
}
