package com.lorent.vovo.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.lorent.common.manager.ViewManager;
import com.lorent.common.tree.DepartmentBean;
import com.lorent.common.tree.MemberBean;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.bean.TreeNodeInfo;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;
import com.lorent.vovo.util.VovoStringUtil;


public class OrgTree extends DepartmentTreeUI{
	public OrgTree(Class<? extends TreeNodeInfoPanel> pc) {
		super(pc);
		instance = this;
	}
	
	private OrgTree instance;
	private JPopupMenu memberInfoMenu;
	private MemberInfoPanel memberInfoPanel;
	private JPopupMenu rightPopupMenu;
	private JMenuItem privateChatItem;
	private JMenuItem multiChatItem;
	
	@Override
	public void init() {
		setCellRenderer(new MyRenderer(this));
		memberInfoMenu = new JPopupMenu();
		memberInfoPanel = new MemberInfoPanel();
		memberInfoMenu.add(memberInfoPanel);
//		this.setComponentPopupMenu(memberInfoMenu);
		this.addMouseListener(new MyMouseListener());
		rightPopupMenu = new JPopupMenu();
//		privateChatItem = new JMenuItem();
//		privateChatItem.addActionListener(null);
		multiChatItem = new JMenuItem(VovoStringUtil.getUIString("OrgTree.multiChatItem.txt"));
		multiChatItem.addActionListener(new MultiChatItemActionListener());
		rightPopupMenu.add(multiChatItem);
	}
	
