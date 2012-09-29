package com.lorent.dao.impl;
import com.lorent.dao.GenericDaoImpl;
import com.lorent.dao.UserDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.UserBean;

public class UserDaoImpl extends GenericDaoImpl<UserBean,Integer> implements UserDao {

	public UserBean createUser(UserBean user) throws CustomSqlException,
			ArgsException {
		if(user==null)throw new ArgsException("args.inputisrequired");
		if(user.getUsername()==null)throw new ArgsException("modelcheckfail.username");
		if(user.getPassword()==null)throw new ArgsException("modelcheckfail.password");
		if(user.getRoles().size()==0)throw new ArgsException("args.userrolenotexists");
		save(user);
		user = getByExample(user).get(0);
		return user;
	}
}
