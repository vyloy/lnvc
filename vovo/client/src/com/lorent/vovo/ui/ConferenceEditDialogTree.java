package com.lorent.vovo.ui;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

public class ConferenceEditDialogTree extends GroupChatCreateTree {

	class MyTreeCellRenderer implements TreeCellRenderer{

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean selected, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			DepartmentTreeNodePanel panel = (DepartmentTreeNodePanel) (node
					.getUserObject());

			panel.setBackground(tree.getBackground());
			panel.setForeground(tree.getForeground());
			panel.setEnabled(tree.isEnabled());
			panel.getCheckBox().setSelected(panel.isSelected());
			boolean departmentFlag = panel.getInfo().isDepartmentFlag();
			
//			System.out.println("leaf:"+leaf+" expanded:"+expanded+" departmentFlag:"+departmentFlag);
			
			panel.setInfo(panel.getInfo());
//			panel.setExpanded(expanded);
			
			panel.repaint();
			panel.getjLabel4().repaint();
			if (!panel.getInfo().isDepartmentFlag()) {
				panel.getjLabel4().setVisible(false);
				panel.setFirstImgVisible(false);
			}
			return panel;
		}
		
	}
	
	public ConferenceEditDialogTree(Class<? extends TreeNodeInfoPanel> pc) {
		super(pc);
	}

	@Override
	public void init() {
//		super.init();
		setCellRenderer(new MyTreeCellRenderer());
	}

	
}
