package com.jqyd.gmc.obd.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jiuqi.bean.JsonUser;
import com.jiuqi.bean.Tuser;
import com.jiuqi.njztc.emc.service.emcuser.EmcUserCenterServiceI;
import com.jqmobile.core.server.service.ServiceFactory;
import com.jqmobile.core.utils.plain.StringUtils;
import com.jqyd.gmc.obd.bean.FarmerToolBean;
import com.jqyd.gmc.obd.bean.JobInfoBean;
import com.jqyd.gmc.obd.bean.NJZTCResult;
import com.jqyd.gmc.obd.bean.PhotoBean;
import com.jqyd.gmc.obd.bean.PositionBean;
import com.jqyd.gmc.obd.bean.TerminalBean;
import com.jqyd.gmc.obd.entity.jobinfo.JobInfoEntity;
import com.jqyd.gmc.obd.entity.photo.PositionPhotoEntity;
import com.jqyd.gmc.obd.entity.position.PositionEntity;
import com.jqyd.gmc.obd.entity.terminal.FarmerMachineEntity;
import com.jqyd.gmc.obd.entity.terminal.FarmerToolEntity;
import com.jqyd.gmc.obd.entity.terminal.TerminalEntity;
import com.jqyd.gmc.obd.entity.terminal.TerminalRelationEntity;
import com.jqyd.gmc.obd.service.jobinfo.IJobInfoService;
import com.jqyd.gmc.obd.service.photo.IPositionPhotoService;
import com.jqyd.gmc.obd.service.position.IPositionService;
import com.jqyd.gmc.obd.service.terminal.IFarmerMachineService;
import com.jqyd.gmc.obd.service.terminal.IFarmerToolService;
import com.jqyd.gmc.obd.service.terminal.ITerminalRelationService;
import com.jqyd.gmc.obd.service.terminal.ITerminalService;

public class TransUtil {
	private static IFarmerMachineService machineService = ServiceFactory.instance(IFarmerMachineService.class);
	private static IFarmerToolService toolService = ServiceFactory.instance(IFarmerToolService.class);
	private static ITerminalService terminalService = ServiceFactory.instance(ITerminalService.class);
	private static ITerminalRelationService relationService = ServiceFactory.instance(ITerminalRelationService.class);
	private static IJobInfoService jobinfoService = ServiceFactory.instance(IJobInfoService.class);
	private static IPositionService positionService = ServiceFactory.instance(IPositionService.class);
	private static IPositionPhotoService positionPhotoService = ServiceFactory.instance(IPositionPhotoService.class);
	private static EmcUserCenterServiceI service = IntfServiceFactory.getInstance(EmcUserCenterServiceI.class, EnumUrl.emc);
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Log terminalLogger = LogFactory.getLog("TERMINALLOG");
	private static Log positionLogger = LogFactory.getLog("POSITIONLOG");
	private static Log jobinfoLogger = LogFactory.getLog("JOBINFOLOG");
	private static Log photoLogger = LogFactory.getLog("PHOTOLOG");
	
	public static void main(String[] args) {
		Tuser coopUser = new Tuser();
		coopUser.setUserType(2);
		coopUser.setAccount("93130282092555811U");
		coopUser.setUserAreaId(getAreaCode("河北","唐山","丰南"));
		coopUser.setMobileNum("");
		JsonUser coopJsonUser = service.register(coopUser, "李克于", "", 2, "");
		System.out.println(coopJsonUser.getUser().getCompanyName());
		Tuser coopUser1 = new Tuser();
		coopUser1.setUserType(2);
		coopUser1.setAccount("130282NA000779X");
		coopUser1.setUserAreaId(getAreaCode("河北","唐山","丰南"));
		coopUser1.setMobileNum("");
		JsonUser coopJsonUser1 = service.register(coopUser, "刘勇", "", 2, "");
		System.out.println(coopJsonUser1.getUser().getCompanyName());
		Tuser coopUser2 = new Tuser();
		coopUser2.setUserType(2);
		coopUser2.setAccount("130282NA000783X");
		coopUser2.setUserAreaId(getAreaCode("河北","唐山","丰南"));
		coopUser2.setMobileNum("");
		JsonUser coopJsonUser2 = service.register(coopUser, "毕红旗", "", 2, "");
		System.out.println(coopJsonUser2.getUser().getCompanyName());
	}
	
