package com.jqyd.gmc.obd.service.terminal;

import com.jqmobile.core.service.ISimpleService;
import com.jqmobile.core.service.Service;
import com.jqyd.gmc.obd.entity.terminal.FarmerToolEntity;
import com.jqyd.gmc.obd.entity.terminal.TerminalEntity;

@Service(impl="com.jqyd.gmc.obd.service.impl.terminal.TerminalServiceImpl")
public interface ITerminalService extends ISimpleService<TerminalEntity>{
	public TerminalEntity findByBrandHeadModel(String imsi);
}
