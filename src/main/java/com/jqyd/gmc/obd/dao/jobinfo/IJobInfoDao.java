package com.jqyd.gmc.obd.dao.jobinfo;

import com.jqmobile.core.dao.DAO;
import com.jqmobile.core.server.db.dao.ISimpleDao;
import com.jqyd.gmc.obd.entity.jobinfo.JobInfoEntity;

@DAO(impl="com.jqyd.gmc.obd.dao.jobinfo.JobInfoDaoImpl")
public interface IJobInfoDao extends ISimpleDao<JobInfoEntity> {
	
}
