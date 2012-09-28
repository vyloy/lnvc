/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VoteDialog.java
 *
 * Created on 2011-12-19, 15:49:36
 */
package com.lorent.lvmc.ui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.List;

import com.lorent.lvmc.bean.OptionDto;
import com.lorent.lvmc.bean.VoteDataChildDto;
import com.lorent.lvmc.bean.VoteDataDto;
import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.DataUtil;
import javax.swing.*;

import org.apache.log4j.Logger;

/**
 *
 * @author test
 */
public class VoteDialog extends javax.swing.JDialog {

	/** Creates new form VoteDialog */
	public VoteDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	private Logger log = Logger.getLogger(VoteDialog.class);
	public voteMainPanel votemMainPanel;

	public VoteDialog(javax.swing.JPanel jPanel, boolean modal)
			throws Exception {
		super((JFrame) DataUtil.getTopWindow(), modal);
		//        super();
		initComponents();
		this.votemMainPanel = (voteMainPanel) jPanel;
		this.dataDto = new VoteDataDto();
		this.dataDto.getList().clear();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

ridioGroup = new javax.swing.ButtonGroup();
topPanel = new javax.swing.JPanel();
votejPanel = new javax.swing.JPanel();
theme = new javax.swing.JLabel();
themeField = new javax.swing.JTextField();
themedescript = new javax.swing.JLabel();
jScrollPane9 = new javax.swing.JScrollPane();
themedescField = new javax.swing.JTextArea();
bottom = new javax.swing.JPanel();
finish3 = new javax.swing.JButton();
cancel3 = new javax.swing.JButton();
_voteitempanel = new javax.swing.JPanel();
jPanel8 = new javax.swing.JPanel();
votetitle7 = new javax.swing.JLabel();
titledescript7 = new javax.swing.JLabel();
voteType7 = new javax.swing.JLabel();
singleSelect7 = new javax.swing.JRadioButton();
yesorno7 = new javax.swing.JRadioButton();
multiselect7 = new javax.swing.JRadioButton();
voteItem7 = new javax.swing.JLabel();
votetitleField7 = new javax.swing.JTextField();
jScrollPane16 = new javax.swing.JScrollPane();
titledescriptField7 = new javax.swing.JTextArea();
voteItemField7 = new javax.swing.JTextField();
voteItemList7 = new javax.swing.JLabel();
jScrollPane17 = new javax.swing.JScrollPane();
voteItemListField7 = new javax.swing.JList(new DefaultListModel());
addItem7 = new javax.swing.JButton();
removeItem7 = new javax.swing.JButton();

setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

theme.setText("\u6295\u7968\u4e3b\u9898");

themedescript.setText("\u4e3b\u9898\u63cf\u8ff0");

themedescField.setColumns(20);
themedescField.setRows(5);
themedescField.addKeyListener(new java.awt.event.KeyAdapter() {
public void keyPressed(java.awt.event.KeyEvent evt) {
themedescFieldKeyPressed(evt);
}
});
jScrollPane9.setViewportView(themedescField);

javax.swing.GroupLayout votejPanelLayout = new javax.swing.GroupLayout(votejPanel);
votejPanel.setLayout(votejPanelLayout);
votejPanelLayout.setHorizontalGroup(
votejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(votejPanelLayout.createSequentialGroup()
.addContainerGap()
.addGroup(votejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(themedescript)
.addComponent(theme))
.addGap(31, 31, 31)
.addGroup(votejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
.addComponent(themeField)
.addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
.addContainerGap(32, Short.MAX_VALUE))
);
votejPanelLayout.setVerticalGroup(
votejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(votejPanelLayout.createSequentialGroup()
.addContainerGap()
.addGroup(votejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(votejPanelLayout.createSequentialGroup()
.addGroup(votejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(theme)
.addComponent(themeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
.addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
.addContainerGap())
.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, votejPanelLayout.createSequentialGroup()
.addComponent(themedescript)
.addGap(32, 32, 32))))
);

javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
topPanel.setLayout(topPanelLayout);
topPanelLayout.setHorizontalGroup(
topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(topPanelLayout.createSequentialGroup()
.addComponent(votejPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);
topPanelLayout.setVerticalGroup(
topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(votejPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
);

finish3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lorent/lvmc/resource/images/vote_icon/dialog-ok-3.png"))); // NOI18N
finish3.setText("\u786e\u5b9a");
finish3.setBorderPainted(false);
finish3.setContentAreaFilled(false);
finish3.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
finish3ActionPerformed(evt);
}
});

cancel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lorent/lvmc/resource/images/vote_icon/dialog-cancel-3.png"))); // NOI18N
cancel3.setText("\u53d6\u6d88");
cancel3.setBorderPainted(false);
cancel3.setContentAreaFilled(false);
cancel3.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
cancel3ActionPerformed(evt);
}
});

javax.swing.GroupLayout bottomLayout = new javax.swing.GroupLayout(bottom);
bottom.setLayout(bottomLayout);
bottomLayout.setHorizontalGroup(
bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(bottomLayout.createSequentialGroup()
.addGap(177, 177, 177)
.addComponent(finish3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(cancel3)
.addContainerGap(140, Short.MAX_VALUE))
);
bottomLayout.setVerticalGroup(
bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(bottomLayout.createSequentialGroup()
.addGroup(bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(finish3)
.addComponent(cancel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);

votetitle7.setText("\u8868\u51b3\u9879\u540d\u79f0");

titledescript7.setText("\u8868\u51b3\u9879\u63cf\u8ff0");

voteType7.setText("\u8868\u51b3\u9879\u7c7b\u578b");

ridioGroup.add(singleSelect7);
singleSelect7.setText("\u5355\u9009");
singleSelect7.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
singleSelect7ActionPerformed(evt);
}
});

ridioGroup.add(yesorno7);
yesorno7.setText("\u662f/\u5426");
yesorno7.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
yesorno7ActionPerformed(evt);
}
});

