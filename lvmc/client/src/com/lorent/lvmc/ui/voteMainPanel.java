/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * voteMainPanel.java
 *
 * Created on 2011-12-20, 9:24:00
 */
package com.lorent.lvmc.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.Connection;

import com.lorent.common.util.ParaUtil;
import com.lorent.lvmc.bean.VoteDataDto;
import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.dto.LoginInfo;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.PermissionUtil;
import com.lorent.lvmc.util.StringUtil;

/**
 *
 * @author test
 */
public class voteMainPanel extends javax.swing.JPanel {

	private Logger log = Logger.getLogger(voteMainPanel.class);

	/** Creates new form voteMainPanel */
	public voteMainPanel() {
		initComponents();
		initPanel(false);
		initVoteData();
		boolean flag = PermissionUtil.hasPermission(PermissionUtil.VOTE_MANAGE);
		this.setVoteManage(flag);
	}

	public void initPanel(boolean flag) {
		//		this.jLabel1.setVisible(flag);
		//		this.jLabel2.setVisible(flag);
		//		this.jLabel3.setVisible(flag);
		//		this.jLabel4.setVisible(flag);
		//		this.addSelectItemBtn.setVisible(flag);
		//		this.jLabel5.setVisible(flag);
	}

	public void reloadData() {
		jTabbedPane1.removeAll();
		initVoteData();
	}

