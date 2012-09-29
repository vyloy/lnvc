package com.lorent.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import net.sf.json.JSONObject;

import com.lorent.dto.TreeBean;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.DepartmentBean;
import com.lorent.model.UserBean;
import com.lorent.util.ClassUtil;
import com.lorent.util.Constant;
import com.lorent.util.PageUtil;
import com.lorent.util.ThreadLocaleUtil;

public class DepartmentAction extends BaseAction<DepartmentBean,Integer> {

	private static final Logger log = Logger.getLogger(DepartmentAction.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DepartmentBean department;
	private DepartmentBean searchModel;
	private List<DepartmentBean>parentList;
	/**
	 * 跳转到部门列表页面
	 * @return
	 */
	public String toDepList()throws Exception {
		log.info("com.lorent.web.action.DepartmentAction toDepList() -- 跳转到部门列表页面 ");
		searchMode = false;
		department = new DepartmentBean();
		UserBean user = ThreadLocaleUtil.getUser(); 
		department.setCustomerId(user.getCustomer().getId());
		department.setIsRoot(false);
		department.setStatus(Constant.RECORD_STATUS_VALID);
		setSubPageMap(department, serviceFacade.getDepartmentService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/departmentAction_toDepList_departmentlist.action");
		return SUCCESS;
	}
	
//	public String toDepList2()throws Exception {
//		searchMode = false;
//		department = new DepartmentBean();
//		UserBean user = ThreadLocaleUtil.getUser(); 
//		department.setCustomerId(user.getCustomer().getId());
//		department.setStatus(Constant.RECORD_STATUS_VALID);
//		Map<String, Criterion> map = new HashMap<String, Criterion>();
//		ClassUtil.parseExampleToCriteriaMap(map, department, null);
//		DetachedCriteria dc = ClassUtil.createCriteriaByMap(map, DepartmentBean.class);
//		List<DepartmentBean> depts = serviceFacade.getDepartmentService().getByCriteria(dc);
//		List<TreeBean> tree = serviceFacade.getDepartmentService().parseTreeBean(depts);
//		pageMap.put("tree", tree);
//		return SUCCESS;
//	}
	
	public String sortDept()throws Exception {
		setSortField();
		if(searchMode){
			return searchDepartment();
		}
		return toDepList();
	}
	
	/**
	 * 添加部门信息
	 * @return
	 */
	public String addDepartment()throws Exception {
		log.info("com.lorent.web.action.DepartmentAction addDepartment() -- 添加部门信息 ");
		department = new DepartmentBean();
		department.setCustomerId(ThreadLocaleUtil.getUser().getCustomer().getId());
		initParentList();
		return SUCCESS;
	}
	/**
	 * 编辑部门信息
	 * @return
	 * @throws Exception
	 */
	public String toEditDepartment()throws Exception {
		log.info("com.lorent.web.action.DepartmentAction toEditDepartment() -- 编辑部门信息 ");
		Integer[]ids = getSelectedIds();
		log.info("编辑部门："+ids);
		if(ids==null)throw new ArgsException("args.chooserequired");
		department = serviceFacade.getDepartmentService().get(ids[0]);
		initParentList();
		return SUCCESS;
	}
	/**
	 * 将用户编辑的信息保存
	 * @return
	 */
	public String doEditDepartment()throws Exception {
		String msg = "";
		if (department.getId()==null) {
			msg = "添加组织结构";
			log.info(msg);
			serviceFacade.getDepartmentService().createDepartment(department);
		}else {
			msg = "修改组织结构" + department.getId();
			log.info(msg);
			serviceFacade.getDepartmentService().renewDepartment(department);
		}
		log.info("部门名称:" + department.getDepartmentName());
		log.info("部门详细描述:" + department.getDepartmentDesc());
		log.info(msg + "成功");
		initCallBackUrl();
		return SUCCESS;
	}
	/**
	 * 删除部门信息
	 * @return
	 */
	public String deleteDepartment()throws Exception {
		Integer[]ids = getSelectedIds();
		log.info("删除组织结构：" + ids);
		if(ids!=null){
			serviceFacade.getDepartmentService().removeDepartment(ids);
		}
		log.info("删除组织结构：" + ids + "成功");
		if(getSearchMode())return searchDepartment();
		return toDepList();
	}
	private void initCallBackUrl(){
		if(searchMode){
			callBackUrl = "app/departmentAction_searchDepartment_departmentlist.action";
		}else{
			callBackUrl = "app/departmentAction_toDepList_departmentlist.action";
		}
	}
	
	public String getCallBackUrl() {
		return callBackUrl;
	}
	
	private void initParentList()throws CustomSqlException {
//		DepartmentBean example = new DepartmentBean();
//		example.setCustomerId(ThreadLocaleUtil.getUser().getCustomer().getId());
//		example.setStatus(Constant.RECORD_STATUS_VALID);
//		parentList = serviceFacade.getDepartmentService().getByExample(example);
		DetachedCriteria dc = DetachedCriteria.forClass(DepartmentBean.class);
		dc.add(Restrictions.eq("customerId", ThreadLocaleUtil.getUser().getCustomer().getId()))
		.add(Restrictions.eq("status", Constant.RECORD_STATUS_VALID));
		if(department.getId()!=null){
			dc.add(Restrictions.not(Restrictions.eq("id", department.getId())));
		}
		
		parentList = serviceFacade.getDepartmentService().getByCriteria(dc);
		if(parentList==null)
			parentList = new ArrayList<DepartmentBean>();
	}
	
	public String searchDepartment() {
		if(!"".equals(getIsSearch())){
			searchModel = department;
			setIsSearch("");
			searchMode = true;
		}
		searchModel.setStatus(Constant.RECORD_STATUS_VALID);
		searchModel.setCustomerId(ThreadLocaleUtil.getUser().getCustomer().getId());
		setSubPageMap(serviceFacade.getDepartmentService(),orderString, searchModel);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/departmentAction_searchDepartment_departmentlist.action");
		return SUCCESS;
	}
	
	/*======================================= setters and getters ====================================*/
	public DepartmentBean getModel() {
		return department;
	}

	public DepartmentBean getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentBean department) {
		this.department = department;
	}
	public List<DepartmentBean> getParentList() {
		return parentList;
	}
	public void setParentList(List<DepartmentBean> parentList) {
		this.parentList = parentList;
	}

}
