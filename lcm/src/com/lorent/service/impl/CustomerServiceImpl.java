package com.lorent.service.impl;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

import org.hibernate.cfg.annotations.QueryBinder;

import com.lorent.dao.CustomerDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.exception.ValidateException;
import com.lorent.model.ConferenceNoBean;
import com.lorent.model.CustomerBean;
import com.lorent.model.DepartmentBean;
import com.lorent.model.RoleBean;
import com.lorent.model.UserBean;
import com.lorent.service.CustomerService;
import com.lorent.util.Constant;
import com.lorent.util.PropertiesUtil;
import com.lorent.util.StringUtil;
import com.lorent.util.ThreadLocaleUtil;

public class CustomerServiceImpl extends GenericServiceImpl<CustomerDao,CustomerBean,Integer> implements
		CustomerService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 删除客户信息记录
	 */
	public boolean removeCustomer(CustomerBean customer) throws CustomSqlException,ArgsException {
		//检查记录是否存在
		if(daoFacade.getCustomerDao().getByExample(customer)==null)
			throw new ArgsException("args.recordnotexists");
		RoleBean role = new RoleBean();
		role.setCustomerId(customer.getId());
		List<RoleBean>roles = daoFacade.getRoleDao().getByExample(role);
		if(roles!=null&&roles.size()!=0)
			daoFacade.getRoleDao().delete(roles);
		customer.setCustomerStatus(Constant.RECORD_DELETED);
		customer.setStatus(Constant.RECORD_STATUS_DELETED);
		daoFacade.getCustomerDao().update(customer);
		return true;
	}
	
	public boolean removeCustomer(List<CustomerBean> customers)
			throws Exception {
		if(!canRemoveCustomer(customers)){
			throw new ValidateException("validate.deleterelateerror");
		}
		for(CustomerBean customer : customers){
			removeCustomer(customer);
		}
		return false;
	}
	
	public boolean canRemoveCustomer(List<CustomerBean> customers){
		Integer[] ids = new Integer[customers.size()];
		for(int i = 0; i < customers.size(); i++){
			ids[i] = customers.get(i).getId();
		}
		
		String condition = StringUtil.arrayToDelimitedString(ids, ",", "", "");
		
		String sql = "select count(*) from sys_user where customer_id in (" + condition + ") and status = 1";
		BigInteger count = (BigInteger)daoFacade.getStaticDao().queryBySqlOneResult(sql);
		if(count.intValue() > 0){
			return false;
		}
		return true;
		
	}
	
	/**
	 * 删除客户信息记录
	 */
	public boolean removeCustomer(Integer[]ids)throws ArgsException,CustomSqlException {
		for(int i=0;i<ids.length;i++){
			removeCustomer(daoFacade.getCustomerDao().get(ids[i]));
		}
		return true;
	}
	/**
	 * 通过客户代码获取客户信息
	 */
	public CustomerBean getByCustomerCode(String code)
			throws CustomSqlException, ArgsException {
		CustomerBean example = new CustomerBean();
		example.setCustomerCode(code);
		List<CustomerBean>cusList = getByExample(example);
		if(cusList==null||cusList.size()==0)return null;
		else return cusList.get(0);
	}
	/**
	 * 初始化客户的相关角色
	 */
	private void initCustomerRole(CustomerBean customer)throws CustomSqlException{
		RoleBean example = new RoleBean();
		example.setCustomerId(customer.getId());
		if(daoFacade.getRoleDao().getByExample(example)!=null)return;
		RoleBean role = new RoleBean(PropertiesUtil.getProperty("messageResource", "page.role.customer.admin",true),
				Constant.ROLE_CUSTOMER_ADMIN,
				"role form customer administrator",
				customer.getId(),
				Constant.RECORD_VALID);
		role.setStatus(Constant.RECORD_STATUS_VALID);
		daoFacade.getRoleDao().save(role);
		role = new RoleBean(PropertiesUtil.getProperty("messageResource", "page.role.customer.depadmin",true),
			Constant.ROLE_CUSTOMER_DEP_ADMIN,
			"role form customer deparment administrator",
			customer.getId(),
			Constant.RECORD_VALID);
		role.setStatus(Constant.RECORD_STATUS_VALID);
		daoFacade.getRoleDao().save(role);
		role = new RoleBean(PropertiesUtil.getProperty("messageResource", "page.role.customer.user",true),
			Constant.ROLE_CUSTOMER_USER,
			"role form customer inner user",
			customer.getId(),
			Constant.RECORD_VALID);
		role.setStatus(Constant.RECORD_STATUS_VALID);
		daoFacade.getRoleDao().save(role);
	}
	/**
	 * 产生客户代码
	 * @return
	 */
	private String getCustomerCode(){
		String no = "";
		boolean isOK = true;
		String hql = "select count(c) from CustomerBean c where c.status = 1 and c.customerCode = ";
		while(isOK){
			String code = Long.toString(UUID.randomUUID().getLeastSignificantBits());
			no = code.substring(code.length()-Constant.getCustomerCodeLength(),code.length());
			List list = daoFacade.getCustomerDao().queryByHql(hql+"'"+no+"'");
			long num = (Long)(list.get(0));
			if(num == 0){
				isOK = false;
			}
		}
		return no;
	}
