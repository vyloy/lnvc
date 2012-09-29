package com.lorent.dao;

import com.lorent.dao.IGenericDao;
import com.lorent.model.AclSid;
/**
 * 用于操作acl_sid表数据的dao接口
 * @author gary
 * @version 1.0
 * @since 2010-09-15
 */
public interface IAclSidDao extends IGenericDao<AclSid, Long>{
	/**
	 * 获取acl_sid记录
	 * @param sid
	 * @param principal
	 * @return
	 */
    public AclSid findBySid(String sid, boolean principal);
}