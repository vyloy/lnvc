package com.lorent.model;

import java.util.HashSet;
import java.util.Set;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;
import com.lorent.exception.ModelCheckFailException;

public class UserBean extends BaseModel  implements AbstractAclModel, UserDetails,ModelCheck {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String realName;
	private Boolean userEnabled = true;
	private String password;
	private String mobile;
	private String phone;
	private String email;
	private Set<RoleBean>roles = new HashSet<RoleBean>();
	private Boolean userAccountNonExpired = true;
	private Boolean userAccountNonLocked = true;
	private Boolean userCredentialsNonExpired = true;
	private Integer userType;
	private CustomerBean customer;
	private String lccAccount;
	private DepartmentBean department;
	private String repeatPassword;
	private String newPassword;
	private String oldPassword;
//	private Set<UserStatusBean> userStatus = new HashSet<UserStatusBean>();
	private Integer sipId;
	private String md5passwd;
//	private Boolean isImport = false;
	private String position;//职位
	private String gender;//性别
	private String code;//员工编码
	private String myPic;//用户头像
	private String sign;//个性签名
	

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSipId() {
		return sipId;
	}

	public void setSipId(Integer sipId) {
		this.sipId = sipId;
	}

	public String getMd5passwd() {
		return md5passwd;
	}

	public void setMd5passwd(String md5passwd) {
		this.md5passwd = md5passwd;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public GrantedAuthority[] getAuthorities() {
		return roles.toArray(new GrantedAuthority[roles.size()]);
	}

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.username;
	}

	public boolean isAccountNonExpired() {
		return this.userAccountNonExpired;
	}


	public boolean isAccountNonLocked() {
		return this.userAccountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return this.userCredentialsNonExpired;
	}

	public boolean isEnabled() {
		return this.userEnabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Set<RoleBean> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleBean> roles) {
		this.roles = roles;
	}

	public Boolean getUserAccountNonExpired() {
		return userAccountNonExpired;
	}

	public void setUserAccountNonExpired(Boolean userAccountNonExpired) {
		this.userAccountNonExpired = userAccountNonExpired;
	}

	public Boolean getUserAccountNonLocked() {
		return userAccountNonLocked;
	}

	public void setUserAccountNonLocked(Boolean userAccountNonLocked) {
		this.userAccountNonLocked = userAccountNonLocked;
	}

	public Boolean getUserCredentialsNonExpired() {
		return userCredentialsNonExpired;
	}

	public void setUserCredentialsNonExpired(Boolean userCredentialsNonExpired) {
		this.userCredentialsNonExpired = userCredentialsNonExpired;
	}

	public Boolean getUserEnabled() {
		return userEnabled;
	}

	public void setUserEnabled(Boolean userEnabled) {
		this.userEnabled = userEnabled;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean checkModel() throws ModelCheckFailException {
		if(this==null)
			throw new ModelCheckFailException("modelcheckfail.notinit");
		if(this.username==null||"".equals(this.username))
			throw new ModelCheckFailException("modelcheckfail.username");
		if(this.password==null||"".equals(this.password))
			throw new ModelCheckFailException("modelcheckfail.password");
		return true;
	}

	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj)return true;
		if(this==null||obj==null)return false;
		if(!(obj instanceof UserBean))return false;
		UserBean user = (UserBean)obj;
		if(user.username==null&&this.username==null)return true;
		if(user.username==null||this.username==null)return false;
		return user.username.equals(this.username);
	}
	@Override
	public int hashCode() {
		if(this==null||this.username==null)return 0;
		return this.username.hashCode();
	}

	public String getLccAccount() {
		return lccAccount;
	}

	public void setLccAccount(String lccAccount) {
		this.lccAccount = lccAccount;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public DepartmentBean getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentBean department) {
		this.department = department;
	}


	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getMyPic() {
		return myPic;
	}

	public void setMyPic(String myPic) {
		this.myPic = myPic;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("UserBean:{");
		sb.append("name:").append(username)
			.append(", email:").append(email)
			.append(", lccno:").append(lccAccount)
			.append("}");
		return sb.toString();
	}
	
	

//	public Set<UserStatusBean> getUserStatus() {
//		return userStatus;
//	}
//
//	public void setUserStatus(Set<UserStatusBean> userStatus) {
//		this.userStatus = userStatus;
//	}

//	public Boolean getIsImport() {
//		return isImport;
//	}
//
//	public void setIsImport(Boolean isImport) {
//		this.isImport = isImport;
//	}
}