ridioGroup.add(multiselect7);
multiselect7.setText("\u591a\u9009");
multiselect7.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
multiselect7ActionPerformed(evt);
}
});

voteItem7.setText("\u6295\u7968\u9009\u9879");

titledescriptField7.setColumns(20);
titledescriptField7.setRows(5);
titledescriptField7.addKeyListener(new java.awt.event.KeyAdapter() {
public void keyPressed(java.awt.event.KeyEvent evt) {
titledescriptField7KeyPressed(evt);
}
});
jScrollPane16.setViewportView(titledescriptField7);

voteItemList7.setText("\u9009\u9879\u5217\u8868");

jScrollPane17.setViewportView(voteItemListField7);

addItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lorent/lvmc/resource/images/vote_icon/list-add-3.png"))); // NOI18N
addItem7.setBorderPainted(false);
addItem7.setContentAreaFilled(false);
addItem7.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
addItem7ActionPerformed(evt);
}
});

removeItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lorent/lvmc/resource/images/vote_icon/list-remove-3.png"))); // NOI18N
removeItem7.setBorderPainted(false);
removeItem7.setContentAreaFilled(false);
removeItem7.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
removeItem7ActionPerformed(evt);
removeItem4ActionPerformed(evt);
}
});

javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
jPanel8.setLayout(jPanel8Layout);
jPanel8Layout.setHorizontalGroup(
jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel8Layout.createSequentialGroup()
.addContainerGap()
.addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(votetitle7)
.addComponent(titledescript7)
.addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addComponent(voteItem7)
.addComponent(voteType7)
.addComponent(voteItemList7)))
.addGap(20, 20, 20)
.addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel8Layout.createSequentialGroup()
.addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(votetitleField7, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
.addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
.addGap(58, 58, 58))
.addGroup(jPanel8Layout.createSequentialGroup()
.addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel8Layout.createSequentialGroup()
.addComponent(singleSelect7)
.addGap(59, 59, 59)
.addComponent(yesorno7)
.addGap(68, 68, 68)
.addComponent(multiselect7))
.addGroup(jPanel8Layout.createSequentialGroup()
.addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
.addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(voteItemField7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(addItem7)
.addComponent(removeItem7))))
.addContainerGap(65, Short.MAX_VALUE))))
);
jPanel8Layout.setVerticalGroup(
jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel8Layout.createSequentialGroup()
.addContainerGap()
.addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(votetitle7)
.addComponent(votetitleField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel8Layout.createSequentialGroup()
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGroup(jPanel8Layout.createSequentialGroup()
.addGap(27, 27, 27)
.addComponent(titledescript7)))
.addGap(21, 21, 21)
.addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(voteType7)
.addComponent(singleSelect7)
.addComponent(yesorno7)
.addComponent(multiselect7))
.addGap(18, 18, 18)
.addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(voteItem7)
.addComponent(voteItemField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(addItem7))
.addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel8Layout.createSequentialGroup()
.addGap(55, 55, 55)
.addComponent(voteItemList7))
.addGroup(jPanel8Layout.createSequentialGroup()
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGroup(jPanel8Layout.createSequentialGroup()
.addGap(54, 54, 54)
.addComponent(removeItem7)))
.addGap(105, 105, 105))
);