//	/**
//	 * 为客户产生会议号码
//	 * @param customer
//	 */
//	private void initConferenceNos(CustomerBean customer)throws ArgsException {
//		if(customer.getCustomerUserlimit()>Constant.CUSTOMER_CONFUSER_LIMIT)
//			throw new ArgsException("args.overuserlimit");
//		if(customer.getCustomerNolimit()==null)customer.setCustomerNolimit(1);
//		for(int i=0;i<customer.getCustomerNolimit();i++){
//			ConferenceNoBean no = new ConferenceNoBean();
//			String str = Integer.toString(i);
//			int len = Constant.CONFERENCE_NO_LENGTH-Constant.CUSTOMER_CODE_LENGTH-str.length();
//			for(int j=0;j<len;j++)str = "0"+str;
//			no.setNoCode(customer.getCustomerCode()+str);
//			no.setIsUsed(false);
//			customer.getNos().add(no);
//		}
//	}
//	private void initConferenceNos2(CustomerBean customer)throws ArgsException {
//		if(customer.getCustomerUserlimit()>Constant.CUSTOMER_CONFUSER_LIMIT)
//			throw new ArgsException("args.overuserlimit");
//		if(customer.getCustomerNolimit()==null)customer.setCustomerNolimit(1);
//		String sql = MessageFormat.format("select createconfno({0},{1},{2},{3},{4})", 
//				customer.getCustomerNolimit(),
//				customer.getId(),
//				"'"+customer.getCustomerCode()+"'",
//				Constant.CUSTOMER_CODE_LENGTH,
//				Constant.CONFERENCE_NO_LENGTH
//				);
//		List list = daoFacade.getCustomerDao().queryBySql(sql);
//	}
	public boolean createCustomer(CustomerBean customer)
			throws CustomSqlException, ArgsException {
		//检查记录是否已存在数据库中
		if(daoFacade.getCustomerDao().getByExample(customer)!=null)
			throw new ArgsException("arg.recordexists");
		if(customer.getCustomerCode()!=null && customer.getCustomerCode().length()!=0){
			validCustomerCode(customer);
		}else{
			customer.setCustomerCode(getCustomerCode());
		}
		customer.setStatus(Constant.RECORD_STATUS_VALID);
		customer.setId(daoFacade.getCustomerDao().save(customer));
		//为客户产生会议号码
		//initConferenceNos2(customer);
		//初始化客户相应的角色
		initCustomerRole(customer);
		//初始化客户的根部门
		initRootDepartment(customer);
 		return true;
	}
	
	private void validCustomerCode(CustomerBean customer)throws ArgsException, CustomSqlException{
		if(customer.getCustomerCode().length() != Constant.getCustomerCodeLength()){
//			throw new ArgsException("{args.customercodelengthmust}" + Constant.getCustomerCodeLength());
			throw new ArgsException(PropertiesUtil.getProperty("exceptionResource_zh_CN", "args.customercodelengthmust")+":"+ Constant.getCustomerCodeLength());
		}
		CustomerBean temp = getByCustomerCode(customer.getCustomerCode());		
		if(customer.getId()==null){//add
			if(temp!=null){
				throw new ArgsException("args.customercodeexists");
			}
		}else{//modify
			if(temp!=null && !temp.getId().equals(customer.getId())){
				throw new ArgsException("args.customercodeexists");
			}
		}
	}
	
	
	
	public boolean renewCustomer(CustomerBean customer)
			throws CustomSqlException, ArgsException {
		if(customer.getCustomerCode()==null || customer.getCustomerCode().length()==0){
			throw new ArgsException("args.customercodeisempty");
		}
		validCustomerCode(customer);
		daoFacade.getCustomerDao().update(customer);
//		初始化客户相应的角色
		initCustomerRole(customer);
		DepartmentBean rootDept = getRootDepartment(customer.getId());
		rootDept.setDepartmentName(customer.getCustomerName());
		daoFacade.getDepartmentDao().update(rootDept);
 		return true;
	}
	/**
	 * 初始化客户的根部门
	 * @param customer
	 */
	private void initRootDepartment(CustomerBean customer)throws CustomSqlException {
		DepartmentBean department = new DepartmentBean();
		department.setCustomerId(customer.getId());
		department.setDepartmentName(customer.getCustomerName());
		department.setDepartmentDesc("root department");
		department.setParentId(null);
		department.setIsRoot(true);
		department.setStatus(Constant.RECORD_STATUS_VALID);
		daoFacade.getDepartmentDao().save(department);
	}
	/**
	 * 获取客户的根部门
	 */
	public DepartmentBean getRootDepartment(int customerId) throws CustomSqlException, ArgsException {
		if(customerId==0)
			throw new ArgsException("args.norootdepartment");
//		DepartmentBean example = new DepartmentBean();
//		example.setCustomerId(customerId);
//		example.setIsRoot(true);
		String hql = "select d from DepartmentBean d where d.customerId = " + customerId + " and isRoot = " + true;
		List<DepartmentBean> list = daoFacade.getDepartmentDao().queryByHql(hql);
		return list.get(0); 
	}

	@Override
	public CustomerBean getFirstValidCustomer() {
		String hql = "select c from CustomerBean c where c.id <> 1 order by c.id";
		List<CustomerBean>  customers = daoFacade.getCustomerDao().queryByHql(hql);
		if(customers == null || customers.size() == 0){
			return null;
		}else{
			return customers.get(0);
		}
	}

	@Override
	public int getCustomerValidUserCount(int customerId) {
		String hql = "select count(u) from UserBean u left join u.customer c where u.status <> -1 and c.id = " + customerId;
		int ret = daoFacade.getCustomerDao().queryByHqlGetCount(hql);
		return ret;
	}
	
	@Override
	public boolean canCreateUser(CustomerBean customer){
		int count = getCustomerValidUserCount(customer.getId());
		if(count >= customer.getCustomerUserlimit().intValue()){
			return false;
		}else{
			return true;
		}
	}

}
