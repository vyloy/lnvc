package com.lorent.service.impl;
import org.springframework.security.Authentication;
import org.springframework.security.acls.AccessControlEntry;
import org.springframework.security.acls.Acl;
import org.springframework.security.acls.AlreadyExistsException;
import org.springframework.security.acls.ChildrenExistException;
import org.springframework.security.acls.MutableAcl;
import org.springframework.security.acls.NotFoundException;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.domain.AccessControlEntryImpl;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.objectidentity.ObjectIdentityImpl;
import org.springframework.security.acls.sid.GrantedAuthoritySid;
import org.springframework.security.acls.sid.PrincipalSid;
import org.springframework.security.acls.sid.Sid;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.AbstractAclModel;
import com.lorent.model.AclClass;
import com.lorent.model.AclEntry;
import com.lorent.model.AclObjectIdentity;
import com.lorent.model.AclSid;
import com.lorent.service.ISecurityAclService;
/**
 * 用于实现acl信息持久化的services实现类
 * @author gary
 * @version 1.0
 * @since 2010-09-15
 */
public class MutableAclServiceImpl extends AclServiceImpl implements
		ISecurityAclService {
	
	public MutableAcl createAcl(ObjectIdentity objectIdentity)
			throws AlreadyExistsException {
		AclObjectIdentity aclObjectIdentity = getAclObjectIdentity(objectIdentity);
		if(aclObjectIdentity!=null){
			 throw new AlreadyExistsException("Object identity '" + aclObjectIdentity + "' already exists");
		}
		AclClass aclClass = daoFacade.getAclClassDao().findByClass(objectIdentity.getJavaType().toString());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PrincipalSid sid = new PrincipalSid(authentication);
		AclSid aclSid = getOrCreateAclSid(sid,true);
		aclObjectIdentity = new AclObjectIdentity();
		aclObjectIdentity.setAclClass(aclClass);
		aclObjectIdentity.setAclSid(aclSid);
		aclObjectIdentity.setObjectId(Long.parseLong(objectIdentity.getIdentifier().toString()));
		aclObjectIdentity.setInheriting(true);
		try {
			daoFacade.getAclObjectIdentityDao().save(aclObjectIdentity);
		} catch (CustomSqlException e) {
			e.printStackTrace();
		}
		Acl acl = readAclById(objectIdentity); 
		return (MutableAcl)acl;
	}
	
	public void createEntrys(final MutableAcl acl) throws CustomSqlException {
		int i = 1;
		for (AccessControlEntry entry_:acl.getEntries()) {
			AccessControlEntryImpl entry = (AccessControlEntryImpl)entry_;
			AclEntry aclEntry = new AclEntry();
			long oid = ((Long)acl.getId()).longValue();
			aclEntry.setAclObject(daoFacade.getAclObjectIdentityDao().get(oid));
			aclEntry.setAceOrder(i);
			AclSid aclSid = getOrCreateAclSid(entry.getSid(), true);
			aclEntry.setAclSid(aclSid);
			aclEntry.setAuditSuccess(entry.isAuditSuccess());
			aclEntry.setAuditFailure(entry.isAuditFailure());
			aclEntry.setGranting(entry.isGranting());
			aclEntry.setMask(entry.getPermission().getMask());
			daoFacade.getAclEntryDao().save(aclEntry);
			i++;
		}
	}
	public void deleteAcl(ObjectIdentity objectIdentity, boolean arg1)
			throws ChildrenExistException {
		String javaType = objectIdentity.getJavaType().toString();
		AclClass aclClass = daoFacade.getAclClassDao().findByClass(javaType);
		String identifier = objectIdentity.getIdentifier().toString();
		long id = Long.parseLong(identifier);
		AclObjectIdentity aclObjectIdentity = daoFacade.getAclObjectIdentityDao().findByObjectId(aclClass.getId(), id);
		daoFacade.getAclEntryDao().deleteByIdentityId(aclObjectIdentity.getId());
		try {
			daoFacade.getAclObjectIdentityDao().delete(aclObjectIdentity);
		} catch (CustomSqlException e) {
			e.printStackTrace();
		}
	}
	
	public MutableAcl updateAcl(MutableAcl acl) throws NotFoundException{
		AclObjectIdentity aclObjectIdentity;
		try {
			aclObjectIdentity = getAclObjectIdentity(acl.getObjectIdentity());
			daoFacade.getAclEntryDao().deleteByIdentityId(aclObjectIdentity.getId());
			createEntrys(acl);
		} catch (CustomSqlException e) {
			e.printStackTrace();
		}
		return (MutableAcl)readAclById(acl.getObjectIdentity());
	}
	
	public void addPermission(AbstractAclModel securedObject,
			Permission permission, Class clazz) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Sid sid;
		if(authentication.getPrincipal() instanceof UserDetails){
			sid = new PrincipalSid(((UserDetails)authentication.getPrincipal()).getUsername());
		}else {
			sid = new PrincipalSid(authentication.getPrincipal().toString());
		}
		addPermission(securedObject, sid, permission, clazz);
	}

	public void addPermission(AbstractAclModel securedObject,
			Sid recipient, Permission permission, Class clazz) {
		ObjectIdentity objectIdentity = new ObjectIdentityImpl(clazz.getCanonicalName(),securedObject.getId());
		MutableAcl acl;
		try {
			acl = (MutableAcl)readAclById(objectIdentity);
		} catch (Exception e) {
			acl = createAcl(objectIdentity);
		}
		acl.insertAce(acl.getEntries().length,permission, recipient, true);
		updateAcl(acl);
	}
	
	public AclObjectIdentity getAclObjectIdentity(ObjectIdentity objectIdentity){
		String javaType = objectIdentity.getJavaType().toString();
		AclClass aclClass = daoFacade.getAclClassDao().findByClass(javaType);
		if(aclClass==null){
			aclClass = new AclClass();
			aclClass.setCls(javaType);
			try {
				daoFacade.getAclClassDao().save(aclClass);
			} catch (CustomSqlException e) {
				e.printStackTrace();
			}
		}
		String identifier = objectIdentity.getIdentifier().toString();
		long id = (Long.valueOf(identifier)).longValue();
		AclObjectIdentity aclObjectIdentity = daoFacade.getAclObjectIdentityDao().findByObjectId(aclClass.getId(),id);
		return aclObjectIdentity;
	}
	
	public AclSid getOrCreateAclSid(Sid sid,boolean allowCreate){
		String sidName = null;
		boolean principal = true;
		if(sid instanceof PrincipalSid){
			sidName = ((PrincipalSid)sid).getPrincipal();
		}else if (sid instanceof GrantedAuthoritySid) {
			sidName = ((GrantedAuthoritySid)sid).getGrantedAuthority();
			principal = false;
		}else {
			throw new IllegalArgumentException("Unsupported implementation of Sid");
		}
		AclSid aclSid = daoFacade.getAclSidDao().findBySid(sidName, principal);
		if(aclSid==null&&allowCreate){
			aclSid = new AclSid();
			aclSid.setSid(sidName);
			aclSid.setPrincipal(principal);
			try {
				daoFacade.getAclSidDao().save(aclSid);
			} catch (CustomSqlException e) {
				e.printStackTrace();
			}
		}
		return aclSid;
	}
}
