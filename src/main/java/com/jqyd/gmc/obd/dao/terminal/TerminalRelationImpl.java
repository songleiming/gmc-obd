package com.jqyd.gmc.obd.dao.terminal;

import com.jqmobile.core.server.db.dao.SimpleDAO;
import com.jqyd.gmc.obd.entity.terminal.TerminalRelationEntity;

public class TerminalRelationImpl extends SimpleDAO<TerminalRelationEntity> implements ITerminalRelationDao{

	@Override
	public TerminalRelationEntity findByTerminalimsi(String imsi) {
		TerminalRelationEntity entity = null;
		try {
			entity = getORM().queryFirst("terminalImsi = ?", imsi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

}
