package com.lorent.web.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.lorent.model.SystemParamBean;

public class SystemParasAction extends BaseAction{

	private static final Logger log = Logger.getLogger(SystemParasAction.class); 
	
	private static final long serialVersionUID = 1L;

	private List<SystemParamBean> paralist;
	private SystemParamBean para;
	private SystemParamBean oldPara;

	@Override
	public Object getModel() {
		return para;
	}
	
	public String toList()throws Exception{
		buttonMap.put("search", false);
		paralist = serviceFacade.getStaticService().getList(SystemParamBean.class);
		return SUCCESS;
	}

	public String toEdit()throws Exception{
		para = serviceFacade.getStaticService().get(getSelectedIds()[0], SystemParamBean.class);
		oldPara = clonePara(para);
		return SUCCESS;
	}
	
	public String toAdd()throws Exception{
		para = new SystemParamBean();
		return SUCCESS;
	}
	
	public String doAddorEdit()throws Exception{
		if(para.getId() == null){//add
			log.info("add system paras : module = " + para.getModule() + " & key = " + para.getKey() + " & value = " + para.getValue());
			serviceFacade.getStaticService().save(para);
		}else{//modify
			log.info("modify system");
			log.info("old paras : module = " + oldPara.getModule() + " & key = " + oldPara.getKey() + " & value = " + oldPara.getValue());
			log.info("new paras : module = " + para.getModule() + " & key = " + para.getKey() + " & value = " + para.getValue());
			serviceFacade.getStaticService().update(para);
		}
		callBackUrl = "app/systemParasAction_toList_parasList.action";
		return SUCCESS;
	}
	
	public String delete()throws Exception{
		SystemParamBean temp = serviceFacade.getStaticService().get(getSelectedIds()[0], SystemParamBean.class);
		log.info("delete system paras : module = " + temp.getModule() + " & key = " + temp.getKey() + " & value = " + temp.getValue());
		serviceFacade.getStaticService().delete(temp);
		return toList();
	}
	
	
	private SystemParamBean clonePara(SystemParamBean p){
		if(p==null){
			return null;
		}
		SystemParamBean temp = new SystemParamBean();
		temp.setKey(p.getKey());
		temp.setValue(p.getValue());
		temp.setModule(p.getModule());
		temp.setDescription(p.getDescription());
		return temp;
	}
	
	//============================setter/getter============================

	public List<SystemParamBean> getParalist() {
		return paralist;
	}
	
	public void setParalist(List<SystemParamBean> paralist) {
		this.paralist = paralist;
	}

	public SystemParamBean getPara() {
		return para;
	}

	public void setPara(SystemParamBean para) {
		this.para = para;
	}
	
	


}
