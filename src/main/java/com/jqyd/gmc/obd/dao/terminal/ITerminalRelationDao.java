package com.jqyd.gmc.obd.dao.terminal;

import com.jqmobile.core.dao.DAO;
import com.jqmobile.core.server.db.dao.ISimpleDao;
import com.jqyd.gmc.obd.entity.terminal.TerminalRelationEntity;

@DAO(impl="com.jqyd.gmc.obd.dao.terminal.TerminalRelationImpl")
public interface ITerminalRelationDao extends ISimpleDao<TerminalRelationEntity>{
	public TerminalRelationEntity findByTerminalimsi(String imsi);
}
