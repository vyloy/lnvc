/*
 * ConferenceEditDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.jtattoo.plaf.vovoglass.VoVoTabbedPaneUI;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.ui.DepartmentTreeUI.CheckRenderer;
import com.lorent.vovo.util.Constants;

/**
 *
 * @author  __USER__
 */
public class ConferenceEditDialog extends javax.swing.JDialog {

	private static Logger log = Logger.getLogger(ConferenceEditDialog.class);

	private SelectedUserList selectedUserList = new SelectedUserList();

	public SelectedUserList getSelectedUserList() {
		return selectedUserList;
	}

	private int opt = -1;
	private Integer confId = -1;
	private String confNo;
	private ConferenceEditDialogTree leftTree = null;
	private ConferencePanel conferencePanel;

	public ConferencePanel getConferencePanel() {
		return conferencePanel;
	}

	public void setConferencePanel(ConferencePanel conferencePanel) {
		this.conferencePanel = conferencePanel;
	}

	public String getConfNo() {
		return confNo;
	}

	public void setConfNo(String confNo) {
		this.confNo = confNo;
	}

	public Integer getConfId() {
		return confId;
	}

	public void setConfId(Integer confId) {
		this.confId = confId;
	}

	public int getOpt() {
		return opt;
	}

	public void setOpt(int opt) {
		this.opt = opt;
	}

	class CheckBoxListener extends MouseAdapter {

		ConferenceEditDialog dialog = null;

		public ConferenceEditDialog getDialog() {
			return dialog;
		}

