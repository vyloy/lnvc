/*
 * @(#)SVGPanel.java  1.0  11. March 2004
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

package org.jhotdraw.samples.svg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.draw.AttributeKeys;
import org.jhotdraw.draw.BezierFigure;
import org.jhotdraw.draw.CreationTool;
import org.jhotdraw.draw.DefaultDrawing;
import org.jhotdraw.draw.DefaultDrawingEditor;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.action.ToolBarButtonFactory;
import org.jhotdraw.samples.svg.figures.SVGEllipse;
import org.jhotdraw.samples.svg.figures.SVGLine;
import org.jhotdraw.samples.svg.figures.SVGPath;
import org.jhotdraw.samples.svg.figures.SVGRect;
import org.jhotdraw.samples.svg.figures.SVGText;
import org.jhotdraw.samples.svg.uicomponent.PageQuickAccessPanel;
import org.jhotdraw.samples.svg.uicomponent.SaveQuestionDialog;
import org.jhotdraw.undo.UndoRedoManager;
import org.jhotdraw.util.ResourceBundleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.whiteboard.client.ViewParam;
import com.lorent.whiteboard.command.impl.BroadcastCommand;
import com.lorent.whiteboard.model.ViewPanel;
/**
 * SVGPanel.
 *
 *
 * @author Werner Randelshofer
 * @version 1.0 11. M�rz 2004  Created.
 */
public class SVGPanel extends JPanel implements ViewPanel {
	private UndoRedoManager undoManager;
    private Drawing drawing;
    private DrawingEditor editor;
    private static final Logger logger = LoggerFactory.getLogger(SVGPanel.class);
    
    public SVGPanel(boolean writable) {
    	this(false,writable);
	}
    
    /** Creates new instance. 
     * @throws UnknownHostException */
    public SVGPanel(boolean doc,boolean writable){
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.samples.svg.Labels");
        view = new org.jhotdraw.draw.DefaultDrawingView(this,doc);
        initComponents();
        
        if(!writable)
        	return;
        initToolbar(labels);
        
        if(doc){
        	createPageButton(labels);
        }
//        JPopupButton pb = new JPopupButton();
//        pb.setEnabled(true);
//        pb.setItemFont(UIManager.getFont("MenuItem.font"));
//        labels.configureToolBarButton(pb, "actions");
//        pb.add(new DuplicateAction());
//        pb.addSeparator();
//        pb.add(new GroupAction(editor));
//        pb.add(new UngroupAction(editor));
//        pb.addSeparator();
//        pb.add(new MoveToFrontAction(editor));
//        pb.add(new MoveToBackAction(editor));
//        pb.addSeparator();
//        pb.add(new CutAction());
//        pb.add(new CopyAction());
//        pb.add(new PasteAction());
//        pb.add(new SelectAllAction());
//        pb.add(new SelectSameAction(editor));
//        pb.addSeparator();
//        pb.add(undoManager.getUndoAction());
//        pb.add(undoManager.getRedoAction());
//        pb.addSeparator();
//        pb.add(new ToggleGridAction(editor));
        
//        JMenu m = new JMenu(labels.getString("zoom"));
//        JRadioButtonMenuItem rbmi;
//        ButtonGroup group = new ButtonGroup();
//        m.add(rbmi = new JRadioButtonMenuItem(new ZoomAction(editor, 0.1, null)));
//        group.add(rbmi);
//        m.add(rbmi = new JRadioButtonMenuItem(new ZoomAction(editor, 0.25, null)));
//        group.add(rbmi);
//        m.add(rbmi = new JRadioButtonMenuItem(new ZoomAction(editor, 0.5, null)));
//        group.add(rbmi);
//        m.add(rbmi = new JRadioButtonMenuItem(new ZoomAction(editor, 0.75, null)));
//        group.add(rbmi);
//        m.add(rbmi = new JRadioButtonMenuItem(new ZoomAction(editor, 1.0, null)));
//        rbmi.setSelected(true);
//        group.add(rbmi);
//        m.add(rbmi = new JRadioButtonMenuItem(new ZoomAction(editor, 1.25, null)));
//        group.add(rbmi);
//        m.add(rbmi = new JRadioButtonMenuItem(new ZoomAction(editor, 1.5, null)));
//        group.add(rbmi);
//        m.add(rbmi = new JRadioButtonMenuItem(new ZoomAction(editor, 2, null)));
//        group.add(rbmi);
//        m.add(rbmi = new JRadioButtonMenuItem(new ZoomAction(editor, 3, null)));
//        group.add(rbmi);
//        m.add(rbmi = new JRadioButtonMenuItem(new ZoomAction(editor, 4, null)));
//        group.add(rbmi);
//        pb.add(m);
//        pb.setFocusable(false);
//        creationToolbar.addSeparator();
//        creationToolbar.add(pb);
        
        
    }
    