javax.swing.GroupLayout _voteitempanelLayout = new javax.swing.GroupLayout(_voteitempanel);
_voteitempanel.setLayout(_voteitempanelLayout);
_voteitempanelLayout.setHorizontalGroup(
_voteitempanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
);
_voteitempanelLayout.setVerticalGroup(
_voteitempanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
);

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
.addComponent(_voteitempanel, 0, 524, Short.MAX_VALUE)
.addComponent(bottom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(_voteitempanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(bottom, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
);

pack();
}// </editor-fold>

	//GEN-END:initComponents
	private void titledescriptField7KeyPressed(java.awt.event.KeyEvent evt) {
		 if ( evt.getKeyCode() == KeyEvent.VK_TAB ) {   
             evt.consume();   
             singleSelect7.requestFocus();
         }   
	}

	private void themedescFieldKeyPressed(java.awt.event.KeyEvent evt) {
		 if ( evt.getKeyCode() == KeyEvent.VK_TAB ) {   
             evt.consume();   
             votetitleField7.requestFocus();
         }   
	}

	private void removeItem4ActionPerformed(java.awt.event.ActionEvent evt) {
		DefaultListModel defaultListModel = (DefaultListModel) this.voteItemListField7
				.getModel();
		int selectItem = voteItemListField7.getSelectedIndex();
		defaultListModel.removeElementAt(selectItem);
	}

	private void removeItem7ActionPerformed(java.awt.event.ActionEvent evt) {
		//GEN-FIRST:event_removeItemActionPerformed
		DefaultListModel defaultListModel = (DefaultListModel) this.voteItemListField7
				.getModel();
		int selectItem = voteItemListField7.getSelectedIndex();
		defaultListModel.removeElementAt(selectItem);
	}

	private void addItem7ActionPerformed(java.awt.event.ActionEvent evt) {
		//GEN-FIRST:event_addItem3ActionPerformed
		String addItem = this.voteItemField7.getText();
		if (null == addItem || addItem.trim().length() == 0)
			return;
		DefaultListModel defaultListModel = (DefaultListModel) this.voteItemListField7
				.getModel();
		int size = defaultListModel.getSize();
		for (int i = 0; i < size; i++) {
			if (defaultListModel.get(i).equals(addItem)) {
				return;
			}
		}
		defaultListModel.addElement(addItem);
		this.voteItemListField7.setModel(defaultListModel);
		this.voteItemField7.setText(null);
	}

	private void multiselect7ActionPerformed(java.awt.event.ActionEvent evt) {
		//GEN-FIRST:event_multiselect3ActionPerformed
		setStatus(true);
	}

	private void yesorno7ActionPerformed(java.awt.event.ActionEvent evt) {
		//GEN-FIRST:event_yesorno3ActionPerformed
		setStatus(false);
	}

	private void singleSelect7ActionPerformed(java.awt.event.ActionEvent evt) {
		//GEN-FIRST:event_singleSelect3ActionPerformed
		setStatus(true);
	}

	public void setStatus(boolean flag) {
		this.voteItemField7.setEnabled(flag);
		this.voteItemListField7.setEnabled(flag);
		this.addItem7.setEnabled(flag);
		this.removeItem7.setEnabled(flag);
	}

	private void cancel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel3ActionPerformed
		this.dispose();
	}//GEN-LAST:event_cancel3ActionPerformed

	private VoteDataDto dataDto;
	private VoteDataChildDto childDto = null;

	private void finish3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finish3ActionPerformed
		dataDto.setTheme(this.themeField.getText());
		dataDto.setThemeDescription(this.themedescField.getText());
		//检查输入项
		if (!(null != this.themeField.getText() && this.themeField.getText()
				.length() > 0)) {
			JOptionPane.showMessageDialog(this, "请输入主题！");
			return;
		}
		if (!(null != this.themedescField.getText() && this.themedescField
				.getText().length() > 0)) {
			JOptionPane.showMessageDialog(this, "请输入主题描述！");
			return;
		}

		if ("add".equals(this.votemMainPanel.getAdd_or_update_flag())) {
			boolean result = checkVoteItem();
			if (!result) {
				return;
			}
			this.votemMainPanel.addTheme(dataDto);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				log.error("finish3ActionPerformed", e);
			}
			doAddVoteItem();
		}
		if ("update".equals(this.votemMainPanel.getAdd_or_update_flag())) {
			this.votemMainPanel.updateTheme(dataDto);
		}
		this.dispose();
	}//GEN-LAST:event_finish3ActionPerformed

	private boolean checkVoteItem() {
		int selectItemType = -1;
		if (null != this.multiselect7.getSelectedObjects()
				&& this.multiselect7.getSelectedObjects().length > 0)
			selectItemType = Constants.SELECTITEMTYPE_MULTI;
		if (null != this.yesorno7.getSelectedObjects()
				&& this.yesorno7.getSelectedObjects().length > 0)
			selectItemType = Constants.SELECTITEMTYPE_YES_OR_NO;
		if (null != this.singleSelect7.getSelectedObjects()
				&& this.singleSelect7.getSelectedObjects().length > 0)
			selectItemType = Constants.SELECTITEMTYPE_SINGLE;
		childDto = new VoteDataChildDto();

		childDto.setItemType("" + selectItemType);
		childDto.setTitle(this.getVotetitleField7().getText());
		childDto.setTitledescription(this.titledescriptField7.getText());
		DefaultListModel defaultListModel = (DefaultListModel) this.voteItemListField7
				.getModel();
		OptionDto[] answer = new OptionDto[defaultListModel.getSize()];
		for (int i = 0; i < defaultListModel.getSize(); i++) {
			OptionDto optionDto = new OptionDto();
			optionDto.setName(defaultListModel.get(i).toString());
			answer[i] = optionDto;
		}
		childDto.getList().add(answer);

		//检查输入项
		if (selectItemType < 0) {
			JOptionPane.showMessageDialog(this, "请输入表决项类型 ！");
			return false;
		}
		if (!(null != this.votetitleField7.getText() && this.votetitleField7
				.getText().length() > 0)) {
			JOptionPane.showMessageDialog(this, "表决项名称不能为空！");
			return false;
		}

		if (selectItemType != Constants.SELECTITEMTYPE_YES_OR_NO) {
			if (childDto.getList().size() > 0) {
				List<OptionDto[]> optionDtos = childDto.getList();
				if (optionDtos.get(0).length < 2) {
					JOptionPane.showMessageDialog(this, "请填写两个或两个以上选项！");
					return false;
				}
			} else {
				JOptionPane.showMessageDialog(this, "选项列表不能为空！");
				return false;
			}
		}
		return true;
	}

	private void doAddVoteItem() {
		this.votemMainPanel.getVoteTabItemPane().addSelectItemPanel(childDto);
	}

	/**
	 * 增加表决项是进行处理 ，把增加的表决项放到List中
	 * @param evt 
	 */
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				VoteDialog dialog = new VoteDialog(new javax.swing.JFrame(),
						true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {

					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JPanel _voteitempanel;
	private javax.swing.JButton addItem7;
	private javax.swing.JPanel bottom;
	private javax.swing.JButton cancel3;
	private javax.swing.JButton finish3;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JScrollPane jScrollPane16;
	private javax.swing.JScrollPane jScrollPane17;
	private javax.swing.JScrollPane jScrollPane9;
	private javax.swing.JRadioButton multiselect7;
	private javax.swing.JButton removeItem7;
	private javax.swing.ButtonGroup ridioGroup;
	private javax.swing.JRadioButton singleSelect7;
	private javax.swing.JLabel theme;
	private javax.swing.JTextField themeField;
	private javax.swing.JTextArea themedescField;
	private javax.swing.JLabel themedescript;
	private javax.swing.JLabel titledescript7;
	private javax.swing.JTextArea titledescriptField7;
	private javax.swing.JPanel topPanel;
	private javax.swing.JLabel voteItem7;
	private javax.swing.JTextField voteItemField7;
	private javax.swing.JLabel voteItemList7;
	private javax.swing.JList voteItemListField7;
	private javax.swing.JLabel voteType7;
	private javax.swing.JPanel votejPanel;
	private javax.swing.JLabel votetitle7;
	private javax.swing.JTextField votetitleField7;
	private javax.swing.JRadioButton yesorno7;

	// End of variables declaration//GEN-END:variables

	public ButtonGroup getRidioGroup() {
		return ridioGroup;
	}

	public JTextField getThemeField() {
		return themeField;
	}

	public JTextArea getThemedescField() {
		return themedescField;
	}

	public javax.swing.JTextField getVotetitleField7() {
		return votetitleField7;
	}

	public void setVotetitleField7(javax.swing.JTextField votetitleField7) {
		this.votetitleField7 = votetitleField7;
	}

}
