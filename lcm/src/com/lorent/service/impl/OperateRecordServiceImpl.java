package com.lorent.service.impl;
import java.sql.Timestamp;

import com.lorent.dao.OperateRecordDao;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.OperateRecord;
import com.lorent.model.UserBean;
import com.lorent.service.OperateRecordService;
import com.lorent.util.ThreadLocaleUtil;

public class OperateRecordServiceImpl extends GenericServiceImpl<OperateRecordDao,OperateRecord,Integer> implements OperateRecordService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void record(OperateRecord record) throws CustomSqlException {
		this.save(record);
		
	}

	public void record(String opdisc) throws CustomSqlException{
		OperateRecord or = new OperateRecord();
		or.setOpdisc(opdisc);
		or.setOptime(new Timestamp(System.currentTimeMillis()));
		UserBean user = ThreadLocaleUtil.getUser();
		if(user==null)return;
		or.setUserId(user.getId());
		or.setUserName(user.getUsername());
		record(or);	
	}

}