	private class MultiChatItemActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			rightPopupMenu.setVisible(false);
			TreePath[] treePaths = OrgTree.this.getSelectionPaths();
			List<String> members = new ArrayList<String>();
			for(int i=0;i<treePaths.length;i++){
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) treePaths[i].getLastPathComponent();
				TreeNodeInfoPanel infoPanel = (TreeNodeInfoPanel) (treeNode.getUserObject());
				if(infoPanel!=null && !infoPanel.getInfo().isDepartmentFlag()){
            		if(!infoPanel.getInfo().getMember().getLccAccount().equals(DataUtil.getUserName())){
            			members.add(infoPanel.getInfo().getMember().getLccAccount());
            		}
            	}
			}
			GroupListItem item = (GroupListItem) Vovo.exeC("groupChat", "createGroupChat",
					"多人聊天", "", members, true);
			if (item!=null) {
				String roomJid = item.getRoomJid();
				Vovo.exeC("groupChat", "fetchOneGroupChatAuthority", roomJid);
				Vovo.exeC("groupChat", "showGroupChat", item.getInfo());
			}
		}
		
	}
	
	class MyMouseListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton()==MouseEvent.BUTTON1 && e.getClickCount()==2){
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)instance.getLastSelectedPathComponent();
				if(node != null){
					TreeNodeInfoPanel infoPanel = (TreeNodeInfoPanel) (node.getUserObject());
					TreeNodeInfo info = infoPanel.getInfo();
					if(!info.isDepartmentFlag()){
						if(info.getMember().getLccAccount().equals(DataUtil.getUserName())){
							Vovo.exeC("main", "showUserSettingDialog");	
						}else{
							Vovo.exeC("chat", "showFriendChat", info.getMember());
						}
					}
				}
			}else if(e.getButton() == MouseEvent.BUTTON3 && e.getClickCount()==1){
				TreePath[] treePaths = OrgTree.this.getSelectionPaths();
				if(treePaths.length<2){
					return;
				}
				int count = 0;
				for(int i=0;i<treePaths.length;i++){
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) treePaths[i].getLastPathComponent();
					TreeNodeInfoPanel infoPanel = (TreeNodeInfoPanel) (treeNode.getUserObject());
					if(infoPanel==null || infoPanel.getInfo().isDepartmentFlag()){
                		continue;
                	}else if(infoPanel!=null && !infoPanel.getInfo().isDepartmentFlag()){
                		if(infoPanel.getInfo().getMember().getLccAccount().equals(DataUtil.getUserName())){
                			continue;
                		}
                	}
					count++;
				}
				if(count>1){
					rightPopupMenu.show(OrgTree.this, e.getX(), e.getY());
				}
			}
		}
	}
	
	class MyRenderer implements TreeCellRenderer {

        private TreePath oldSelectedPath = null;
        
        private MyRenderer(final JTree tree)
        {
            tree.addMouseMotionListener(new MouseMotionListener()
            {
                public void mouseDragged(MouseEvent mouseEvent)
                {
                    // Nothing to do
                }
 
                public void mouseMoved(MouseEvent mouseEvent)
                {
                    DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
                    int selRow = tree.getRowForLocation(mouseEvent.getX(), mouseEvent.getY());
                    if (selRow < 0)
                    {
                        TreePath currentSelected = oldSelectedPath;
                        oldSelectedPath = null;
                        if (currentSelected != null){
                            treeModel.nodeChanged((TreeNode) currentSelected.getLastPathComponent());
                        }
                        if(memberInfoMenu.isVisible()){
                        	memberInfoMenu.setVisible(false);
                        }
                    } else
                    {
                        TreePath selectedPath = tree.getPathForRow(selRow);
                        if ((oldSelectedPath == null) || !selectedPath.equals(oldSelectedPath))
                        {
                            oldSelectedPath = selectedPath;
                            DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) oldSelectedPath.getLastPathComponent();
                            
                            if(treeNode==null){
                            	return;
                            }else if(treeNode!=null){
                            	TreeNodeInfoPanel infoPanel = (TreeNodeInfoPanel) (treeNode.getUserObject());
                            	if(infoPanel==null || infoPanel.getInfo().isDepartmentFlag()){
                            		return;
                            	}
                            }
                            treeModel.nodeChanged((TreeNode) oldSelectedPath.getLastPathComponent());
                            Rectangle rowBounds = tree.getRowBounds(selRow);
                            MainFrame mainFrame = Vovo.getViewManager().getView(Constants.ViewKey.MAINFRAME.toString());
                            if(Vovo.getViewManager().isCheckShowLeft(mainFrame, memberInfoMenu.getPreferredSize().width)){
//                            	System.out.println(" no no ");
                            	memberInfoMenu.show(tree, 0 - memberInfoMenu.getPreferredSize().width + rowBounds.x, rowBounds.y);
                            }else{
                            	System.out.println("mainFrame.getWidth() :" + memberInfoMenu.getPreferredSize().width);
                            	System.out.println("rowBounds.width :" + rowBounds.width);
                            	if(mainFrame.getWidth()>rowBounds.width){
                            		memberInfoMenu.show(tree, rowBounds.x + rowBounds.width, rowBounds.y);
                            	}else{
                            		memberInfoMenu.show(tree, 0 - rowBounds.x + mainFrame.getWidth(), rowBounds.y);
                            	}
                            }
//                            ViewManager.HorizontalLocation hl = Vovo.getViewManager().getComponentHorizontalLocationInScreen(Vovo.getViewManager().getView(Constants.ViewKey.MAINFRAME.toString()));
//                            if(hl.equals(ViewManager.HorizontalLocation.CENTER)||hl.equals(ViewManager.HorizontalLocation.RIGHT)){
//                            	memberInfoMenu.show(tree, 0 - memberInfoMenu.getWidth() + rowBounds.x, rowBounds.y);
//                            }else if(hl.equals(ViewManager.HorizontalLocation.LEFT)){
//                            	
//                            }
                        }
                    }
                    tree.repaint();
                }
            });
        }
		
		
		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean sel, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			TreeNodeInfoPanel infoPanel = (TreeNodeInfoPanel) (node.getUserObject());
			TreeNodeInfo info = infoPanel.getInfo();
			boolean isFocus = (oldSelectedPath != null) && (value == oldSelectedPath.getLastPathComponent());
			JPanel panel = null;
			if(info.isDepartmentFlag()){
				DepartmentBean bean = info.getDepartment();
				SimpleDeptNodePanel deptPanel = new SimpleDeptNodePanel();
				deptPanel.setDeptInfo(bean);
				panel = deptPanel;
				if(isFocus){
					if(memberInfoMenu.isVisible()){
						memberInfoMenu.setVisible(false);	
					}			
				}
			}else{
				MemberBean bean = info.getMember();
				MemberTreeNodePanel memberPanel = new MemberTreeNodePanel();
				memberPanel.setMemberInfo(bean);
				panel = memberPanel;
				if(isFocus){
					memberInfoPanel.setInfo(bean);
				}
			}

			if(sel){
				panel.setOpaque(true);
				panel.setBackground(new Color(252, 235, 166));
			}else{
				if(isFocus){
					panel.setOpaque(true);
					panel.setBackground(new Color(0xC3E2F8));
				}else{
					panel.setOpaque(false);
				}
			}
			return panel;
		}

	}
	
	
}
