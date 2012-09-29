/*
 * TestJtattoLF.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.lvmc.ui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author  __USER__
 */
public class TestJtattoLF extends javax.swing.JFrame {

	/** Creates new form TestJtattoLF */
	public TestJtattoLF() {
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

		jPopupMenu1 = new javax.swing.JPopupMenu();
		jMenuItem5 = new javax.swing.JMenuItem();
		jMenuItem6 = new javax.swing.JMenuItem();
		jPanel1 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jScrollPane3 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();
		jComboBox1 = new javax.swing.JComboBox();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTree1 = new javax.swing.JTree();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();
		jMenuItem2 = new javax.swing.JMenuItem();
		jMenuItem3 = new javax.swing.JMenuItem();
		jMenuItem4 = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();

		jMenuItem5.setText("Item");
		jPopupMenu1.add(jMenuItem5);

		jMenuItem6.setText("Item");
		jPopupMenu1.add(jMenuItem6);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jButton1.setText("jButton1");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		jPanel1.add(jButton1);

		jButton2.setText("jButton2");
		jPanel1.add(jButton2);

		jButton3.setText("jButton3");
		jPanel1.add(jButton3);

		jButton4.setText("jButton4");
		jPanel1.add(jButton4);

		jList1.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4",
					"Item 5" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane3.setViewportView(jList1);

		jPanel1.add(jScrollPane3);

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));
		jPanel1.add(jComboBox1);

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		jPanel1.add(jScrollPane1);

		jScrollPane2.setViewportView(jTree1);

		jPanel1.add(jScrollPane2);

		getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

		jMenu1.setText("File");

		jMenuItem1.setText("Item");
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem1);

		jMenuItem2.setText("Item");
		jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem2ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem2);

		jMenuItem3.setText("Item");
		jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem3ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem3);

		jMenuItem4.setText("Item");
		jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem4ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem4);

		jMenuBar1.add(jMenu1);

		jMenu2.setText("Edit");
		jMenuBar1.add(jMenu2);

		setJMenuBar(jMenuBar1);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		jPopupMenu1.show(jButton1, 0, jButton1.getSize().height);
	}

	private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {
		com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.setTheme("Default");
		try {
			UIManager
					.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		getRootPane().updateUI();
		SwingUtilities.updateComponentTreeUI(this);
	}

	private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
		com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme("Default");
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		getRootPane().updateUI();
		SwingUtilities.updateComponentTreeUI(this);
	}

	private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
		com.jtattoo.plaf.mint.MintLookAndFeel.setTheme("Default");
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		getRootPane().updateUI();
		SwingUtilities.updateComponentTreeUI(this);
	}

	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
		com.jtattoo.plaf.hifi.HiFiLookAndFeel.setTheme("Default");
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		getRootPane().updateUI();
		SwingUtilities.updateComponentTreeUI(this);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
				} catch (Exception e) {
					e.printStackTrace();
				}
				new TestJtattoLF().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JList jList1;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JMenuItem jMenuItem2;
	private javax.swing.JMenuItem jMenuItem3;
	private javax.swing.JMenuItem jMenuItem4;
	private javax.swing.JMenuItem jMenuItem5;
	private javax.swing.JMenuItem jMenuItem6;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPopupMenu jPopupMenu1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTree jTree1;
	// End of variables declaration//GEN-END:variables

}