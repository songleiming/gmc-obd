package com.jqyd.gmc.obd.service.impl.terminal;

import com.jqmobile.core.server.service.SimpleServiceImpl;
import com.jqyd.gmc.obd.dao.terminal.ITerminalDao;
import com.jqyd.gmc.obd.entity.terminal.TerminalEntity;
import com.jqyd.gmc.obd.service.terminal.ITerminalService;

public class TerminalServiceImpl extends SimpleServiceImpl<TerminalEntity, ITerminalDao> implements ITerminalService{

	@Override
	public TerminalEntity findByBrandHeadModel(String imsi) {
		return getDao().findByBrandHeadModel(imsi);
	}

}
