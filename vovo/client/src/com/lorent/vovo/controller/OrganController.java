package com.lorent.vovo.controller;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.lorent.common.controller.BaseController;
import com.lorent.common.tree.BroadcastEvent;
import com.lorent.common.tree.DepartmentBean;
import com.lorent.common.tree.MemberBean;
import com.lorent.common.util.NetworkUtil;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.dto.LoginInfo;
import com.lorent.vovo.ui.GroupMemberListPanelManager;
import com.lorent.vovo.ui.TreeManager;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;
import com.lorent.vovo.util.MyOpenfireUtil;
import com.lorent.vovo.util.TreeUtil;

public class OrganController extends BaseController {
	
	private static Logger log = Logger.getLogger(OrganController.class);
	
	public void memberChange(final String operate, final MemberBean bean)throws Exception{
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				try{
					if(operate.equals(BroadcastEvent.ADD_USER)){
						TreeManager.addMember(bean);
					}else if(operate.equals(BroadcastEvent.UPDATE_USER)){
						TreeManager.updateMember(bean, false, -1);
						GroupMemberListPanelManager.reflashGroupMemberListPanelItem(bean);
					}else if(operate.equals(BroadcastEvent.DELETE_USER)){
						if(bean.getLccAccount().equals(DataUtil.getUserName())){
							MyOpenfireUtil.closeConnection();
							OrganController.this.showMessageDialog(null, "你的账号被管理员删除，程序终止");
							System.exit(0);
						}else{
							TreeManager.removeMember(bean);
							GroupMemberListPanelManager.deleteGroupMemberListPanelItem(bean.getLccAccount());
							MyOpenfireUtil.revokeOwnershipByUserDel(bean.getLccAccount());
						}
					}
				}catch(Exception ex){
					log.error(ex);
				}
			}
		});
	}
	
	public void deptChange(final String operate, final DepartmentBean bean)throws Exception{
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				try{
					if(operate.equals(BroadcastEvent.ADD_DEPT)){
						TreeManager.addDept(bean);
					}else if(operate.equals(BroadcastEvent.UPDATE_DEPT)){
						TreeManager.updateDept(bean);
					}else if(operate.equals(BroadcastEvent.DELETE_DEPT)){
						TreeManager.removeDept(bean.getId());
					}
				}catch(Exception ex){
					log.error(ex);
				}
			}
		});
	}
	
	public void userStatusChange(final String user, final int status)throws Exception{
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				try{
					MemberBean bean = TreeUtil.getMemberBeanByLccno(user);
					if(bean != null){
//						TreeManager.updateMember(bean, true, status);
						TreeManager.reflashMemberNodeByStatus(user, status);
						bean.setState(status);
						GroupMemberListPanelManager.reflashGroupMemberListPanelItemByStatus(bean);
					}
				}catch(Exception ex){
					ex.printStackTrace();
					log.error(ex);
				}
			}
		});
	}
	
	public void broadcastMyIpAddress(String user,int status) throws Exception{
		if (status == Constants.STATUS_ONLINE || status == Constants.STATUS_AWAY || status == Constants.STATUS_BUSY) {
			LoginInfo info = context.getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
			MemberBean bean = new MemberBean();
			bean.setLccAccount(info.getUsername());
			bean.setIp(NetworkUtil.getSimpleIP());
			Vovo.getLcmUtil().broadcastMyIpAddress(bean,info.getUsername(),user);
			log.info("vovo broadcastMyIpAddress "+bean+","+info.getUsername()+","+user);
		}
	}
}
