package com.lorent.lvmc.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import net.infonode.docking.DockingWindow;
import net.infonode.docking.OperationAbortedException;
import net.infonode.docking.RootWindow;
import net.infonode.docking.TabWindow;
import net.infonode.docking.View;
import net.infonode.docking.util.DockingUtil;
import net.infonode.docking.util.ViewMap;

import org.apache.log4j.Logger;

import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.ui.BasicPopupMenu.AddPanelMenuItemStrategy;
import com.lorent.lvmc.ui.DockingLayoutMeetingPanel.MyView;

public class WindowPopupMenu extends BasicPopupMenu {

	/**
	 * 
	 */
	Logger log = Logger.getLogger(WindowPopupMenu.class);
	
	public WindowPopupMenu(java.awt.Component c){
		super(c);
		gridLayout1 = new GridLayout(2,3); 
		imagePath = "/com/lorent/lvmc/resource/images/window/";
		init(new InitWindowPopupMenu(), new AddPanelMenuItemStrategy());
	}
	
	/*protected void init(){
		//GEN-FIRST:event_viewsMenuItemStateChanged
		this.setLayout(gridLayout1);
        this.removeAll();
        String fileName = "";
        DockingLayoutMeetingPanel dockingPanel;
		try {
			dockingPanel = (DockingLayoutMeetingPanel)ViewManager.getComponent(DockingLayoutMeetingPanel.class);
			final RootWindow rootWindow = dockingPanel.getRootWindow();
	        ViewMap viewMap = dockingPanel.getViewMap();
	        for (int i = 0; i < viewMap.getViewCount(); i++) {
	            final MyView view = (MyView)viewMap.getView(i);
	            if(view.getImageFileName()==null || "".equals(view.getImageFileName().trim())){
	            	fileName = imagePath + "";
	            	continue;
	            }else{
	            	fileName = imagePath + view.getImageFileName();
	            }
	            ImageIcon icon = new ImageIcon(this.getClass().getResource(fileName));
	            java.awt.event.MouseAdapter mouseAdapter = new java.awt.event.MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                	if(e.getButton() == MouseEvent.BUTTON1){
	                		DockingUtil.addWindow(view, rootWindow);
	                        view.restoreFocus();
	                        getCurrentObj().setVisible(false);
	                	}
	                }
	            };
	            this.addPanel(icon, view.getTitle(), view.getTitle(), mouseAdapter);
	        }
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}*/
	
	
	public class InitWindowPopupMenu implements InitPopupMenuInter{

