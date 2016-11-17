package com.jqyd.gmc.obd.dao.handle;

import com.jqmobile.core.dao.DAO;
import com.jqmobile.core.server.db.dao.ISimpleDao;
import com.jqyd.gmc.obd.entity.handle.TerminalHandleEntity;

@DAO(impl="com.jqyd.gmc.obd.dao.handle.HandleDaoImpl")
public interface IHandleDao extends ISimpleDao<TerminalHandleEntity> {
	public TerminalHandleEntity findByCode(String terminalCode);
}
