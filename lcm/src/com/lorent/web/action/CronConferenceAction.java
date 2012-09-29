package com.lorent.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.lorent.dto.StartEndDatePair;
import com.lorent.enums.ConfBelongType;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomAuthenticationException;
import com.lorent.exception.CustomSqlException;
import com.lorent.exception.ValidateException;
import com.lorent.model.ConferenceNewBean;
import com.lorent.model.ConferenceNoBean;
import com.lorent.model.ConferenceRoleBean;
import com.lorent.model.ConferenceTypeBean;
import com.lorent.model.CronConferenceBean;
import com.lorent.model.MediaBean;
import com.lorent.model.UserBean;
import com.lorent.util.ClassUtil;
import com.lorent.util.Constant;
import com.lorent.util.PageUtil;
import com.lorent.util.PasswordUtil;
import com.lorent.util.PropertiesUtil;
import com.lorent.util.StringUtil;
import com.lorent.util.ThreadLocaleUtil;

public class CronConferenceAction extends BaseAction<CronConferenceBean,Integer> {

	private static final Logger log = Logger.getLogger(CronConferenceAction.class); 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CronConferenceBean conf;
	private List<ConferenceNoBean>noList;
	private Map<Integer, String>confModeList;
	private UserBean loginUser = ThreadLocaleUtil.getUser();
	private Map<Integer, String>cronTypeList;
	private List<UserBean>userList;
	private Map<Integer, String>mcuMediaLayout = Constant.MCU_MEDIA_LAYOUT;
	private Map<String,String>mediaQuality = Constant.MCU_QUALITYS; 
	private Map<Long, String>allowBeforeList;
	private Map<String, String>dateList;
	private Map<String, String>weekList;
	private enum ModeEnum{ADMIN_MODE,USER_MODE};
	private ModeEnum mode;
	private CronConferenceBean searchModel;
	private String belongType;
	private Map<Integer,String> confMedias;
	private StartEndDatePair datetimePair;
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	public CronConferenceAction() {
		initAllowBeforeList();
		initConfModeList();
		initCronTypeList();
		initDateList();
		initWeekList();
	}
	/**
	 * 跳转到周期会议列表页面，显示登录用户所属客户的周期会议
	 * @return
	 */
	public String toCronConfList() throws Exception {
		log.info("com.lorent.web.action.CronConferenceAction toCronConfList() --  跳转到周期会议列表页面，显示登录用户所属客户的周期会议");
		searchMode = false;
		conf = new CronConferenceBean();
		mode = ModeEnum.ADMIN_MODE;
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/cronConferenceAction_toCronConfList_cronconflist.action");
		return toList();
	}
	
	public String sortConf() throws Exception{
		setSortField();
		if(searchMode){
			return searchConfs();
		}
		return toCronConfList();
	}
	
	/**
	 * 添加会议
	 * @return
	 */
	public String addConf()throws Exception {
		log.info("com.lorent.web.action.CronConferenceAction addConf() --  添加会议");
		UserBean user = ThreadLocaleUtil.getUser();
		initCallbackUrl();
		//判断是否可以增加周期会议
		serviceFacade.getConferenceService().canCreateConf(user.getCustomer(), Constant.CONF_TYPE_PERCONF);
		
		conf = new CronConferenceBean();
		conf.setOwner(ThreadLocaleUtil.getUser());
		conf.setConfType(Constant.CONF_TYPE_PERCONF);
		conf.setCustomer(loginUser.getCustomer());
		initUserList();
		initConfMedias();
		initConferenceType();
		conf.setConfSubject(serviceFacade.getConferenceService().getDefaultConfSubject(user.getUsername()));
		conf.setLength(30);
		initTime();
		//-- add by wuzehua
		this.setConferenceNew(new ConferenceNewBean());
		//-- end
		
		return SUCCESS;
	}
	
	private void initTime(){
		datetimePair = new StartEndDatePair();
		if(conf.getCronHours()==null){
			datetimePair.setStartTime(sdf.format(new Date()));
		}else{
			datetimePair.setStartTime(sdf.format(conf.getCronHours()));
		}
	}
	
	private void parseTime()throws Exception{
		conf.setCronHours(sdf.parse(datetimePair.getStartTime()));
	}
	
	
	