		public void setDialog(ConferenceEditDialog dialog) {
			this.dialog = dialog;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			JTree tree = (JTree) e.getSource();
			Point p = e.getPoint();
			int x = e.getX();
			int y = e.getY();

			int row = tree.getRowForLocation(x, y);
			TreePath path = tree.getPathForRow(row);

			if (null == path) {
				return;
			}

			Object component = path.getLastPathComponent();
			if (null == component) {
				return;
			}

			if (component instanceof DefaultMutableTreeNode) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) component;
				DepartmentTreeNodePanel panel = (DepartmentTreeNodePanel) node
						.getUserObject();
				Rectangle chRect = panel.getCheckBox().getBounds();
				Rectangle rowRect = tree.getPathBounds(path);
				chRect.setLocation(chRect.x + rowRect.x, chRect.y + rowRect.y);
				if (e.getClickCount() == 1 && chRect.contains(p)) {
					//	            	panel.setSelected(!panel.isSelected());
					setNodeSelecd(node, !panel.isSelected());
					tree.repaint();
				}
			}
		}

		public void setNodeSelecd(DefaultMutableTreeNode node, boolean b) {
			DepartmentTreeNodePanel panel = (DepartmentTreeNodePanel) node
					.getUserObject();
			panel.setSelected(b);
			//			ConferenceEditDialogTree dialog = Vovo.getMyContext().getViewManager().getView(Constants.ViewKey.groupChatCreateDialog.toString());
			if (node.isLeaf()) {
				if (b) {
					dialog.getSelectedUserList().addDepartmentTreeNodePanel(
							panel);
				} else {
					dialog.getSelectedUserList().removeDepartmentTreeNodePanel(
							panel);
				}
			}
			if (!node.isLeaf()) {
				Enumeration en = node.children();
				while (en.hasMoreElements()) {
					DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) en
							.nextElement();
					setNodeSelecd(childNode, b);
				}
			}
		}
	}

	/** Creates new form ConferenceEditDialog */
	public ConferenceEditDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();

		leftTree = (ConferenceEditDialogTree) TreeManager.createNewTree(this
				.getClass(), ConferenceEditDialogTree.class,
				DepartmentTreeNodePanel.class);
		leftScrollPane.setViewportView(leftTree);
		leftScrollPane.getViewport().setOpaque(false);
		leftTree.setOpaque(false);
		CheckBoxListener checkBoxListener = new CheckBoxListener();
		checkBoxListener.setDialog(this);
		leftTree.addMouseListener(checkBoxListener);
		rightScrollPane.setViewportView(selectedUserList);
		selectedUserList.setOpaque(false);
		rightScrollPane.getViewport().setOpaque(false);
		try {
			BufferedImage areaimg = Vovo.getMyContext().getDataManager()
					.getValue(Constants.DataKey.BACKGROUND_IMAGE.toString());

			//			GaussianFilter gaussianFilter = new GaussianFilter(8.5f);
			//			BufferedImage resultImage = gaussianFilter.filter(areaimg, areaimg);

			BufferedImage resultImage = Vovo.getMyContext().getDataManager()
					.getValue(
							Constants.DataKey.BACKGROUND_GAUSSIAN_IMAGE
									.toString());

			ImagePainter ip = new ImagePainter(resultImage);
			//			ip.setScaleToFit(true);
			ip.setScaleType(ScaleType.Distort);

			backgroundXPanel.setBackgroundPainter(ip);

			BufferedImage whiteimg = ImageIO.read(getClass().getResource(
					"/com/lorent/vovo/resource/images/whitealpha.png"));
			ImagePainter imagePainter = new ImagePainter(whiteimg);
			imagePainter.setScaleType(ScaleType.Distort);
			imagePainter.setScaleToFit(true);
			advanceOptionXPanel.setBackgroundPainter(imagePainter);

			ImagePainter imagePainter2 = new ImagePainter(whiteimg);
			imagePainter2.setScaleType(ScaleType.Distort);
			imagePainter2.setScaleToFit(true);
			baseXPanel.setBackgroundPainter(imagePainter2);

			ImagePainter imagePainter3 = new ImagePainter(whiteimg);
			imagePainter3.setScaleType(ScaleType.Distort);
			imagePainter3.setScaleToFit(true);
			noticeXPanel.setBackgroundPainter(imagePainter3);

			leftScrollPane.getViewport().setOpaque(false);
		} catch (Exception e) {
			log.error("ConferenceEditDialog()", e);
			e.printStackTrace();
		}
		mainTabbedPane.setUI(new VoVoTabbedPaneUI());
		//		jTable1.getColumnModel().getColumn(1).setCellEditor(
		//				new DefaultCellEditor(new JCheckBox()));
		DefaultTableModel model = (DefaultTableModel) confUserListTable
				.getModel();
		TableColumn column = confUserListTable.getColumnModel().getColumn(4);
		TableColumn column2 = confUserListTable.getColumnModel().getColumn(5);
		confUserListTable.removeColumn(column2);
		confUserListTable.removeColumn(column);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		backgroundXPanel = new org.jdesktop.swingx.JXPanel();
		mainTabbedPane = new javax.swing.JTabbedPane();
		baseXPanel = new org.jdesktop.swingx.JXPanel();
		jLabel1 = new javax.swing.JLabel();
		conferenceNameTextField = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		conferencePasswordField = new javax.swing.JPasswordField();
		jLabel3 = new javax.swing.JLabel();
		confenenceTopicTextField = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		conferenceDescriptionTextArea = new javax.swing.JTextArea();
		jLabel10 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		noticeXPanel = new org.jdesktop.swingx.JXPanel();
		jLabel8 = new javax.swing.JLabel();
		emailNoticeCheckBox = new javax.swing.JCheckBox();
		smsNoticeCheckBox = new javax.swing.JCheckBox();
		vovoNoticeCheckBox = new javax.swing.JCheckBox();
		leftScrollPane = new javax.swing.JScrollPane();
		rightScrollPane = new javax.swing.JScrollPane();
		advanceOptionXPanel = new org.jdesktop.swingx.JXPanel();
		jLabel5 = new javax.swing.JLabel();
		confTypeComboBox = new javax.swing.JComboBox();
		jLabel6 = new javax.swing.JLabel();
		confRoleComboBox = new javax.swing.JComboBox();
		jLabel7 = new javax.swing.JLabel();
		needApplyComboBox = new javax.swing.JComboBox();
		jScrollPane2 = new javax.swing.JScrollPane();
		confUserListTable = new javax.swing.JTable();
		jLabel9 = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		backgroundXPanel.setLayout(new java.awt.BorderLayout());

		mainTabbedPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1,
				1, 1, 1));
		mainTabbedPane
				.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

		baseXPanel.setOpaque(false);

		jLabel1.setText("*\u540d\u79f0");

		jLabel2.setText("\u5bc6\u7801");

		jLabel3.setText("\u4e3b\u9898");

		jLabel4.setText("\u63cf\u8ff0");

		jScrollPane1
				.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		conferenceDescriptionTextArea.setColumns(20);
		conferenceDescriptionTextArea.setLineWrap(true);
		conferenceDescriptionTextArea.setRows(5);
		jScrollPane1.setViewportView(conferenceDescriptionTextArea);

		jLabel10.setText("(\u965020\u5b57)");

		jLabel11.setText("(\u965015\u4f4d)");

		jLabel12.setText("(\u965030\u5b57)");

		jLabel13.setText("(\u9650200\u5b57)");

		javax.swing.GroupLayout baseXPanelLayout = new javax.swing.GroupLayout(
				baseXPanel);
		baseXPanel.setLayout(baseXPanelLayout);
		baseXPanelLayout
				.setHorizontalGroup(baseXPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								baseXPanelLayout
										.createSequentialGroup()
										.addGap(58, 58, 58)
										.addGroup(
												baseXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(jLabel4)
														.addComponent(jLabel3)
														.addComponent(jLabel2)
														.addComponent(jLabel1))
										.addGap(18, 18, 18)
										.addGroup(
												baseXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																conferencePasswordField,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																483,
																Short.MAX_VALUE)
														.addComponent(
																confenenceTopicTextField,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																483,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																483,
																Short.MAX_VALUE)
														.addComponent(
																conferenceNameTextField,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																483,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												baseXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel10)
														.addComponent(jLabel12)
														.addComponent(jLabel11)
														.addComponent(jLabel13))
										.addGap(12, 12, 12)));
		baseXPanelLayout
				.setVerticalGroup(baseXPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								baseXPanelLayout
										.createSequentialGroup()
										.addGap(26, 26, 26)
										.addGroup(
												baseXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																conferenceNameTextField)
														.addComponent(jLabel10))
										.addGap(18, 18, 18)
										.addGroup(
												baseXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																conferencePasswordField)
														.addComponent(jLabel11))
										.addGap(18, 18, 18)
										.addGroup(
												baseXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(
																confenenceTopicTextField)
														.addComponent(jLabel12))
										.addGroup(
												baseXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																baseXPanelLayout
																		.createSequentialGroup()
																		.addGap(
																				18,
																				18,
																				18)
																		.addGroup(
																				baseXPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel4)
																						.addComponent(
																								jScrollPane1,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								160,
																								Short.MAX_VALUE))
																		.addGap(
																				76,
																				76,
																				76))
														.addGroup(
																baseXPanelLayout
																		.createSequentialGroup()
																		.addGap(
																				27,
																				27,
																				27)
																		.addComponent(
																				jLabel13)
																		.addContainerGap()))));

		mainTabbedPane.addTab("\u57fa\u672c\u4fe1\u606f", baseXPanel);

		noticeXPanel.setOpaque(false);

		jLabel8.setText("\u901a\u77e5\u65b9\u5f0f");

		emailNoticeCheckBox.setText("EMail");
		emailNoticeCheckBox.setOpaque(false);

		smsNoticeCheckBox.setText("\u77ed\u4fe1");
		smsNoticeCheckBox.setOpaque(false);

		vovoNoticeCheckBox.setText("VoVo");
		vovoNoticeCheckBox.setOpaque(false);

		leftScrollPane.setOpaque(false);

		rightScrollPane.setOpaque(false);

		javax.swing.GroupLayout noticeXPanelLayout = new javax.swing.GroupLayout(
				noticeXPanel);
		noticeXPanel.setLayout(noticeXPanelLayout);
		noticeXPanelLayout
				.setHorizontalGroup(noticeXPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								noticeXPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												noticeXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																noticeXPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				leftScrollPane,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				293,
																				Short.MAX_VALUE)
																		.addGap(
																				39,
																				39,
																				39)
																		.addComponent(
																				rightScrollPane,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				302,
																				Short.MAX_VALUE))
														.addGroup(
																noticeXPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel8)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				emailNoticeCheckBox)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				smsNoticeCheckBox)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				vovoNoticeCheckBox)))
										.addContainerGap()));
		noticeXPanelLayout
				.setVerticalGroup(noticeXPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								noticeXPanelLayout
										.createSequentialGroup()
										.addGap(44, 44, 44)
										.addGroup(
												noticeXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																rightScrollPane,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																286,
																Short.MAX_VALUE)
														.addComponent(
																leftScrollPane,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																286,
																Short.MAX_VALUE))
										.addGap(18, 18, 18)
										.addGroup(
												noticeXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel8)
														.addComponent(
																emailNoticeCheckBox)
														.addComponent(
																smsNoticeCheckBox)
														.addComponent(
																vovoNoticeCheckBox))
										.addContainerGap()));

		mainTabbedPane.addTab("\u4f1a\u8bae\u901a\u77e5", noticeXPanel);

		advanceOptionXPanel.setOpaque(false);

		jLabel5.setText("\u4f1a\u8bae\u7c7b\u578b\u540d\u79f0");

		confTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel());

		jLabel6.setText("\u4f1a\u8bae\u9ed8\u8ba4\u89d2\u8272");

		confRoleComboBox.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		jLabel7.setText("\u662f\u5426\u9700\u8981\u7533\u8bf7");

		needApplyComboBox.setModel(new DefaultComboBoxModel(new String[] { "是",
				"否" }));

		confUserListTable.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "用户", "主持人", "主讲人", "普通与会者", "userid",
						"username" }) {
			Class[] types = new Class[] { java.lang.Object.class,
					java.lang.Boolean.class, java.lang.Boolean.class,
					java.lang.Boolean.class, java.lang.Object.class,
					java.lang.Object.class };
			boolean[] canEdit = new boolean[] { false, true, true, true, false,
					false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane2.setViewportView(confUserListTable);

		jLabel9.setText("\u7528\u6237\u89d2\u8272\u8bbe\u7f6e");

		javax.swing.GroupLayout advanceOptionXPanelLayout = new javax.swing.GroupLayout(
				advanceOptionXPanel);
		advanceOptionXPanel.setLayout(advanceOptionXPanelLayout);
		advanceOptionXPanelLayout
				.setHorizontalGroup(advanceOptionXPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								advanceOptionXPanelLayout
										.createSequentialGroup()
										.addGap(69, 69, 69)
										.addGroup(
												advanceOptionXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabel9,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																589,
																Short.MAX_VALUE)
														.addGroup(
																advanceOptionXPanelLayout
																		.createSequentialGroup()
																		.addGroup(
																				advanceOptionXPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								jScrollPane2,
																								javax.swing.GroupLayout.Alignment.LEADING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								514,
																								Short.MAX_VALUE)
																						.addGroup(
																								advanceOptionXPanelLayout
																										.createSequentialGroup()
																										.addGroup(
																												advanceOptionXPanelLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING)
																														.addComponent(
																																jLabel7,
																																javax.swing.GroupLayout.Alignment.LEADING,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																69,
																																Short.MAX_VALUE)
																														.addComponent(
																																jLabel6,
																																javax.swing.GroupLayout.Alignment.LEADING,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																69,
																																Short.MAX_VALUE)
																														.addComponent(
																																jLabel5,
																																javax.swing.GroupLayout.Alignment.LEADING,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																69,
																																Short.MAX_VALUE))
																										.addGap(
																												27,
																												27,
																												27)
																										.addGroup(
																												advanceOptionXPanelLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																needApplyComboBox,
																																0,
																																418,
																																Short.MAX_VALUE)
																														.addComponent(
																																confRoleComboBox,
																																0,
																																418,
																																Short.MAX_VALUE)
																														.addComponent(
																																confTypeComboBox,
																																0,
																																418,
																																Short.MAX_VALUE))))
																		.addGap(
																				75,
																				75,
																				75)))));
		advanceOptionXPanelLayout
				.setVerticalGroup(advanceOptionXPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								advanceOptionXPanelLayout
										.createSequentialGroup()
										.addGap(29, 29, 29)
										.addGroup(
												advanceOptionXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabel5,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																confTypeComboBox))
										.addGap(18, 18, 18)
										.addGroup(
												advanceOptionXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabel6,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																confRoleComboBox))
										.addGap(18, 18, 18)
										.addGroup(
												advanceOptionXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabel7,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																needApplyComboBox))
										.addGap(18, 18, 18)
										.addComponent(
												jLabel9,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGap(12, 12, 12)
										.addComponent(
												jScrollPane2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												193,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(23, 23, 23)));

		mainTabbedPane.addTab("\u9ad8\u7ea7\u9009\u9879", advanceOptionXPanel);

		backgroundXPanel.add(mainTabbedPane, java.awt.BorderLayout.CENTER);

		jPanel4.setOpaque(false);
		jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		okButton.setText("\u786e\u5b9a");
		okButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});
		jPanel4.add(okButton);

		cancelButton.setText("\u53d6\u6d88");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
		jPanel4.add(cancelButton);

		backgroundXPanel.add(jPanel4, java.awt.BorderLayout.SOUTH);

		getContentPane().add(backgroundXPanel, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("conference", "doCreateOrModifyConference", this);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				ConferenceEditDialog dialog = new ConferenceEditDialog(
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
	private org.jdesktop.swingx.JXPanel advanceOptionXPanel;
	private org.jdesktop.swingx.JXPanel backgroundXPanel;
	private org.jdesktop.swingx.JXPanel baseXPanel;
	private javax.swing.JButton cancelButton;
	private javax.swing.JComboBox confRoleComboBox;
	private javax.swing.JComboBox confTypeComboBox;
	private javax.swing.JTable confUserListTable;
	private javax.swing.JTextField confenenceTopicTextField;
	private javax.swing.JTextArea conferenceDescriptionTextArea;
	private javax.swing.JTextField conferenceNameTextField;
	private javax.swing.JPasswordField conferencePasswordField;
	private javax.swing.JCheckBox emailNoticeCheckBox;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane leftScrollPane;
	private javax.swing.JTabbedPane mainTabbedPane;
	private javax.swing.JComboBox needApplyComboBox;
	private org.jdesktop.swingx.JXPanel noticeXPanel;
	private javax.swing.JButton okButton;
	private javax.swing.JScrollPane rightScrollPane;
	private javax.swing.JCheckBox smsNoticeCheckBox;
	private javax.swing.JCheckBox vovoNoticeCheckBox;

	// End of variables declaration//GEN-END:variables
	public javax.swing.JTextField getConfenenceTopicTextField() {
		return confenenceTopicTextField;
	}

	public javax.swing.JTextArea getConferenceDescriptionTextArea() {
		return conferenceDescriptionTextArea;
	}

	public javax.swing.JTextField getConferenceNameTextField() {
		return conferenceNameTextField;
	}

	public javax.swing.JPasswordField getConferencePasswordField() {
		return conferencePasswordField;
	}

	public javax.swing.JComboBox getConfTypeComboBox() {
		return confTypeComboBox;
	}

	public javax.swing.JComboBox getConfRoleComboBox() {
		return confRoleComboBox;
	}

	public javax.swing.JComboBox getNeedApplyComboBox() {
		return needApplyComboBox;
	}

	public javax.swing.JTabbedPane getMainTabbedPane() {
		return mainTabbedPane;
	}

	public org.jdesktop.swingx.JXPanel getNoticeXPanel() {
		return noticeXPanel;
	}

	public javax.swing.JCheckBox getEmailNoticeCheckBox() {
		return emailNoticeCheckBox;
	}

	public javax.swing.JCheckBox getSmsNoticeCheckBox() {
		return smsNoticeCheckBox;
	}

	public javax.swing.JCheckBox getVovoNoticeCheckBox() {
		return vovoNoticeCheckBox;
	}

	public javax.swing.JTable getConfUserListTable() {
		return confUserListTable;
	}

}