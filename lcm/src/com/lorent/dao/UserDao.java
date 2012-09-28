package com.lorent.dao;

import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.UserBean;

public interface UserDao extends IGenericDao<UserBean,Integer>{
	UserBean createUser(UserBean user)throws CustomSqlException,ArgsException;
}
