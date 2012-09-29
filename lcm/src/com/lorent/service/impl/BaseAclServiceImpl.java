package com.lorent.service.impl;

import org.springframework.security.acls.domain.AclAuthorizationStrategy;
import org.springframework.security.acls.domain.AuditLogger;
import com.lorent.dao.impl.DaoFacade;;

public class BaseAclServiceImpl {
	protected DaoFacade daoFacade;
	protected AuditLogger auditLogger;
	protected AclAuthorizationStrategy aclAuthorizationStrategy;
	
	public AuditLogger getAuditLogger() {
		return auditLogger;
	}
	public void setAuditLogger(AuditLogger auditLogger) {
		this.auditLogger = auditLogger;
	}
	public DaoFacade getDaoFacade() {
		return daoFacade;
	}
	public void setDaoFacade(DaoFacade daoFacade) {
		this.daoFacade = daoFacade;
	}
	public AclAuthorizationStrategy getAclAuthorizationStrategy() {
		return aclAuthorizationStrategy;
	}
	public void setAclAuthorizationStrategy(
			AclAuthorizationStrategy aclAuthorizationStrategy) {
		this.aclAuthorizationStrategy = aclAuthorizationStrategy;
	}
}