	public static int terminalTrans(TerminalBean terminal){
		// 鉴权通过开始转换省市县
		long areaCode = getAreaCode(terminal.getCooperProvince(), terminal.getCooperCity(), terminal.getCooperCounty());
		/*if(areaCode==0){
		 	try {
				NJZTCResult result = new NJZTCResult();
				result.setGuid(terminal.getInfoGuid());
				result.setInterfaceType(5);
				result.setNStatus(5);
				result.setNDesc("合作社地址错误");
				result.setTerminalCode(terminal.getTerminalCode());
				result.setTerminalImsi(terminal.getTerminalImsi());
				boolean respStatu = HandleUtil.handle(terminal.getTerminalCode(),result);
				terminalLogger.info("Terminal处理结果推送:"+respStatu+"---"+terminal.getInfoGuid());
			} catch (Exception e) {
				terminalLogger.info("Terminal处理结果推送失败:"+terminal.getInfoGuid());
			}
			return 5;
		}*/
		//转换合作社
		Tuser coopUser = new Tuser();
		coopUser.setUserType(2);
		coopUser.setAccount(terminal.getCooperCode());
		coopUser.setUserAreaId(areaCode);
		coopUser.setMobileNum(terminal.getCooperContactsTel());
		JsonUser coopJsonUser = null;
		try {
			coopJsonUser = service.register(coopUser, terminal.getCooperContacts(), terminal.getCooperContactsTel(), 2, terminal.getTerminalIp());
		} catch (Exception e) {
			e.printStackTrace();
			terminalLogger.info("Terminal合作社注册失败"+terminal.toString()+LogPrintUtil.LogExceptionStack(e));
		}
		//转换农机
		TerminalEntity te = null;
		try {
			te = terminalService.findByBrandHeadModel(terminal.getTerminalImsi());
			if(te!=null){
				te.setTerminalBrand(terminal.getTerminalBrand());
				te.setTerminalCode(terminal.getTerminalCode());
				te.setTerminalImsi(terminal.getTerminalImsi());
				te.setTerminalModel(terminal.getTerminalModel());
				te.setTerminalToolNumber(terminal.getTerminalToolNumber());
				boolean updateStatu = terminalService.update(te);
				terminalLogger.info("Terminal终端修改："+updateStatu);
			}else{
				te = new TerminalEntity();
				te.setRecid();
				te.setTerminalBrand(terminal.getTerminalBrand());
				te.setTerminalCode(terminal.getTerminalCode());
				te.setTerminalImsi(terminal.getTerminalImsi());
				te.setTerminalModel(terminal.getTerminalModel());
				te.setTerminalToolNumber(terminal.getTerminalToolNumber());
				boolean saveStatu = terminalService.create(te);
				terminalLogger.info("Terminal终端保存："+saveStatu);
			}
		} catch (Exception e) {
			terminalLogger.info("Terminal终端信息保存异常"+LogPrintUtil.LogExceptionStack(e));
		}
		FarmerMachineEntity machine = null;
		String machineGuid = "";
		try {
			machine = machineService.findByBrandHeadModel(terminal.getFarmerMachineBrand(), terminal.getFarmerMachineHeading(), terminal.getFarmerMachineModel(),terminal.getFarmerMachineLicense());
			if(machine != null){
				machine.setFarmerMachineBrand(terminal.getFarmerMachineBrand());
				machine.setFarmerMachineHeading(terminal.getFarmerMachineHeading());
				machine.setFarmerMachineLicense(terminal.getFarmerMachineLicense());
				machine.setFarmerMachineModel(terminal.getFarmerMachineModel());
				boolean statu = machineService.update(machine);
				System.out.println("农机修改"+statu);
				terminalLogger.info("Terminal农机信息修改："+statu);
			}else{
				machine = new FarmerMachineEntity();
				machine.setRecid();
				machine.setFarmerMachineBrand(terminal.getFarmerMachineBrand());
				machine.setFarmerMachineHeading(terminal.getFarmerMachineHeading());
				machine.setFarmerMachineLicense(terminal.getFarmerMachineLicense());
				machine.setFarmerMachineModel(terminal.getFarmerMachineModel());
				machineGuid = machine.getRecid().replaceAll("-", "");
				boolean statu = machineService.create(machine);
				System.out.println("农机保存"+statu);
				terminalLogger.info("Terminal农机信息保存："+statu);
			}
		} catch (Exception e) {
			terminalLogger.info("Terminal农机信息保存失败："+LogPrintUtil.LogExceptionStack(e)+"-------"+terminal.toString());
		}
		//转换农具
		List<FarmerToolBean> toolBeanList = terminal.getFarmerTools();
		if(toolBeanList!=null && toolBeanList.size()>0){
			for(int i = 0;i<toolBeanList.size();i++){
				FarmerToolEntity tool = null;
				try {
					tool = toolService.findByBrandHeadModel(toolBeanList.get(i).getFarmerToolBrand(), toolBeanList.get(i).getFarmerToolHeading(), toolBeanList.get(i).getFarmerToolModel());
					if(tool!=null){
						tool.setFarmerMachineGuid(machineGuid);
						tool.setFarmerToolBrand(toolBeanList.get(i).getFarmerToolBrand());
						tool.setFarmerToolHeading(toolBeanList.get(i).getFarmerToolHeading());
						tool.setFarmerToolModel(toolBeanList.get(i).getFarmerToolModel());
						tool.setFarmerToolWidth(toolBeanList.get(i).getFarmerToolWidth());
						boolean statu = toolService.update(tool);
						System.out.println("农具修改"+statu);
						terminalLogger.info("Terminal农具信息修改："+statu);
					}else{
						tool = new FarmerToolEntity();
						tool.setRecid();
						tool.setFarmerMachineGuid(machineGuid);
						tool.setFarmerToolBrand(toolBeanList.get(i).getFarmerToolBrand());
						tool.setFarmerToolHeading(toolBeanList.get(i).getFarmerToolHeading());
						tool.setFarmerToolModel(toolBeanList.get(i).getFarmerToolModel());
						tool.setFarmerToolWidth(toolBeanList.get(i).getFarmerToolWidth());
						boolean statu = toolService.create(tool);
						System.out.println("农具保存"+statu);
						terminalLogger.info("Terminal农具信息保存："+statu);
					}
				} catch (Exception e) {
					terminalLogger.info("Terminal农具信息保存异常："+LogPrintUtil.LogExceptionStack(e));
				}
			}
		}
		String coopGuid = "";
		long coopAreaCode = 0;
		if(coopJsonUser!=null && coopJsonUser.getUser()!=null){
			coopGuid = coopJsonUser.getUser().getGuid();
			coopAreaCode = coopJsonUser.getUser().getCompanyArea();
		}
		try {
			if(!coopGuid.isEmpty() && !machineGuid.isEmpty() && te != null){
				TerminalRelationEntity tr = null;
				tr = relationService.findByTerminalimsi(te.getTerminalImsi());
				if(tr!=null){
					try {
						NJZTCResult result = new NJZTCResult();
						result.setGuid(terminal.getInfoGuid());
						result.setInterfaceType(1);
						result.setNStatus(6);
						result.setNDesc("终端关联关系已存在");
						result.setTerminalCode(terminal.getTerminalCode());
						result.setTerminalImsi(terminal.getTerminalImsi());
						boolean respStatu = HandleUtil.handle(terminal.getTerminalCode(),result);
						terminalLogger.info("Terminal处理结果推送:"+respStatu+"---"+terminal.toString());
					} catch (Exception e) {
						terminalLogger.info("Terminal处理结果推送失败:"+terminal.toString());
					}
					return 6;
				}else{
					tr = new TerminalRelationEntity();
					tr.setRecid();
					tr.setCoopGuid(coopGuid);
					tr.setCoopName(terminal.getCooperName());
					tr.setCoopAreaCode(String.valueOf(coopAreaCode));
					tr.setFarmerMachineGuid(machineGuid);
					tr.setTerminalBrand(terminal.getTerminalBrand());
					tr.setTerminalModel(terminal.getTerminalModel());
					tr.setTerminalImsi(te.getTerminalImsi());
					tr.setTerminalToolNumber(terminal.getTerminalToolNumber());
					boolean statu = relationService.create(tr);
					System.out.println("终端关联关系保存"+statu);
					terminalLogger.info("Terminal终端关联关系保存："+statu);
					try {
						NJZTCResult result = new NJZTCResult();
						result.setGuid(terminal.getInfoGuid());
						result.setInterfaceType(1);
						result.setNStatus(0);
						result.setNDesc("成功");
						result.setTerminalCode(terminal.getTerminalCode());
						result.setTerminalImsi(terminal.getTerminalImsi());
						boolean respStatu = HandleUtil.handle(terminal.getTerminalCode(),result);
						terminalLogger.info("Terminal处理结果推送:"+respStatu+"---"+terminal.getInfoGuid());
					} catch (Exception e) {
						terminalLogger.info("Terminal处理结果推送失败:"+terminal.toString());
					}
				}
			}else{
				System.out.println("Terminal终端关联关系保存失败："+terminal.toString());
				terminalLogger.info("Terminal终端关联关系保存失败："+terminal.toString());
			}
		} catch (Exception e) {
			terminalLogger.info("Terminal终端关联关系保存异常"+LogPrintUtil.LogExceptionStack(e)+"----------"+terminal.toString());
		}
		return 0;
	}
	
