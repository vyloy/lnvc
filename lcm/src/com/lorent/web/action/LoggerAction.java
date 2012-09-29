package com.lorent.web.action;

import com.lorent.model.OperateRecord;
import com.lorent.util.PageUtil;

public class LoggerAction extends BaseAction<OperateRecord, Integer>{

	private static final long serialVersionUID = 1L;
	
	private OperateRecord record;
	private OperateRecord searchModel;
	
	public OperateRecord getModel() {
		return record;
	}
	
	public String toLoggerList(){
		searchMode = false;
 		buttonMap.put("edit", false);
		buttonMap.put("add", false);
		buttonMap.put("delete", false);
		record = new OperateRecord();
		searchModel = new OperateRecord();
		setSubPageMap(record, serviceFacade.getOperateRecordService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/loggerAction_toLoggerList_loggerlist.action");
		return SUCCESS;
	}
	
	public String sortLogger(){
		setSortField();
		if(searchMode){
			return searchLogger();
		}
		return toLoggerList();
	}
	
	public String searchLogger(){
		searchMode = true;
		setSubPageMap(searchModel, serviceFacade.getOperateRecordService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/loggerAction_searchLogger_loggerlist.action");
		return SUCCESS;
	}

	public OperateRecord getRecord() {
		return record;
	}

	public void setRecord(OperateRecord record) {
		this.record = record;
	}

	
	public OperateRecord getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(OperateRecord searchModel) {
		this.searchModel = searchModel;
	}

}
