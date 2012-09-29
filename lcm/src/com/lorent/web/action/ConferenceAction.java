package com.lorent.web.action;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import com.lorent.model.ConferenceBean;
import com.lorent.model.ConferenceNewBean;
import com.lorent.model.ConferenceRoleBean;
import com.lorent.model.ConferenceTypeBean;
import com.lorent.model.MediaBean;
import com.lorent.model.UserBean;
import com.lorent.util.ClassUtil;
import com.lorent.util.Constant;
import com.lorent.util.PageUtil;
import com.lorent.util.PasswordUtil;
import com.lorent.util.PropertiesUtil;
import com.lorent.util.StringUtil;
import com.lorent.util.ThreadLocaleUtil;

public class ConferenceAction extends BaseAction<ConferenceBean,Integer> {
	
	private static final Logger log = Logger.getLogger(ConferenceAction.class); 
	
	private static final long serialVersionUID = 1L;
	private ConferenceBean conf;
	private List<UserBean>userList;
	private String pageTitle;
	private Map<Integer, String>confStatusList;
	private Map<Integer, String>confTypeList;
	private Map<Integer, String>confModeList;
//	private Map<String,Date>dateStrMap;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private enum ModeEnum{ONGOING,APP_CONF,IMP_CONF,PER_CONF};
	private ModeEnum mode;
	private Map<Long, String>allowBeforeList;
	private ConferenceBean searchModel;
	private Map<Integer, String>mcuMediaLayout = Constant.MCU_MEDIA_LAYOUT;
	private Map<String,String>mediaQuality = Constant.MCU_QUALITYS;
	private String belongType;
	private Map<Integer,String> confMedias;
	private StartEndDatePair datetimePair;

