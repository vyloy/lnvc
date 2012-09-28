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

import com.lorent.lvmc.bean.OptionDto;
import com.lorent.lvmc.bean.VoteDataChildDto;
import com.lorent.lvmc.bean.VoteDataDto;
import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.DataUtil;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author test
 */
public class VoteItemPanelDialog extends javax.swing.JDialog {

	/** Creates new form VoteDialog */
	public VoteItemPanelDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	private voteMainPanel votemMainPanel;
	private boolean isNewFlag;

	public VoteItemPanelDialog(javax.swing.JPanel jPanel, boolean modal,
			boolean isNewFlag) throws Exception {
		super((JFrame) DataUtil.getTopWindow(), modal);
		//        super();
		initComponents();
		this.votemMainPanel = (voteMainPanel) jPanel;
		this.isNewFlag = isNewFlag;
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
//GEN-BEGIN:initComponents
// <editor-fold defaultstate="collapsed" desc="Generated Code">
private void initComponents() {

ridioGroup = new javax.swing.ButtonGroup();
itemPanel = new javax.swing.JPanel();
middlePanel = new javax.swing.JPanel();
jPanel4 = new javax.swing.JPanel();
votetitle3 = new javax.swing.JLabel();
titledescript3 = new javax.swing.JLabel();
voteType3 = new javax.swing.JLabel();
singleSelect3 = new javax.swing.JRadioButton();
yesorno3 = new javax.swing.JRadioButton();
multiselect3 = new javax.swing.JRadioButton();
voteItem3 = new javax.swing.JLabel();
votetitleField3 = new javax.swing.JTextField();
jScrollPane7 = new javax.swing.JScrollPane();
titledescriptField3 = new javax.swing.JTextArea();
voteItemField3 = new javax.swing.JTextField();
voteItemList3 = new javax.swing.JLabel();
jScrollPane8 = new javax.swing.JScrollPane();
voteItemListField3 = new javax.swing.JList(new DefaultListModel());
addItem3 = new javax.swing.JButton();
removeItem = new javax.swing.JButton();
bottom = new javax.swing.JPanel();
finish3 = new javax.swing.JButton();
finishExitButton = new javax.swing.JButton();
cancel3 = new javax.swing.JButton();

setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

votetitle3.setText("\u8868\u51b3\u9879\u540d\u79f0");

titledescript3.setText("\u8868\u51b3\u9879\u63cf\u8ff0");

voteType3.setText("\u8868\u51b3\u9879\u7c7b\u578b");

ridioGroup.add(singleSelect3);
singleSelect3.setText("\u5355\u9009");
singleSelect3.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
singleSelect3ActionPerformed(evt);
}
});

ridioGroup.add(yesorno3);
yesorno3.setText("\u662f/\u5426");
yesorno3.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
yesorno3ActionPerformed(evt);
}
});

ridioGroup.add(multiselect3);
multiselect3.setText("\u591a\u9009");
multiselect3.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
multiselect3ActionPerformed(evt);
}
});

voteItem3.setText("\u6295\u7968\u9009\u9879");

titledescriptField3.setColumns(20);
titledescriptField3.setRows(5);
jScrollPane7.setViewportView(titledescriptField3);

voteItemList3.setText("\u9009\u9879\u5217\u8868");

//voteItemListField3.setModel([]);
jScrollPane8.setViewportView(voteItemListField3);

addItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lorent/lvmc/resource/images/vote_icon/list-add-3.png"))); // NOI18N
addItem3.setBorderPainted(false);
addItem3.setContentAreaFilled(false);
addItem3.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
addItem3ActionPerformed(evt);
}
});

removeItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lorent/lvmc/resource/images/vote_icon/list-remove-3.png"))); // NOI18N
removeItem.setBorderPainted(false);
removeItem.setContentAreaFilled(false);
removeItem.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
removeItemActionPerformed(evt);
}
});

javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
jPanel4.setLayout(jPanel4Layout);
jPanel4Layout.setHorizontalGroup(
jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel4Layout.createSequentialGroup()
.addContainerGap()
.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(votetitle3)
.addComponent(titledescript3)
.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addComponent(voteItem3)
.addComponent(voteType3)
.addComponent(voteItemList3)))
.addGap(20, 20, 20)
.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel4Layout.createSequentialGroup()
.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(votetitleField3, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
.addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
.addGap(58, 58, 58))
.addGroup(jPanel4Layout.createSequentialGroup()
.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel4Layout.createSequentialGroup()
.addComponent(singleSelect3)
.addGap(59, 59, 59)
.addComponent(yesorno3)
.addGap(68, 68, 68)
.addComponent(multiselect3))
.addGroup(jPanel4Layout.createSequentialGroup()
.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
.addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(voteItemField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(addItem3)
.addComponent(removeItem))))
.addContainerGap(65, Short.MAX_VALUE))))
);
jPanel4Layout.setVerticalGroup(
jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel4Layout.createSequentialGroup()
.addContainerGap()
.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(votetitle3)
.addComponent(votetitleField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel4Layout.createSequentialGroup()
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGroup(jPanel4Layout.createSequentialGroup()
.addGap(27, 27, 27)
.addComponent(titledescript3)))
.addGap(21, 21, 21)
.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(voteType3)
.addComponent(singleSelect3)
.addComponent(yesorno3)
.addComponent(multiselect3))
.addGap(18, 18, 18)
.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(voteItem3)
.addComponent(voteItemField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(addItem3))
.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel4Layout.createSequentialGroup()
.addGap(55, 55, 55)
.addComponent(voteItemList3))
.addGroup(jPanel4Layout.createSequentialGroup()
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGroup(jPanel4Layout.createSequentialGroup()
.addGap(54, 54, 54)
.addComponent(removeItem)))
.addGap(105, 105, 105))
);

javax.swing.GroupLayout middlePanelLayout = new javax.swing.GroupLayout(middlePanel);
middlePanel.setLayout(middlePanelLayout);
middlePanelLayout.setHorizontalGroup(
middlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
);
middlePanelLayout.setVerticalGroup(
middlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
);

bottom.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

finish3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lorent/lvmc/resource/images/vote_icon/edit-add-2.png"))); // NOI18N
finish3.setText("\u786e\u5b9a\u5e76\u7ee7\u7eed\u6dfb\u52a0");
finish3.setBorderPainted(false);
finish3.setContentAreaFilled(false);
finish3.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
finish3ActionPerformed(evt);
}
});
bottom.add(finish3);

finishExitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lorent/lvmc/resource/images/vote_icon/dialog-ok-3.png"))); // NOI18N
finishExitButton.setText("\u786e\u5b9a\u5e76\u5173\u95ed");
finishExitButton.setBorderPainted(false);
finishExitButton.setContentAreaFilled(false);
finishExitButton.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
finishExitButtonActionPerformed(evt);
}
});
bottom.add(finishExitButton);

cancel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lorent/lvmc/resource/images/vote_icon/dialog-cancel-3.png"))); // NOI18N
cancel3.setText("\u5173\u95ed");
cancel3.setBorderPainted(false);
cancel3.setContentAreaFilled(false);
cancel3.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
cancel3ActionPerformed(evt);
}
});
bottom.add(cancel3);

