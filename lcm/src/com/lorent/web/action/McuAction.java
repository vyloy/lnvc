package com.lorent.web.action;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.lorent.model.McuMixerBean;
import com.lorent.model.McuServerBean;
import com.lorent.util.PageUtil;
import com.lorent.util.StringUtil;

public class McuAction extends BaseAction<McuServerBean,Integer>{
	
	private static final Logger log = Logger.getLogger(McuAction.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private McuServerBean server;
	private McuServerBean searchModel; 
	/**
	 * 跳转到mcu服务器列表页面
	 * @return
	 * @throws Exception
	 */
	public String toMcuListPage()throws Exception {
		log.info("查看MCU设置列表");
		searchMode = false;
		server = new McuServerBean();
		setSubPageMap(server, serviceFacade.getMcuServerService(),orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/mcuAction_toMcuListPage_admin_mcu.action");
		return SUCCESS;
	}
	/**
	 * 跳转到mcu信息编辑页面
	 * @return
	 * @throws Exception
	 */
	public String toEditMcu()throws Exception {
		log.info("com.lorent.web.action.McuAction toEditMcu() -- 跳转到mcu信息编辑页面 ");
		HttpServletRequest request = ServletActionContext.getRequest();
		int serverId = Integer.parseInt(request.getParameterValues("choose")[0]);
		server = serviceFacade.getMcuServerService().get(serverId);
		return SUCCESS;
	}
	public String addMcu()throws Exception {
		setServer(new McuServerBean());
		return SUCCESS;
	}
	
	public String sortMcu()throws Exception{
		setSortField();
		if(searchMode){
			return searchMcu();
		}
		return toMcuListPage();
	}
	/**
	 * 为当前的mcu增加mixer
	 * @return
	 * @throws Exception
	 */
	public String addMixer()throws Exception {
		log.info("com.lorent.web.action.McuAction addMixer() -- 为当前的mcu增加mixer ");
		HttpServletRequest request = ServletActionContext.getRequest();
		String mixerName = request.getParameter("mixerName");
		String mixerKey = request.getParameter("mixerKey");
//		String mixerKey = mixerName+"@"+server.getServerUrl();
		map = new HashMap<String, String>();
		if("".equals(mixerName.trim())){
			map.put("error",getText("page.errormsg.required",new String[]{getText("page.text.mixername")}));
		}else if ("".equals(mixerKey.trim())) {
			map.put("error",getText("page.errormsg.required",new String[]{getText("page.text.mixerkey")}));
		}else{
			McuMixerBean mixer = new McuMixerBean();
			mixer.setMixerName(mixerName);
			mixer.setMixerKey(mixerKey);
			mixer.setServer(server);
			mixer.setMixerIp(server.getServerIp());
			if(server.getMixers().contains(mixer)){
				map.put("error",getText("action.recordexists"));
			}else {
				server.getMixers().add(mixer);
				map.put("success",mixerName);
			}
		}
		jo = JSONObject.fromObject(map);
		result = jo.toString();
		return SUCCESS;
	}
	/**
	 * 将编辑后的数据写入数据库
	 * @return
	 * @throws Exception
	 */
	public String doEditMcu()throws Exception {
//		for(McuMixerBean mixer:server.getMixers()){
//			mixer.setServer(server);
//			mixer.setMixerIp(server.getServerIp());
//		}
		
		
		McuMixerBean mixer = new McuMixerBean();
//		mixer.setMixerName(server.getServerName()+"default");
//		mixer.setMixerKey(server.getServerName()+"default");
//		mixer.setServer(server);
//		mixer.setMixerIp(server.getServerIp());
//		serviceFacade.getMcuMixerService().delete(new ArrayList(server.getMixers()));
//		server.getMixers().clear();
//		server.getMixers().add(mixer);
			
		serviceFacade.getMcuServerService().createOrUpdate(server);
		return SUCCESS;
	}
	/**
	 * 删除选择的mcu记录
	 * @return
	 * @throws Exception
	 */
	public String deleteMcu()throws Exception {
		log.info("com.lorent.web.action.McuAction deleteMcu() -- 删除选择的mcu记录 ");
		HttpServletRequest request  = ServletActionContext.getRequest();
		String[]ids = request.getParameterValues("choose");
		log.info("删除mcu：" + ids);
		if(ids==null||ids.length==0){
			setErrorMsg(getText("page.errormsg.chooserequired"));
		}else{
			serviceFacade.getMcuServerService().remove(StringUtil.toInteger(ids));
		}
		if(getSearchMode())return searchMcu();
		return toMcuListPage();
	}
	/**
	 * 搜索mcu记录
	 * @return
	 * @throws Exception
	 */
	public String searchMcu()throws Exception {
		if(!"".equals(getIsSearch())){
			searchModel = server;
			setIsSearch("");
			searchMode = true;
		}
		setSearchMode(true);
		setSubPageMap(searchModel,serviceFacade.getMcuServerService(),orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/mcuAction_searchMcu_admin_mcu.action");
		return SUCCESS;
	}

	/* ===============================getters and setters==================================== */
	public String getCallBackUrl() {
		if(callBackUrl==null||callBackUrl.equals("")){
			if(getSearchMode())
				return "app/mcuAction_searchMcu_admin_mcu.action";
			else 
				return"app/mcuAction_toMcuListPage_admin_mcu.action";
		}
		return callBackUrl;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public McuServerBean getServer() {
		return server;
	}
	public void setServer(McuServerBean server) {
		this.server = server;
	}
	public McuServerBean getModel() {
		return server;
	}
	public McuServerBean getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(McuServerBean searchModel) {
		this.searchModel = searchModel;
	}
}