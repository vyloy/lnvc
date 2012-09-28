package com.lorent.service;

import java.util.List;

import com.lorent.annotation.AfterLogger;
import com.lorent.common.tree.OrgTree;
import com.lorent.dao.DepartmentDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.DepartmentBean;

public interface DepartmentService extends IGenericService<DepartmentDao,DepartmentBean,Integer> {
	/**
	 * 创建新的部门
	 * @param department
	 * @return
	 * @throws CustomSqlException
	 * @throws ArgsException
	 * @throws Exception 
	 */
	@AfterLogger(msgKey="logger.DepartmentService.createDepartment")
	Integer createDepartment(DepartmentBean department)throws Exception;
	/**
	 * 更新部门信息记录
	 * @param department
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	@AfterLogger(msgKey="logger.DepartmentService.renewDepartment")
	void renewDepartment(DepartmentBean department)throws Exception;
	/**
	 * 删除部门信息记录
	 * @param id
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	@AfterLogger(msgKey="logger.DepartmentService.removeDepartment")
	void removeDepartment(Integer id)throws Exception;
	/**
	 * 删除部门信息记录
	 * @param ids
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	void removeDepartment(Integer[]ids)throws Exception;
//	/**
//	 * 将部门转化为页面树形信息
//	 * @param depts
//	 * @return
//	 */
//	public List<TreeBean> parseTreeBean(List<DepartmentBean> depts);

	/**
	 * 根据部门ID获取所有下级部门(包括当前部门)
	 * @param deptId
	 * @return
	 */
	public List<DepartmentBean> getSubDeptsByDeptId(Integer deptId);
	
	public List<DepartmentBean> getDeptByCustomer(int customerId);
	
	public OrgTree getOrgTreeByCustomer(int customerId);
	
	
}
