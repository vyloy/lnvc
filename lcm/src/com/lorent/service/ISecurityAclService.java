package com.lorent.service;
import org.springframework.security.acls.MutableAclService;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.sid.Sid;
import com.lorent.model.AbstractAclModel;
/**
 * 实现acl信息持久化的service接口
 * @author gary
 * @version 1.0
 * @since 2010-09-15
 */
public interface ISecurityAclService extends MutableAclService{
	/**
	 * 为指定的对象添加acl授权
	 * @param securedObject 需添加授权的对象
	 * @param permission 为对象添加的授权
	 * @param clazz 对象的class
	 */
    public void addPermission(AbstractAclModel securedObject, Permission permission, Class clazz);
    /**
	 * 为指定的对象添加acl授权
	 * @param securedObject 需添加授权的对象
	 * @param recipient 主体的sid
	 * @param permission 为对象添加的授权
	 * @param clazz 对象的class
	 */
    public void addPermission(AbstractAclModel securedObject, Sid recipient, Permission permission, Class clazz);
}