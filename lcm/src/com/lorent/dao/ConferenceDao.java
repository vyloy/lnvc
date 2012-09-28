package com.lorent.dao;

import java.util.List;

import com.lorent.exception.CustomSqlException;
import com.lorent.model.ConferenceBean;

public interface ConferenceDao extends IGenericDao<ConferenceBean,Integer> {
	List<ConferenceBean>getUnTriggerConfs()throws CustomSqlException;
}
