package com.jqyd.gmc.obd.service.handle;


import com.jqmobile.core.service.ISimpleService;
import com.jqmobile.core.service.Service;
import com.jqyd.gmc.obd.entity.handle.TerminalHandleEntity;

@Service(impl="com.jqyd.gmc.obd.service.impl.handle.HandleServiceImpl")
public interface IHandleService extends ISimpleService<TerminalHandleEntity> {
	public TerminalHandleEntity findByCode(String terminalCode);
}
