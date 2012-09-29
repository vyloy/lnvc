package com.lorent.model;

import java.io.Serializable;

/**
 * 抽象的acl model类
 * 需acl保护的bean需继承此类
 * @author gary
 * @version 1.0
 * @since 2010-09-15
 */
public interface AbstractAclModel extends Serializable {
//	private Integer id;

//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
	Integer getId();
	void setId(Integer id);
}