	/**
	 * 跳转到会议信息编辑页面
	 * @return
	 * @throws Exception
	 */
	public String toEditConf()throws Exception {
		log.info("com.lorent.web.action.CronConferenceAction toEditConf() --  跳转到会议信息编辑页面");
		Integer[]ids = getSelectedIds();
		conf = serviceFacade.getCronConferenceService().get(ids[0]);
		initUserList();
		initConfMedias();
		initConferenceType();
		initTime();
		//-- add by chard
		ConferenceNewBean conferenceNewPo = new ConferenceNewBean();
		conferenceNewPo.setConfNo(conf.getConfno());//设置会议号码
		//根据会议号码获取会议
		List<ConferenceNewBean> confnewList=serviceFacade.getConferenceNewService().getByExample(conferenceNewPo);
		if(null!=confnewList&&confnewList.size()>0){
			conferenceNewPo=confnewList.get(0);
		}
		this.setAllUsers(serviceFacade.getUserService().getAll());
		setConferenceNew(conferenceNewPo);
		Integer[] typeIds = {this.getConferenceNew().getConferenceTypeId()};
		List<ConferenceRoleBean> roles = serviceFacade.getConferenceRoleService().getRolesByConferenceTypeId(typeIds);
		Map<Integer,String> conferenceRoles = new HashMap<Integer,String>();
		Integer[] roleIds = new Integer[roles.size()];
		for(int i=0;i<roleIds.length;i++){
			roleIds[i] = roles.get(i).getId();
			conferenceRoles.put(roleIds[i], roles.get(i).getRoleName());
		}
		setConferenceRoles(conferenceRoles);
		//-- end
		return SUCCESS;
	}
	/**
	 * 删除会议
	 * @return
	 * @throws Exception
	 */
	public String deleteConf()throws Exception {
		log.info("com.lorent.web.action.CronConferenceAction deleteConf() --  删除周期会议");
		Integer[]ids = getSelectedIds();
		log.info("删除周期会议：" +  ids);
		serviceFacade.getCronConferenceService().removeConf(ids);
		if(getSearchMode())
			return searchConfs();
		return switchListPage();
	}
	/**
	 * 搜索会议
	 * @return
	 */
	public String searchConfs()throws Exception {
		log.info("com.lorent.web.action.CronConferenceAction searchConfs() --  搜索周期会议");
		if(!"".equals(getIsSearch())){
			searchModel = conf;
			setIsSearch("");
			searchMode = true;
		}
		Map<String, Criterion> map = new HashMap<String, Criterion>();
		if(belongType!=null){//我创建的会议，我参与的会议，部门会议
			if(ConfBelongType.MY_BUILD.equals(belongType)){
				setMyBuildConfCondition(map);
			}else if(ConfBelongType.MY_JOIN.equals(belongType)){
				setMyJoinConfCondition(map);
			}else if(ConfBelongType.DEPT.equals(belongType)){
				setDeptConfCondition(map);
			}
		}
		UserBean user = ThreadLocaleUtil.getUser();
		if(!user.getUserType().equals(Constant.USER_TYPE_ADMIN)){
			map.put("customer.id", Restrictions.eq("customer.id", user.getCustomer().getId()));
		}
		map.put("status", Restrictions.eq("status", Constant.RECORD_STATUS_VALID));
		ClassUtil.parseExampleToCriteriaMap(map, searchModel, null);
		DetachedCriteria dc = ClassUtil.createCriteriaByMap(map, CronConferenceBean.class);
		setSubPageMap(dc, serviceFacade.getCronConferenceService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/cronConferenceAction_searchConfs_cronconflist.action");
		return SUCCESS;
	}
	/**
	 * 将用户编辑的信息保存
	 * @return
	 * @throws Exception
	 */
	public String doEditConf() throws Exception {
		log.info("com.lorent.web.action.CronConferenceAction doEditConf() --  将用户编辑的信息保存");
		parseTime();
		setConfUsers();
//		if(conf.getUsers().size()==0)
//			throw new ArgsException("args.confuserrequired");
		UserBean currUser = ThreadLocaleUtil.getUser();
		serviceFacade.getConferenceService().createConfNo(conf, currUser.getCustomer().getCustomerCode(), Constant.CONF_TYPE_PERCONF);
		if(conf.getRealPassword()!=null&&!"".equals(conf.getRealPassword().trim())){
			PasswordUtil pu = new PasswordUtil();
			conf.setConfPass(pu.getEncString(conf.getRealPassword()));
		}
		if(!(null!=conf.getIsmix()&&conf.getIsmix().toString().length()>0)){
			log.error("", new ArgsException("args.conftype_ismix"));
			throw new ArgsException("args.conftype_ismix"); 
		}
		// -- start add by chard
		ConferenceNewBean conferenceNewPo = new ConferenceNewBean();
		BeanUtils.copyProperties(conferenceNewPo,conferenceNew);
		HttpServletRequest request = ServletActionContext.getRequest();
		String[]urIds = request.getParameterValues("userRole");
		// -- end
		if(conf.getId()==null){
			log.info("添加周期会议");
//			conf.setMcuMixerKey(loginUser.getCustomer().getMcuServer().getMixers().iterator().next().getMixerKey());
//			conf.setMcuMediaQuality(mediaQuality.keySet().iterator().next());
			serviceFacade.getCronConferenceService().createCronConference(conf);
			
			//-- start add by chard
			conferenceNewPo.setConfNo(conf.getConfno());
			conferenceNewPo.setConferenceName(conf.getConfSubject());
			conferenceNewPo.setCreator(currUser.getId());
			serviceFacade.getConferenceNewService().createConferenceNew(conferenceNewPo, urIds);
			
			//--end
		}else {
			log.info("修改周期会议" + conf.getId());
			serviceFacade.getCronConferenceService().renewCronConference(conf);
			
			// -- start add by chard 
			serviceFacade.getConferenceNewService().renewConferenceNew(conferenceNewPo, urIds);
			// -- end
		}
		log.info("周期会议名称" + conf.getConfSubject());
		log.info("周期会议触发类型" + conf.getCronType());
		log.info("周期会议触发日期" + conf.getCronHours());
		log.info("周期会议触发时间" + conf.getCronTime());
		log.info("周期会议密码" + conf.getConfPass());
		initCallbackUrl();
		log.info("成功");
		return SUCCESS;
	}
	/**
	 * 校验会议密码
	 * @return
	 */
	public String checkPass() {
		log.info("com.lorent.web.action.CronConferenceAction checkPass() --  校验会议密码");
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer id = StringUtil.toInteger(request.getParameter("id"), null);
		String pass = request.getParameter("pass");
		map = new HashMap<String, String>();
		try {
			if(pass==null||"".equals(pass)){
				log.error("",new ArgsException("args.passrequired"));
				throw new ArgsException("args.passrequired");
			}
			conf = serviceFacade.getCronConferenceService().get(id);
			PasswordUtil pu = new PasswordUtil();
			if (!pu.getEncString(pass).equals(conf.getConfPass())) {
				log.error("",new CustomAuthenticationException("authen.wrong.pass"));
				throw new CustomAuthenticationException("authen.wrong.pass");
			}
			map.put("success", "op success");
		} catch (Exception e) {
			log.error("",e);
			e.printStackTrace();
			map.put("error", e.getMessage());
		}
		jo = JSONObject.fromObject(map);
		result = jo.toString();
		return SUCCESS;
	}
	/**
	 * 显示会议的详细信息
	 * @return
	 * @throws Exception
	 */
	public String showDesc()throws Exception {
		log.info("com.lorent.web.action.CronConferenceAction showDesc() --  显示会议的详细信息");
		Integer[]ids = getSelectedIds();
		conf = serviceFacade.getCronConferenceService().get(ids[0]);
		return SUCCESS;
	}
	/* ================================================== private methods ============================================ */
	/**
	 * 根据用户操作的模式跳转到不同的列表页面
	 */
	private String switchListPage()throws Exception {
		switch(mode){
			case ADMIN_MODE:
				return toCronConfList();
//			case USER_MODE:
//				return toUsersCronConfList();
			default:
				return toCronConfList();
		}
	}
	/**
	 * 初始化会议模式列表
	 *
	 */
	private void initConfModeList() {
		log.info("com.lorent.web.action.CronConferenceAction initConfModeList() --  初始化会议模式列表");
		if(confModeList==null){
			confModeList = new TreeMap<Integer, String>();
			confModeList.put(Constant.CONF_MODE_HOSTMODE, getText("page.conf.mode.hostmode"));
			confModeList.put(Constant.CONF_MODE_FREEMODE, getText("page.conf.mode.freemode"));
		}
	}
	/**
	 * 初始化周期会议类型列表
	 *
	 */
	private void initCronTypeList() {
		log.info("com.lorent.web.action.CronConferenceAction initCronTypeList() --  初始化周期会议类型列表");
		if(cronTypeList==null){
			cronTypeList = new TreeMap<Integer, String>();
			cronTypeList.put(Constant.CRON_TYPE_MONTH, getText("page.cron.type.month"));
			cronTypeList.put(Constant.CRON_TYPE_WEEK, getText("page.cron.type.week"));
		}
	}
	/**
	 * 为列表准备合适的数据
	 * @param criteria
	 * @return
	 */
	private String toList(){
		log.info("com.lorent.web.action.CronConferenceAction toList() --  为列表准备合适的数据");
		Map<String, Criterion> map = new HashMap<String, Criterion>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String belong = request.getParameter("belong");
		if(belong==null){
			belong = belongType;
		}
		if(belong!=null){//我创建的会议，我参与的会议，部门会议
			if(ConfBelongType.MY_BUILD.equals(belong)){
				setMyBuildConfCondition(map);
				belongType = ConfBelongType.MY_BUILD;
			}else if(ConfBelongType.MY_JOIN.equals(belong)){
				setMyJoinConfCondition(map);
				belongType = ConfBelongType.MY_JOIN;
			}else if(ConfBelongType.DEPT.equals(belong)){
				setDeptConfCondition(map);
				belongType = ConfBelongType.DEPT;
			}
		}else{
			belongType = null;
		}
		UserBean user = ThreadLocaleUtil.getUser();
		if(!user.getUserType().equals(Constant.USER_TYPE_ADMIN)){
			map.put("customer.id", Restrictions.eq("customer.id", user.getCustomer().getId()));
		}
		map.put("status", Restrictions.eq("status", Constant.RECORD_STATUS_VALID));
//		ClassUtil.parseExampleToCriteriaMap(map, conf, null);
		DetachedCriteria dc = ClassUtil.createCriteriaByMap(map, CronConferenceBean.class);
		setSubPageMap(dc, serviceFacade.getCronConferenceService(), orderString);
		return SUCCESS;
	}
	
	private void setMyBuildConfCondition(Map<String, Criterion> map){
		UserBean user = ThreadLocaleUtil.getUser();
		map.put("owner.id", Restrictions.eq("owner.id", user.getId()));
 		buttonMap.put("edit", true);
		buttonMap.put("add", true);
		buttonMap.put("delete", true);
		buttonMap.put("stopConf", false);
	}
	
	private void setMyJoinConfCondition(Map<String, Criterion> map){
		UserBean user = ThreadLocaleUtil.getUser();
		map.put("users.id", Restrictions.eq("users.id", user.getId()));
 		buttonMap.put("edit", false);
		buttonMap.put("add", false);
		buttonMap.put("delete", false);
		buttonMap.put("stopConf", false);
	}
	
	private void setDeptConfCondition(Map<String, Criterion> map){
		UserBean user = ThreadLocaleUtil.getUser();
		if(user.getDepartment()!=null){
			map.put("owner.department.id", Restrictions.eq("ownerdepartment.id", user.getDepartment().getId()));
		}
 		buttonMap.put("edit", true);
		buttonMap.put("add", true);
		buttonMap.put("delete", true);
		buttonMap.put("stopConf", false);
	}
	
	/**
	 * 初始化用户列表
	 * @throws Exception
	 */
	private void initUserList()throws Exception {
		log.info("com.lorent.web.action.CronConferenceAction initUserList() --  初始化用户列表");
		DetachedCriteria criteria = DetachedCriteria.forClass(UserBean.class);
		criteria.createAlias("customer", "customer")
			.add(Restrictions.eq("customer.id", loginUser.getCustomer().getId()))
			.add(Restrictions.eq("userAccountNonExpired", true))
			.add(Restrictions.eq("userAccountNonLocked", true))
			.add(Restrictions.eq("status", Constant.RECORD_STATUS_VALID));
		if(ConfBelongType.DEPT.equals(belongType)){//部门管理员只能邀请当前部门的人员
			criteria.createAlias("department", "department").add(Restrictions.eq("department.id", loginUser.getDepartment().getId()));
		}else if(ConfBelongType.MY_BUILD.equals(belongType)){//内部用户只能邀请内部用户
			criteria.createAlias("roles", "roles").add(Restrictions.eq("roles.roleCode", Constant.ROLE_CUSTOMER_USER));
		}
		userList = serviceFacade.getUserService().getByCriteria(criteria);
		if(userList==null)
			userList = new ArrayList<UserBean>();
	}
	/**
	 * 初始化允许提前列表
	 *
	 */
	private void initAllowBeforeList(){
		log.info("com.lorent.web.action.CronConferenceAction initAllowBeforeList() --  初始化允许提前列表");
		if(allowBeforeList==null){
			allowBeforeList = new TreeMap<Long, String>();
			allowBeforeList.put(7200l, getText("page.conf.twohour"));
			allowBeforeList.put(3600l, getText("page.conf.ahour"));
			allowBeforeList.put(1800l, getText("page.conf.hafthours"));
		}
	}
	/**
	 * 初始化日期列表
	 *
	 */
	private void initDateList() {
		log.info("com.lorent.web.action.CronConferenceAction initDateList() --  初始化日期列表");
		if(dateList==null){
			dateList = new LinkedHashMap<String, String>();
			String date = getText("page.cronconf.date"); 
			for(int i=0;i<31;i++)
				dateList.put(i+1+"", i+1+date);
			dateList.put(Constant.WEEK_EVERY_DAY, getText("page.cron.weekday.everyday"));
		}
	}
	/**
	 * 初始化星期列表
	 *
	 */
	private void initWeekList(){
		log.info("com.lorent.web.action.CronConferenceAction initWeekList() --  初始化星期列表");
		if(weekList==null){
			weekList = new LinkedHashMap<String, String>();
			weekList.put(Constant.WEEK_DAY_SUN, getText("page.cron.weekday.sun"));
			weekList.put(Constant.WEEK_DAY_MON, getText("page.cron.weekday.mon"));
			weekList.put(Constant.WEEK_DAY_TUS, getText("page.cron.weekday.tus"));
			weekList.put(Constant.WEEK_DAY_WEB, getText("page.cron.weekday.web"));
			weekList.put(Constant.WEEK_DAY_THURS, getText("page.cron.weekday.thurs"));
			weekList.put(Constant.WEEK_DAY_FRI, getText("page.cron.weekday.fri"));
			weekList.put(Constant.WEEK_DAY_SAT, getText("page.cron.weekday.sat"));
			weekList.put(Constant.WEEK_WORK_DAY, getText("page.cron.weekday.workday"));
			weekList.put(Constant.WEEK_EVERY_DAY, getText("page.cron.weekday.everyday"));
		}
	}
	/**
	 * 初始化编辑后跳转的url
	 *
	 */
	private void initCallbackUrl() {
		log.info("com.lorent.web.action.CronConferenceAction initCallbackUrl() --   初始化编辑后跳转的url");
		String condition = "";
		if(belongType!=null){
			condition = "?belong=" + belongType;
		}
		switch (mode) {
		case ADMIN_MODE:
			callBackUrl = "app/cronConferenceAction_toCronConfList_cronconflist.action" + condition;
			break;
		default:
			break;
		}
	}
	/**
	 * 设置参与会议的用户
	 * @throws CustomSqlException
	 */
	private void setConfUsers()throws Exception {
		log.info("com.lorent.web.action.CronConferenceAction setConfUsers() --   设置参与会议的用户");
		HttpServletRequest request = ServletActionContext.getRequest();
		String[]ids = request.getParameterValues("userId");
		if(ids==null||ids.length==0)return;
		conf.setUsers(new HashSet<UserBean>());
		for (int i = 0; i < ids.length; i++) {
			conf.getUsers().add(serviceFacade.getUserService().get(Integer.parseInt(ids[i])));
		}
		if(conf.getUsers().size() > ThreadLocaleUtil.getUser().getCustomer().getConfPeopleLimit()){
//			throw new ArgsException("{args.useroverconflimit}:" + ThreadLocaleUtil.getUser().getCustomer().getConfPeopleLimit());
			log.error("",new ArgsException(PropertiesUtil.getProperty("exceptionResource_zh_CN", "args.useroverconflimit")+":" + ThreadLocaleUtil.getUser().getCustomer().getConfPeopleLimit()));
			throw new ArgsException(PropertiesUtil.getProperty("exceptionResource_zh_CN", "args.useroverconflimit")+":" + ThreadLocaleUtil.getUser().getCustomer().getConfPeopleLimit());
		}
	}
	
	private void initConfMedias() throws Exception{
		if(confMedias==null){
			UserBean user = ThreadLocaleUtil.getUser();
			confMedias = new TreeMap<Integer, String>();
			DetachedCriteria dc = DetachedCriteria.forClass(MediaBean.class);
			dc.add(Restrictions.eq("status", Constant.RECORD_STATUS_VALID));
//			.createAlias("customer", "customer")
//			.add(Restrictions.eq("customer.id", user.getCustomer().getId()));
			
			List<MediaBean> medias = serviceFacade.getMediaService().getByCriteria(dc);
			for(MediaBean media : medias){
				confMedias.put(media.getId(), media.getMediaName());				
			}
		}
	}
	
	/* ====================================== getters and setters ================================== */
	
	public CronConferenceBean getModel() {
		return conf;
	}

	public CronConferenceBean getConf() {
		return conf;
	}

	public void setConf(CronConferenceBean conf) {
		this.conf = conf;
	}
	public List<ConferenceNoBean> getNoList() {
		return noList;
	}
	public void setNoList(List<ConferenceNoBean> noList) {
		this.noList = noList;
	}
	public Map<Integer, String> getConfModeList() {
		return confModeList;
	}
	public void setConfModeList(Map<Integer, String> confModeList) {
		this.confModeList = confModeList;
	}
	public Map<Integer, String> getCronTypeList() {
		return cronTypeList;
	}
	public void setCronTypeList(Map<Integer, String> cronTypeList) {
		this.cronTypeList = cronTypeList;
	}
	public List<UserBean> getUserList() {
		return userList;
	}
	public void setUserList(List<UserBean> userList) {
		this.userList = userList;
	}
	public Map<Integer, String> getMcuMediaLayout() {
		return mcuMediaLayout;
	}
	public void setMcuMediaLayout(Map<Integer, String> mcuMediaLayout) {
		this.mcuMediaLayout = mcuMediaLayout;
	}
	public Map<String, String> getMediaQuality() {
		return mediaQuality;
	}
	public void setMediaQuality(Map<String, String> mediaQuality) {
		this.mediaQuality = mediaQuality;
	}
	public Map<Long, String> getAllowBeforeList() {
		return allowBeforeList;
	}
	public void setAllowBeforeList(Map<Long, String> allowBeforeList) {
		this.allowBeforeList = allowBeforeList;
	}
	public Map<String, String> getWeekList() {
		return weekList;
	}
	public void setWeekList(Map<String, String> weekList) {
		this.weekList = weekList;
	}
	public Map<String, String> getDateList() {
		return dateList;
	}
	public void setDateList(Map<String, String> dateList) {
		this.dateList = dateList;
	}
	public CronConferenceBean getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(CronConferenceBean searchModel) {
		this.searchModel = searchModel;
	}
	public Map<Integer, String> getConfMedias() {
		return confMedias;
	}
	public void setConfMedias(Map<Integer, String> confMedias) {
		this.confMedias = confMedias;
	}
	public StartEndDatePair getDatetimePair() {
		return datetimePair;
	}
	public void setDatetimePair(StartEndDatePair datetimePair) {
		this.datetimePair = datetimePair;
	}

	
	
	private List<ConferenceTypeBean> conferenceTypes;
	private Map<Integer,String> conferenceTypeMap;
	private Map<Integer,String> conferenceRoles;
	private List<UserBean> allUsers;
	private ConferenceNewBean conferenceNew;

	public List<ConferenceTypeBean> getConferenceTypes() {
		return conferenceTypes;
	}
	public void setConferenceTypes(List<ConferenceTypeBean> conferenceTypes) {
		this.conferenceTypes = conferenceTypes;
	}
	public Map<Integer, String> getConferenceTypeMap() {
		return conferenceTypeMap;
	}
	public void setConferenceTypeMap(Map<Integer, String> conferenceTypeMap) {
		this.conferenceTypeMap = conferenceTypeMap;
	}
	public Map<Integer, String> getConferenceRoles() {
		return conferenceRoles;
	}
	public void setConferenceRoles(Map<Integer, String> conferenceRoles) {
		this.conferenceRoles = conferenceRoles;
	}
	public List<UserBean> getAllUsers() {
		return allUsers;
	}
	public void setAllUsers(List<UserBean> allUsers) {
		this.allUsers = allUsers;
	}
	public ConferenceNewBean getConferenceNew() {
		return conferenceNew;
	}
	public void setConferenceNew(ConferenceNewBean conferenceNew) {
		this.conferenceNew = conferenceNew;
	}
	
	public void initConferenceType() throws Exception{
		conferenceTypes = serviceFacade.getConferenceTypeService().getByExample(new ConferenceTypeBean());
		Map<Integer,String> conferenceTypeMap = new HashMap<Integer,String>();
		if(conferenceTypes != null){
			for(ConferenceTypeBean bean:conferenceTypes){
				conferenceTypeMap.put(bean.getId(), bean.getTypeName());
			}
		}
		this.setConferenceTypeMap(conferenceTypeMap);
	}
}
