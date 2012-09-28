package com.lorent.vovo.ui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.lorent.vovo.controller.LoginStatusManager;
import com.lorent.vovo.dto.LoginStatus;

public class StatusCombobox extends JComboBox {
	public StatusCombobox(){
		setModel(new DefaultComboBoxModel(LoginStatusManager.getLoginStatusList().toArray()));
		setRenderer(new StatusComboBoxRenderer());
	}
	
	public int getSelectStatus(){
		LoginStatus status = (LoginStatus) this.getSelectedItem();
		return status.getCode();
	}
	
	public void setSelectStatus(Integer status){
		LoginStatus temp = LoginStatusManager.getLoginStatus(status);
		if(temp==null){
			return;
		}
		this.setSelectedItem(temp);
	}
}
