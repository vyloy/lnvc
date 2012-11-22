/*
 * VodListPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import com.lorent.vovo.VovoVod;

/**
 *
 * @author  __USER__
 */
public class VodListPanel extends javax.swing.JPanel {

	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	private int pageIndex = 0;

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	private int maxPageIndex = -1;

	public int getMaxPageIndex() {
		return maxPageIndex;
	}

	public void setMaxPageIndex(int maxPageIndex) {
		this.maxPageIndex = maxPageIndex;
	}

	/** Creates new form VodListPanel */
	public VodListPanel() {
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

		navigatePagePanel = new javax.swing.JPanel();
		lastPageButton = new javax.swing.JButton();
		navigateNumberPanel = new javax.swing.JPanel();
		nextPageButton = new javax.swing.JButton();
		listPanel = new javax.swing.JPanel();

		setOpaque(false);
		setLayout(new java.awt.BorderLayout());

		navigatePagePanel.setOpaque(false);

		lastPageButton.setFont(new java.awt.Font("微软雅黑", 0, 12));
		lastPageButton.setText("\u4e0a\u4e00\u9875");
		lastPageButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				lastPageButtonActionPerformed(evt);
			}
		});
		navigatePagePanel.add(lastPageButton);

		navigateNumberPanel.setLayout(new java.awt.FlowLayout(
				java.awt.FlowLayout.CENTER, 5, 0));
		navigatePagePanel.add(navigateNumberPanel);

		nextPageButton.setFont(new java.awt.Font("微软雅黑", 0, 12));
		nextPageButton.setText("\u4e0b\u4e00\u9875");
		nextPageButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nextPageButtonActionPerformed(evt);
			}
		});
		navigatePagePanel.add(nextPageButton);

		add(navigatePagePanel, java.awt.BorderLayout.PAGE_END);

		listPanel.setOpaque(false);
		listPanel.setLayout(new java.awt.GridLayout(0, 6));
		add(listPanel, java.awt.BorderLayout.CENTER);
	}// </editor-fold>
	//GEN-END:initComponents

	private void nextPageButtonActionPerformed(java.awt.event.ActionEvent evt) {
		pageIndex++;
		if (pageIndex > maxPageIndex) {
			pageIndex = maxPageIndex;
		}
		VovoVod.exeC("vod", "reflashVodList", this, pageIndex);
	}

	private void lastPageButtonActionPerformed(java.awt.event.ActionEvent evt) {
		pageIndex--;
		if (pageIndex < 0) {
			pageIndex = 0;
		}
		VovoVod.exeC("vod", "reflashVodList", this, pageIndex);
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton lastPageButton;
	private javax.swing.JPanel listPanel;
	private javax.swing.JPanel navigateNumberPanel;
	private javax.swing.JPanel navigatePagePanel;
	private javax.swing.JButton nextPageButton;

	// End of variables declaration//GEN-END:variables

	public javax.swing.JPanel getNavigateNumberPanel() {
		return navigateNumberPanel;
	}

	public javax.swing.JPanel getNavigatePagePanel() {
		return navigatePagePanel;
	}

	public javax.swing.JPanel getListPanel() {
		return listPanel;
	}

}