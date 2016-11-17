package com.jqyd.gmc.obd.service.jobinfo;


import com.jqmobile.core.service.ISimpleService;
import com.jqmobile.core.service.Service;
import com.jqyd.gmc.obd.entity.jobinfo.JobInfoEntity;

@Service(impl="com.jqyd.gmc.obd.service.impl.jobinfo.JobInfoServiceImpl")
public interface IJobInfoService extends ISimpleService<JobInfoEntity> {
	
}
