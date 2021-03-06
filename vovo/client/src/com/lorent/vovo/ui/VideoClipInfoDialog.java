/*
 * VideoClipInfoDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;
import javax.swing.JLabel;

/**
 *
 * @author  __USER__
 */
public class VideoClipInfoDialog extends javax.swing.JDialog {

	/** Creates new form VideoClipInfoDialog */
	public VideoClipInfoDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jXPanel1 = new org.jdesktop.swingx.JXPanel();
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		createrNameTextField = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		titleTextField = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		descriptionTextArea = new javax.swing.JTextArea();
		liveStreamUrlLabel = new javax.swing.JLabel();
		liveStreamUrlTextField = new javax.swing.JTextField();
		jPanel2 = new javax.swing.JPanel();
		pictureXPanel = new org.jdesktop.swingx.JXPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jXPanel1.setLayout(new java.awt.BorderLayout());

		jLabel1.setText("\u4e0a\u4f20\uff1a");

		createrNameTextField.setText("jTextField1");

		jLabel2.setText("\u6807\u9898\uff1a");

		titleTextField.setText("jTextField1");

		jLabel3.setText("\u63cf\u8ff0\uff1a");

		descriptionTextArea.setColumns(20);
		descriptionTextArea.setRows(5);
		jScrollPane1.setViewportView(descriptionTextArea);

		liveStreamUrlLabel.setText("\u89c6\u9891\uff1a");

		liveStreamUrlTextField.setText("jTextField1");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel1)
																										.addGap(
																												38,
																												38,
																												38)
																										.addComponent(
																												createrNameTextField,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												341,
																												Short.MAX_VALUE))
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel2)
																										.addGap(
																												38,
																												38,
																												38)
																										.addGroup(
																												jPanel1Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jScrollPane1,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																341,
																																Short.MAX_VALUE)
																														.addComponent(
																																titleTextField,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																341,
																																Short.MAX_VALUE))))
																		.addGap(
																				32,
																				32,
																				32))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel3)
																		.addContainerGap(
																				411,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				liveStreamUrlLabel)
																		.addGap(
																				38,
																				38,
																				38)
																		.addComponent(
																				liveStreamUrlTextField,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				341,
																				Short.MAX_VALUE)
																		.addGap(
																				32,
																				32,
																				32)))));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																createrNameTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																titleTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel3)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																124,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												29, Short.MAX_VALUE)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																liveStreamUrlLabel)
														.addComponent(
																liveStreamUrlTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));

		jXPanel1.add(jPanel1, java.awt.BorderLayout.CENTER);

		pictureXPanel.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));

		javax.swing.GroupLayout pictureXPanelLayout = new javax.swing.GroupLayout(
				pictureXPanel);
		pictureXPanel.setLayout(pictureXPanelLayout);
		pictureXPanelLayout.setHorizontalGroup(pictureXPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 203, Short.MAX_VALUE));
		pictureXPanelLayout.setVerticalGroup(pictureXPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 242, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(pictureXPanel,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(pictureXPanel,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addContainerGap()));

		jXPanel1.add(jPanel2, java.awt.BorderLayout.WEST);

		getContentPane().add(jXPanel1, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				VideoClipInfoDialog dialog = new VideoClipInfoDialog(
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
	private javax.swing.JTextField createrNameTextField;
	private javax.swing.JTextArea descriptionTextArea;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private org.jdesktop.swingx.JXPanel jXPanel1;
	private javax.swing.JLabel liveStreamUrlLabel;
	private javax.swing.JTextField liveStreamUrlTextField;
	private org.jdesktop.swingx.JXPanel pictureXPanel;
	private javax.swing.JTextField titleTextField;

	// End of variables declaration//GEN-END:variables

	public javax.swing.JTextArea getDescriptionTextArea() {
		return descriptionTextArea;
	}

	public javax.swing.JTextField getCreaterNameTextField() {
		return createrNameTextField;
	}

	public org.jdesktop.swingx.JXPanel getPictureXPanel() {
		return pictureXPanel;
	}

	public javax.swing.JTextField getTitleTextField() {
		return titleTextField;
	}

	public javax.swing.JLabel getLiveStreamUrlLabel() {
		return liveStreamUrlLabel;
	}

	public javax.swing.JTextField getLiveStreamUrlTextField() {
		return liveStreamUrlTextField;
	}

	public JLabel getUploadLabel() {
		return jLabel1;
	}
}