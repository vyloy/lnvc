package com.lorent.service;

import com.lorent.dao.RatesDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.RatesBean;

public interface RatesService extends IGenericService<RatesDao,RatesBean,Integer> {
	Integer createRates(RatesBean rates)throws CustomSqlException,ArgsException;
	void renewRates(RatesBean rates)throws CustomSqlException,ArgsException;
//	void removeRates(Integer id)throws CustomSqlException,ArgsException;
//	void removeRates(Integer[]ids)throws CustomSqlException,ArgsException;
}
