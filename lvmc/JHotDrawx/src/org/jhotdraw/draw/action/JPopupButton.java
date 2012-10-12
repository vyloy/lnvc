/*
 * @(#)JPopupButton.java  1.1  2006-06-25
 *
 * Copyright (c) 1996-2006 by the original authors of JHotDraw
 * and all its contributors ("JHotDraw.org")
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * JHotDraw.org ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * JHotDraw.org.
 */

package org.jhotdraw.draw.action;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.MenuElement;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
/**
 * JPopupButton provides a popup menu.
 *
 * @author  wrandels
 * @version 1.1 2006-06-25 Added more "add" methods.
 */
public class JPopupButton extends javax.swing.JButton {
    private JPopupMenu popupMenu;
    private int columnCount = 1;
    private Action action;
    private Rectangle actionArea;
    private Font itemFont;
    private StateUpdater stateUpdater;
    public final static Font ITEM_FONT = new Font("Dialog", Font.PLAIN, 10);
    private static List<JPopupMenu> list=new LinkedList<JPopupMenu>();
    static{
    	JPopupMenu.setDefaultLightWeightPopupEnabled(false);
    }
    
    /** Creates new form JToolBarMenu */
    public JPopupButton() {
        this(null);
    }
    
    
    public JPopupButton(StateUpdater stateUpdater) {
    	initComponents();
        setFocusable(false);
        itemFont = ITEM_FONT;
        this.setEnabled(false);
		this.stateUpdater = stateUpdater;
	}


	public void setItemFont(Font newValue) {
        itemFont = newValue;
        if (popupMenu != null) {
            updateFont(popupMenu);
        }
    }
    
	private boolean firstAction=true;
	private void initStateUpdater(AbstractSelectedAction action){
		if (firstAction) {
			firstAction = false;
			if (stateUpdater != null) {
				action.setStateUpdater(stateUpdater);
			}
			action.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if (evt.getPropertyName().equals("enabled")) {
						setEnabled(((Boolean) evt.getNewValue()).booleanValue());
					} else {
						repaint();
					}
				}
			});
		}
		
	}
	
    public void setAction(AbstractSelectedAction action, Rectangle actionArea) {
        this.action = action;
        this.actionArea = actionArea;
        initStateUpdater(action);
    }
    public void setAction(AbstractSelectedAction action) {
    	initStateUpdater(action);
    	setAction((Action)action);
    }
    
    public int getColumnCount() {
        return columnCount;
    }
    public void setColumnCount(int count, boolean isVertical) {
        columnCount = count;
        getPopupMenu().setLayout(new VerticalGridLayout(0, getColumnCount(), isVertical));
    }
    public void add(AbstractSelectedAction action) {
        JMenuItem item = getPopupMenu().add(action);
        if (getColumnCount() > 1) {
            item.setUI(new PaletteMenuItemUI());
        }
        item.setFont(itemFont);
        initStateUpdater(action);
    }
    public void add(Action action) {
		JMenuItem item = getPopupMenu().add(action);
		if (getColumnCount() > 1) {
			item.setUI(new PaletteMenuItemUI());
		}
		item.setFont(itemFont);
    }
    public void add(JMenu submenu) {
        JMenuItem item = getPopupMenu().add(submenu);
        updateFont(submenu);
    }
    private void updateFont(MenuElement menu) {
        menu.getComponent().setFont(itemFont);
        for (MenuElement child : menu.getSubElements()) {
            updateFont(child);
        }
    }
    
    public void add(JMenuItem item) {
        getPopupMenu().add(item);
        item.setFont(itemFont);
    }
    public void addSeparator() {
        getPopupMenu().addSeparator();
    }
    
    public void setPopupMenu(JPopupMenu popupMenu) {
        this.popupMenu = popupMenu;
    }
    public JPopupMenu getPopupMenu() {
        if (popupMenu == null) {
            popupMenu = new JPopupMenu();
            popupMenu.addPopupMenuListener(new PopupMenuListener() {
				
				@Override
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					list.add(popupMenu);
				}
				
				@Override
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					JPopupButton.this.getModel().setPressed(false);
					list.remove(popupMenu);
				}
				
				@Override
				public void popupMenuCanceled(PopupMenuEvent e) {
				}
			});
            popupMenu.setLayout(new VerticalGridLayout(0, getColumnCount()));
        }
        return popupMenu;
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents

        FormListener formListener = new FormListener();

        addMouseListener(formListener);

    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.MouseListener {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == JPopupButton.this&&JPopupButton.this.isEnabled()) {
                JPopupButton.this.showPopup(evt);
            }
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == JPopupButton.this) {
                JPopupButton.this.performAction(evt);
            }
        }
    }//GEN-END:initComponents
    
    private void performAction(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_performAction
        // Add your handling code here:
        if (actionArea != null && actionArea.contains(evt.getX()-getInsets().left, evt.getY()-getInsets().top)) {
            action.actionPerformed(
                    new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED,
                    null,
                    evt.getWhen(),
                    evt.getModifiers())
                    );
            
        }
    }//GEN-LAST:event_performAction
    
    private void showPopup(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPopup
        // Add your handling code here:
        if (popupMenu != null
                && (actionArea == null || ! actionArea.contains(evt.getX()-getInsets().left, evt.getY()-getInsets().top))
                ) {
            int x, y;
            
            x = 0;
            y = getHeight();
            if (getParent() instanceof JToolBar) {
                JToolBar toolbar = (JToolBar) getParent();
                if (toolbar.getOrientation() == JToolBar.VERTICAL) {
                    y = 0;
                    if (toolbar.getX() > toolbar.getParent().getInsets().left) {
                        x = -popupMenu.getPreferredSize().width;
                    } else {
                        x = getWidth();
                    }
                } else {
                    if (toolbar.getY() > toolbar.getParent().getInsets().top) {
                        y = -popupMenu.getPreferredSize().height;
                    }
                }
            }
            popupMenu.show(this, x, y);
        }
    }//GEN-LAST:event_showPopup


	public StateUpdater getStateUpdater() {
		return stateUpdater;
	}


	public void setStateUpdater(StateUpdater stateUpdater) {
		this.stateUpdater = stateUpdater;
	}
    
	public static void dispose(){
		for (JPopupMenu p : list) {
			p.setVisible(false);
		}
		list.clear();
	}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
