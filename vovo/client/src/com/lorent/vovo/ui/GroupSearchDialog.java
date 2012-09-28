/*
 * GroupSearchPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;

import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.lorent.vovo.Vovo;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.VovoStringUtil;

/**
 *
 * @author  __USER__
 */
public class GroupSearchDialog extends javax.swing.JDialog {

	/** Creates new form GroupSearchPanel */
	public GroupSearchDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		//		jScrollPane1.setViewportView(box);
		listPanel.removeAll();
		jScrollPane1.getViewport().setOpaque(false);
		try {
			BufferedImage backgroundimg = Vovo.getMyContext().getDataManager()
					.getValue(
							Constants.DataKey.BACKGROUND_GAUSSIAN_IMAGE
									.toString());
			ImagePainter ip = new ImagePainter(backgroundimg);
			//			ip.setScaleToFit(true);
			ip.setScaleType(ScaleType.Distort);
			//			this.bannerPanel.setBackgroundPainter(ip);
			backgroundXPanel.setBackgroundPainter(ip);

			BufferedImage whiteImage = Vovo.getMyContext().getDataManager()
					.getValue(Constants.DataKey.WHITE_IMAGE.toString());
			ImagePainter ip2 = new ImagePainter(whiteImage);
			ip2.setScaleToFit(true);
			ip2.setScaleType(ScaleType.Distort);
			jXPanel1.setBackgroundPainter(ip2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		backgroundXPanel = new org.jdesktop.swingx.JXPanel();
		jPanel2 = new javax.swing.JPanel();
		jTextField1 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jXPanel1 = new org.jdesktop.swingx.JXPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		listPanel = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jPanel6 = new javax.swing.JPanel();
		jPanel7 = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(VovoStringUtil.getUIString("groupsearchdialog.title"));

		backgroundXPanel.setOpaque(false);
		backgroundXPanel.setLayout(new java.awt.BorderLayout());

		jPanel2.setOpaque(false);
		jPanel2.setLayout(new java.awt.GridBagLayout());

		jTextField1.setPreferredSize(new java.awt.Dimension(200, 21));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		jPanel2.add(jTextField1, gridBagConstraints);

		jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/zoom-3.png"))); // NOI18N
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		jPanel2.add(jButton1, gridBagConstraints);

		backgroundXPanel.add(jPanel2, java.awt.BorderLayout.PAGE_START);

		jXPanel1.setOpaque(false);
		jXPanel1.setLayout(new java.awt.BorderLayout());

		jScrollPane1
				.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane1.setOpaque(false);
		jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 280));

		listPanel.setOpaque(false);
		listPanel.setLayout(new javax.swing.BoxLayout(listPanel,
				javax.swing.BoxLayout.Y_AXIS));

		jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		listPanel.add(jPanel3);

		jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		listPanel.add(jPanel4);

		jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		listPanel.add(jPanel5);
		listPanel.add(jPanel6);
		listPanel.add(jPanel7);

		jScrollPane1.setViewportView(listPanel);

		jXPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		backgroundXPanel.add(jXPanel1, java.awt.BorderLayout.CENTER);

		getContentPane().add(backgroundXPanel, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("groupChat", "searchGroupChat", jTextField1.getText());
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				GroupSearchDialog dialog = new GroupSearchDialog(
						new javax.swing.JFrame(), true);
				//				javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
				//				jPanel3.setMaximumSize(new java.awt.Dimension(300, 32767));
				//				jPanel3.setMinimumSize(new java.awt.Dimension(300, 33));
				//				jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3,
				//						javax.swing.BoxLayout.Y_AXIS));
				List<GroupSearchPanelItem> list = new ArrayList<GroupSearchPanelItem>();
				GroupSearchPanelItem searchPanel = new GroupSearchPanelItem();

				for (int i = 0; i < 20; i++) {
					GroupListItem item = new GroupListItem();
					item.setRoomJid(String.valueOf(i));
					item.setGroupName(String.valueOf(i));
					item.setGroupDesc(String.valueOf(i));
					if (!searchPanel.addItem(item)) {
						list.add(searchPanel);

						searchPanel = new GroupSearchPanelItem();
						searchPanel.addItem(item);
					}
					//									jPanel3.add(item);
				}
				if (!list.contains(searchPanel)) {
					list.add(searchPanel);
				}
				System.out.println(list.size());
				for (GroupSearchPanelItem pitem : list) {
					dialog.box.add(pitem);
				}
				//								dialog.jScrollPane1.remove(dialog.jPanel3);
				//								dialog.jScrollPane1.setViewportView(jPanel3);
				dialog.jScrollPane1.repaint();
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
	private org.jdesktop.swingx.JXPanel backgroundXPanel;
	private javax.swing.JButton jButton1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField jTextField1;
	private org.jdesktop.swingx.JXPanel jXPanel1;
	private javax.swing.JPanel listPanel;
	// End of variables declaration//GEN-END:variables

	private Box box = Box.createVerticalBox();

	public void clearGroupChat() {
		box.removeAll();
		listPanel.removeAll();
	}

	public void clearTextField() {
		jTextField1.setText("");
	}

	public void repaint() {
		super.repaint();
		box.revalidate();
		box.repaint();
	}

	public void addGroupChatItem(GroupSearchPanelItem item) {
		//		box.add(item);
		//				jScrollPane1.setViewportView(jPanel3);
		//		jScrollPane1.revalidate();
		//		jScrollPane1.repaint();
		listPanel.add(item);
	}
}