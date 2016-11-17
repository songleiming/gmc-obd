package com.jqyd.gmc.obd.service.impl.handle;

import com.jqmobile.core.server.service.SimpleServiceImpl;
import com.jqyd.gmc.obd.dao.handle.IHandleDao;
import com.jqyd.gmc.obd.entity.handle.TerminalHandleEntity;
import com.jqyd.gmc.obd.service.handle.IHandleService;

public class HandleServiceImpl extends SimpleServiceImpl<TerminalHandleEntity, IHandleDao> implements IHandleService {

	@Override
	public TerminalHandleEntity findByCode(String terminalCode) {
		return getDao().findByCode(terminalCode);
	}

}
