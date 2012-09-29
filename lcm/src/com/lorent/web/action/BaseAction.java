package com.lorent.web.action;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.criterion.DetachedCriteria;

import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.exception.NoDataYetException;
import com.lorent.service.Service;
import com.lorent.service.impl.ServiceCallBack;
import com.lorent.service.impl.ServiceFacade;
import com.lorent.util.Constant;
import com.lorent.util.PageUtil;
import com.lorent.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
public abstract class BaseAction<E,ID> extends ActionSupport implements ModelDriven<E>,ServletRequestAware , ServletContextAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(BaseAction.class); 
	//serveice类
	protected ServiceFacade serviceFacade;
	//pageMap，用于填充分页查询结果
	protected Map<String, Object>pageMap = new HashMap<String, Object>();
	//状态列表
	private Map<Integer, String>statusList; 
	//用于填充处理消息的Map
	protected Map<String, String>map;
	//错误消息
	protected String errorMsg = "";
	//操作成功的消息
	protected String successMsg = "";
	//是否在搜索模式
	protected boolean searchMode = false;
	//用于ajax操作完成后指定页面跳转的url
	protected String callBackUrl;
	//此字符串用于判断
	protected String isSearch = "";
//	用于响应ajax请求时返回json格式的字符串
	protected String result;
//	用于转换将Object转换为json对象的对象
	protected JSONObject jo;
//	是与否列表
	protected Map<Integer, String>yesNoList;
	protected Map<Integer, String>mixOrTransList;
