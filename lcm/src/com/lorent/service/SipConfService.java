package com.lorent.service;

import java.util.Date;
import java.util.List;

import com.lorent.dao.SipConfDao;
import com.lorent.dto.RecordBean;
import com.lorent.model.SipConfBean;

public interface SipConfService extends IGenericService<SipConfDao, SipConfBean, Integer>{
	public List<SipConfBean> getAllUser();
	public List<RecordBean> getRecordByCondition(String fromlcc, String tolcc,Date date) throws Exception;
}
