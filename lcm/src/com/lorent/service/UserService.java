package com.lorent.service;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.security.userdetails.UserDetailsService;

import com.lorent.annotation.AfterLogger;
import com.lorent.common.dto.VovoMyInfo;
import com.lorent.dao.UserDao;
import com.lorent.dto.ImportUserConfig;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.UserBean;
/**
 * @author jack
 *
 */
public interface UserService extends IGenericService<UserDao,UserBean, Integer>, UserDetailsService {
	public boolean changePassword(UserBean user,
			String oldPass,String newPass,String repeatPass )throws Exception;
	@AfterLogger(msgKey="logger.UserService.createUser")
	public boolean createUser(UserBean user) throws Exception;
	@AfterLogger(msgKey="logger.UserService.renewUser")
	public boolean renewUser(UserBean user) throws Exception;
	@AfterLogger(msgKey="logger.UserService.removeUser")
	public boolean removeUser(List<UserBean> users)throws Exception;
	public UserBean getByLccAccount(String lccAccount)throws CustomSqlException;
	public boolean userIsValid(UserBean user)throws CustomSqlException;
	
	/**
	 * 获取该用户创建会议可以邀请的用户列表
	 * @param user
	 * @param belongType 参考ConfBelongType
	 * @return
	 */
	public List<UserBean> getConfUser(UserBean user, String belongType)throws Exception;
	public UserBean getLoginUser(String username, String passwd)throws Exception;
	public void createInitData()throws Exception;
	/**
	 * 获取导入用户配置
	 * @return
	 */
	public ImportUserConfig getImportUserConfig()throws Exception;
	
	/**
	 * 导入用户
	 * @param config
	 */
	//public void doImportUser(ImportUserConfig config, CustomerBean customer)throws Exception;
	public VovoMyInfo getVovoMyInfo(String lccno)throws Exception;
	public Workbook exportToXLS() throws CustomSqlException, FileNotFoundException,
			IOException;
	public ImportResult importFromXLS(InputStream inputStream) throws Exception;
	
	public static class ImportResult{
		public final int count;
		public final int success;
		public final Workbook book;
		public ImportResult(int count, int success, Workbook book) {
			this.count = count;
			this.success = success;
			this.book = book;
		}
	}
	
	public static final int REAL_NAME=0;
	public static final int USER_NAME=1;
	public static final int GENDER=2;
	public static final int MOBILE=3;
	public static final int EMAIL=4;
	public static final int CODE=5;
	public static final int POSITION=6;
	public static final int DEPARTMENT=7;
	public static final int DEPARTMENT_CODE=8;
	public static final int PASSWORD=9;
	public static final int LCC_NO=10;
	public static final int COMMENT=11;
	
	public void createOrUpdateUCSUser(Object[] users)throws Exception;
	public void deleteUCSUser(UserBean user) throws Exception;
}
