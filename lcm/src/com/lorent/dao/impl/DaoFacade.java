package com.lorent.dao.impl;
import java.io.Serializable;

import com.lorent.dao.AuthorityDao;
import com.lorent.dao.BillingDao;
import com.lorent.dao.ConfRoleAuthorityDao;
import com.lorent.dao.ConfUserAuthorityDao;
import com.lorent.dao.ConfUserRoleDao;
import com.lorent.dao.ConferenceDao;
import com.lorent.dao.ConferenceNewDao;
import com.lorent.dao.ConferenceNoDao;
import com.lorent.dao.ConferenceRoleDao;
import com.lorent.dao.ConferenceTypeDao;
import com.lorent.dao.ConferenceTypeRoleDao;
import com.lorent.dao.ConferenceUserDao;
import com.lorent.dao.CronConferenceDao;
import com.lorent.dao.CustomerDao;
import com.lorent.dao.DepartmentDao;
import com.lorent.dao.IAclClassDao;
import com.lorent.dao.IAclEntryDao;
import com.lorent.dao.IAclObjectIdentityDao;
import com.lorent.dao.IAclSidDao;
import com.lorent.dao.McuMixerDao;
import com.lorent.dao.McuServerDao;
import com.lorent.dao.MediaDao;
import com.lorent.dao.OperateRecordDao;
import com.lorent.dao.RatesDao;
import com.lorent.dao.RoleDao;
import com.lorent.dao.SipConfDao;
import com.lorent.dao.SysServerConfigDao;
import com.lorent.dao.UserDao;