javax.swing.GroupLayout itemPanelLayout = new javax.swing.GroupLayout(itemPanel);
itemPanel.setLayout(itemPanelLayout);
itemPanelLayout.setHorizontalGroup(
itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(itemPanelLayout.createSequentialGroup()
.addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addComponent(bottom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
.addComponent(middlePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addContainerGap())
);
itemPanelLayout.setVerticalGroup(
itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(itemPanelLayout.createSequentialGroup()
.addComponent(middlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(bottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
);

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(itemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(itemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
);

pack();
}// </editor-fold>

	//GEN-END:initComponents

	private void addItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItem3ActionPerformed
		String addItem = this.voteItemField3.getText();
		if (null == addItem || addItem.trim().length() == 0)
			return;
		DefaultListModel defaultListModel = (DefaultListModel) this.voteItemListField3
				.getModel();
		int size = defaultListModel.getSize();
		for (int i = 0; i < size; i++) {
			if (defaultListModel.get(i).equals(addItem)) {
				return;
			}
		}
		defaultListModel.addElement(addItem);
		this.voteItemListField3.setModel(defaultListModel);
		this.voteItemField3.setText(null);
	}//GEN-LAST:event_addItem3ActionPerformed

	private void removeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemActionPerformed
		DefaultListModel defaultListModel = (DefaultListModel) this.voteItemListField3
				.getModel();
		int selectItem = voteItemListField3.getSelectedIndex();
		defaultListModel.removeElementAt(selectItem);
	}//GEN-LAST:event_removeItemActionPerformed

	private void cancel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel3ActionPerformed
		this.dispose();
	}//GEN-LAST:event_cancel3ActionPerformed

	private void doFinish(boolean needClose) {
		int selectItemType = -1;
		if (null != this.multiselect3.getSelectedObjects()
				&& this.multiselect3.getSelectedObjects().length > 0)
			selectItemType = Constants.SELECTITEMTYPE_MULTI;
		if (null != this.yesorno3.getSelectedObjects()
				&& this.yesorno3.getSelectedObjects().length > 0)
			selectItemType = Constants.SELECTITEMTYPE_YES_OR_NO;
		if (null != this.singleSelect3.getSelectedObjects()
				&& this.singleSelect3.getSelectedObjects().length > 0)
			selectItemType = Constants.SELECTITEMTYPE_SINGLE;
		childDto = new VoteDataChildDto();

		childDto.setItemType("" + selectItemType);
		childDto.setTitle(this.getVotetitleField3().getText());
		childDto.setTitledescription(this.titledescriptField3.getText());
		DefaultListModel defaultListModel = (DefaultListModel) this.voteItemListField3
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
			return;
		}
		if (!(null != this.votetitleField3.getText() && this.votetitleField3
				.getText().length() > 0)) {
			JOptionPane.showMessageDialog(this, "表决项名称不能为空！");
			return;
		}

		if (selectItemType != Constants.SELECTITEMTYPE_YES_OR_NO) {
			if (childDto.getList().size() > 0) {
				List<OptionDto[]> optionDtos = childDto.getList();
				if (optionDtos.get(0).length < 2) {
					JOptionPane.showMessageDialog(this, "请填写两个或两个以上选项！");
					return;
				}
			} else {
				JOptionPane.showMessageDialog(this, "选项列表不能为空！");
				return;
			}
		}

		if (!isNewFlag) {
			((VoteTabItemPane) this.votemMainPanel.getjTabbedPane1()
					.getSelectedComponent()).updateSelectedItemPanel(childDto);
			this.dispose();
		} else {
			((VoteTabItemPane) this.votemMainPanel.getjTabbedPane1()
					.getSelectedComponent()).addSelectItemPanel(childDto);
			this.votetitleField3.setText(null);
			this.titledescriptField3.setText(null);
			this.singleSelect3.setSelected(true);
			this.voteItemField3.setText(null);
			((DefaultListModel) this.voteItemListField3.getModel())
					.removeAllElements();
			setStatus(true);
		}
		if (needClose) {
			this.dispose();
		}
	}

	//    private VoteDataDto dataDto;
	private VoteDataChildDto childDto = null;

	private void finish3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finish3ActionPerformed
		doFinish(false);
	}//GEN-LAST:event_finish3ActionPerformed

	public void check() {

	}

	private void yesorno3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesorno3ActionPerformed
		setStatus(false);
	}//GEN-LAST:event_yesorno3ActionPerformed

	private void singleSelect3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singleSelect3ActionPerformed
		setStatus(true);
	}//GEN-LAST:event_singleSelect3ActionPerformed

	private void multiselect3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiselect3ActionPerformed
		setStatus(true);
	}//GEN-LAST:event_multiselect3ActionPerformed

	private void finishExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishExitButtonActionPerformed
		doFinish(true);
	}//GEN-LAST:event_finishExitButtonActionPerformed

	public void setStatus(boolean flag) {
		this.voteItemField3.setEnabled(flag);
		this.voteItemListField3.setEnabled(flag);
		this.addItem3.setEnabled(flag);
		this.removeItem.setEnabled(flag);
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
				VoteItemPanelDialog dialog = new VoteItemPanelDialog(
						new javax.swing.JFrame(), true);
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
	private javax.swing.JButton addItem3;
	private javax.swing.JPanel bottom;
	private javax.swing.JButton cancel3;
	private javax.swing.JButton finish3;
	private javax.swing.JButton finishExitButton;
	private javax.swing.JPanel itemPanel;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane7;
	private javax.swing.JScrollPane jScrollPane8;
	private javax.swing.JPanel middlePanel;
	private javax.swing.JRadioButton multiselect3;
	private javax.swing.JButton removeItem;
	private javax.swing.ButtonGroup ridioGroup;
	private javax.swing.JRadioButton singleSelect3;
	private javax.swing.JLabel titledescript3;
	private javax.swing.JTextArea titledescriptField3;
	private javax.swing.JLabel voteItem3;
	private javax.swing.JTextField voteItemField3;
	private javax.swing.JLabel voteItemList3;
	private javax.swing.JList voteItemListField3;
	private javax.swing.JLabel voteType3;
	private javax.swing.JLabel votetitle3;
	private javax.swing.JTextField votetitleField3;
	private javax.swing.JRadioButton yesorno3;

	// End of variables declaration//GEN-END:variables

	public ButtonGroup getRidioGroup() {
		return ridioGroup;
	}

	public JRadioButton getMultiselect3() {
		return multiselect3;
	}

	public JRadioButton getYesorno3() {
		return yesorno3;
	}

	public JRadioButton getSingleSelect3() {
		return singleSelect3;
	}

	public JTextArea getTitledescriptField3() {
		return titledescriptField3;
	}

	public JTextField getVoteItemField3() {
		return voteItemField3;
	}

	public JList getVoteItemListField3() {
		return voteItemListField3;
	}

	public JTextField getVotetitleField3() {
		return votetitleField3;
	}

}
