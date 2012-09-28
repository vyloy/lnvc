package com.lorent.vovo.ui;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.lorent.vovo.Vovo;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;


public class DepartmentCheckboxListener extends MouseAdapter{

	
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
        	DefaultMutableTreeNode node = (DefaultMutableTreeNode)component;
        	DepartmentTreeNodePanel panel = (DepartmentTreeNodePanel)node.getUserObject();
            Rectangle chRect = panel.getCheckBox().getBounds();
            Rectangle rowRect = tree.getPathBounds(path);
            chRect.setLocation(chRect.x + rowRect.x, chRect.y + rowRect.y);
            if (e.getClickCount() == 1 && chRect.contains(p)) {
//            	panel.setSelected(!panel.isSelected());
            	setNodeSelecd(node,!panel.isSelected());
                tree.repaint();
            }
        }
    }
	
	public void setNodeSelecd(DefaultMutableTreeNode node,boolean b){
		DepartmentTreeNodePanel panel = (DepartmentTreeNodePanel)node.getUserObject();
		panel.setSelected(b);
		GroupChatCreateDialog dialog = Vovo.getMyContext().getViewManager().getView(Constants.ViewKey.groupChatCreateDialog.toString());
		if(node.isLeaf()){
			if(b){
				dialog.getSelectedUserList().addDepartmentTreeNodePanel(panel);
			}else{
				dialog.getSelectedUserList().removeDepartmentTreeNodePanel(panel);
			}
		}
		if(!node.isLeaf()){
			Enumeration en = node.children();
			while(en.hasMoreElements()){
				DefaultMutableTreeNode childNode = (DefaultMutableTreeNode)en.nextElement();
				setNodeSelecd(childNode,b);
			}
		}
	}
	
}
