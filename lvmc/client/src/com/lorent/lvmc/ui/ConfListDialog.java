/*
 * ConfListDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.lvmc.ui;

import java.awt.event.KeyEvent;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author  __USER__
 */
public class ConfListDialog extends javax.swing.JDialog {

	private boolean clickedOK = false;

	public boolean isClickedOK() {
		return clickedOK;
	}

	public void setClickedOK(boolean clickedOK) {
		this.clickedOK = clickedOK;
	}

	/** Creates new form ConfListDialog */
	public ConfListDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		confListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		confListTable = new javax.swing.JTable();
		jPanel2 = new javax.swing.JPanel();
		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("\u9009\u62e9\u4f1a\u8bae");

		jPanel1.setLayout(new java.awt.BorderLayout());

		jScrollPane1.setPreferredSize(new java.awt.Dimension(454, 220));

		confListTable.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "会议号码", "会议名称", "会议描述" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class, java.lang.String.class };
			boolean[] canEdit = new boolean[] { false, false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		confListTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				confListTableMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(confListTable);

		jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

		jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
				100, 5));

		okButton.setText("\u786e\u5b9a");
		okButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});
		jPanel2.add(okButton);

		cancelButton.setText("\u53d6\u6d88");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
		jPanel2.add(cancelButton);

		getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void confListTableMouseClicked(java.awt.event.MouseEvent evt) {
		if (evt.getClickCount() == 2 && evt.getButton() == 1) {
			//			int selectedRow = confListTable.getSelectedRow();
			//			if (selectedRow != -1) {
			//				System.out.println("select row " + selectedRow);
			//			}
			setClickedOK(true);
			this.dispose();
		}
	}

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
		setClickedOK(true);
		this.dispose();
	}

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				ConfListDialog dialog = new ConfListDialog(
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
	private javax.swing.JButton cancelButton;
	private javax.swing.JTable confListTable;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton okButton;

	// End of variables declaration//GEN-END:variables
	public javax.swing.JTable getConfListTable() {
		return confListTable;
	}

}