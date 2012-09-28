package com.lorent.service.impl;
import com.lorent.dao.RatesDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.RatesBean;
import com.lorent.service.RatesService;
import com.lorent.util.Constant;

public class RatesServiceImpl extends GenericServiceImpl<RatesDao,RatesBean,Integer> implements
		RatesService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer createRates(RatesBean rates) throws CustomSqlException, ArgsException {
		RatesBean example = new RatesBean();
		example.setRatesName(rates.getRatesName());
		if(daoFacade.getRatesDao().getByExample(example)!=null){
			throw new ArgsException("arg.recordexists");
		}
		rates.setStatus(Constant.RECORD_STATUS_VALID);
		return daoFacade.getRatesDao().save(rates);
	}

	public void renewRates(RatesBean rates) throws CustomSqlException, ArgsException {
		daoFacade.getRatesDao().update(rates);
	}

//	public void removeRates(Integer id) throws CustomSqlException, ArgsException {
//		
//	}
//
//	public void removeRates(Integer[] ids) throws CustomSqlException, ArgsException {
//		// TODO Auto-generated method stub
//		
//	}

}