	/* ================================================ public methods =================================================== */
	/**
	 * constractor
	 *
	 */
	public ConferenceAction(){
		initAllowBeforeList();
		initConfModeList();
		initConfStatusList();
		initConfTypeList();
		try {
			initConferenceType();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	/**
	 * 跳转到正在进行的会议列表页面
	 * @return
	 * @throws Exception
	 */
	public String toOngoningConfs()throws Exception{
		log.debug(" com.lorent.web.action.ConferenceAction toOngoningConfs() -- 跳转到正在进行的会议列表页面");
		mode = ModeEnum.ONGOING;
		conf = new ConferenceBean();
		conf.setConfStatus(Constant.CONF_STATUS_ONGOING);
		setPageTitle(getText("page.display.text.onconf"));
		buttonMap.put("edit", true);
		buttonMap.put("add", false);
		buttonMap.put("delete", false);
		buttonMap.put("stopConf", true);
		PropertiesUtil.setConstant("key", "value");
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/conferenceAction_toOngoningConfs_conflist.action");
		return toList();
	}
	/**
	 * 跳转到即时会议列表页面
	 * @return
	 */
	public String toImdConfs() {
		log.debug(" com.lorent.web.action.ConferenceAction toImdConfs() -- 跳转到即时会议列表页面");
		mode = ModeEnum.IMP_CONF;
		conf = new ConferenceBean();
		conf.setConfType(Constant.CONF_TYPE_IMDCONF);
		setPageTitle(getText("page.display.text.impconf"));
		buttonMap.put("edit", true);
		buttonMap.put("add", true);
		buttonMap.put("delete", true);
		buttonMap.put("stopConf", false);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/conferenceAction_toImdConfs_conflist.action");
		try {
			initConferenceType();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toList();
	}
	/**
	 * 跳转到预约会议列表页面
	 * @return
	 */
 	public String toAppConfs() {
		log.debug(" com.lorent.web.action.ConferenceAction toAppConfs() -- 跳转到预约会议列表页面");
 		mode = ModeEnum.APP_CONF;
 		conf = new ConferenceBean();
 		conf.setConfType(Constant.CONF_TYPE_APPCONF);
 		setPageTitle(getText("page.display.text.appconf"));
 		buttonMap.put("edit", true);
		buttonMap.put("add", true);
		buttonMap.put("delete", true);
		buttonMap.put("stopConf", false);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/conferenceAction_toAppConfs_conflist.action");
		return toList();
	}
 	
 	
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	public String toEditConf()throws Exception {
		log.debug(" com.lorent.web.action.ConferenceAction toEditConf() -- 跳转到编辑页面");
		Integer[]ids = getSelectedIds();
		conf = serviceFacade.getConferenceService().get(ids[0]);
		if(conf.getConfStatus().equals(Constant.CONF_STATUS_FINISHED)
				||conf.getConfStatus().equals(Constant.CONF_STATUS_STOPED)){
			initCallBackUrl();
			log.error("",new ArgsException("args.confisfinished"));
			throw new ArgsException("args.confisfinished");
		}
		
		//-- add by chard
		ConferenceNewBean conferenceNewPo = new ConferenceNewBean();
		conferenceNewPo.setConfNo(conf.getConfno());//设置会议号码
		//根据会议号码获取会议
		List<ConferenceNewBean> confnewList=serviceFacade.getConferenceNewService().getByExample(conferenceNewPo);
		if(null!=confnewList&&confnewList.size()>0){
			conferenceNewPo=confnewList.get(0);
		}
		setConferenceNew(conferenceNewPo);
		//编辑时设置权限页签的数据
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
		
		initUsers();
		this.setAllUsers(serviceFacade.getUserService().getAll());
		initDateStrMap();
		initConfMedias();
		return SUCCESS;
	}
	
	public String sortConf() throws Exception{
		setSortField();
		if(searchMode){
			return searchConfs();
		}
		switch(mode){
			case ONGOING:
				return toOngoningConfs();
			case IMP_CONF:
				return toImdConfs();
			case APP_CONF:
				return toAppConfs();
			default:
				return SUCCESS;
		}
	}
	
	
	public String mediaOperate()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		boolean play = Boolean.parseBoolean(request.getParameter("play"));
		Integer confId = Integer.parseInt(request.getParameter("confid"));
		serviceFacade.getConferenceService().mediaPlay(confId, play);
		return SUCCESS;
	}
	
	/**
	 * 验证用户输入参数
	 *
	 */
	public void validateDoEditConf() {
		log.debug(" com.lorent.web.action.ConferenceAction validateDoEditConf() -- 验证用户输入参数");
		boolean isNew = conf.getId()==null;
		Date now = new Date();
		try {
			//serviceFacade.getConferenceService().toggleUserStatus(conf, false);
			setConfUsers();
			parseDateMap();
			if(conf.getConfType().equals(Constant.CONF_TYPE_IMDCONF) && isNew){
				conf.setStartTime(now);
			}
			if(conf.getStartTime()==null){
				log.error("", new ArgsException("args.starttimerequired") );
				throw new ArgsException("args.starttimerequired"); 
			}
			if(conf.getEndTime()==null){
				log.error("", new ArgsException("args.endtimerequired"));
				throw new ArgsException("args.endtimerequired"); 
			}
			if(isNew&&conf.getStartTime().before(now)){
				log.error("", new ArgsException("args.startbeforenow"));
				throw new ArgsException("args.startbeforenow");
			}
			if(conf.getEndTime().before(now)){
				log.error("", new ArgsException("args.endbeforenow"));
				throw new ArgsException("args.endbeforenow");
			}
			if(!conf.getEndTime().after(conf.getStartTime())){
				log.error("", new ArgsException("args.endbeforestart"));
				throw new ArgsException("args.endbeforestart"); 
			}
			if(!(null!=conf.getIsmix()&&conf.getIsmix().toString().length()>0)){
				log.error("", new ArgsException("args.conftype_ismix"));
				throw new ArgsException("args.conftype_ismix"); 
			}
			//校验号码在会议时间段是否空闲
//			if(conf.getId()!=null)
//				serviceFacade.getStaticService().removeConfNoStatus(conf);
//			ConferenceNoBean no = serviceFacade.getStaticService()
//				.get(conf.getConferenceNo().getId(), ConferenceNoBean.class);
//			conf.setConferenceNo(no);
//			if(conf.getId()!=null)
//				serviceFacade.getConferenceService().removeNoStatus(conf);
//			ConfNoStatusBean noStatus = serviceFacade.getConferenceService().containsNoStatus(conf); 
//			if(noStatus!=null)
//				throw new ArgsException("args.confnoinuse");
//			noStatus = new ConfNoStatusBean();
//			noStatus.setStartTime(conf.getStartTime());
//			noStatus.setEndTime(conf.getEndTime());
//			no.getNoStatus().add(noStatus);
//			if(conf.getUsers().size()>conf.getConfMemberCount())
//				throw new ArgsException("args.useroverconflimit");
//			userStatusValidate(conf);
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("error", e.getMessage());
			log.error("", e);
		}
	}
	
	private List<ConferenceTypeBean> conferenceTypes;
	private Map<Integer,String> conferenceTypeMap;
	private Map<Integer,String> conferenceRoles;
	private List<UserBean> allUsers;
	private ConferenceNewBean conferenceNew;
	
	
	public ConferenceNewBean getConferenceNew() {
		return conferenceNew;
	}
	public void setConferenceNew(ConferenceNewBean conferenceNew) {
		this.conferenceNew = conferenceNew;
	}
	public List<UserBean> getAllUsers() {
		return allUsers;
	}
	public void setAllUsers(List<UserBean> allUsers) {
		this.allUsers = allUsers;
	}
	public Map<Integer, String> getConferenceRoles() {
		return conferenceRoles;
	}
	public void setConferenceRoles(Map<Integer, String> conferenceRoles) {
		this.conferenceRoles = conferenceRoles;
	}
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
	public String toSearchTypeRoleUserInfo() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String typeStr = request.getParameter("conferenceType");
		Integer[] typeIds = {Integer.parseInt(typeStr)};
		List<ConferenceRoleBean> roles = serviceFacade.getConferenceRoleService().getRolesByConferenceTypeId(typeIds);
		Map<Integer,String> conferenceRoles = new HashMap<Integer,String>();
		for(int i=0;i<roles.size();i++){
			conferenceRoles.put(roles.get(i).getId(), roles.get(i).getRoleName());
		}
		this.setConferenceRoles(conferenceRoles);
		return SUCCESS;
	}
	

	
	/**
	 * 将用户输入的会议信息持久化
	 * @return
	 */
	public String doEditConf()throws Exception {
		try{
			log.info("修改");
			UserBean user = ThreadLocaleUtil.getUser();
			if(user.getUserType().equals(Constant.USER_TYPE_INNERUSER)){
				conf.setCustomer(user.getCustomer());
			}
			if(conf.getRealPassword()!=null&&!"".equals(conf.getRealPassword())){
				PasswordUtil pu = new PasswordUtil();
				conf.setConfPass(pu.getEncString(conf.getRealPassword()));
			}
			if(conf.getConfStatus()==null)
				conf.setConfStatus(Constant.CONF_STATUS_NOT_START);
			// -- start add by chard
			ConferenceNewBean conferenceNewPo = new ConferenceNewBean();
			BeanUtils.copyProperties(conferenceNewPo,conferenceNew);
			HttpServletRequest request = ServletActionContext.getRequest();
			String[]urIds = request.getParameterValues("userRole");
			// -- end
			if(conf.getId()==null){
				log.info("增加会议");
				conf.setOwner(user);
				try{
					serviceFacade.getConferenceService().createConfNo(conf, user.getCustomer().getCustomerCode(), conf.getConfType());
					serviceFacade.getConferenceService().createConf(conf);
					
					//-- start add by chard
					conferenceNewPo.setConfNo(conf.getConfno());
					conferenceNewPo.setConferenceName(conf.getConfSubject());
					conferenceNewPo.setCreator(user.getId());
					serviceFacade.getConferenceNewService().createConferenceNew(conferenceNewPo, urIds);
					
					//--end
				}catch(Exception e){
					conf.setId(null);
					log.error("",e);
					throw e;
				}
			}else {
				log.info("修改会议" + conf.getId());
				serviceFacade.getConferenceService().renewConf(conf);
				// -- start add by chard 
				serviceFacade.getConferenceNewService().renewConferenceNew(conferenceNewPo, urIds);
				// -- end
				log.info("修改会议成功！");
			}
			log.info("会议名称" + conf.getConfSubject());
			log.info("会议结束时间" + conf.getEndTime());
			log.info("会议开始时间" + conf.getStartTime());
			log.info("会议密码" + conf.getConfPass());
			initCallBackUrl();
		}catch(Exception e){
			e.printStackTrace();
			log.error("",e);
			throw e;
		}
		return SUCCESS;
	}
	/**
	 * 响应创建会议请求创建会议
	 * @return
	 */
	public String addConf()throws Exception {
		log.debug(" com.lorent.web.action.ConferenceAction addConf() -- 响应创建会议请求创建会议");
		initCallBackUrl();
		UserBean user = ThreadLocaleUtil.getUser();
		if(mode == ModeEnum.APP_CONF){
			serviceFacade.getConferenceService().canCreateConf(user.getCustomer(), Constant.CONF_TYPE_APPCONF);
		}else if(mode == ModeEnum.IMP_CONF){	
			serviceFacade.getConferenceService().canCreateConf(user.getCustomer(), Constant.CONF_TYPE_IMDCONF);
		}
		conf = new ConferenceBean();
		if(user.getCustomer()!=null){
			conf.setCustomer(user.getCustomer());
		}
		if(conf.getCustomer()==null||conf.getCustomer().getMcuServer()==null){
			log.error("", new ArgsException("validate.customerempty"));
			throw new ArgsException("validate.customerempty");
		}
		//-- add by wuzehua
		this.setConferenceNew(new ConferenceNewBean());
		//-- end
		
		initUsers();
		this.setAllUsers(serviceFacade.getUserService().getAll());
		initDateStrMap();
		setConfType();
		initConfMedias();
		conf.setConfSubject(serviceFacade.getConferenceService().getDefaultConfSubject(user.getUsername()));
		return SUCCESS;
	}
	/**
	 * 删除会议
	 * @return
	 */
	public String deleteConf()throws Exception {
		log.debug(" com.lorent.web.action.ConferenceAction deleteConf() -- 删除会议");
		initCallBackUrl();
		Integer[] ids = getSelectedIds();
		log.info("删除会议：" + ids);
		if(ids==null)return switchListPage();
		serviceFacade.getConferenceService().removeConference(ids);
		log.info("已成功删除会议：" + ids);
		if(getSearchMode())return searchConfs();
		return switchListPage();
	}
	/**
	 * 终止会议
	 * @return
	 * @throws Exception
	 */
	public String stopConf()throws Exception {
		log.debug(" com.lorent.web.action.ConferenceAction stopConf() -- 终止会议");
		Integer[]ids = getSelectedIds();
		log.info("终止会议：" + ids);
		if(ids==null)return switchListPage();
		serviceFacade.getConferenceService().stopConference(ids);
		return switchListPage();
	}
	/**
	 * 搜索会议信息
	 * @return
	 */
	public String searchConfs() {
		log.debug(" com.lorent.web.action.ConferenceAction searchConfs() -- 搜索会议信息");
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
		DetachedCriteria dc = ClassUtil.createCriteriaByMap(map, ConferenceBean.class);
		setSubPageMap(dc, serviceFacade.getConferenceService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/conferenceAction_searchConfs_conflist.action");
		return SUCCESS;
	}
	/**
	 * 校验会议密码
	 * @return
	 * @throws Exception
	 */
	public String checkPass()throws Exception {
		log.debug(" com.lorent.web.action.ConferenceAction checkPass() -- 校验会议密码");
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
	/**
	 * 显示会议详细信息
	 * @return
	 * @throws Exception
	 */
	public String showDesc()throws Exception {
		log.debug(" com.lorent.web.action.ConferenceAction showDesc() -- 显示会议详细信息");
		Integer[] ids = getSelectedIds();
		conf = serviceFacade.getConferenceService().get(ids[0]);
		return SUCCESS;
	}
	/**
	 * 根据mode参数跳转到不同的会议列表页面
	 * @return
	 * @throws Exception
	 */
	public String switchListPage()throws Exception {
		switch (mode) {
		case ONGOING:
			return toOngoningConfs();
		case IMP_CONF:
			return toImdConfs();
		case APP_CONF:
			return toAppConfs();
		case PER_CONF:
			return "";
		default:
			return SUCCESS;
		}
	}
	/*============================================= private methods =====================================*/
	/**
	 * 设置会议类型
	 *
	 */
	private void setConfType() {
		if(conf==null)conf = new ConferenceBean();
		switch (mode) {
		case IMP_CONF:
			conf.setConfType(Constant.CONF_TYPE_IMDCONF);
			break;
		case APP_CONF:
			conf.setConfType(Constant.CONF_TYPE_APPCONF);
			break;
		case PER_CONF:
			conf.setConfType(Constant.CONF_TYPE_PERCONF);
		default:
			break;
		}
	}
	
	/**
	 * 初始化用户信息列表
	 *
	 */
	private void initUsers() throws Exception{
		log.debug(" com.lorent.web.action.ConferenceAction initUsers() -- 初始化用户信息列表");
		UserBean user = ThreadLocaleUtil.getUser();
		DetachedCriteria criteria = DetachedCriteria.forClass(UserBean.class); 
		criteria.createAlias("customer", "customer")
			.add(Restrictions.eq("customer.id", user.getCustomer().getId()))
			.add(Restrictions.eq("userAccountNonExpired", true))
			.add(Restrictions.eq("userAccountNonLocked", true))
			.add(Restrictions.eq("status", Constant.RECORD_STATUS_VALID));
		if(ConfBelongType.DEPT.equals(belongType)){//部门管理员只能邀请当前部门的人员
			criteria.createAlias("department", "department").add(Restrictions.eq("department.id", user.getDepartment().getId()));
		}else if(ConfBelongType.MY_BUILD.equals(belongType)){//内部用户只能邀请内部用户
			criteria.createAlias("department", "department").add(Restrictions.eq("department.id", user.getDepartment().getId()));
			criteria.createAlias("roles", "roles").add(Restrictions.eq("roles.roleCode", Constant.ROLE_CUSTOMER_USER));
		}
		userList = serviceFacade.getUserService().getByCriteria(criteria);
		if(userList==null)
			userList = new ArrayList<UserBean>();
	}
	/**
	 * 初始化会议状态信息列表
	 *
	 */
	private void initConfStatusList() {
		log.debug(" com.lorent.web.action.ConferenceAction initConfStatusList() -- 初始化会议状态信息列表");
		if(confStatusList==null){
			confStatusList = new TreeMap<Integer, String>();
			confStatusList.put(Constant.CONF_STATUS_NOT_START, getText("page.conf.notstart"));
			confStatusList.put(Constant.CONF_STATUS_ONGOING, getText("page.conf.ongoing"));
			confStatusList.put(Constant.CONF_STATUS_FINISHED, getText("page.conf.finished"));
			confStatusList.put(Constant.CONF_STATUS_STOPED, getText("page.conf.stoped"));
//			confStatusList.put(Constant.CONF_STATUS_DELETED, getText("page.conf.deleted"));
		}
	}
	/**
	 * 初始化或以类型列表
	 *
	 */
	private void initConfTypeList() {
		log.debug(" com.lorent.web.action.ConferenceAction initConfTypeList() -- 初始化或以类型列表");
		if(confTypeList==null){
			confTypeList = new TreeMap<Integer, String>();
			confTypeList.put(Constant.CONF_TYPE_IMDCONF,getText("page.display.text.impconf"));
			confTypeList.put(Constant.CONF_TYPE_APPCONF, getText("page.display.text.appconf"));
			confTypeList.put(Constant.CONF_TYPE_PERCONF, getText("page.display.text.perconf"));
		}
	}
	/**
	 * 初始化会议模式列表
	 *
	 */
	private void initConfModeList() {
		log.debug(" com.lorent.web.action.ConferenceAction initConfModeList() -- 初始化会议模式列表");
		if(confModeList==null){
			confModeList = new TreeMap<Integer, String>();
			confModeList.put(Constant.CONF_MODE_HOSTMODE, getText("page.conf.mode.hostmode"));
			confModeList.put(Constant.CONF_MODE_FREEMODE, getText("page.conf.mode.freemode"));
		}
	}
	/**
	 * 初始化日期Map
	 *
	 */
	private void initDateStrMap()throws Exception{
		log.debug(" com.lorent.web.action.ConferenceAction initDateStrMap() -- 初始化日期Map");
//		dateStrMap = new HashMap<String, Date>();
//		if(conf.getStartTime()!=null){
//			dateStrMap.put("startTimeDate", conf.getStartTime());
//			dateStrMap.put("startTimeHours", conf.getStartTime());
//		}
//		if(conf.getEndTime()!=null){
//			dateStrMap.put("endTimeDate", conf.getEndTime());
//			dateStrMap.put("endTimeHours", conf.getEndTime());
//		}else{
//			Calendar ca = Calendar.getInstance();
//			ca.add(Calendar.MINUTE, 30);
//			conf.setEndTime(ca.getTime());
//			dateStrMap.put("endTimeDate", conf.getEndTime());
//			dateStrMap.put("endTimeHours", conf.getEndTime());
//		}
		try{
			datetimePair = new StartEndDatePair();
			if(conf.getStartTime()!=null){
				datetimePair.setStartDate(conf.getStartTime());
				datetimePair.setStartTime(sdf.format(conf.getStartTime()).split(" ")[1]);
			}else{
				datetimePair.setStartDate(new Date());
				datetimePair.setStartTime(sdf.format(new Date()).split(" ")[1]);
			}
			if(conf.getEndTime()!=null){
				datetimePair.setEndDate(conf.getEndTime());
				datetimePair.setEndTime(sdf.format(conf.getEndTime()).split(" ")[1]);
			}else{
				Calendar ca = Calendar.getInstance();
				ca.add(Calendar.MINUTE, 30);
				conf.setEndTime(ca.getTime());
				datetimePair.setEndDate(ca.getTime());
				datetimePair.setEndTime(sdf.format(ca.getTime()).split(" ")[1]);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("com.lorent.web.action.ConferenceAction initDateStrMap() -- 初始化日期Map 抛异常："+e.getMessage());
			log.error("",e);
			throw e;
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
	
	/**
	 * 将输入的日期转化成Java中的Date，并插入conf
	 *
	 */
	private void parseDateMap()throws ArgsException{
		try {
			String date = null;
			if(conf.getConfStatus()!=Constant.CONF_STATUS_ONGOING){
				date = sdf.format(datetimePair.getStartDate()).split(" ")[0];
				date = date + " " + datetimePair.getStartTime();
				conf.setStartTime(sdf.parse(date));
			}
			date = sdf.format(datetimePair.getEndDate()).split(" ")[0];
			date = date + " " + datetimePair.getEndTime();
			conf.setEndTime(sdf.parse(date));
		} catch (Exception e) {
			log.error("",e);
			throw new ArgsException("args.dateformatwrong");
		}
	}
	
	
	
	/**
	 * 设置参与会议的用户
	 * @throws CustomSqlException
	 */
	private void setConfUsers()throws Exception {
		log.debug(" com.lorent.web.action.ConferenceAction setConfUsers() -- 设置参与会议的用户");
		HttpServletRequest request = ServletActionContext.getRequest();
		String[]ids = request.getParameterValues("userId");
		conf.setUsers(new HashSet<UserBean>());
		if(ids==null||ids.length==0)return;
		for (int i = 0; i < ids.length; i++) {
			conf.getUsers().add(serviceFacade.getUserService().get(Integer.parseInt(ids[i])));
		}
		if(conf.getUsers().size() > ThreadLocaleUtil.getUser().getCustomer().getConfPeopleLimit()){
			log.error("",new ArgsException(PropertiesUtil.getProperty("exceptionResource_zh_CN", "args.useroverconflimit")+":" + ThreadLocaleUtil.getUser().getCustomer().getConfPeopleLimit()));
//			throw new ArgsException("args.useroverconflimit"+":" + ThreadLocaleUtil.getUser().getCustomer().getConfPeopleLimit());
			throw new ArgsException(PropertiesUtil.getProperty("exceptionResource_zh_CN", "args.useroverconflimit")+":" + ThreadLocaleUtil.getUser().getCustomer().getConfPeopleLimit());
		}
	}
	/**
	 * 获取calbackurl
	 * @return
	 */
	public String getCallBackUrl() {
		return callBackUrl;
	}
	
	private void initCallBackUrl(){
		String condition = "";
		if(belongType!=null){
			condition = "?belong=" + belongType;
		}
		switch (mode) {
		case ONGOING:
			callBackUrl = "app/conferenceAction_toOngoningConfs_conflist.action";
			break;
		case IMP_CONF:
			callBackUrl = "app/conferenceAction_toImdConfs_conflist.action" + condition;
			break;
		case APP_CONF:
			callBackUrl = "app/conferenceAction_toAppConfs_conflist.action" + condition;
			break;
		case PER_CONF:
			callBackUrl = "app/conferenceAction_toPerConfs_conflist.action";
			break;
		default:
			callBackUrl = "";
		}
	}
	/**
	 * 为会议列表页面取出合适的数据
	 * @return
	 */
	private String toList() {
		log.info(" com.lorent.web.action.ConferenceAction toList() -- 为会议列表页面取出合适的数据");
		searchMode = false;
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
			
		}else{//admin
			buttonMap.put("edit", false);
		}
		map.put("status", Restrictions.eq("status", Constant.RECORD_STATUS_VALID));
		map.put("confType", 
				Restrictions.not(Restrictions.or(
						Restrictions.eq("confType", Constant.CONF_TYPE_MEETING), 
						Restrictions.eq("confType", Constant.CONF_TYPE_OTHER))));
		ClassUtil.parseExampleToCriteriaMap(map, conf, null);
		DetachedCriteria dc = ClassUtil.createCriteriaByMap(map, ConferenceBean.class);
		setSubPageMap(dc, serviceFacade.getConferenceService(), orderString);
		initConfModeList();
		initConfStatusList();
		initConfTypeList();
		return SUCCESS;
	}
	
	private void setMyBuildConfCondition(Map<String, Criterion> map){
		UserBean user = ThreadLocaleUtil.getUser();
		map.put("owner.id", Restrictions.eq("owner.id", user.getId()));
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
	}
	/**
	 * 初始化允许提前时间列表
	 *
	 */
	private void initAllowBeforeList(){
		allowBeforeList = new TreeMap<Long, String>();
		allowBeforeList.put(7200l, getText("page.conf.twohour"));
		allowBeforeList.put(3600l, getText("page.conf.ahour"));
		allowBeforeList.put(1800l, getText("page.conf.hafthours"));
	}
	
	/*===============================================getters and setters===========================================================*/
	public ConferenceBean getModel() {
		return conf;
	}

	public ConferenceBean getConf() {
		return conf;
	}

	public void setConf(ConferenceBean conf) {
		this.conf = conf;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}

	public String getOrderStr() {
		return orderString;
	}

	public void setOrderStr(String orderStr) {
		this.orderString = orderStr;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public Map<Integer, String> getConfStatusList() {
		return confStatusList;
	}

	public void setConfStatusList(Map<Integer, String> confStatusList) {
		this.confStatusList = confStatusList;
	}

	public Map<Integer, String> getConfTypeList() {
		return confTypeList;
	}

	public void setConfTypeList(Map<Integer, String> confTypeList) {
		this.confTypeList = confTypeList;
	}

	public Map<Integer, String> getConfModeList() {
		return confModeList;
	}

	public void setConfModeList(Map<Integer, String> confModeList) {
		this.confModeList = confModeList;
	}

	public List<UserBean> getUserList() {
		return userList;
	}

	public void setUserList(List<UserBean> userList) {
		this.userList = userList;
	}

//	public Map<String, Date> getDateStrMap() {
//		return dateStrMap;
//	}
//
//	public void setDateStrMap(Map<String, Date> dateStrMap) {
//		this.dateStrMap = dateStrMap;
//	}
	public Map<Long, String> getAllowBeforeList() {
		return allowBeforeList;
	}
	public void setAllowBeforeList(Map<Long, String> allowBeforeList) {
		this.allowBeforeList = allowBeforeList;
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
//	public String getConfPass() {
//		return confPass;
//	}
//	public void setConfPass(String confPass) {
//		this.confPass = confPass;
//	}
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
	
}
