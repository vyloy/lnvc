package com.lorent.service.impl;

import java.util.List;

import com.lorent.dao.impl.DaoFacade;
import com.lorent.model.MonitorNetBean;

public class MonitorService {
	
	private DaoFacade daoFacade;
	
	public List<MonitorNetBean> getAllNetData(){
		List<MonitorNetBean> list = daoFacade.getMonitorStaticDao().getList(MonitorNetBean.class);
		return list;
	}

	public void setDaoFacade(DaoFacade daoFacade) {
		this.daoFacade = daoFacade;
	}
}
