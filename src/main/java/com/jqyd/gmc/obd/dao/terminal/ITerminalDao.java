package com.jqyd.gmc.obd.dao.terminal;

import com.jqmobile.core.dao.DAO;
import com.jqmobile.core.server.db.dao.ISimpleDao;
import com.jqyd.gmc.obd.entity.terminal.TerminalEntity;

@DAO(impl="com.jqyd.gmc.obd.dao.terminal.TerminalDaoImpl")
public interface ITerminalDao extends ISimpleDao<TerminalEntity>{
	public TerminalEntity findByBrandHeadModel(String imsi);
}