    private void initToolbar(ResourceBundleUtil labels){
    	jPanel1 = new javax.swing.JPanel();
        creationToolbar = new javax.swing.JToolBar();
        creationToolbar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		ViewParam.setView(view);
        	}
        });
        attributesToolbar = new javax.swing.JToolBar();
        attributesToolbar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		ViewParam.setView(view);
        	}
        });
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(creationToolbar, java.awt.BorderLayout.PAGE_START);
        jPanel1.add(attributesToolbar, java.awt.BorderLayout.SOUTH);
        add(jPanel1, java.awt.BorderLayout.SOUTH);
        
        addCreationButtonsTo(creationToolbar, editor);
        ToolBarButtonFactory.addAttributesButtonsTo(attributesToolbar, editor);
        
        creationToolbar.addSeparator();
        AbstractButton zoomButton = ToolBarButtonFactory.createZoomButton(view);
        zoomButton.setEnabled(true);
        Dimension preferredSize = new Dimension(48, 32);
		zoomButton.setPreferredSize(preferredSize);
		zoomButton.setMaximumSize(preferredSize);
		zoomButton.setMinimumSize(preferredSize);
        creationToolbar.add(zoomButton);
        creationToolbar.addSeparator();
        JButton saveButton = new JButton(new ImageIcon(SVGPanel.class.getResource("/org/jhotdraw/draw/action/images/save.png")));
        saveButton.setToolTipText(labels.getString("save"));
        ToolBarButtonFactory.setButtonSize(saveButton);
        saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SaveQuestionDialog dialog = new SaveQuestionDialog(view.getPagecount());
				dialog.setLocationRelativeTo(SVGPanel.this);
				dialog.setVisible(true);
			}
		});
        creationToolbar.add(saveButton);
        addComponentListener(new ComponentAdapter() {
    		@Override
    		public void componentShown(ComponentEvent e) {
    			ViewParam.setView(view);
    		}
    	});
    }
    
    private void createPageButton(ResourceBundleUtil labels){
    	creationToolbar.addSeparator();
        JButton pre = new JButton();
        pre.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		view.prePage();
        		recoverScrollBar();
        	}
        });
        pre.setFocusable(false);
        ToolBarButtonFactory.setButtonSize(pre);
        pre.setIcon(new ImageIcon(SVGPanel.class.getResource("/org/jhotdraw/draw/action/images/previous.png")));
        JButton next = new JButton();
        next.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		view.nextPage();
        		recoverScrollBar();
        	}
        });
        next.setFocusable(false);
        ToolBarButtonFactory.setButtonSize(next);
        next.setIcon(new ImageIcon(SVGPanel.class.getResource("/org/jhotdraw/draw/action/images/next.png")));
        
        JButton first = new JButton();
        first.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		view.firstPage();
        		recoverScrollBar();
        	}
        });
        first.setFocusable(false);
        ToolBarButtonFactory.setButtonSize(first);
        first.setIcon(new ImageIcon(SVGPanel.class.getResource("/org/jhotdraw/draw/action/images/first.png")));
        
        JButton last = new JButton();
        last.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		view.lastPage();
        		recoverScrollBar();
        	}
        });
        last.setFocusable(false);
        ToolBarButtonFactory.setButtonSize(last);
        last.setIcon(new ImageIcon(SVGPanel.class.getResource("/org/jhotdraw/draw/action/images/last.png")));
        
        first.setToolTipText(labels.getString("firstPage"));
        creationToolbar.add(first);
        pre.setToolTipText(labels.getString("prePage"));
        creationToolbar.add(pre);
        PageQuickAccessPanel pageQuickAccessPanel = view.getPageQuickAccessPanel();
        pageQuickAccessPanel.setReciever(first, pre, next, last);
		creationToolbar.add(pageQuickAccessPanel);
        next.setToolTipText(labels.getString("nextPage"));
        creationToolbar.add(next);
        last.setToolTipText(labels.getString("lastPage"));
        creationToolbar.add(last);
    }
    
    private void recoverScrollBar(){
    	scrollPane.getVerticalScrollBar().setValue(0);
    	scrollPane.getHorizontalScrollBar().setValue(0);
    }
    
	public void setDrawing(Drawing d) {
        undoManager.discardAllEdits();
        view.getDrawing().removeUndoableEditListener(undoManager);
        view.setDrawing(d);
        d.addUndoableEditListener(undoManager);
    }
    public Drawing getDrawing() {
        return view.getDrawing();
    }
    public DefaultDrawingView getView() {
        return view;
    }
    public DrawingEditor getEditor() {
        return editor;
    }
    public static Collection<Action> createSelectionActions(DrawingEditor editor) {
        LinkedList<Action> a = new LinkedList<Action>();
//        a.add(new DuplicateAction());
//        
//        a.add(null); // separator
//        a.add(new GroupAction(editor, new SVGGroup()));
//        a.add(new UngroupAction(editor, new SVGGroup()));
//        a.add(new CombineAction(editor));
//        a.add(new SplitAction(editor));
//        
//        a.add(null); // separator
//        a.add(new MoveToFrontAction(editor));
//        a.add(new MoveToBackAction(editor));
        
        return a;
    }
    private void addCreationButtonsTo(JToolBar tb, final DrawingEditor editor) {
        // AttributeKeys for the entitie sets
        HashMap<AttributeKey,Object> attributes;
        
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.samples.svg.Labels");
        ResourceBundleUtil drawLabels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        
        ToolBarButtonFactory.addSelectionToolTo(tb, editor, 
                ToolBarButtonFactory.createDrawingActions(editor), 
                createSelectionActions(editor)
                );
        tb.addSeparator();
        
        attributes = new HashMap<AttributeKey,Object>();
        attributes.put(AttributeKeys.FILL_COLOR, Color.white);
        attributes.put(AttributeKeys.STROKE_COLOR, Color.black);
        ToolBarButtonFactory.addToolTo(tb, editor, new CreationTool(new SVGRect(), attributes), "createRectangle", drawLabels);
        ToolBarButtonFactory.addToolTo(tb, editor, new CreationTool(new SVGEllipse(), attributes), "createEllipse", drawLabels);
//        ToolBarButtonFactory.addToolTo(tb, editor, new PathTool(new SVGPath(), new BezierFigure(true), attributes), "createPolygon", drawLabels);
        attributes = new HashMap<AttributeKey,Object>();
        attributes.put(AttributeKeys.FILL_COLOR, null);
        attributes.put(AttributeKeys.STROKE_COLOR, Color.black);
        ToolBarButtonFactory.addToolTo(tb, editor, new CreationTool(new SVGLine(), attributes), "createLine", drawLabels);
        ToolBarButtonFactory.addToolTo(tb, editor, new PathTool(new SVGPath(), new BezierFigure(false), attributes), "createScribble", drawLabels);
        attributes = new HashMap<AttributeKey,Object>();
        attributes.put(AttributeKeys.FILL_COLOR, Color.black);
        attributes.put(AttributeKeys.STROKE_COLOR, null);
        ToolBarButtonFactory.addToolTo(tb, editor, new CreationTool(new SVGText(), attributes), "createText", drawLabels);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        undoManager = new UndoRedoManager();
        editor = new DefaultDrawingEditor();
        editor.add(view);
        
        DefaultDrawing drawing = new DefaultDrawing();
        view.setDrawing(drawing);
        view.setDOMFactory(new SVGFigureFactory());
        drawing.addUndoableEditListener(undoManager);
        
        scrollPane = new javax.swing.JScrollPane();
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ViewParam.setView(view);
			}
		});
        setLayout(new java.awt.BorderLayout());

        scrollPane.setViewportView(view);
        add(scrollPane, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar attributesToolbar;
    private javax.swing.JToolBar creationToolbar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scrollPane;
    private org.jhotdraw.draw.DefaultDrawingView view;
    // End of variables declaration//GEN-END:variables
    
    
    private final ConcurrentHashMap<Long,BroadcastCommand> commands=new ConcurrentHashMap<Long,BroadcastCommand>();


	public ConcurrentHashMap<Long, BroadcastCommand> getCommands() {
		return commands;
	}

	public BroadcastCommand getCommand(long id){
		return commands.get(id);
	}
	
	public BroadcastCommand removeCommand(long id){
		return commands.remove(id);
	}
	
	public boolean containsCommand(BroadcastCommand command){
		return commands.contains(command);
	}

	public javax.swing.JScrollPane getScrollPane() {
		return scrollPane;
	}
	
}
