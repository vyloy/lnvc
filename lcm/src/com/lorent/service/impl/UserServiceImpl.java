package com.lorent.service.impl;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.lorent.common.dto.VovoMyInfo;
import com.lorent.common.tree.BroadcastEvent;
import com.lorent.common.tree.MemberBean;
import com.lorent.common.tree.OrgNode;
import com.lorent.common.tree.OrgTree;
import com.lorent.dao.UserDao;
import com.lorent.dto.ImportUserConfig;
import com.lorent.enums.ConfBelongType;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.exception.ModelCheckFailException;
import com.lorent.model.CustomerBean;
import com.lorent.model.DepartmentBean;
import com.lorent.model.McuServerBean;
import com.lorent.model.RatesBean;
import com.lorent.model.RoleBean;
import com.lorent.model.SipConfBean;
import com.lorent.model.UserBean;
import com.lorent.service.DepartmentService;
import com.lorent.service.UserService;
import com.lorent.util.Constant;
import com.lorent.util.MD5Builder;
import com.lorent.util.OpenfireUtil;
import com.lorent.util.PropertiesUtil;
import com.lorent.util.StringUtil;

public class UserServiceImpl extends GenericServiceImpl<UserDao,UserBean,Integer> implements UserService {
	
	private Logger log = Logger.getLogger(UserServiceImpl.class);
	
