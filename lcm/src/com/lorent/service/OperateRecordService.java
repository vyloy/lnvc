package com.lorent.service;
import com.lorent.dao.OperateRecordDao;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.OperateRecord;

public interface OperateRecordService extends IGenericService<OperateRecordDao,OperateRecord, Integer> {
	public void record(OperateRecord record)throws CustomSqlException;
	public void record(String opdisc)throws CustomSqlException;
}