		/**
		 * 窗口聚焦过程分四种情况：
		 * 1        要聚焦的窗口没有最大化也没有最小化，且没有最大化的非聚焦窗口，
		 * 先设置所有的窗口view.setFocused(false);然后对聚焦窗口view.setFocused(true);view.restoreFocus();
		 * 2       要聚焦的窗口的窗口已经最大化无需变化，判断聚焦窗口是否最大化，先获取窗口所在的tabwindow:TabWindow tabWindow = DockingUtil.getTabWindowFor(view);
		 * 之后用tabWindow.isMaximized()判断是否最大化。
		 * 3       要聚焦的窗口最小化，对所有窗口设置view.setFocused(false);
		 * 然后对聚焦窗口view.setFocused(true);view.restoreFocus();view.restore();
		 * 4        有最大化的非聚焦窗口，先获取最大化的非聚焦窗口的tabwindow:(TabWindow)(rootWindow.getMaximizedWindow());
		 * 再把tabwindow的view保存，把tabwindow关闭（为了还原后清除选中状态），重新把保存的view加入到rootWindow
		 * 最后对要聚焦窗口通过view.setFocused(true);view.restoreFocus();设置，
		 * 还要对聚焦窗口所要在的TABWINDOW设置showTab.requestFocusInWindow();
		 * 
		 * 由于以上操作会造成鼠标点击窗口聚焦，原聚焦窗口未发生变化，在DockingLayoutMeetingPanel类中为rootWindow
		 * 添加监听器DockingWindowAdapter中的方法viewFocusChanged中对所有窗口view.setFocused(false);然后
		 * 设置点击窗口聚焦view.setFocused(true);view.restoreFocus();
		 *   
		 */
		@Override
		public void init(final AddMenuItemStrategyInter strategy) {
			WindowPopupMenu.this.setLayout(gridLayout1);
			WindowPopupMenu.this.removeAll();
	        String fileName = "";
	        
			try {
				DockingLayoutMeetingPanel dockingPanel = (DockingLayoutMeetingPanel)ViewManager.getComponent(DockingLayoutMeetingPanel.class);
				final RootWindow rootWindow = dockingPanel.getRootWindow();
		        final ViewMap viewMap = dockingPanel.getViewMap();
		        for (int i = 0; i < viewMap.getViewCount(); i++) {
		            final MyView view = (MyView)viewMap.getView(i);
		            if(view.getImageFileName()==null || "".equals(view.getImageFileName().trim())){
		            	fileName = imagePath + "";
		            	continue;
		            }else{
		            	fileName = imagePath + view.getImageFileName();
		            }
//		            view.setFocused(false);
		            ImageIcon icon = new ImageIcon(this.getClass().getResource(fileName));
		            java.awt.event.MouseAdapter mouseAdapter = new java.awt.event.MouseAdapter() {
		                @Override
		                public void mouseClicked(MouseEvent e) {
		                	mouseClickProc(e);
		                }
		                
		                public void mouseClickProc(MouseEvent e){
		                	if(e.getButton() == MouseEvent.BUTTON1){
		                		getCurrentObj().setVisible(false);
		                		TabWindow tabWindow = DockingUtil.getTabWindowFor(view);
		                		if(tabWindow != null && tabWindow.isMaximized()){
		                			if(view == tabWindow.getSelectedWindow()){
		                				return;
		                			}
		                		}
//		                		KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
		                		if(rootWindow.getMaximizedWindow()!=null){
		                			TabWindow maxTab = (TabWindow)(rootWindow.getMaximizedWindow());
		                			maxTab.restore();
		                			java.util.ArrayList<DockingWindow> list = new java.util.ArrayList<DockingWindow>();
		                			int count = maxTab.getChildWindowCount();
		                			for(int i=0;i<count;i++){
		                				list.add(maxTab.getChildWindow(i));
		                			}
		                			maxTab.close();
		                			
		                			for(DockingWindow w:list){
		                				DockingUtil.addWindow(w, rootWindow);
		                			}
//		                			MyView view1 = (MyView)maxTab.getSelectedWindow();
//		                			view1.setFocused(false);
		                			
		                		}
		                		
		                		
		                		for (int i = 0; i < viewMap.getViewCount(); i++){
		                			MyView v = (MyView)viewMap.getView(i);
//		                			DockingUtil.addWindow(v, rootWindow);
//		                			v.restore();
//		                			System.out.println(v.getTitle()+"==========================================");
//		                			v.setFocused(false);
//		                			TabWindow tw = DockingUtil.getTabWindowFor(v);
		                			v.setFocused(false);
		                			if(v!=view ){
//		                				System.out.println(v.getTitle()+"==========================================");
//		                				try {
//		                					tw.restoreWithAbort();
//		                					tw.setRequestFocusEnabled(false);
//										} catch (Exception e1) {
//											e1.printStackTrace();
//										}
		                				/*v.setFocused(true);
		                				TabWindow showTab = DockingUtil.getTabWindowFor(v);
		                				showTab.requestFocusInWindow();*/
		                			}else{
		                				
//		                				System.out.println("set up view focus==================================" + view.getTitle());
		                				DockingUtil.addWindow(view, rootWindow);
		                				view.setFocused(true);
		                				view.restoreFocus();
		                				view.restore();
		                				TabWindow showTab = DockingUtil.getTabWindowFor(view);
		                				showTab.requestFocusInWindow();
//		                				view.requestFocusInWindow();
//		                				rootWindow.requestFocusInWindow();
		                			}
		                		}
		                		
		                	}
		                }
		            };
		            strategy.addMenuItem(icon, view.getTitle(), view.getTitle(), mouseAdapter);
		        }
			} catch (Exception e1) {
				log.error("init", e1);
				e1.printStackTrace();
			}
		}
		
	}
	
}
