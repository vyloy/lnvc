package com.lorent.service.impl;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.acls.Acl;
import org.springframework.security.acls.AclService;
import org.springframework.security.acls.NotFoundException;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.domain.AccessControlEntryImpl;
import org.springframework.security.acls.domain.AclImpl;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.sid.GrantedAuthoritySid;
import org.springframework.security.acls.sid.PrincipalSid;
import org.springframework.security.acls.sid.Sid;
import org.springframework.security.util.FieldUtils;
import com.lorent.model.*;
/**
 * 用于读取acl记录的service实现类
 * @author gary
 * @version 1.0
 * @since 2010-09-15
 */
public class AclServiceImpl extends BaseAclServiceImpl implements AclService {
	/**
	 * 获取与指定acl记录对应的子记录
	 */
	public ObjectIdentity[] findChildren(ObjectIdentity arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 根据oid获取acl记录
	 */
	public Acl readAclById(ObjectIdentity objectIdentity) throws NotFoundException {
		return readAclById(objectIdentity,null);
	}
	/**
	 * 重载
	 * 根据oid获取acl记录
	 */
	public Acl readAclById(ObjectIdentity objectIdentity, Sid[] sids)
			throws NotFoundException {
		Map map = readAclsById(new ObjectIdentity[]{objectIdentity},sids);
		return (Acl)map.get(objectIdentity);
	}
	/**
	 * 根据oid获取acl记录
	 */
	public Map readAclsById(ObjectIdentity[]objectIdentities) throws NotFoundException {
		return readAclsById(objectIdentities);
	}
	/**
	 * 重载
	 * 根据oid获取acl记录
	 */
	public Map readAclsById(ObjectIdentity[] objectIdentitys, Sid[] sids)
			throws NotFoundException {
		final Map<ObjectIdentity,Acl> acls = new HashMap<ObjectIdentity,Acl>();
		for (ObjectIdentity objectIdentity:objectIdentitys) {
			String javaType = objectIdentity.getJavaType().toString();
			AclClass aclClass = daoFacade.getAclClassDao().findByClass(javaType);
			String identifier = objectIdentity.getIdentifier().toString();
			long id = (Long.valueOf(identifier)).longValue();
			AclObjectIdentity aclObjectIdentity = daoFacade.getAclObjectIdentityDao().findByObjectId(aclClass.getId(), id);
			if(aclObjectIdentity==null){
				throw new NotFoundException("Could not found specified aclObjectIdentity.");
			}
			AclSid aclOwnerSid = aclObjectIdentity.getAclSid();
			Sid owner;
			if(aclOwnerSid.isPrincipal()){
				owner = new PrincipalSid(aclOwnerSid.getSid());
			}else {
				owner = new GrantedAuthoritySid(aclOwnerSid.getSid());
			}
			AclImpl acl = new AclImpl(objectIdentity,
					aclObjectIdentity.getId(), aclAuthorizationStrategy, auditLogger,
					null,null,false,owner);
			acls.put(objectIdentity, acl);
			Field field = FieldUtils.getField(AclImpl.class, "aces");
			List<AccessControlEntryImpl> aces;
			try {
				field.setAccessible(true);
				aces = (List)field.get(acl);
			} catch (Exception e) {
				throw new IllegalStateException("Could not obtain AclImpl.ace field: cause[" + e.getMessage() + "]");
			}
			List<AclEntry>aclEntries = daoFacade.getAclEntryDao().findByIdentityId(aclObjectIdentity.getId());
			for (AclEntry aclEntry:aclEntries) {
				AclSid aclSid = aclEntry.getAclSid();
				Sid recipient;
				if(aclSid.isPrincipal()){
					recipient = new PrincipalSid(aclSid.getSid());
				}else {
					recipient = new GrantedAuthoritySid(aclSid.getSid());
				}
				int mask = aclEntry.getMask();
				Permission permission = convertMaskIntoPermission(mask);
				boolean granting = aclEntry.isGranting();
				boolean auditSuccess = aclEntry.isAuditSuccess();
				boolean auditFailure = aclEntry.isAuditFailure();
				AccessControlEntryImpl ace = new AccessControlEntryImpl(aclEntry.getId(), acl, recipient, 
						permission, granting, auditSuccess, auditFailure);
				if(!aces.contains(ace)){
					aces.add(ace);
				}
			}
		}
		return acls;
	}
	
	protected Permission convertMaskIntoPermission(int mask) {
        return (Permission)BasePermission.buildFromMask(mask);
    } 
}
