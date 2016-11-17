package com.jqyd.gmc.obd.dao.handle;

import com.jqmobile.core.server.db.dao.SimpleDAO;
import com.jqyd.gmc.obd.entity.handle.TerminalHandleEntity;

public class HandleDaoImpl extends SimpleDAO<TerminalHandleEntity> implements IHandleDao {

	@Override
	public TerminalHandleEntity findByCode(String terminalCode) {
		TerminalHandleEntity entity = null;
		try {
			entity = getORM().queryFirst(" terminalCode=?", terminalCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

}
