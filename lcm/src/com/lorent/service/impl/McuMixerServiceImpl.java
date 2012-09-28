package com.lorent.service.impl;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.lorent.dao.McuMixerDao;
import com.lorent.exception.ArgsException;
import com.lorent.model.McuMixerBean;
import com.lorent.service.McuMixerService;
import com.lorent.util.McuUtil;

public class McuMixerServiceImpl extends GenericServiceImpl<McuMixerDao, McuMixerBean, Integer>
	implements McuMixerService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void createMixer(McuMixerBean mixer) throws Exception {
		if(getByExample(mixer)!=null)throw new ArgsException("arg.recordexists");
		mixer.setMixerIp(mixer.getServer().getServerIp());
		save(mixer);
	}

	public void removeMixer(McuMixerBean mixer) throws Exception {
		if(getByExample(mixer)==null)throw new ArgsException("sql.recordnotexists");
		delete(mixer);
	}

//	public void restoreMixerToMcu(String mcuIP) throws Exception {
//		DetachedCriteria criteria = DetachedCriteria.forClass(McuMixerBean.class);
//		criteria.add(Restrictions.eq("mixerIp", mcuIP));
//		List<McuMixerBean>mixers = daoFacade.getMcuMixerDao().getByCriteria(criteria);
//		if(mixers==null||mixers.size()==0)
//			return;
//		for(McuMixerBean mixer:mixers){
////			McuUtil.createMixerToMcu(server)
//		}
//	}
	
}