	private static final long serialVersionUID = 1L;

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		UserBean user = new UserBean();
		user.setUsername(userName);
		user.setStatus(Constant.RECORD_STATUS_VALID);
		try {
			List<UserBean>users = daoFacade.getUserDao().getByExample(user);
			if(users==null||users.size()==0)
				throw new CustomSqlException("sql.usernotexits");
			user = users.get(0);
			return user;
		} catch (CustomSqlException e) {
			throw new UsernameNotFoundException(e.getMessage());
		} 
	} 

	public boolean changePassword(UserBean user,String oldPass, String newPass,
			String repeatPass)throws Exception{
		if(!newPass.equals(repeatPass))
			throw new ModelCheckFailException("modelcheckfail.user.repeanotequals");
		if(!MD5Builder.getMD5(oldPass,user.getUsername()).equals(user.getPassword()))
			throw new ModelCheckFailException("modelcheckfail.user.passwordwrong");
		if("admin".equals(user.getUsername())){
			PropertiesUtil.setConstant("initdata.admin.password", newPass);
		}
		user.setPassword(MD5Builder.getMD5(newPass,user.getUsername()));
		user.setMd5passwd(MD5Builder.getMD5(newPass));
		daoFacade.getUserDao().merge(user);
//		user.setNewPassword(newPass);
//		user.setRepeatPassword(repeatPass);
		if(!user.getUserType().equals(Constant.USER_TYPE_ADMIN)){
			SipConfBean sipConf = confSipByUser(user);
			sipConf.setSecret(newPass);
			daoFacade.getSipConfDao().update(sipConf);
		}
		return true;
	}
	
	public boolean createUser(UserBean user) throws Exception{
		validateBeforeCreate(user);
		user.setPassword(MD5Builder.getMD5(user.getNewPassword().trim(),user.getUsername().trim()));
		user.setMd5passwd(MD5Builder.getMD5(user.getNewPassword().trim()));
		if(user.getLccAccount()==null || user.getLccAccount().equals("")){
			setLccAccount(user);
			while(getByLccAccount(user.getLccAccount())!=null){//如果重复则重新生成号码
				setLccAccount(user);
			}
			log.info("generate lccno = " + user.getLccAccount());
		}
		SipConfBean sipConf = confSipByUser(user);
		int sipId = daoFacade.getSipConfDao().save(sipConf);
		user.setSipId(sipId);
		user.setStatus(Constant.RECORD_STATUS_VALID);
		int id = daoFacade.getUserDao().save(user);
		daoFacade.getUserDao().commitTransaction();
		try{
			OpenfireUtil.getInstance().addGroupUser(PropertiesUtil.getConstant("openfire.systemGroup"), user.getLccAccount());
			//notify client
			OpenfireUtil.getInstance().sendGroupBroadcast(BroadcastEvent.ADD_USER, parseMemberBean(user, null));
		}catch(Exception ex){
			daoFacade.getUserDao().delete(new Integer[]{id});
			daoFacade.getSipConfDao().delete(new Integer[]{sipId});
			log.error(ex);
			throw ex;
		}
		return true;
	}
	
	
	private MemberBean parseMemberBean(UserBean user, MemberBean member){
		MemberBean temp = null;
		if(member == null){
			temp = new MemberBean();
		}else{
			temp = member;
		}
		temp.setId(user.getId());
		temp.setDeptId(user.getDepartment().getId());
		temp.setLccAccount(user.getLccAccount());
		temp.setPost(user.getPosition());
		temp.setRealName(user.getRealName());
		temp.setUsername(user.getUsername());
		temp.setDefaultImg(user.getMyPic());
		temp.setDeptName(user.getDepartment().getDepartmentName());
		temp.setEmail(user.getEmail());
		temp.setSign(user.getSign());
		temp.setMobile(user.getMobile());
		temp.setGender(user.getGender());
		temp.setDefaultImg(user.getMyPic());
		return temp;
	}
	
	private void validateBeforeCreate(UserBean user)throws Exception{
		doAddAndModifyUserCheck(true, user);
		boolean flag = serviceFacade.getCustomerService().canCreateUser(user.getCustomer());
		if(!flag){
			throw new ArgsException("args.customeroveruserlimit");
		}
		UserBean temp = new UserBean();
		temp.setUsername(user.getUsername());
		temp.setStatus(Constant.RECORD_STATUS_VALID);
		if(daoFacade.getUserDao().getByExample(temp)!=null){
			throw new ArgsException("args.usernameisused");
		}
		if(user.getLccAccount()!=null && !user.getLccAccount().equals("")){
			if(user.getLccAccount().length() != Constant.getLccnoLength()){
				throw new ArgsException("{args.lccnolengthmust}" + Constant.getLccnoLength());
			}
			if(getByLccAccount(user.getLccAccount())!=null){
				throw new ArgsException("args.lccnoexist");
			}
		}
	}
	
	private void doAddAndModifyUserCheck(boolean add, UserBean user)throws ArgsException{
		if(StringUtil.isEmpty(user.getUsername()))
			throw new ArgsException("user.username.empty");
		if(StringUtil.isEmpty(user.getRealName()))
			throw new ArgsException("user.realname.empty");
		String email = user.getEmail();
		if(StringUtil.isEmpty(email))
			throw new ArgsException("user.email.empty");
		if(!StringUtil.isEmail(email))
			throw new ArgsException("user.email.invalid");
		if(StringUtil.isEmpty(user.getDepartment().getDepartmentName()))
			throw new ArgsException("user.department.empty");
		if(add){
			String password = user.getNewPassword();
			if(StringUtil.isEmpty(password))
				throw new ArgsException("user.password.empty");
			String repeatPassword = user.getRepeatPassword();
			if(StringUtil.isEmpty(repeatPassword))
				throw new ArgsException("user.repeatpassword.empty");
			if(!password.equals(repeatPassword))
				throw new ArgsException("user.password.notSame");
		}
	}
	
	public boolean renewUser(UserBean user) throws Exception{
		doAddAndModifyUserCheck(false, user);
		if(!user.getUserType().equals(Constant.USER_TYPE_ADMIN)){
			if (user.getLccAccount() == null || user.getLccAccount().equals("")) {
				throw new ArgsException("args.lccno_required");
			}
			if(user.getLccAccount().length() != Constant.getLccnoLength()){
				throw new ArgsException("{args.lccnolengthmust}" + Constant.getLccnoLength());
			}
			UserBean temp = getByLccAccount(user.getLccAccount());
			if(temp!=null && !temp.getId().equals(user.getId())){
				throw new ArgsException("args.lccnoexist");
			}
			SipConfBean sipConf = confSipByUser(user);
			daoFacade.getSipConfDao().update(sipConf);
		}
//		OpenfireUtil.getInstance().removeGroupUser(PropertiesUtil.getConstant("openfire.systemGroup"), get(user.getId()).getLccAccount());
		daoFacade.getUserDao().update(user);
//		OpenfireUtil.getInstance().addGroupUser(PropertiesUtil.getConstant("openfire.systemGroup"), user.getLccAccount());
//		OpenfireUtil.getInstance().updateGroupUser(PropertiesUtil.getConstant("openfire.systemGroup"), user.getLccAccount(), user.getPassword(), user.getEmail(),"aa");
//		daoFacade.getUserDao().update(user);
		
		//notify client
		OpenfireUtil.getInstance().sendGroupBroadcast(BroadcastEvent.UPDATE_USER, parseMemberBean(user, null));
		return true;
	}
	
	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	} 
	
	private void setLccAccount(UserBean user){
		String code = StringUtil.getRandom(Constant.getLccnoLength() - Constant.getCustomerCodeLength());
		user.setLccAccount(user.getCustomer().getCustomerCode() + code);
	}

	public UserBean getByLccAccount(String lccAccount) throws CustomSqlException {
		UserBean user = new UserBean();
		user.setLccAccount(lccAccount);
		user.setStatus(Constant.RECORD_STATUS_VALID);
		List<UserBean>list = daoFacade.getUserDao().getByExample(user);
		if(list==null||list.size()==0)return null;
		return list.get(0);
	}

	public boolean removeUser(List<UserBean> users) throws Exception {
		//假删除
		for(UserBean user : users){
			SipConfBean sipConf = daoFacade.getSipConfDao().get(user.getSipId());
			//TODO 暂时先删除sipConf
			daoFacade.getSipConfDao().delete(sipConf);
			user.setStatus(Constant.RECORD_STATUS_DELETED);
			user.setSipId(-1);
			daoFacade.getUserDao().update(user);
			OpenfireUtil.getInstance().removeGroupUser(PropertiesUtil.getConstant("openfire.systemGroup"), user.getLccAccount());
			//notify client
			OpenfireUtil.getInstance().sendGroupBroadcast(BroadcastEvent.DELETE_USER, parseMemberBean(user, null));
			OpenfireUtil.getInstance().sendMsgToDeletedUser(BroadcastEvent.DELETE_USER, parseMemberBean(user, null));
		}
		return true;
	}
	
	/*================================= private methods =================================*/
	/**
	 * 根据user信息设置sipconf
	 */
	private SipConfBean confSipByUser(UserBean user)throws CustomSqlException{
		SipConfBean sipConf = null;
		if(user.getSipId()==null){
			sipConf=new SipConfBean();
		}else{
			sipConf = daoFacade.getSipConfDao().get(user.getSipId());	
		}
		sipConf.setUsername(user.getUsername());
		if(user.getNewPassword()!=null){
			sipConf.setSecret(user.getNewPassword());
		}
		sipConf.setName(user.getLccAccount());
		sipConf.setNonExpired(user.getUserAccountNonExpired());
		sipConf.setNonLocked(user.getUserAccountNonLocked());
		return sipConf;
	}

	public boolean userIsValid(UserBean user) throws CustomSqlException {
		UserBean curUser = daoFacade.getUserDao().get(user.getId());
		if(curUser.getUserAccountNonExpired()&&curUser.getUserAccountNonLocked()){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<UserBean> getConfUser(UserBean user, String belongType) throws Exception{
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
		List<UserBean> userList = serviceFacade.getUserService().getByCriteria(criteria);
		return userList;
	}

	@Override
	public UserBean getLoginUser(String username, String passwd)
			throws Exception {
		UserBean ex = new UserBean();
		ex.setUsername(username);
		ex.setPassword(MD5Builder.getMD5(passwd,username));
		List<UserBean> users = daoFacade.getUserDao().getByExample(ex);
		return users.get(0);
	}

	@Override
	public void createInitData() throws Exception {
		UserBean user;
		try {
			user = (UserBean)serviceFacade.getUserService()
										.loadUserByUsername(PropertiesUtil.getConstant("initdata.admin.name"));
		} catch (Exception e) {
			e.printStackTrace();
			user = null;
		}
		if(user!=null)
			return;
		log.info("==============init data===================");
		CustomerBean customer = new CustomerBean();
		customer.setCustomerName(PropertiesUtil.getConstant("initdata.admin.customer.name"));
		customer.setCustomerUserlimit(0);
		customer.setCustomerNolimit(0);
		customer.setPerConfNoLimit(0);
		customer.setCustomerCode(PropertiesUtil.getConstant("initdata.admin.customer.code"));
		customer.setStatus(Constant.RECORD_STATUS_VALID);
		customer.setCustomerStatus(Constant.RECORD_VALID);
		customer.setId(serviceFacade.getCustomerService().save(customer));
		PropertiesUtil.setConstant("initdata.admin.customer.id", customer.getId().toString());
		
		
		user = new UserBean();
		Integer sysRoleCustomerId = Integer.parseInt(PropertiesUtil.getConstant("initdata.role.sysrole.customerid"));
		setInitRole("initdata.role.admin",sysRoleCustomerId,user);
		setInitRole("initdata.role.customer.admin",customer.getId(),user);
		setInitRole("initdata.role.customer.user",customer.getId(),user);
		setInitRole("initdata.role.customer.depadmin",customer.getId(),user);
		setInitRole("initdata.role.member.user",sysRoleCustomerId,user);
		
//		if(true){
//			throw new Exception("test init");
//		}
		
		user.setUsername(PropertiesUtil.getConstant("initdata.admin.name"));
		user.setPassword(MD5Builder.getMD5(PropertiesUtil.getConstant("initdata.admin.password"),user.getUsername()));
		user.setMd5passwd(MD5Builder.getMD5(PropertiesUtil.getConstant("initdata.admin.password")));
		user.setLccAccount("admin");
		user.setStatus(Constant.RECORD_STATUS_VALID);
		user.setUserAccountNonExpired(true);
		user.setUserAccountNonLocked(true);
		user.setUserCredentialsNonExpired(true);
		user.setUserEnabled(true);
		user.setCustomer(customer);
		user.setUserType(Constant.USER_TYPE_ADMIN);
		user.setId(serviceFacade.getUserService().save(user));
		PropertiesUtil.setConstant("initdata.admin.id", user.getId().toString());	
		
		customer = new CustomerBean();
		McuServerBean mcu = serviceFacade.getMcuServerService().getAll().get(0);
		RatesBean rates = serviceFacade.getRatesService().getAll().get(0);
		customer.setMcuServer(mcu);
		customer.setRates(rates);
		customer.setCustomerName(PropertiesUtil.getConstant("initdata.customer.name"));
		customer.setCustomerUserlimit(10);
		customer.setCustomerNolimit(0);
		customer.setPerConfNoLimit(0);
		customer.setConfPeopleLimit(9);
//		customer.setCustomerCode(PropertiesUtil.getConstant("initdata.customer.code"));
		customer.setStatus(Constant.RECORD_STATUS_VALID);
		customer.setCustomerStatus(Constant.RECORD_VALID);
		serviceFacade.getCustomerService().createCustomer(customer);
	}
	
	/**
	 * 初始化角色表
	 * @param constKey
	 * @param customerId
	 * @param user
	 */
	private void setInitRole(String constKey,Integer customerId,UserBean user) throws Exception{
		String roleCode = PropertiesUtil.getConstant(constKey);
		RoleBean role = new RoleBean();
		role.setRoleName(PropertiesUtil.getProperty("messageResource", roleCode,true));
		role.setRoleDesc(PropertiesUtil.getProperty("messageResource", roleCode,true));
		role.setRoleCode(roleCode);
		role.setCustomerId(customerId);
		role.setStatus(Constant.RECORD_STATUS_VALID);
		RoleBean adminRole = role;
		adminRole.setId(serviceFacade.getRoleService().save(role));
		user.getRoles().add(adminRole);
	}

	@Override
	public ImportUserConfig getImportUserConfig() throws Exception{
		ImportUserConfig config = new ImportUserConfig();
		config.setUrl(daoFacade.getStaticDao().getSystemParams("importUserDBurl", ""));
		config.setUsername(daoFacade.getStaticDao().getSystemParams("importUserDBusername", ""));
		config.setPasswd(daoFacade.getStaticDao().getSystemParams("importUserDBpasswd", ""));
		return config;
	}

	@Override
	public VovoMyInfo getVovoMyInfo(String lccno) throws Exception{
		UserBean user = getByLccAccount(lccno);
		VovoMyInfo info = new VovoMyInfo();
		parseMemberBean(user, info);
		return info;
	}

//	@Override
//	public void doImportUser(ImportUserConfig config, CustomerBean customer)throws Exception {
//		//获取数据
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		RoleBean role = new RoleBean();
//		role.setCustomerId(customer.getId());
//		role.setRoleCode(Constant.ROLE_CUSTOMER_ADMIN);
//		role = serviceFacade.getRoleService().getByExample(role).get(0);
//		
//		try {
//			conn = DBUtil.getConnection(config.getUrl(), config.getUsername(), config.getPasswd());
//			ps = conn.prepareStatement("select id, name, department from usert");
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				String id = rs.getString(1);
//				String name = rs.getString(2);
//				String depart = rs.getString(3);
//				// 导入数据
//				UserBean userBean = new UserBean();
//				userBean.setUsername(name + "@" + depart);
//				userBean.setRealPassword("123456");
//				userBean.setPassword(MD5Builder.getMD5(userBean.getRealPassword(), userBean.getUsername()));
//				userBean.setMd5passwd(MD5Builder.getMD5(userBean.getRealPassword()));
//				userBean.setCustomer(customer);
//				userBean.setLccAccount(id);
//				userBean.setUserAccountNonExpired(true);
//				userBean.setUserAccountNonLocked(true);
//				userBean.setUserCredentialsNonExpired(true);
//				userBean.setUserEnabled(true);
//				userBean.setUserType(Constant.RECORD_STATUS_VALID);
//				userBean.getRoles().clear();
//				userBean.getRoles().add(role);
////				userBean.setIsImport(true);
//				createUser(userBean);
//			}
//		} finally {
//			DBUtil.close(rs, ps, conn);
//		}
//		
//		//保存设置数据
//		daoFacade.getStaticDao().setSystemParams("importUserDBurl", config.getUrl(), "", true);
//		daoFacade.getStaticDao().setSystemParams("importUserDBusername", config.getUsername(), "", true);
//		daoFacade.getStaticDao().setSystemParams("importUserDBpasswd", config.getPasswd(), "", true);
//		
//	}


	public Workbook exportToXLS() throws CustomSqlException, FileNotFoundException, IOException{
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet();
		Row row0 = sheet.createRow(0);
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Cell cell = row0.createCell(REAL_NAME);
		cell.setCellValue("姓名*（最大长度为30）");
		cell.setCellStyle(cellStyle);
		cell = row0.createCell(USER_NAME);
		cell.setCellValue("用户名*（最大长度为20）");
		cell.setCellStyle(cellStyle);
		cell = row0.createCell(GENDER);
		cell.setCellValue("性别");
		cell.setCellStyle(cellStyle);
		cell = row0.createCell(MOBILE);
		cell.setCellValue("电话号码（最大长度为20）");
		cell.setCellStyle(cellStyle);
		cell = row0.createCell(EMAIL);
		cell.setCellValue("Email");
		cell.setCellStyle(cellStyle);
		cell = row0.createCell(CODE);
		cell.setCellValue("员工编码（最大长度为20）");
		cell.setCellStyle(cellStyle);
		cell = row0.createCell(POSITION);
		cell.setCellValue("职位（最大长度为50）");
		cell.setCellStyle(cellStyle);
		cell = row0.createCell(DEPARTMENT);
		cell.setCellValue("部门*（由根部门开始描述，部门之间用'@'隔开，例如：络威网络@IT部）");
		cell.setCellStyle(cellStyle);
		cell = row0.createCell(DEPARTMENT_CODE);
		cell.setCellValue("部门编码（最大长度为50）");
		cell.setCellStyle(cellStyle);
		cell = row0.createCell(PASSWORD);
		cell.setCellValue("用户密码*（最大长度为12）");
		cell.setCellStyle(cellStyle);
		cell = row0.createCell(LCC_NO);
		cell.setCellValue("LCC号码*（长度由LCM系统服务设置）");
		cell.setCellStyle(cellStyle);
		int rowIndex=1;
		DetachedCriteria criteria = DetachedCriteria.forClass(UserBean.class);
		criteria.add(Restrictions.ne("status", -1));
		List<UserBean> users = serviceFacade.getUserService().getByCriteria(criteria);
		for(UserBean user:users){
			Hibernate.initialize(user);
			DepartmentBean department = user.getDepartment();
			if(department==null)
				continue;
			Row row = sheet.createRow(rowIndex++);
			row.createCell(REAL_NAME).setCellValue(user.getRealName());
			row.createCell(USER_NAME).setCellValue(user.getUsername());
			row.createCell(GENDER).setCellValue(genderMap.get(user.getGender()));
			row.createCell(MOBILE).setCellValue(user.getMobile());
			row.createCell(EMAIL).setCellValue(user.getEmail());
			row.createCell(CODE).setCellValue(user.getCode());
			row.createCell(POSITION).setCellValue(user.getPosition());
			row.createCell(DEPARTMENT).setCellValue(getDepartmentPathString(department));
			row.createCell(DEPARTMENT_CODE).setCellValue(department.getCode());
			row.createCell(LCC_NO).setCellValue(user.getLccAccount());
		}
		for (int i = REAL_NAME; i < LCC_NO; i++) {
			sheet.autoSizeColumn(i);
		}
		return wb;
	}
	
	public static final Map<String,String> genderMap=new HashMap<String,String>();
	static {
		genderMap.put("male", "男");
		genderMap.put("female", "女");
	}
	public static final Map<String,String> importGenderMap=new HashMap<String,String>();
	static {
		importGenderMap.put("male", "male");
		importGenderMap.put("female", "female");
		importGenderMap.put("男", "male");
		importGenderMap.put("女", "female");
	}
	
	@Override
	public ImportResult importFromXLS(InputStream inputStream) throws Exception {
		Workbook wb = new HSSFWorkbook(inputStream);
		Sheet sheet = wb.getSheetAt(0);
		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
		if(physicalNumberOfRows<2){
			throw new RuntimeException("Excel中没有有效内容");
		}
		Iterator<Row> rowIterator = sheet.rowIterator();
		if(rowIterator.hasNext())
			rowIterator.next();
		int count=0,success=0;
		while(rowIterator.hasNext()){
			Row r = rowIterator.next();
			count++;
			try{
				processRow(r);
				r.createCell(COMMENT).setCellValue("成功");
				success++;
			}catch(Exception e){
				String message = e.getMessage();
				log.error(message,e);
				r.createCell(COMMENT).setCellValue(message);
			}
		}
		sheet.autoSizeColumn(COMMENT);
		return new ImportResult(count,success,wb);
	}

	private void processRow(Row row) throws Exception{
		Cell cell = row.getCell(REAL_NAME);
		String realName = getCellValue(cell);
		cell = row.getCell(USER_NAME);
		String userName = getCellValue(cell);
		cell = row.getCell(DEPARTMENT);
		String departmentPath = getCellValue(cell);
		cell = row.getCell(PASSWORD);
		String password = getCellValue(cell);
		cell = row.getCell(LCC_NO);
		String lcc = getCellValue(cell);
		
		if (StringUtil.isEmpty(realName) || StringUtil.isEmpty(userName)
				|| StringUtil.isEmpty(departmentPath)
				|| StringUtil.isEmpty(password)
				|| StringUtil.isEmpty(lcc)) {
			throw new RuntimeException("姓名、用户名、部门、密码与LCC号码都是必填项");
		}
		if(realName.length()>30)
			throw new RuntimeException("姓名过长");
		if(userName.length()>20)
			throw new RuntimeException("用户名过长");
		if(password.length()>12)
			throw new RuntimeException("密码过长");
		if(lcc.length()>15)
			throw new RuntimeException("LCC号码过长");
		if(!StringUtil.isNumeric(lcc))
			throw new RuntimeException("LCC号码只能由数字组成");
		if(getByLccAccount(lcc)!=null)
			throw new RuntimeException("LCC号码已被使用");
		
		cell=row.getCell(GENDER);
		String gender = getCellValue(cell);
		cell=row.getCell(MOBILE);
		String mobile = getCellValue(cell);
		cell=row.getCell(EMAIL);
		String email = getCellValue(cell);
		cell=row.getCell(CODE);
		String userCode = getCellValue(cell);
		cell=row.getCell(POSITION);
		String position = getCellValue(cell);
		UserBean user = new UserBean();
		user.setPassword(password);
		user.setNewPassword(password);
		user.setRepeatPassword(password);
		user.setRealName(realName);
		user.setUsername(userName);
		user.setGender(importGenderMap.get(gender));
		user.setMobile(mobile);
		user.setEmail(email);
		user.setCode(userCode);
		user.setPosition(position);
		user.setLccAccount(lcc);
		user.setUserType(Constant.USER_TYPE_INNERUSER);
		checkUserBean(user);
		
		
		cell = row.getCell(DEPARTMENT_CODE);
		String departmentCode = getCellValue(cell);
		if(departmentCode!=null&&departmentCode.length()>50)
			throw new RuntimeException("部门编码名字过长");
		String[] departments = departmentPath.split("@");
		
		
		DepartmentService departmentService = serviceFacade.getDepartmentService();
		DepartmentBean department=null,parent=null;
		CustomerBean customer = serviceFacade.getCustomerService().getFirstValidCustomer();
		Integer customerId = customer.getId();
		OrgTree<com.lorent.common.tree.DepartmentBean, MemberBean> tree = departmentService.getOrgTreeByCustomer(customerId);
		OrgNode<com.lorent.common.tree.DepartmentBean, MemberBean> node = tree.getRoot();
		com.lorent.common.tree.DepartmentBean org = node.getOrganization();
		boolean create=false;
		if(!departments[0].equals(org.getName())){
			throw new RuntimeException("根部门必须是"+org.getName());
		}
		OUT:
		for(int i=1;i<departments.length;i++){
			String departmentName=departments[i].trim();
			if(departmentName.length()>20)
				throw new RuntimeException("部门名字过长");
			
			if(!create){
				for(OrgNode<com.lorent.common.tree.DepartmentBean, MemberBean> _n:node.getChildNodes()){
					com.lorent.common.tree.DepartmentBean _o = _n.getOrganization();
					if(_o!=null&&_o.getName().equals(departmentName)){
						node=_n;
						org=_o;
						continue OUT;
					}
				}
				create=true;
				parent=departmentService.get(org.getId());
			}
			department=new DepartmentBean();
			department.setDepartmentName(departmentName);
			if(departmentCode!=null){
				department.setCode(departmentCode);
			}
			department.setCustomerId(customerId);
			String searchStr = createSearchStr(parent);
			department.setSearchStr(searchStr);
			department.setParentId(parent.getId());
			department.setIsRoot(false);
			department.setStatus(Constant.RECORD_STATUS_VALID);
			departmentService.createDepartment(department);
			parent=department;
		}
		
		if(department==null){
			department=departmentService.get(org.getId());
		}
		user.setCustomer(customer);
		user.setDepartment(department);
		createUser(user);
	}
	
	private void checkUserBean(UserBean user){
		String check=user.getEmail();
		if(check!=null&&check.length()>50){
			throw new RuntimeException("email地址过长");
		}
		check=user.getMobile();
		if(check!=null&&check.length()>20){
			throw new RuntimeException("电话号码过长");
		}
		check=user.getGender();
		if(check!=null&&check.length()>10){
			throw new RuntimeException("性别字符过长");
		}
		check=user.getCode();
		if(check!=null&&check.length()>20){
			throw new RuntimeException("员工编码过长");
		}
		check=user.getPosition();
		if(check!=null&&check.length()>50){
			throw new RuntimeException("职位过长");
		}
	}
	
	private String getCellValue(Cell cell){
		if(cell==null)
			return null;
		String result=null;
		switch(cell.getCellType()){
		case Cell.CELL_TYPE_STRING:
			result= cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			cell.setCellType(Cell.CELL_TYPE_STRING);
			result= cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			result= String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			result= "";
			break;
		}
		if(result!=null)
			result=result.trim();
		return result;
	}
	
	private String createSearchStr(DepartmentBean parent) {
		String searchStr = "";
		if(parent.getIsRoot()){
			searchStr = parent.getId() + ""; 
		}else{
			searchStr = parent.getSearchStr() + "@" + parent.getId();
		}
		return searchStr;
	}

	private String getDepartmentPathString(DepartmentBean db) throws CustomSqlException{
		String searchStr = db.getSearchStr();
		if(searchStr==null||searchStr.trim().isEmpty()){
			return db.getDepartmentName();
		}
		String[] parentIdStrings = searchStr.split("@");
		Integer[] parentIds = new Integer[parentIdStrings.length];
		for(int i=parentIdStrings.length-1;i>=0;i--){
			parentIds[i]=Integer.parseInt(parentIdStrings[i]);
		}
		StringBuilder result = new StringBuilder();
		List<DepartmentBean> list = daoFacade.getDepartmentDao().get(parentIds);
		for (DepartmentBean pd : list) {
			result.append(pd.getDepartmentName());
			result.append('@');
		}
		result.append(db.getDepartmentName());
		return result.toString();
	}

	/**
	 * 
	 * @param users 每一个Object是String[],其中Str[0]=username,Str[1]=realname,Str[2]=lccno,Str[3]=passwd
	 * @return
	 */
	@Override
	public void createOrUpdateUCSUser(Object[] users)throws Exception{
		List<UserBean> data = new ArrayList<UserBean>();
		CustomerBean customer = serviceFacade.getCustomerService().getFirstValidCustomer();
		DepartmentBean rootDepartment = serviceFacade.getCustomerService().getRootDepartment(customer.getId());
		for(Object user : users){
			Object[] values = (Object[])user;
			UserBean temp = new UserBean();
			temp.setUsername(values[0].toString());
			temp.setStatus(Constant.RECORD_STATUS_VALID);
			List<UserBean> result = serviceFacade.getUserService().getByExample(temp);
			if(result == null || result.size() == 0){//不存在
				temp.setCustomer(customer);
				temp.setDepartment(rootDepartment);
			}else{
				temp = result.get(0);
			}
			temp.setRealName(values[1].toString());
			temp.setLccAccount(values[2].toString());
			temp.setPassword(MD5Builder.getMD5(values[3].toString(),values[0].toString()));
			temp.setMd5passwd(MD5Builder.getMD5(values[3].toString()));
			data.add(temp);
		}
		daoFacade.getStaticDao().saveOrUpdate(data);
	}
	
	
}