	/**
	 * 按钮状态根据主题 是否开始，是否结束的状态的进行判断
	 */
	public void voteStatus(boolean isStart, boolean isClose) {
		//未开始投票 
		if (!isStart) {
			this.addthemeBtn.setEnabled(true);//新建主题
			this.startVoteBtn.setEnabled(true);//开始
			this.voteFinishBtn.setEnabled(false);//结束
			this.comitVoteBtn.setEnabled(false);//提交
			this.voteResultBtn.setEnabled(false);//统计
			if (null != this.getjTabbedPane1().getSelectedComponent())
				((VoteTabItemPane) this.getjTabbedPane1()
						.getSelectedComponent()).getAddSelectItemBtn()
						.setEnabled(true);//增加表决项
			this.updateThemeBtn.setEnabled(true);//修改主题
			((VoteTabItemPane) this.getjTabbedPane1().getSelectedComponent())
					.setSelectOptionEdit(false);

			List<SelectItemPanel> list = ((VoteTabItemPane) this
					.getjTabbedPane1().getSelectedComponent())
					.getSelectItemPanelList();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				SelectItemPanel selectItemPanel = (SelectItemPanel) iterator
						.next();
				selectItemPanel.getjButton1().setEnabled(true);
				selectItemPanel.getjButton2().setEnabled(true);
			}

		}
		//开始投票 未结束
		if (isStart && !isClose) {
			this.addthemeBtn.setEnabled(true);//新建主题
			this.startVoteBtn.setEnabled(false);//开始
			this.voteFinishBtn.setEnabled(true);//结束
			this.comitVoteBtn.setEnabled(true);//提交
			this.voteResultBtn.setEnabled(true);//统计
			if (null != this.getjTabbedPane1().getSelectedComponent())
				((VoteTabItemPane) this.getjTabbedPane1()
						.getSelectedComponent()).getAddSelectItemBtn()
						.setEnabled(false);//增加表决项
			this.updateThemeBtn.setEnabled(false);//修改主题
			((VoteTabItemPane) this.getjTabbedPane1().getSelectedComponent())
					.setSelectOptionEdit(true);
		}
		//未开始投票 未结束
		if (!isStart && !isClose) {
			this.addthemeBtn.setEnabled(true);//新建主题
			this.startVoteBtn.setEnabled(true);//开始
			this.voteFinishBtn.setEnabled(false);//结束
			this.comitVoteBtn.setEnabled(false);//提交
			this.voteResultBtn.setEnabled(false);//统计
			if (null != this.getjTabbedPane1().getSelectedComponent())
				((VoteTabItemPane) this.getjTabbedPane1()
						.getSelectedComponent()).getAddSelectItemBtn()
						.setEnabled(true);//增加表决项
			this.updateThemeBtn.setEnabled(true);//修改主题
			((VoteTabItemPane) this.getjTabbedPane1().getSelectedComponent())
					.setSelectOptionEdit(false);

			List<SelectItemPanel> list = ((VoteTabItemPane) this
					.getjTabbedPane1().getSelectedComponent())
					.getSelectItemPanelList();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				SelectItemPanel selectItemPanel = (SelectItemPanel) iterator
						.next();
				selectItemPanel.getjButton1().setEnabled(true);
				selectItemPanel.getjButton2().setEnabled(true);
			}
		}
		//结束
		if (isClose) {
			this.addthemeBtn.setEnabled(true);//新建主题
			this.startVoteBtn.setEnabled(false);//开始
			this.voteFinishBtn.setEnabled(false);//结束
			this.comitVoteBtn.setEnabled(false);//提交
			this.voteResultBtn.setEnabled(true);//统计
			if (null != this.getjTabbedPane1().getSelectedComponent())
				((VoteTabItemPane) this.getjTabbedPane1()
						.getSelectedComponent()).getAddSelectItemBtn()
						.setEnabled(false);//增加表决项
			this.updateThemeBtn.setEnabled(false);//修改主题
			((VoteTabItemPane) this.getjTabbedPane1().getSelectedComponent())
					.setSelectOptionEdit(false);
		}
	}

	public void addTheme(VoteDataDto dataDto) {
		ControllerFacade.execute("voteController", "addNewTheme", ParaUtil
				.newInstance().setString(
						"addNewtheme",
						this.getVoteTheme_add(dataDto.getTheme(), dataDto
								.getThemeDescription())));
		//		voteStatus(false, false);
	}

	private VoteTabItemPane voteTabItemPane;

	public VoteTabItemPane getVoteTabItemPane() {
		return voteTabItemPane;
	}

	public void setVoteTabItemPane(VoteTabItemPane voteTabItemPane) {
		this.voteTabItemPane = voteTabItemPane;
	}

	//更新主题
	public void updateTheme(VoteDataDto dataDto) {
		if (!(null != ((VoteTabItemPane) this.getjTabbedPane1()
				.getSelectedComponent()).getId() && ((VoteTabItemPane) this
				.getjTabbedPane1().getSelectedComponent()).getId().length() > 0))
			return;
		ControllerFacade.execute("voteController", "updateTheme", ParaUtil
				.newInstance().setString(
						"updatetheme",
						getVoteTheme_update(dataDto.getTheme(), dataDto
								.getThemeDescription())));
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jTabbedPane1 = new javax.swing.JTabbedPane();
		jToolBar1 = new javax.swing.JToolBar();
		addthemeBtn = new javax.swing.JButton();
		updateThemeBtn = new javax.swing.JButton();
		startVoteBtn = new javax.swing.JButton();
		comitVoteBtn = new javax.swing.JButton();
		voteFinishBtn = new javax.swing.JButton();
		voteResultBtn = new javax.swing.JButton();

		setLayout(new java.awt.BorderLayout());

		jTabbedPane1.setAutoscrolls(true);
		jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				jTabbedPane1StateChanged(evt);
			}
		});
		add(jTabbedPane1, java.awt.BorderLayout.CENTER);

		jToolBar1.setFloatable(false);

		addthemeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/lvmc/resource/images/vote_icon/edit-add-2.png"))); // NOI18N
		addthemeBtn.setText("\u65b0\u5efa\u4e3b\u9898");
		addthemeBtn.setContentAreaFilled(false);
		addthemeBtn.setFocusable(false);
		addthemeBtn
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		addthemeBtn.setMaximumSize(new java.awt.Dimension(60, 47));
		addthemeBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		addthemeBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addthemeBtnActionPerformed(evt);
			}
		});
		jToolBar1.add(addthemeBtn);

		updateThemeBtn
				.setIcon(new javax.swing.ImageIcon(
						getClass()
								.getResource(
										"/com/lorent/lvmc/resource/images/vote_icon/edit-4.png"))); // NOI18N
		updateThemeBtn.setText("\u4fee\u6539\u4e3b\u9898");
		updateThemeBtn.setContentAreaFilled(false);
		updateThemeBtn.setFocusable(false);
		updateThemeBtn
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		updateThemeBtn.setMaximumSize(new java.awt.Dimension(60, 47));
		updateThemeBtn
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		updateThemeBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateThemeBtnActionPerformed(evt);
			}
		});
		jToolBar1.add(updateThemeBtn);

		startVoteBtn
				.setIcon(new javax.swing.ImageIcon(
						getClass()
								.getResource(
										"/com/lorent/lvmc/resource/images/vote_icon/edit-history-3.png"))); // NOI18N
		startVoteBtn.setText("\u5f00\u59cb");
		startVoteBtn.setContentAreaFilled(false);
		startVoteBtn.setFocusable(false);
		startVoteBtn
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		startVoteBtn.setMaximumSize(new java.awt.Dimension(60, 47));
		startVoteBtn.setMinimumSize(new java.awt.Dimension(55, 47));
		startVoteBtn.setPreferredSize(new java.awt.Dimension(55, 47));
		startVoteBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		startVoteBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startVoteBtnActionPerformed(evt);
			}
		});
		jToolBar1.add(startVoteBtn);

		comitVoteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/lvmc/resource/images/vote_icon/go-next.png"))); // NOI18N
		comitVoteBtn.setText("\u63d0\u4ea4");
		comitVoteBtn.setContentAreaFilled(false);
		comitVoteBtn.setFocusable(false);
		comitVoteBtn
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		comitVoteBtn.setMaximumSize(new java.awt.Dimension(60, 47));
		comitVoteBtn.setMinimumSize(new java.awt.Dimension(55, 47));
		comitVoteBtn.setPreferredSize(new java.awt.Dimension(55, 47));
		comitVoteBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		comitVoteBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comitVoteBtnActionPerformed(evt);
			}
		});
		jToolBar1.add(comitVoteBtn);

		voteFinishBtn
				.setIcon(new javax.swing.ImageIcon(
						getClass()
								.getResource(
										"/com/lorent/lvmc/resource/images/vote_icon/edit-history.png"))); // NOI18N
		voteFinishBtn.setText("\u7ed3\u675f");
		voteFinishBtn.setContentAreaFilled(false);
		voteFinishBtn.setFocusable(false);
		voteFinishBtn
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		voteFinishBtn.setMaximumSize(new java.awt.Dimension(60, 47));
		voteFinishBtn.setMinimumSize(new java.awt.Dimension(55, 47));
		voteFinishBtn.setPreferredSize(new java.awt.Dimension(55, 47));
		voteFinishBtn
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		voteFinishBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				voteFinishBtnActionPerformed(evt);
			}
		});
		jToolBar1.add(voteFinishBtn);

		voteResultBtn
				.setIcon(new javax.swing.ImageIcon(
						getClass()
								.getResource(
										"/com/lorent/lvmc/resource/images/vote_icon/office-chart-line-percentage.png"))); // NOI18N
		voteResultBtn.setText("\u7edf\u8ba1\u7ed3\u679c");
		voteResultBtn.setContentAreaFilled(false);
		voteResultBtn.setFocusable(false);
		voteResultBtn
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		voteResultBtn.setMaximumSize(new java.awt.Dimension(60, 47));
		voteResultBtn
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		voteResultBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				voteResultBtnActionPerformed(evt);
			}
		});
		jToolBar1.add(voteResultBtn);

		add(jToolBar1, java.awt.BorderLayout.PAGE_START);
	}// </editor-fold>
	//GEN-END:initComponents

	private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {
		JTabbedPane jTabbedPane = (JTabbedPane) evt.getSource();
		VoteTabItemPane select = (VoteTabItemPane) jTabbedPane.getSelectedComponent();
		if(select != null){
			changeButtonStatus(select);
		}
	}

	private String add_or_update_flag;

	public String getAdd_or_update_flag() {
		return add_or_update_flag;
	}

	public void setAdd_or_update_flag(String add_or_update_flag) {
		this.add_or_update_flag = add_or_update_flag;
	}

	public void changeButtonStatus(VoteTabItemPane voteTabItemPane) {
		voteTabItemPane.setButtonStatus();
	}

	private void addthemeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addthemeBtnActionPerformed
		try {
			VoteDialog dialog = new VoteDialog(this, true);
			setAdd_or_update_flag("add");
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸对象
			int w = (screen.width - this.getSize().width) / 2;//水平位置
			int h = (screen.height - this.getSize().height) / 2;//垂直位置
			dialog.setLocation(w, h);
			dialog.setTitle(StringUtil
					.getUIString("voteMainPanel.addnewVoteDialogTitle"));
			dialog.setVisible(true);
			this.validate();
			//---------------------------------------
			//			int count = this.jTabbedPane1.getTabCount();
			//			VoteTabItemPane itemPane = new VoteTabItemPane();
			//			this.jTabbedPane1.addTab("testII", itemPane);
			//			this.jTabbedPane1.setSelectedIndex(count);
			//			this.jTabbedPane1.setTitleAt(0, "hahahahahah");
		} catch (Exception ex) {
			log.error("addthemeBtnActionPerformed", ex);
		}
	}//GEN-LAST:event_addthemeBtnActionPerformed

	private void comitVoteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comitVoteBtnActionPerformed
		VoteTabItemPane voteTabItemPane = (VoteTabItemPane) this.jTabbedPane1
				.getSelectedComponent();
		String paraxml = voteTabItemPane.getVoteSelected(voteTabItemPane
				.getId());
		if (voteTabItemPane.getSelectedAnswerList().size() > 0) {
			voteTabItemPane.selectedItem();
			ControllerFacade.execute("voteController", "comiteResult", ParaUtil
					.newInstance().setString("comitevote", paraxml));
			this.comitVoteBtn.setEnabled(false);
			voteTabItemPane.setSelectOptionEdit(false);
		} else {
			JOptionPane.showMessageDialog(this, StringUtil
					.getUIString("voteMainPanel.noseletedcommite"));
			return;
		}
	}//GEN-LAST:event_comitVoteBtnActionPerformed

	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private void startVoteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startVoteBtnActionPerformed
		VoteTabItemPane voteTabItemPane = (VoteTabItemPane) this.jTabbedPane1
				.getSelectedComponent();
		if (null == voteTabItemPane)
			return;
		String theme = voteTabItemPane.getjLabel3().getText();
		String themedesc = voteTabItemPane.getjLabel4().getText();
		voteTabItemPane.getjLabel5().setVisible(true);
		int minuteCount = 30;
		Calendar nowTime = Calendar.getInstance();
		String startTime = dateFormat.format(nowTime.getTime());
		ControllerFacade.execute("voteController", "updateTheme",
				ParaUtil.newInstance()
						.setString(
								"updatetheme",
								getVoteTheme_isStart_update(startTime, ""
										+ minuteCount)));
		voteTabItemPane.getjLabel5().setText(
				StringUtil.getUIString("voteStatus.starting"));
		voteTabItemPane.getjLabel3().setText(theme);
		voteTabItemPane.getjLabel4().setText(themedesc);
	}//GEN-LAST:event_startVoteBtnActionPerformed

	private int LeftMinute = 0, leftSecond = 0;
	private TimePanel timePanel = null;
	private boolean ispausestart = false;

	private void voteFinishBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voteFinishBtnActionPerformed
		VoteTabItemPane voteTabItemPane = (VoteTabItemPane) this.jTabbedPane1
				.getSelectedComponent();
		if (null == voteTabItemPane)
			return;
		String theme = voteTabItemPane.getjLabel3().getText();
		String themedesc = voteTabItemPane.getjLabel4().getText();
		ControllerFacade.execute("voteController", "updateTheme", ParaUtil
				.newInstance().setString("updatetheme",
						getVoteTheme_isClose_update()));
		//		voteTabItemPane.setIsclose("true");
		//		voteTabItemPane.setIsstar("true");
		//		this.voteStatus(true, true);
		voteTabItemPane.getjLabel5().setText(
				StringUtil.getUIString("voteStatus.end"));
		voteTabItemPane.getjLabel3().setText(theme);
		voteTabItemPane.getjLabel4().setText(themedesc);
		//		voteTabItemPane.setSelectOptionEdit(false);

		//刷新界面
		//		reloadData();
	}//GEN-LAST:event_voteFinishBtnActionPerformed

	private void voteResultBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voteResultBtnActionPerformed
		VoteTabItemPane voteTabItemPane = (VoteTabItemPane) this.jTabbedPane1
				.getSelectedComponent();
		if (null == voteTabItemPane)
			return;
		log.debug(voteTabItemPane.getSelectItemPanelList().size());
		for (Iterator<SelectItemPanel> it = voteTabItemPane
				.getSelectItemPanelList().iterator(); it.hasNext();) {
			SelectItemPanel selectItemPanel = it.next();
			System.out.print(selectItemPanel.getjLabel1().getText() + " "
					+ selectItemPanel.getjLabel2().getText() + " "
					+ selectItemPanel.getjLabel4().getText() + "\n");
		}

		ControllerFacade.execute("voteController", "getVoteResult", ParaUtil
				.newInstance().setString("voteresult",
						getVoteResult(voteTabItemPane.getId())));
	}//GEN-LAST:event_voteResultBtnActionPerformed

	/**
	 * 增加投票主题
	 * @return  xml 格式的字符串
	 */
	private String getVoteTheme_add(String title, String title_remark) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("<operate name=\"create_vote\">");
		Connection conn = DataUtil.getValue(DataUtil.Key.Connection);
		LoginInfo info = DataUtil.getValue(DataUtil.Key.LoginInfo);
		buffer.append("<roomjid>" + info.getConfno() + "@conference."
				+ conn.getServiceName() + "</roomjid>");
		buffer.append("<title>" + title + "</title>");
		buffer.append("<title_remark>" + title_remark + "</title_remark>");
		buffer.append("<effective_time>" + "" + "</effective_time>");
		buffer
				.append("<conference_id>" + info.getConfno()
						+ "</conference_id>");
		buffer.append("<begin_time>" + "" + "</begin_time>");
		buffer.append("<creator>" + info.getUsername() + "</creator>");
		buffer.append("<selects>");
		buffer.append("</selects>");
		buffer.append("</operate>");
		return buffer.toString();
	}

	/**
	 * 修改投票主题
	 * @return  xml 格式的字符串
	 */
	private String getVoteTheme_update(String title, String title_remark) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<operate name=\"update_vote\">");
		Connection conn = DataUtil.getValue(DataUtil.Key.Connection);
		LoginInfo info = DataUtil.getValue(DataUtil.Key.LoginInfo);
		buffer.append("<roomjid>" + info.getConfno() + "@conference."
				+ conn.getServiceName() + "</roomjid>");
		buffer.append("<id>"
				+ ((VoteTabItemPane) this.getjTabbedPane1()
						.getSelectedComponent()).getId() + "</id>");//主题id
		buffer.append("<title>" + title + "</title>");
		buffer.append("<title_remark>" + title_remark + "</title_remark>");
		buffer.append("<effective_time>" + "" + "</effective_time>");
		buffer
				.append("<conference_id>" + info.getConfno()
						+ "</conference_id>");
		buffer.append("<begin_time>" + "" + "</begin_time>");
		buffer.append("<is_start>false</is_start>");
		buffer.append("<is_close>false</is_close>");
		buffer.append("</operate>");
		return buffer.toString();
	}

	/**
	 * 开始事件(修改投票主题)
	 * @return  xml 格式的字符串
	 */
	private String getVoteTheme_isStart_update(String startTime,
			String effecttime) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<operate name=\"update_vote\">");
		Connection conn = DataUtil.getValue(DataUtil.Key.Connection);
		LoginInfo info = DataUtil.getValue(DataUtil.Key.LoginInfo);
		buffer.append("<roomjid>" + info.getConfno() + "@conference."
				+ conn.getServiceName() + "</roomjid>");
		buffer.append("<id>"
				+ ((VoteTabItemPane) this.getjTabbedPane1()
						.getSelectedComponent()).getId() + "</id>");//主题id
		buffer
				.append("<title>"
						+ this.getjTabbedPane1().getTitleAt(
								this.getjTabbedPane1().getSelectedIndex())
						+ "</title>");
		buffer.append("<title_remark>"
				+ ((VoteTabItemPane) this.getjTabbedPane1()
						.getSelectedComponent()).getjLabel4().getText()
				+ "</title_remark>");
		buffer.append("<effective_time>" + effecttime + "</effective_time>");
		buffer.append("<conference_id></conference_id>");
		buffer.append("<begin_time>" + startTime + "</begin_time>");
		buffer.append("<is_start>true</is_start>");
		buffer.append("<is_close>false</is_close>");
		buffer.append("</operate>");
		return buffer.toString();
	}

	/**
	 * 结束事件(修改投票主题)
	 * @return  xml 格式的字符串
	 */
	private String getVoteTheme_isClose_update() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<operate name=\"update_vote\">");
		Connection conn = DataUtil.getValue(DataUtil.Key.Connection);
		LoginInfo info = DataUtil.getValue(DataUtil.Key.LoginInfo);
		buffer.append("<roomjid>" + info.getConfno() + "@conference."
				+ conn.getServiceName() + "</roomjid>");
		buffer.append("<id>"
				+ ((VoteTabItemPane) this.getjTabbedPane1()
						.getSelectedComponent()).getId() + "</id>");//主题id
		buffer
				.append("<title>"
						+ this.getjTabbedPane1().getTitleAt(
								this.getjTabbedPane1().getSelectedIndex())
						+ "</title>");
		buffer.append("<title_remark>"
				+ ((VoteTabItemPane) this.getjTabbedPane1()
						.getSelectedComponent()).getjLabel4().getText()
				+ "</title_remark>");
		buffer.append("<effective_time></effective_time>");
		buffer.append("<conference_id></conference_id>");
		buffer.append("<begin_time></begin_time>");
		buffer.append("<is_start>true</is_start>");
		buffer.append("<is_close>true</is_close>");
		buffer.append("</operate>");
		return buffer.toString();
	}

	/**
	 * 
	 * @param 获取统计结果
	 * @return 
	 */
	public String getVoteResult(String themeid) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<operate name=\"count\">");
		Connection conn = DataUtil.getValue(DataUtil.Key.Connection);
		LoginInfo info = DataUtil.getValue(DataUtil.Key.LoginInfo);
		buffer.append("<id>" + themeid + "</id>");//表决项id
		buffer.append("<roomjid>" + info.getConfno() + "@conference."
				+ conn.getServiceName() + "</roomjid>");
		buffer.append("</operate>");
		return buffer.toString();
	}

	private void updateThemeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateThemeBtnActionPerformed
		VoteTabItemPane voteTabItemPane = (VoteTabItemPane) this
				.getjTabbedPane1().getSelectedComponent();
		try {
			VoteDialog_Update dialog = new VoteDialog_Update(this, true);
			dialog.getThemeField().setText(
					this.getjTabbedPane1().getTitleAt(
							this.getjTabbedPane1().getSelectedIndex()));
			dialog.getThemedescField().setText(
					voteTabItemPane.getjLabel4().getText());
			setAdd_or_update_flag("update");
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸对象
			int w = (screen.width - this.getSize().width) / 2;//水平位置
			int h = (screen.height - this.getSize().height) / 2;//垂直位置
			dialog.setLocation(w, h);
			dialog.setTitle(StringUtil
					.getUIString("voteMainPanel.updateVoteDialogTitle"));
			dialog.setVisible(true);
		} catch (Exception ex) {
			log.error("updateThemeBtnActionPerformed", ex);
		}
	}//GEN-LAST:event_updateThemeBtnActionPerformed

	public String itemid = "";

	public void setLeftTime(String leftTime) {
		//		this.jLabel5.setText(this.getjLabel5().getText() + "  投票剩余时间："
		//				+ leftTime);
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton addthemeBtn;
	private javax.swing.JButton comitVoteBtn;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JToolBar jToolBar1;
	private javax.swing.JButton startVoteBtn;
	private javax.swing.JButton updateThemeBtn;
	private javax.swing.JButton voteFinishBtn;
	private javax.swing.JButton voteResultBtn;
	// End of variables declaration//GEN-END:variables
	private String id;

	public JButton getstartVoteBtn() {
		return startVoteBtn;
	}

	public JButton getvoteFinishBtn() {
		return voteFinishBtn;
	}

	public JButton getupdateThemeBtn() {
		return updateThemeBtn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private int initFlag = 0;

	public int getInitFlag() {
		return initFlag;
	}

	public void setInitFlag(int initFlag) {
		this.initFlag = initFlag;
	}

	private void initVoteData() {
		initFlag = 0;
		ControllerFacade.execute("voteController", "load_Vote", ParaUtil
				.newInstance().setString("load_vote", getVoteInitXml()));
		initFlag = 1;
	}

	public String getVoteInitXml() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<operate name=\"load_vote\">");
		Connection conn = DataUtil.getValue(DataUtil.Key.Connection);
		LoginInfo info = DataUtil.getValue(DataUtil.Key.LoginInfo);
		buffer.append("<roomjid>" + info.getConfno() + "@conference."
				+ conn.getServiceName() + "</roomjid>");
		buffer.append("</operate>");
		return buffer.toString();
	}

	public JButton getComitVoteBtn() {
		return comitVoteBtn;
	}

	public void setToolBarEnable(boolean enabled) {
		this.addthemeBtn.setEnabled(enabled);
		this.updateThemeBtn.setEnabled(enabled);
		this.startVoteBtn.setEnabled(enabled);
		this.voteFinishBtn.setEnabled(enabled);
		this.voteResultBtn.setEnabled(enabled);
		this.comitVoteBtn.setEnabled(enabled);
	}

	public javax.swing.JTabbedPane getjTabbedPane1() {
		return jTabbedPane1;
	}

	public void setjTabbedPane1(javax.swing.JTabbedPane jTabbedPane1) {
		this.jTabbedPane1 = jTabbedPane1;
	}
	
	public void setVoteManage(boolean f){
		ViewManager.setComponentByAuthority(this.addthemeBtn, f);
		ViewManager.setComponentByAuthority(this.updateThemeBtn, f);
		ViewManager.setComponentByAuthority(this.startVoteBtn, f);
		ViewManager.setComponentByAuthority(this.voteFinishBtn, f);
	}

}
