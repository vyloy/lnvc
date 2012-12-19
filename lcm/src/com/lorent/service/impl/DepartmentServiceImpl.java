package com.lorent.service.impl;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.lorent.common.tree.BroadcastEvent;
import com.lorent.common.tree.MemberBean;
import com.lorent.common.tree.OrgNode;
import com.lorent.common.tree.OrgTree;
import com.lorent.dao.DepartmentDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.DepartmentBean;
import com.lorent.service.DepartmentService;
import com.lorent.util.Constant;
import com.lorent.util.OpenfireUtil;
import com.lorent.util.StringUtil;

public class DepartmentServiceImpl extends GenericServiceImpl<DepartmentDao,DepartmentBean,Integer> implements
		DepartmentService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 创建新的部门
	 * @param department
	 * @return
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	public Integer createDepartment(DepartmentBean department) throws Exception {
		department.setCustomerId(serviceFacade.getCustomerService().getFirstValidCustomer().getId());
		String searchStr = createSearchStr(department.getParentId());
		department.setSearchStr(searchStr);
		department.setIsRoot(false);
		department.setStatus(Constant.RECORD_STATUS_VALID);
		doAddAndModifyDeptCheck(department,true);
		int id = daoFacade.getDepartmentDao().save(department);
		//notify client
		OpenfireUtil.getInstance().sendGroupBroadcast(BroadcastEvent.ADD_DEPT, parseDepartmentBean(department));
		return id;
	}
	
	private void doAddAndModifyDeptCheck(DepartmentBean d,boolean add) throws ArgsException, CustomSqlException{
		String departmentName = d.getDepartmentName();
		if(StringUtil.isEmpty(departmentName))
			throw new ArgsException("department.name.empty");
		if(!add)
			return;
		DetachedCriteria criteria=DetachedCriteria.forClass(DepartmentBean.class);
		criteria.add(Restrictions.eq("departmentName", departmentName));
		criteria.add(Restrictions.eq("searchStr", d.getSearchStr()));
		criteria.add(Restrictions.ne("status", -1));
		List<DepartmentBean> result = this.getByCriteria(criteria);
		if(result!=null&&!result.isEmpty())
			throw new ArgsException("department.name.exists");
	}
	
	private com.lorent.common.tree.DepartmentBean parseDepartmentBean(DepartmentBean dept){
		com.lorent.common.tree.DepartmentBean temp = new com.lorent.common.tree.DepartmentBean();
		temp.setId(dept.getId());
		temp.setName(dept.getDepartmentName());
		temp.setSearchStr(dept.getSearchStr());
		temp.setParentId(dept.getParentId());
		return temp;
	}
	
	private String createSearchStr(int parentId)throws Exception{
		DepartmentBean parentDept = get(parentId);
		String searchStr = "";
		if(parentDept.getIsRoot()){
			searchStr = parentDept.getId() + ""; 
		}else{
			searchStr = parentDept.getSearchStr() + "@" + parentDept.getId();
		}
		return searchStr;
	}
	/**
	 * 更新部门信息记录
	 * @param department
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	public void renewDepartment(DepartmentBean department) throws Exception {
		int preParentId = getPreParentBySearchStr(department.getSearchStr());
		if(preParentId != department.getParentId()){//如果更改上级部门
			String myStr = department.getSearchStr() + "@" + department.getId() + "";
			String hql = "select d from DepartmentBean d where d.searchStr like '" + myStr + "%'";
			List<DepartmentBean> subDepts = daoFacade.getDepartmentDao().queryByHql(hql);
			String newStr = createSearchStr(department.getParentId()) + "@" + department.getId();
			for(DepartmentBean subDept : subDepts){
				String endStr = subDept.getSearchStr().replace(myStr, "");
				subDept.setSearchStr(newStr + endStr);
				update(subDept);
			}
			department.setSearchStr(createSearchStr(department.getParentId()));
			
		}
		doAddAndModifyDeptCheck(department,false);
		daoFacade.getDepartmentDao().update(department);
		//notify client
		OpenfireUtil.getInstance().sendGroupBroadcast(BroadcastEvent.UPDATE_DEPT, parseDepartmentBean(department));
	}
	
	private int getPreParentBySearchStr(String searchStr){
		String[] temp = searchStr.split("@");
		return Integer.parseInt(temp[temp.length - 1]);
	}
	
	/**
	 * 删除部门信息记录
	 * @param id
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	public void removeDepartment(Integer id) throws Exception {
		DepartmentBean department = daoFacade.getDepartmentDao().get(id);
		if(department==null)
			throw new ArgsException("args.recordnotexists");
		//如果存在下级部门或用户时不能删除
		String sql = "select count(*) from sys_user u left join sys_deparment d on u.department_id = d.deparment_id " +
				"where d.deparment_id=" + department.getId() + " and d.status = " + Constant.RECORD_STATUS_VALID + " and u.status=" + Constant.RECORD_STATUS_VALID;
		List ret = daoFacade.getDepartmentDao().queryBySql(sql);
		BigInteger count = (BigInteger)ret.get(0);
		if(count.intValue() > 0){
			throw new ArgsException("args.has_user_not_delete");
		}
		sql = "select count(*) from sys_deparment d " +
		"where d.searchstr like '" + department.getSearchStr() + "@" + department.getId() + "%' and d.status = " + Constant.RECORD_STATUS_VALID;
		ret = daoFacade.getDepartmentDao().queryBySql(sql);
		count = (BigInteger)ret.get(0);
		if(count.intValue() > 0){
			throw new ArgsException("args.has_subdept_not_delete");
		}
		department.setStatus(Constant.RECORD_STATUS_DELETED);
		daoFacade.getDepartmentDao().update(department);
		//notify client
		OpenfireUtil.getInstance().sendGroupBroadcast(BroadcastEvent.DELETE_DEPT, parseDepartmentBean(department));
	}
	/**
	 * 删除部门信息记录
	 * @param ids
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	public void removeDepartment(Integer[] ids) throws Exception {
		for (int i = 0; i < ids.length; i++) {
			removeDepartment(ids[i]);
		}
	}
	
//	public List<TreeBean> parseTreeBean(List<DepartmentBean> depts) {
//		List<TreeBean> nodes = new ArrayList<TreeBean>();
//		for(DepartmentBean dept : depts){
//			TreeBean node = new TreeBean();
//			node.setId(dept.getId());
//			if(dept.getParent()==null){
//				node.setPid(-1);
//			}else{
//				node.setPid(dept.getParent().getId());
//			}
//			node.setName(dept.getDepartmentName());
//			node.setTitle(dept.getDepartmentName());
//			
//			nodes.add(node);
//		}
//		return nodes;
//	}
	
	/**
	 * 根据部门ID获取所有下级部门(包括当前部门)
	 * @param deptId
	 * @return
	 */
	public List<DepartmentBean> getSubDeptsByDeptId(Integer deptId) {
		String sql = 
			" WITH RECURSIVE dept AS (" +
		    " select * from sys_deparment where deparment_id = " + deptId + 
		    " UNION ALL" +
		    " select sys_deparment.* from sys_deparment, dept where dept.deparment_id = sys_deparment.deparment_parent and sys_deparment.status = 1 " +
		    " )" +
		    " select * from dept";
		List<DepartmentBean> depts = daoFacade.getDepartmentDao().queryBySql(sql);
		return depts;
	}
	
	public List<DepartmentBean> getDeptByCustomer(int customerId){
		//按先按order排序，再按name排序
		String hql = "select d from DepartmentBean d where d.customerId = " + customerId + " and d.status = " + Constant.RECORD_STATUS_VALID + " order by d.order desc, d.id";
		List<DepartmentBean> depts =  daoFacade.getDepartmentDao().queryByHql(hql);
		return depts;
	}

	@Override
	public OrgTree<com.lorent.common.tree.DepartmentBean, MemberBean> getOrgTreeByCustomer(int customerId) {
		String hql = "select u.id, u.username, u.realName, u.position, u.lccAccount, u.status, d.id, d.departmentName, d.searchStr, u.sign, u.myPic, u.mobile, u.email, u.gender, d.parentId, u.isCustomPic, u.customPic " +
				" from UserBean u right join u.department d" +
				" where d.status = " + Constant.RECORD_STATUS_VALID + 
//				" and u.status = " + Constant.RECORD_STATUS_VALID + 
				" and d.customerId = " + customerId + 
//				" and d.isRoot = " + false + 
				" order by d.searchStr";
		List<Object[]> rets = daoFacade.getDepartmentDao().queryByHql(hql);
		
		Map<String, Map<Integer, OrgNode<com.lorent.common.tree.DepartmentBean, MemberBean>>> 
			temp = new HashMap<String, Map<Integer, OrgNode<com.lorent.common.tree.DepartmentBean, MemberBean>>>();
		int rootDeptId = -1;
		for(Object[] ret : rets){
			Integer userId = (Integer) ret[0];
			String username = (String)ret[1];
			String realName = (String)ret[2];
			String position = (String)ret[3];
			String lccAccount = (String)ret[4];
			Integer status = (Integer)ret[5];
			Integer deptId = (Integer)ret[6];
			String deptName = (String)ret[7];
			String searchStr = (String)ret[8];
			String sign = (String)ret[9];
			String myPic = (String)ret[10];
			String mobile = (String)ret[11];
			String email = (String)ret[12];
			String gender = (String)ret[13];
			Integer parentId = (Integer)ret[14];
			Integer isCustomPic = (Integer)ret[15];
			byte[] customPic = (byte[])ret[16];
			if(searchStr == null){
				searchStr = "root";
				rootDeptId = deptId;
			}
			
			Map<Integer, OrgNode<com.lorent.common.tree.DepartmentBean, MemberBean>> pnodes = null;
			if(temp.containsKey(searchStr)){
				pnodes = temp.get(searchStr);
			}else{
				pnodes = new HashMap<Integer, OrgNode<com.lorent.common.tree.DepartmentBean, MemberBean>>();
				temp.put(searchStr, pnodes);
			}
			OrgNode<com.lorent.common.tree.DepartmentBean, MemberBean> pnode = null;
			if(pnodes.containsKey(deptId)){
				pnode = pnodes.get(deptId);
			}else{
				pnode = new OrgNode<com.lorent.common.tree.DepartmentBean, MemberBean>();
				pnodes.put(deptId, pnode);
				com.lorent.common.tree.DepartmentBean dbean = new com.lorent.common.tree.DepartmentBean();
				dbean.setId(deptId);
				dbean.setName(deptName);
				dbean.setSearchStr(searchStr);
				dbean.setParentId(parentId);
				pnode.setOrganization(dbean);
			}
			
			if(userId != null && status == 1){
				MemberBean mbean = new MemberBean();
				mbean.setId(userId);
				mbean.setLccAccount(lccAccount);
				mbean.setPost(position);
				mbean.setRealName(realName);
				mbean.setUsername(username);
				mbean.setSign(sign);
				mbean.setDefaultImg(myPic);
				mbean.setEmail(email);
				mbean.setMobile(mobile);
				mbean.setDeptName(deptName);
				mbean.setDeptId(deptId);
				mbean.setGender(gender);
				mbean.setIsCustomPic(isCustomPic);
				mbean.setCustomPic(customPic);
				OrgNode<com.lorent.common.tree.DepartmentBean, MemberBean> mnode = new OrgNode<com.lorent.common.tree.DepartmentBean, MemberBean>();
				mnode.setMember(mbean);
				
				pnode.addChild(mnode);
			}

		}
		OrgNode<com.lorent.common.tree.DepartmentBean, MemberBean> root = temp.get("root").get(rootDeptId);
		OrgTree<com.lorent.common.tree.DepartmentBean, MemberBean> tree = new OrgTree<com.lorent.common.tree.DepartmentBean, MemberBean>();
		tree.setRoot(root);
		genderTree(root, temp);
		
		System.out.println(rets);
		return tree;
	}
	
	private void genderTree(OrgNode<com.lorent.common.tree.DepartmentBean, MemberBean> node,
			Map<String, Map<Integer, OrgNode<com.lorent.common.tree.DepartmentBean, MemberBean>>> map){
		Map<Integer, OrgNode<com.lorent.common.tree.DepartmentBean, MemberBean>> temp = null;
		if(node.getParent() == null){
			temp = map.get(node.getOrganization().getId() + "");
		}else{
			temp = map.get(node.getOrganization().getSearchStr() + "@" + node.getOrganization().getId());
		}
		
		if(temp != null){
			for(OrgNode<com.lorent.common.tree.DepartmentBean, MemberBean> org : temp.values()){
				node.addChild(org);
				if(org.getOrganization()!=null){
					genderTree(org, map);
				}
			}
		}
	}

}
