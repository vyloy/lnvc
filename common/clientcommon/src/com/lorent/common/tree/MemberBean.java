package com.lorent.common.tree;

import java.io.Serializable;
import java.util.List;

import javax.swing.ImageIcon;

public class MemberBean implements Serializable{

	private int id;
	private String jid;
	private String desc;
	private int state;//在线状态
	private String post;//职务
	private String lccAccount;
	private String defaultImg;
	private ImageIcon icon;
	private String realName;//
	private List<Integer> departments;//新增时用,顺序：从父级到根级
	private String username;
	private Integer deptId;
	private String sign;
	private String mobile;
	private String email;
	private String gender;
	private String deptName;
	
	public MemberBean(){
		
	}
	
	public void copy(MemberBean bean){
		this.setState(bean.getState());
		this.setDefaultImg(bean.getDefaultImg());
		this.setDepartments(bean.getDepartments());
		this.setDeptId(bean.getDeptId());
		this.setDeptName(bean.getDeptName());
		this.setDesc(bean.getDesc());
		this.setEmail(bean.getEmail());
		this.setGender(bean.getGender());
		this.setIcon(bean.getIcon());
		this.setId(bean.getId());
		this.setJid(bean.getJid());
		this.setLccAccount(bean.getLccAccount());
		this.setMobile(bean.getMobile());
		this.setPost(bean.getPost());
		this.setRealName(bean.getRealName());
		this.setSign(bean.getSign());
		this.setUsername(bean.getUsername());
	}
	
	public MemberBean(int id,String jid,String desc,int state,String post,String lccAccount,String defaultImg,ImageIcon icon,String realName){
		this.id = id;
		this.jid = jid;
		this.desc = desc;
		this.state = state;
		this.post = post;
		this.lccAccount = lccAccount;
		this.defaultImg = defaultImg;
		this.icon = icon;
		this.realName = realName;
	}
	
	
	
	public List<Integer> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Integer> departments) {
		this.departments = departments;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getDefaultImg() {
		return defaultImg;
	}
	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJid() {
		if(jid==null){
			jid = lccAccount;
		}
		return jid;
	}
	public void setJid(String jid) {
		this.jid = jid;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getLccAccount() {
		return lccAccount;
	}
	public void setLccAccount(String lccAccount) {
		this.jid = lccAccount;
		this.lccAccount = lccAccount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
	
}
