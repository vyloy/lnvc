package com.lorent.service;

import java.util.List;

import com.lorent.annotation.AfterLogger;
import com.lorent.dao.CustomerDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.CustomerBean;
import com.lorent.model.DepartmentBean;

public interface CustomerService extends IGenericService<CustomerDao,CustomerBean,Integer> {
	@AfterLogger(msgKey="logger.CustomerService.createCustomer")
	boolean createCustomer(CustomerBean customer)throws CustomSqlException,ArgsException;
	@AfterLogger(msgKey="logger.CustomerService.renewCustomer")
	boolean renewCustomer(CustomerBean customer)throws CustomSqlException,ArgsException;
	boolean removeCustomer(CustomerBean customer)throws CustomSqlException,ArgsException;
	boolean removeCustomer(Integer[]ids)throws CustomSqlException,ArgsException;
	@AfterLogger(msgKey="logger.CustomerService.removeCustomer")
	boolean removeCustomer(List<CustomerBean> customers)throws Exception;
	CustomerBean getByCustomerCode(String code)throws CustomSqlException,ArgsException;
	DepartmentBean getRootDepartment(int customerId)throws CustomSqlException,ArgsException; 
	public boolean canRemoveCustomer(List<CustomerBean> customers);
	public CustomerBean getFirstValidCustomer();
	public int getCustomerValidUserCount(int customerId);
	public boolean canCreateUser(CustomerBean customer);
}