	/**
	 * 作业信息类转换
	 * @param bean
	 * @return
	 */
	public static boolean jobInfoTrans(JobInfoBean bean) {
		NJZTCResult result = new NJZTCResult();
		boolean status = false;
		try {
			JobInfoEntity entity = new JobInfoEntity();
			entity.setRecid();
			entity.setTerminalImsi(bean.getTerminalImsi());
			if(StringUtils.isNotEmpty(bean.getPlateNumber(), true)){
				entity.setPlateNumber(bean.getPlateNumber());
			}
			if(bean.getJobType()!=null&&!"".equals(bean.getJobType())){
				entity.setJobType(Integer.parseInt(bean.getJobType()));
			}
			if(bean.getJobAppointDate()!=null&&!"".equals(bean.getJobAppointDate())){
				entity.setJobAppointDate(sdf.parse(bean.getJobAppointDate()).getTime());
			}
			if(bean.getJobBeginDate()!=null&&!"".equals(bean.getJobBeginDate())){
				entity.setJobBeginDate(sdf.parse(bean.getJobBeginDate()).getTime());
			}
			if(bean.getJobEndDate()!=null&&!"".equals(bean.getJobEndDate())){
				entity.setJobEndDate(sdf.parse(bean.getJobEndDate()).getTime());
			}
			entity.setJobPlace(bean.getJobPlace());
			entity.setJobLocation(bean.getJobLocation());
			entity.setJobTime(bean.getJobTime());
			entity.setJobScale(bean.getJobScale());
			entity.setJobDepth(bean.getJobDepth());
			entity.setJobPassPercent(bean.getJobPassPercent());
			entity.setJobEffectiveArea(bean.getJobEffectiveArea());
			entity.setJobCoordinateGroup(bean.getJobCoordinateGroup());
			status = jobinfoService.create(entity);
			result.setGuid(bean.getInfoGuid());
			result.setInterfaceType(3);
			result.setNStatus(0);
			result.setNDesc("成功");
			result.setTerminalCode(bean.getTerminalCode());
			result.setTerminalImsi(bean.getTerminalImsi());
			boolean respStatu = HandleUtil.handle(bean.getTerminalCode(),result);
			jobinfoLogger.info("JobInfo处理结果推送:"+respStatu+"---"+bean.getInfoGuid());
		} catch (Exception e) {
			e.printStackTrace();
			result.setGuid(bean.getInfoGuid());
			result.setInterfaceType(3);
			result.setNStatus(3);
			result.setNDesc("必填信息不全");
			result.setTerminalCode(bean.getTerminalCode());
			result.setTerminalImsi(bean.getTerminalImsi());
			boolean respStatu = HandleUtil.handle(bean.getTerminalCode(),result);
			jobinfoLogger.info("JobInfo处理结果推送:"+respStatu+"---"+bean.getInfoGuid());
			jobinfoLogger.info("JobInfo信息保存失败:"+LogPrintUtil.LogExceptionStack(e));
		}
		return status;
	}