//	字段验证信息
	protected String fieldValidateMsg = "";
	//升序还是降序
	protected String ascOrDesc = PageUtil.DESC;
	//排序属性
	protected String orderString = "id";
	
	protected HttpServletRequest request;
	protected HttpSession session;
	protected ServletContext application;

	
	protected Map<String, Boolean> buttonMap = new HashMap<String, Boolean>();
	{
		buttonMap.put("edit", true);
		buttonMap.put("delete", true);
		buttonMap.put("search", true);
		buttonMap.put("add", true);
		buttonMap.put("selectAll", true);
		buttonMap.put("stopConf", false);
		buttonMap.put("closeBilling", false);
		buttonMap.put("statistics", false);
		buttonMap.put("importUser", false);
	}
	/**
	 * 初始化pagemap
	 * @param currentPage
	 * @param uri
	 */
	protected void initSubPage(int currentPage,String uri,String orderByStr){
		pageMap.put(PageUtil.PAGEMAP_KEY_CURRENTPAGE, currentPage);
		pageMap.put(PageUtil.PAGEMAP_KEY_PAGEROW, PageUtil.PAGEROW);
		pageMap.put(PageUtil.PAGEMAP_KEY_PAGEURL, uri);
		if(orderByStr!=null&&!"".equals(orderByStr))
			pageMap.put(PageUtil.PAGEMAP_KEY_ORDERBY, orderByStr);
		pageMap.put(PageUtil.PAGEMAP_KEY_ORDERDESC,ascOrDesc);
		pageMap.put(PageUtil.PAGEMAP_KEY_ENABLELIKE, true);
	}
	/**
	 * 获取状态列表
	 * @return
	 */
	public Map<Integer, String> getStatusList() {
		if(statusList==null){
			statusList = new TreeMap<Integer, String>();
//			statusList.put(Constant.RECORD_DELETED, getText("page.record.status.delete"));
			statusList.put(Constant.RECORD_NEW, getText("page.record.status.notactivated"));
			statusList.put(Constant.RECORD_VALID, getText("page.record.status.activated"));
//			statusList.put(Constant.RECORD_EXPIRED, getText("page.record.status.stop"));
		}
		return statusList;
	}
	
	
	/**
	 * 将分页查询结果保存到pageMap中
	 * @param example 类型为泛型E，指定需分页显示的数据对象
	 * @param service Service接口，指定进行分页使用的service对象
	 */
	protected void setSubPageMap(E example,Service<E, ID>service,String orderByStr) {
		int currentPage = 1;
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		try {
			initSubPage(currentPage, request.getRequestURI(),orderByStr);
			service.getSubPageResult(example, pageMap);
			setErrorMsg("");
		} catch (NoDataYetException e) {
			setErrorMsg(getText("page.errormsg.readfail"));
			setSuccessMsg("");
		} catch (CustomSqlException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
			setSuccessMsg("");
			log.error("",e);
		} catch(Exception e){
			e.printStackTrace();
			setErrorMsg(getText("page.errormsg.readfail"));
			setSuccessMsg("");
			log.error("",e);
		}
	}
	
	public void setSubPageMap(String hql, Service<E, ID>service){
		int currentPage = 1;
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		try{
			initSubPage(currentPage, request.getRequestURI(),"");
			service.getSubPageResult(hql, pageMap);
			setErrorMsg("");
		}catch(Exception e){
			log.error("",e);
			e.printStackTrace();
			setErrorMsg(getText("page.errormsg.readfail"));
			setSuccessMsg("");
		}
	}
	
	public void setSubPageMap(String hql, Service<E, ID>service,List params){
		int currentPage = 1;
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		try{
			initSubPage(currentPage, request.getRequestURI(),"");
			service.getSubPageResult(hql, pageMap,params);
			setErrorMsg("");
		}catch(Exception e){
			log.error("",e);
			e.printStackTrace();
			setErrorMsg(getText("page.errormsg.readfail"));
			setSuccessMsg("");
		}
	}
	
	/**
	 * 将分页查询结果保存到pageMap中
	 * 查找与criteriaExample匹配的记录
	 * 使用QBC查询
	 * 不支持根据criteriaExample对象的集合属性查询
	 * @param pageMap
	 * @param service
	 * @param orderStr
	 * @param criteriaExample
	 */
	protected void setSubPageMap(Service<E, ID>service,String orderStr,E criteriaExample) {
		int currentPage = 1;
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		try {
			initSubPage(currentPage, request.getRequestURI(),orderStr);
			service.getSubPageResult(pageMap,criteriaExample);
			setErrorMsg("");
		} catch (CustomSqlException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
			setSuccessMsg("");
			log.error("",e);
		} catch(Exception e){
			log.error("",e);
			e.printStackTrace();
			setErrorMsg(getText("page.errormsg.readfail"));
			setSuccessMsg("");
		}
	}
	/**
	 * 将分页查询结果保存到pageMap中
	 * QBC 采用DetachedCriteria作为查询条件集合
	 * @param criteria
	 * @param service
	 * @param orderStr
	 */
	protected void setSubPageMap(DetachedCriteria criteria,Service<E, ID>service,String orderStr) {
		int currentPage = 1;
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		try {
			initSubPage(currentPage, request.getRequestURI(),orderStr);
			service.getSubPageResult(criteria,pageMap);
			setErrorMsg("");
		} catch (CustomSqlException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
			setSuccessMsg("");
			log.error("",e);
		} catch(Exception e){
			e.printStackTrace();
			setErrorMsg(getText("page.errormsg.readfail"));
			setSuccessMsg("");
			log.error("",e);
		}
	}
	
	/**
	 * 获取选定的ID
	 * @return
	 */
	protected Integer[] getSelectedIds()throws ArgsException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[]ids = request.getParameterValues("choose");
		if(ids==null||ids.length==0||"".equals(ids[0])){
			log.error("", new ArgsException("args.chooserequired"));
			throw new ArgsException("args.chooserequired");
		}
		return StringUtil.toInteger(ids);
	}
	
	protected String getPara(String key){
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getParameter(key);
	}
	
	protected Integer[] getSelectedIdsNoException(){
		Integer[] ids = null;
		try{
			ids = this.getSelectedIds();
		}catch(Exception ex){
			ids = new Integer[0];
		}
		return ids;
	}
	
	/*===============================getters和setters===============================*/
	public Map<Integer, String> getYesNoList() {
		this.yesNoList = new TreeMap<Integer, String>();
		yesNoList.put(Constant.YES, getText("page.display.yes"));
		yesNoList.put(Constant.NO, getText("page.display.no"));
		return yesNoList;
	}
	public Map<Integer, String> getMixOrTransList() {
		this.mixOrTransList = new TreeMap<Integer, String>();
		mixOrTransList.put(Constant.MIX, getText("page.conf.mix"));
		mixOrTransList.put(Constant.NOTMIX, getText("page.conf.notmix"));
		return mixOrTransList;
	}
	
	public void setMixOrTransList(Map<Integer, String> mixOrTransList) {
		this.mixOrTransList = mixOrTransList;
	}
	protected void setSortField(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String sortby = request.getParameter("sortby");
		if(sortby==null){
			orderString = "id";
		}else{
			if(sortby.equals(orderString)){//点击同一标题则升序降序切换
				if(PageUtil.ASC.equals(ascOrDesc)){
					ascOrDesc = PageUtil.DESC;
				}else if(PageUtil.DESC.equals(ascOrDesc)){
					ascOrDesc = PageUtil.ASC;
				}
			}else{
				ascOrDesc = PageUtil.ASC;
			}
			orderString = sortby;
		}
	}
	
	public void searchSubPageMap(ServiceCallBack service){
		int currentPage = 1;
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		try{
			initSubPage(currentPage, request.getRequestURI(),"");
//			serviceFacade.getConferenceNewService().getSearchSubPageMapResult(pageMap,params);
			service.searchCallBack();
			setErrorMsg("");
		}catch(Exception e){
			log.error("",e);
			e.printStackTrace();
			setErrorMsg(getText("page.errormsg.readfail"));
			setSuccessMsg("");
		}
	}
	
	protected boolean isDelete(){
		boolean flag = false;
		HttpServletRequest request = ServletActionContext.getRequest();
		String delStr = request.getParameter("isdel");
		if(delStr!=null && "yes".equalsIgnoreCase(delStr)){
			flag = true;
		}
		return flag;
	}
	
	public void setYesNoList(Map<Integer, String> yesNoList) {
		this.yesNoList = yesNoList;
	}
	
	public Map<String, Object> getPageMap() {
		return pageMap;
	}
	public void setPageMap(Map<String, Object> pageMap) {
		this.pageMap = pageMap;
	}
	public ServiceFacade getServiceFacade() {
		return serviceFacade;
	}
	public void setServiceFacade(ServiceFacade serviceFacade) {
		this.serviceFacade = serviceFacade;
	}
	
	public long getCurrentTime(){
		return System.currentTimeMillis();
	}
	
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public boolean getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(boolean searchMode) {
		this.searchMode = searchMode;
	}
	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}
	
	public String getIsSearch() {
		return isSearch;
	}
	public void setIsSearch(String isSearch) {
		this.isSearch = isSearch;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public JSONObject getJo() {
		return jo;
	}
	public void setJo(JSONObject jo) {
		this.jo = jo;
	}
	public Map<String, Boolean> getButtonMap() {
		return buttonMap;
	}
	public void setButtonMap(Map<String, Boolean> buttonMap) {
		this.buttonMap = buttonMap;
	}
	public String getCallBackUrl() {
		return callBackUrl;
	}
	
	 @Override
	 public void setServletRequest(HttpServletRequest arg0) {
         this.request = arg0;
         if(request!=null){
        	 this.session = this.request.getSession();
         }
	 }
	 @Override
	 public void setServletContext(ServletContext arg0) {
	 	this.application = arg0;
	 }
	 
	 
}