public class DaoFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private RoleDao roleDao;
	private UserDao userDao;
	private IAclClassDao aclClassDao;
	private IAclEntryDao aclEntryDao;
	private IAclObjectIdentityDao aclObjectIdentityDao;
	private IAclSidDao aclSidDao;
	private McuServerDao mcuServerDao;
	private McuMixerDao mcuMixerDao;
	private CustomerDao customerDao;
	private StaticDao staticDao;
	private ConferenceDao conferenceDao;
	private OperateRecordDao operateRecordDao;
	private BillingDao billingDao;
	private RatesDao ratesDao;
	private DepartmentDao departmentDao;
	private CronConferenceDao cronConferenceDao;
	private SipConfDao sipConfDao;
	private ConferenceNoDao conferenceNoDao;
	private MediaDao mediaDao;
	private MonitorStaticDao monitorStaticDao;
	private ConferenceTypeDao conferenceTypeDao;
	private AuthorityDao authorityDao;
	private ConferenceRoleDao conferenceRoleDao;
	private ConfRoleAuthorityDao confRoleAuthorityDao;
	private ConferenceTypeRoleDao conferenceTypeRoleDao;
	private ConferenceNewDao conferenceNewDao;
	private ConferenceUserDao conferenceUserDao;
	private ConfUserRoleDao confUserRoleDao;
	private ConfUserAuthorityDao confUserAuthorityDao;
	
	private SysServerConfigDao sysServerConfigDao;
	
	public SysServerConfigDao getSysServerConfigDao() {
		return sysServerConfigDao;
	}
	public void setSysServerConfigDao(SysServerConfigDao sysServerConfigDao) {
		this.sysServerConfigDao = sysServerConfigDao;
	}
	public ConferenceTypeRoleDao getConferenceTypeRoleDao() {
		return conferenceTypeRoleDao;
	}
	public void setConferenceTypeRoleDao(ConferenceTypeRoleDao conferenceTypeRoleDao) {
		this.conferenceTypeRoleDao = conferenceTypeRoleDao;
	}
	public MonitorStaticDao getMonitorStaticDao() {
		return monitorStaticDao;
	}
	public void setMonitorStaticDao(MonitorStaticDao monitorStaticDao) {
		this.monitorStaticDao = monitorStaticDao;
	}
	public ConferenceNoDao getConferenceNoDao() {
		return conferenceNoDao;
	}
	public void setConferenceNoDao(ConferenceNoDao conferenceNoDao) {
		this.conferenceNoDao = conferenceNoDao;
	}
	public CustomerDao getCustomerDao() {
		return customerDao;
	}
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	public McuMixerDao getMcuMixerDao() {
		return mcuMixerDao;
	}
	public void setMcuMixerDao(McuMixerDao mcuMixerDao) {
		this.mcuMixerDao = mcuMixerDao;
	}
	public McuServerDao getMcuServerDao() {
		return mcuServerDao;
	}
	public void setMcuServerDao(McuServerDao mcuServerDao) {
		this.mcuServerDao = mcuServerDao;
	}
	public RoleDao getRoleDao() {
		return roleDao;
	}
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public IAclClassDao getAclClassDao() {
		return aclClassDao;
	}
	public void setAclClassDao(IAclClassDao aclClassDao) {
		this.aclClassDao = aclClassDao;
	}
	public IAclEntryDao getAclEntryDao() {
		return aclEntryDao;
	}
	public void setAclEntryDao(IAclEntryDao aclEntryDao) {
		this.aclEntryDao = aclEntryDao;
	}
	public IAclObjectIdentityDao getAclObjectIdentityDao() {
		return aclObjectIdentityDao;
	}
	public void setAclObjectIdentityDao(IAclObjectIdentityDao aclObjectIdentityDao) {
		this.aclObjectIdentityDao = aclObjectIdentityDao;
	}
	public IAclSidDao getAclSidDao() {
		return aclSidDao;
	}
	public void setAclSidDao(IAclSidDao aclSidDao) {
		this.aclSidDao = aclSidDao;
	}
	public StaticDao getStaticDao() {
		return staticDao;
	}
	public void setStaticDao(StaticDao staticDao) {
		this.staticDao = staticDao;
	}
	public ConferenceDao getConferenceDao() {
		return conferenceDao;
	}
	public void setConferenceDao(ConferenceDao conferenceDao) {
		this.conferenceDao = conferenceDao;
	}
	public OperateRecordDao getOperateRecordDao() {
		return operateRecordDao;
	}
	public void setOperateRecordDao(OperateRecordDao operateRecordDao) {
		this.operateRecordDao = operateRecordDao;
	}
	public BillingDao getBillingDao() {
		return billingDao;
	}
	public void setBillingDao(BillingDao billingDao) {
		this.billingDao = billingDao;
	}
	public RatesDao getRatesDao() {
		return ratesDao;
	}
	public void setRatesDao(RatesDao ratesDao) {
		this.ratesDao = ratesDao;
	}
	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	public CronConferenceDao getCronConferenceDao() {
		return cronConferenceDao;
	}
	public void setCronConferenceDao(CronConferenceDao cronConferenceDao) {
		this.cronConferenceDao = cronConferenceDao;
	}
	public SipConfDao getSipConfDao() {
		return sipConfDao;
	}
	public void setSipConfDao(SipConfDao sipConfDao) {
		this.sipConfDao = sipConfDao;
	}
	public MediaDao getMediaDao() {
		return mediaDao;
	}
	public void setMediaDao(MediaDao mediaDao) {
		this.mediaDao = mediaDao;
	}
	public ConferenceTypeDao getConferenceTypeDao() {
		return conferenceTypeDao;
	}
	public void setConferenceTypeDao(ConferenceTypeDao conferenceTypeDao) {
		this.conferenceTypeDao = conferenceTypeDao;
	}
	public AuthorityDao getAuthorityDao() {
		return authorityDao;
	}
	public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}
	public ConferenceRoleDao getConferenceRoleDao() {
		return conferenceRoleDao;
	}
	public void setConferenceRoleDao(ConferenceRoleDao conferenceRoleDao) {
		this.conferenceRoleDao = conferenceRoleDao;
	}
	public ConfRoleAuthorityDao getConfRoleAuthorityDao() {
		return confRoleAuthorityDao;
	}
	public void setConfRoleAuthorityDao(ConfRoleAuthorityDao confRoleAuthorityDao) {
		this.confRoleAuthorityDao = confRoleAuthorityDao;
	}
	public ConferenceNewDao getConferenceNewDao() {
		return conferenceNewDao;
	}
	public void setConferenceNewDao(ConferenceNewDao conferenceNewDao) {
		this.conferenceNewDao = conferenceNewDao;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public ConferenceUserDao getConferenceUserDao() {
		return conferenceUserDao;
	}
	public void setConferenceUserDao(ConferenceUserDao conferenceUserDao) {
		this.conferenceUserDao = conferenceUserDao;
	}
	public ConfUserRoleDao getConfUserRoleDao() {
		return confUserRoleDao;
	}
	public void setConfUserRoleDao(ConfUserRoleDao confUserRoleDao) {
		this.confUserRoleDao = confUserRoleDao;
	}
	public ConfUserAuthorityDao getConfUserAuthorityDao() {
		return confUserAuthorityDao;
	}
	public void setConfUserAuthorityDao(ConfUserAuthorityDao confUserAuthorityDao) {
		this.confUserAuthorityDao = confUserAuthorityDao;
	}
}
