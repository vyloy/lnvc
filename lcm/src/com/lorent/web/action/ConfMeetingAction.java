package com.lorent.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomAuthenticationException;
import com.lorent.model.ConferenceBean;
import com.lorent.model.MediaBean;
import com.lorent.model.UserBean;
import com.lorent.util.ClassUtil;
import com.lorent.util.Constant;
import com.lorent.util.PageUtil;
import com.lorent.util.PasswordUtil;
import com.lorent.util.StringUtil;
import com.lorent.util.ThreadLocaleUtil;

public class ConfMeetingAction extends BaseAction<ConferenceBean, Integer>{

	private static final Logger log = Logger.getLogger(ConfMeetingAction.class); 
	
	private static final long serialVersionUID = 1L;
	
	private ConferenceBean conf;
	private ConferenceBean searchModel;
	private UserBean user = ThreadLocaleUtil.getUser();
	private Map<Integer, String> confTypeList;
	private Map<Integer, String>mcuMediaLayout = Constant.MCU_MEDIA_LAYOUT;
	private Map<String,String>mediaQuality = Constant.MCU_QUALITYS;
	private Map<Integer,String> confMedias;

	public String toConfMeetingList(){
		searchMode = false;
		searchModel = new ConferenceBean();
		
 		buttonMap.put("edit", true);
		buttonMap.put("add", true);
		buttonMap.put("delete", true);
		
		Map<String, Criterion> condition = new HashMap<String, Criterion>();
		setDefaultCondition(condition);
		DetachedCriteria dc = ClassUtil.createCriteriaByMap(condition, ConferenceBean.class);
		setSubPageMap(dc, serviceFacade.getConferenceService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/confmeetingAction_toConfMeetingList_confmeeting_list.action");
		initConfTypeList();
		initCallBackUrl();
		return SUCCESS;
	}
	
	public String toEditConf()throws Exception{
		log.debug(" com.lorent.web.action.ConfMeetingAction toEditConf() -- 编辑会议室!");
		try{
			Integer[]ids = getSelectedIds();
			conf = serviceFacade.getConferenceService().get(ids[0]);
		}catch(Exception e){
			e.printStackTrace();
			log.error("",e);
			throw e;
		}
		initConfMedias();
		return SUCCESS;
	}
	
	public String toAddConf()throws Exception{
		log.debug(" com.lorent.web.action.ConfMeetingAction toAddConf() -- 增加会议室!");
		try{
			//判断时候可以增加
			serviceFacade.getConferenceService().canCreateConf(user.getCustomer(), Constant.CONF_TYPE_MEETING);
			conf = new ConferenceBean();
			conf.setCustomer(user.getCustomer());
			conf.setConfType(Constant.CONF_TYPE_MEETING);
			conf.setConfSubject(serviceFacade.getConferenceService().getDefaultConfSubject(user.getUsername()));
			initConfMedias();
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("",e);
			throw e;
		}
	}
	
	public String doSaveOrUpdateConf() throws Exception{
		log.debug(" com.lorent.web.action.ConfMeetingAction doSaveOrUpdateConf() -- 增加或编辑会议室!");
		if(conf.getRealPassword()!=null){
			PasswordUtil pu = new PasswordUtil();
			conf.setConfPass(pu.getEncString(conf.getRealPassword()));
		}
		if(!(null!=conf.getIsmix()&&conf.getIsmix().toString().length()>0)){
			log.error("", new ArgsException("args.conftype_ismix"));
			throw new ArgsException("args.conftype_ismix"); 
		}
//		conf.setMcuMixerKey(user.getCustomer().getMcuServer().getMixers().iterator().next().getMixerKey());
		String msg = "";
		if(conf.getId()==null){//add
			msg = "添加会议室";
			log.info(msg);
			try{
				serviceFacade.getConferenceService().createConfNo(conf, user.getCustomer().getCustomerCode(), Constant.CONF_TYPE_MEETING);
				serviceFacade.getConferenceService().createConfMeeting(conf);
			}catch(Exception e){
				conf.setId(null);
				log.error("",e);
				throw e;
			}
		}else{//modify
			msg = "修改会议室" + conf.getId();
			log.info(msg);
			serviceFacade.getConferenceService().renewConfMeeting(conf);
		}
		log.info(msg + "成功");
		return SUCCESS;
	}
	
	public String deleteConf() throws Exception{
		log.debug(" com.lorent.web.action.ConfMeetingAction deleteConf() -- 删除会议室!");
		log.info("删除会议室" + getSelectedIds());
		serviceFacade.getConferenceService().removeConfMeeting(getSelectedIds());
		log.info("删除会议室" + getSelectedIds() + "成功");
		return toConfMeetingList();
	}
	
	
	//初始搜索条件
	private void setDefaultCondition(Map<String, Criterion> map){
		map.put("confType", Restrictions.eq("confType", Constant.CONF_TYPE_MEETING));
		map.put("status", Restrictions.eq("status", Constant.RECORD_VALID));
		map.put("customer.id", Restrictions.eq("customer.id", user.getCustomer().getId()));		
	}
	
	public String searchConf(){
		searchMode = true;
		Map<String, Criterion> condition = new HashMap<String, Criterion>();
		ClassUtil.parseExampleToCriteriaMap(condition, searchModel, null);
		setDefaultCondition(condition);
		DetachedCriteria dc = ClassUtil.createCriteriaByMap(condition, ConferenceBean.class);
		setSubPageMap(dc, serviceFacade.getConferenceService(), orderString);
		return SUCCESS;
	}
	
	public String sortConf() throws Exception{
		setSortField();
		if(searchMode){
			return searchConf();
		}else{
			return toConfMeetingList();
		}
	}
	
	public String checkPass()throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer id = StringUtil.toInteger(request.getParameter("id"), null);
		String pass = request.getParameter("pass");
		map = new HashMap<String, String>();
		try {
			if(pass==null||"".equals(pass)){
				log.error("",new ArgsException("args.passrequired"));
				throw new ArgsException("args.passrequired");
			}
			conf = serviceFacade.getConferenceService().get(id);
			PasswordUtil pu = new PasswordUtil();
			if (!pu.getEncString(pass).equals(conf.getConfPass())) {
				log.error("",new CustomAuthenticationException("authen.wrong.pass"));
				throw new CustomAuthenticationException("authen.wrong.pass");
			}
			map.put("success", "op success");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("",e);
			map.put("error", e.getMessage());
		}
		jo = JSONObject.fromObject(map);
		result = jo.toString();
		return SUCCESS;
	}
	