	/**
	 * 作业照片类转换
	 * @param bean
	 * @return
	 */
	public static boolean positionPhotoTrans(PhotoBean bean) {
		PositionPhotoEntity entity = new PositionPhotoEntity();
		entity.setRecid();
		entity.setTerminalImsi(bean.getTerminalImsi());
		if(StringUtils.isNotEmpty(bean.getPlateNumber(), true)){
			entity.setPlateNumber(bean.getPlateNumber());
		}
		if(StringUtils.isNotEmpty(bean.getLongitude(), true)){
			entity.setLongitude(bean.getLongitude());
		}
		if(StringUtils.isNotEmpty(bean.getLatitude(), true)){
			entity.setLatitude(bean.getLatitude());
		}
		entity.setPhotoDateTime(bean.getPhotoDateTime());
		entity.setPhotoRoute(bean.getPhotoRoute());
		boolean status = false;
		try {
			status = positionPhotoService.create(entity);
			NJZTCResult result = new NJZTCResult();
			result.setGuid(bean.getInfoGuid());
			result.setInterfaceType(5);
			result.setNStatus(0);
			result.setNDesc("成功");
			result.setTerminalCode(bean.getTerminalCode());
			result.setTerminalImsi(bean.getTerminalImsi());
			boolean respStatu = HandleUtil.handle(bean.getTerminalCode(),result);
			photoLogger.info("PositionPhoto处理结果推送:"+respStatu+"---"+bean.toString());
		} catch (Exception e) {
			e.printStackTrace();
			photoLogger.info("PositionPhoto信息保存失败:"+LogPrintUtil.LogExceptionStack(e));
		}
		return status;
	}
	/**
	 * 位置信息类转换
	 * @param bean
	 * @return
	 */
	public static boolean positionTrans(PositionBean bean) {
		boolean status = false;
		try {
			PositionEntity entity = new PositionEntity();
			entity.setRecid();
			entity.setTerminalImsi(bean.getTerminalImsi());
			if(StringUtils.isNotEmpty(bean.getPlateNumber(), true)){
				entity.setPlateNumber(bean.getPlateNumber());
			}
			if(StringUtils.isNotEmpty(bean.getLongitude(), true)){
				entity.setLongitude(bean.getLongitude());
			}
			if(StringUtils.isNotEmpty(bean.getLatitude(), true)){
				entity.setLatitude(bean.getLatitude());
			}
			if(StringUtils.isNotEmpty(bean.getPositionDate(), true)){
				entity.setPositionDate(sdf.parse(bean.getPositionDate()).getTime());
			}
			if(StringUtils.isNotEmpty(bean.getPositionType(), true)){
				entity.setPositionType(Integer.parseInt(bean.getPositionType()));
			}
			if(StringUtils.isNotEmpty(bean.getJobStatus(), true)){
				entity.setJobStatus(Integer.parseInt(bean.getJobStatus()));
			}
			if(StringUtils.isNotEmpty(bean.getJobType(), true)){
				entity.setJobType(Integer.parseInt(bean.getJobType()));
			}
			if(StringUtils.isNotEmpty(bean.getJobPassStatus(), true)){
				entity.setJobPassStatus(Integer.parseInt(bean.getJobPassStatus()));
			}
			if(StringUtils.isNotEmpty(bean.getJobDepth(), true)){
				entity.setJobDepth(bean.getJobDepth());
			}
			if(StringUtils.isNotEmpty(bean.getJobStartStatus(), true)){
				entity.setJobStartStatus(Integer.parseInt(bean.getJobStartStatus()));
			}
			entity.setFarmerMachineDerection(bean.getFarmerMachineDerection());
			entity.setFarmerMachineSpeed(bean.getFarmerMachineSpeed());
			entity.setFarmerMachineAltitude(bean.getFarmerMachineAltitude());
			status = positionService.create(entity);
			NJZTCResult result = new NJZTCResult();
			result.setGuid(bean.getInfoGuid());
			result.setInterfaceType(4);
			result.setNStatus(0);
			result.setNDesc("成功");
			result.setTerminalCode(bean.getTerminalCode());
			result.setTerminalImsi(bean.getTerminalImsi());
			boolean respStatu = HandleUtil.handle(bean.getTerminalCode(),result);
			positionLogger.info("Position处理结果推送:"+respStatu+"---"+bean.getInfoGuid());
		} catch (Exception e) {
			e.printStackTrace();
			positionLogger.info("Position信息保存失败:"+LogPrintUtil.LogExceptionStack(e));
		}
		return status;
	}

	/**
	 * 批量传输数时转换为list
	 * @param str
	 * @param type
	 * @return
	 */
	public static <T> T strToArray(String str,Class<T> type){
		List<T> list = new ArrayList<T>();
		Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(str).getAsJsonArray();
        for(int i = 0;i<jsonArray.size();i++){
        	JsonElement el = jsonArray.get(i);
        	gson.fromJson(el, type);
        	list.add((T) gson.fromJson(el, type));
        }
		return (T) list;
	}
	/**
	 * 根据参数中的省市县调用接口查询该地区行政区划代码
	 * 注：分别查询省市县行政区划，并针对省区划前两位、市区划前两位与市区划前四位、县区划前四位进行判断，如果都相等则返回县区划，否则返回0
	 * @param province
	 * @param city
	 * @param county
	 * @return
	 */
	private static long getAreaCode(String... area){
		try {
			String code = String.valueOf(service.getAreaCodeByNames(area));
			if("00000000".equals(code.substring(4))){
				return 0;
			}
			return Long.parseLong(code);
		} catch (Exception e) {
			e.printStackTrace();
			terminalLogger.info("Terminal获取合作社行政区划错误:"+LogPrintUtil.LogExceptionStack(e));
			return 0;
		}
	}
}
