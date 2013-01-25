/*
 * AboutDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.lvmc.ui;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

import com.lorent.lvmc.util.StringUtil;

/**
 *
 * @author  Jack
 */
public class AboutDialog extends JDialog {

	/** Creates new form AboutDialog */
	public AboutDialog(java.awt.Frame parent, Boolean modal) {
		super(parent, modal);
		initComponents();
		setIconImage(Toolkit.getDefaultToolkit().createImage(
				getClass().getResource(
						"/com/lorent/lvmc/resource/images/main_icon.png")));
		this.setTitle(StringUtil.getUIString("trayicon.menu.about"));
		this.appName.setText(StringUtil.getUIString("about.appName"));
		this.versionLbl.setText(StringUtil.getUIString("about.version"));
		this.companyLbl.setText(StringUtil.getUIString("about.company"));
		this.companySiteLbl
				.setText(StringUtil.getUIString("about.companySite"));
		this.productSiteLbl
				.setText(StringUtil.getUIString("about.productSite"));
		this.emailLbl.setText(StringUtil.getUIString("about.email"));
		this.phoneLbl.setText(StringUtil.getUIString("about.phone"));
		this.version.setText(StringUtil.getAppString("readable.version"));
		this.company.setText(StringUtil.getUIString("about.lorent"));
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		logo = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		appName = new javax.swing.JLabel();
		versionLbl = new javax.swing.JLabel();
		companyLbl = new javax.swing.JLabel();
		companySiteLbl = new javax.swing.JLabel();
		version = new javax.swing.JLabel();
		company = new javax.swing.JLabel();
		companySite = new javax.swing.JLabel();
		productSiteLbl = new javax.swing.JLabel();
		productSite = new javax.swing.JLabel();
		emailLbl = new javax.swing.JLabel();
		email = new javax.swing.JLabel();
		phoneLbl = new javax.swing.JLabel();
		phone = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("about");

		logo.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/lvmc/resource/images/about.png"))); // NOI18N
		getContentPane().add(logo, java.awt.BorderLayout.WEST);

		appName.setFont(new java.awt.Font("宋体", 1, 18));

		versionLbl.setFont(new java.awt.Font("宋体", 1, 12));
		versionLbl.setText("Version:");

		companyLbl.setFont(new java.awt.Font("宋体", 1, 12));
		companyLbl.setText("Vendor:");

		companySiteLbl.setFont(new java.awt.Font("宋体", 1, 12));
		companySiteLbl.setText("Homepage:");

		version.setText("1.0");

		company.setText("lorentnetworks");

		companySite.setText("http://www.lorentnetworks.com/");

		productSiteLbl.setFont(new java.awt.Font("宋体", 1, 12));
		productSiteLbl.setText("Product:");

		productSite.setText("http://www.lorentnetworks.com/");

		emailLbl.setFont(new java.awt.Font("宋体", 1, 12));
		emailLbl.setText("email:");

		email.setText("service@cn.lorentnetworks.com");

		phoneLbl.setFont(new java.awt.Font("宋体", 1, 12));
		phoneLbl.setText("phone:");

		phone.setText("0757-81207132");

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
										.addGroup(
												jPanel1Layout
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
																						.addComponent(
																								versionLbl,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								58,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								companyLbl,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								58,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								companySiteLbl,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								75,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								productSiteLbl,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								75,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								emailLbl,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								75,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								phoneLbl,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								75,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								company)
																						.addComponent(
																								version)
																						.addComponent(
																								companySite)
																						.addComponent(
																								productSite)
																						.addComponent(
																								email)
																						.addComponent(
																								phone)))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(
																				10,
																				10,
																				10)
																		.addComponent(
																				appName,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				247,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(31, Short.MAX_VALUE)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												appName,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												32,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																versionLbl)
														.addComponent(version))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																companyLbl)
														.addComponent(company))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																companySiteLbl,
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																companySite,
																javax.swing.GroupLayout.Alignment.TRAILING))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																productSiteLbl)
														.addComponent(
																productSite))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(emailLbl)
														.addComponent(email))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(phoneLbl)
														.addComponent(phone))
										.addContainerGap(12, Short.MAX_VALUE)));

		getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_END);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				AboutDialog dialog = new AboutDialog(new javax.swing.JFrame(),
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
	private javax.swing.JLabel appName;
	private javax.swing.JLabel company;
	private javax.swing.JLabel companyLbl;
	private javax.swing.JLabel companySite;
	private javax.swing.JLabel companySiteLbl;
	private javax.swing.JLabel email;
	private javax.swing.JLabel emailLbl;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JLabel logo;
	private javax.swing.JLabel phone;
	private javax.swing.JLabel phoneLbl;
	private javax.swing.JLabel productSite;
	private javax.swing.JLabel productSiteLbl;
	private javax.swing.JLabel version;
	private javax.swing.JLabel versionLbl;
	// End of variables declaration//GEN-END:variables

}