package com.jqyd.gmc.obd.dao.terminal;

import com.jqmobile.core.server.db.dao.SimpleDAO;
import com.jqyd.gmc.obd.entity.terminal.TerminalEntity;

public class TerminalDaoImpl extends SimpleDAO<TerminalEntity> implements ITerminalDao{

	@Override
	public TerminalEntity findByBrandHeadModel(String imsi) {
		TerminalEntity entity = null;
		try {
			entity = getORM().queryFirst("terminalImsi = ?",imsi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

}