	private void initCallBackUrl(){
		callBackUrl = "app/confmeetingAction_toConfMeetingList_confmeeting_list.action";
	}

	@Override
	public ConferenceBean getModel() {
		return conf;
	}
	
	//---------------初始化--------------------------
	/**
	 * 初始化或以类型列表
	 *
	 */
	private void initConfTypeList() {
		if(confTypeList==null){
			confTypeList = new TreeMap<Integer, String>();
			confTypeList.put(Constant.CONF_TYPE_IMDCONF,getText("page.display.text.impconf"));
			confTypeList.put(Constant.CONF_TYPE_APPCONF, getText("page.display.text.appconf"));
			confTypeList.put(Constant.CONF_TYPE_PERCONF, getText("page.display.text.perconf"));
		}
	}
	
	private void initConfMedias() throws Exception{
		if(confMedias==null){
			confMedias = new TreeMap<Integer, String>();
			
			DetachedCriteria dc = DetachedCriteria.forClass(MediaBean.class);
			dc.add(Restrictions.eq("status", Constant.RECORD_STATUS_VALID));
			
			List<MediaBean> medias = serviceFacade.getMediaService().getByCriteria(dc);
			for(MediaBean media : medias){
				confMedias.put(media.getId(), media.getMediaName());				
			}
		}
	}
	
	//---------------getter/setter--------------------
	
	public ConferenceBean getConf() {
		return conf;
	}

	public void setConf(ConferenceBean conf) {
		this.conf = conf;
	}

	public Map<Integer, String> getConfTypeList() {
		return confTypeList;
	}

	public Map<Integer, String> getMcuMediaLayout() {
		return mcuMediaLayout;
	}

	public Map<String, String> getMediaQuality() {
		return mediaQuality;
	}

	public Map<Integer, String> getConfMedias() {
		return confMedias;
	}

	public ConferenceBean getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(ConferenceBean searchModel) {
		this.searchModel = searchModel;
	}
	
	


}
