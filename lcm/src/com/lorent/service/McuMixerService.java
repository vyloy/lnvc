package com.lorent.service;

import com.lorent.dao.McuMixerDao;
import com.lorent.model.McuMixerBean;

public interface McuMixerService extends IGenericService<McuMixerDao, McuMixerBean, Integer> {
	void createMixer(McuMixerBean mixer)throws Exception;
	void removeMixer(McuMixerBean mixer)throws Exception;
//	void restoreMixerToMcu(String mcuIP)throws Exception;
}
