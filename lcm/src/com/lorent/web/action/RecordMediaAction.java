package com.lorent.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lorent.dto.RecordBean;
import com.lorent.model.SipConfBean;

public class RecordMediaAction extends BaseAction<SipConfBean, Integer>{
	private static final Logger log = Logger.getLogger(RatesAction.class); 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SipConfBean sipConfBean;

	public SipConfBean getSipConfBean() {
		return sipConfBean;
	}

	public void setSipConfBean(SipConfBean sipConfBean) {
		this.sipConfBean = sipConfBean;
	}

	@Override
	public SipConfBean getModel() {
		return  sipConfBean;
	}
	
	private Map<String, String> lccnos;
	private String fromlcc;
	private String tolcc;
	private Date searchDate;
	private List<RecordBean> records;

	public String toRecordList() throws Exception {
		try {
			List<SipConfBean> users = serviceFacade.getSipConfService().getAll();
			lccnos = new HashMap<String, String>();
			if(null!=users)
			for (SipConfBean user : users) {
				lccnos.put(user.getName(), user.getName());
			}
			records = serviceFacade.getSipConfService().getRecordByCondition(null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("",e);
			throw e;
		}
		return SUCCESS;
	}

	public String searchRecord() throws Exception {
		log.info("fromlcc=" + fromlcc + "&tolcc=" + tolcc + "&searchDate="
				+ searchDate);
		records = serviceFacade.getSipConfService().getRecordByCondition(
				fromlcc.trim(), tolcc.trim(), searchDate);
		
		return SUCCESS;
	}

	// ===================getter setter=============================

	public Map<String, String> getLccnos() {
		return lccnos;
	}

	public void setLccnos(Map<String, String> lccnos) {
		this.lccnos = lccnos;
	}

	public List<RecordBean> getRecords() {
		return records;
	}

	public void setRecords(List<RecordBean> records) {
		this.records = records;
	}

	public String getFromlcc() {
		return fromlcc;
	}

	public void setFromlcc(String fromlcc) {
		this.fromlcc = fromlcc;
	}

	public String getTolcc() {
		return tolcc;
	}

	public void setTolcc(String tolcc) {
		this.tolcc = tolcc;
	}

	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

}
