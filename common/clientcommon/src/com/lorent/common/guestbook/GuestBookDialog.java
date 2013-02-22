/*
 * GuestBookDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.common.guestbook;

import javax.swing.JOptionPane;

import com.lorent.common.util.StringUtil;

/**
 *
 * @author  Jack
 */
public class GuestBookDialog extends javax.swing.JDialog {

	private StringUtil strUtil = new StringUtil(
			"com/lorent/common/resource/i18n/view");

	/** Creates new form GuestBookDialog */
	public GuestBookDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		this.setTitle(strUtil.getString("guestbook.title"));
		this.descLbl.setText(strUtil.getString("guestbook.desc"));
		this.noticeLbl.setText(strUtil.getString("guestbook.notice"));
		this.submitBtn.setText(strUtil.getString("guestbook.submit"));
		this.setSize(600, 400);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jPanel3 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		descLbl = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		contextArea = new javax.swing.JTextArea();
		jPanel4 = new javax.swing.JPanel();
		noticeLbl = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		submitBtn = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10,
				5));

		jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/common/guestbook/guestbook.png"))); // NOI18N
		jPanel3.add(jLabel1);

		descLbl.setFont(new java.awt.Font("宋体", 0, 18));
		descLbl.setText("jLabel1");
		jPanel3.add(descLbl);

		getContentPane().add(jPanel3, java.awt.BorderLayout.NORTH);

		jPanel2.setLayout(new java.awt.GridBagLayout());

		contextArea.setColumns(20);
		contextArea.setFont(new java.awt.Font("宋体", 0, 14));
		contextArea.setLineWrap(true);
		contextArea.setRows(5);
		jScrollPane1.setViewportView(contextArea);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
		jPanel2.add(jScrollPane1, gridBagConstraints);

		jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		noticeLbl.setFont(new java.awt.Font("宋体", 0, 14));
		noticeLbl.setText("jLabel1");
		jPanel4.add(noticeLbl);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
		jPanel2.add(jPanel4, gridBagConstraints);

		getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

		jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		submitBtn.setFont(new java.awt.Font("宋体", 0, 14));
		submitBtn.setText("jButton1");
		submitBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				submitBtnActionPerformed(evt);
			}
		});
		jPanel1.add(submitBtn);

		getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		setBounds((screenSize.width - 518) / 2, (screenSize.height - 382) / 2,
				518, 382);
	}// </editor-fold>
	//GEN-END:initComponents

	private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			beforeSubmit(contextArea.getText());
			JOptionPane.showMessageDialog(this, strUtil
					.getString("guestbook.sendsuccess"));
			this.dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), strUtil
					.getString("error.title"), JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void beforeSubmit(String text) throws Exception {

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				GuestBookDialog dialog = new GuestBookDialog(
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
	private javax.swing.JTextArea contextArea;
	private javax.swing.JLabel descLbl;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel noticeLbl;
	private javax.swing.JButton submitBtn;
	// End of variables declaration//GEN-END:variables

}