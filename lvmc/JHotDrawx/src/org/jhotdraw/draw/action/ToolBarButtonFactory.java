/*
 * @(#)ToolBarButtonFactory.java  1.2  2006-07-16
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

import static org.jhotdraw.draw.AttributeKeys.END_DECORATION;
import static org.jhotdraw.draw.AttributeKeys.FILL_COLOR;
import static org.jhotdraw.draw.AttributeKeys.FILL_UNDER_STROKE;
import static org.jhotdraw.draw.AttributeKeys.FONT_BOLD;
import static org.jhotdraw.draw.AttributeKeys.FONT_FACE;
import static org.jhotdraw.draw.AttributeKeys.FONT_ITALIC;
import static org.jhotdraw.draw.AttributeKeys.FONT_UNDERLINED;
import static org.jhotdraw.draw.AttributeKeys.START_DECORATION;
import static org.jhotdraw.draw.AttributeKeys.STROKE_COLOR;
import static org.jhotdraw.draw.AttributeKeys.STROKE_DASHES;
import static org.jhotdraw.draw.AttributeKeys.STROKE_INNER_WIDTH_FACTOR;
import static org.jhotdraw.draw.AttributeKeys.STROKE_PLACEMENT;
import static org.jhotdraw.draw.AttributeKeys.STROKE_TYPE;
import static org.jhotdraw.draw.AttributeKeys.STROKE_WIDTH;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.text.StyledEditorKit;

import org.jhotdraw.app.action.CopyAction;
import org.jhotdraw.app.action.CutAction;
import org.jhotdraw.app.action.DeleteAction;
import org.jhotdraw.app.action.DuplicateAction;
import org.jhotdraw.app.action.PasteAction;
import org.jhotdraw.app.action.SelectAllAction;
import org.jhotdraw.draw.ArrowTip;
import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.draw.AttributeKeys;
import org.jhotdraw.draw.Constrainer;
import org.jhotdraw.draw.DelegationSelectionTool;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.GridConstrainer;
import org.jhotdraw.draw.LineDecoration;
import org.jhotdraw.draw.Tool;
import org.jhotdraw.draw.ToolEvent;
import org.jhotdraw.draw.ToolListener;
import org.jhotdraw.geom.DoubleStroke;
import org.jhotdraw.samples.svg.action.RefreshAction;
import org.jhotdraw.samples.svg.figures.SVGLine;
import org.jhotdraw.samples.svg.figures.SVGPath;
import org.jhotdraw.samples.svg.figures.SVGText;
import org.jhotdraw.util.ResourceBundleUtil;

/**
 * ToolBarButtonFactory.
 *
 * @author Werner Randelshofer
 * @version 1.2 2006-07-16 Split some methods up for better reuse. 
 * <br>1.1 2006-03-27 Font exclusion list updated.
 * <br>1.0 13. Februar 2006 Created.
 */
public class ToolBarButtonFactory {
    public final static Map<String,Color> DEFAULT_COLORS;
    static {
        LinkedHashMap<String,Color> m = new LinkedHashMap<String,Color>();
        m.put("Cayenne", new Color(128, 0, 0));
        m.put("Asparagus", new Color(128, 128, 0));
        m.put("Clover", new Color(0, 128, 0));
        m.put("Teal", new Color(0, 128, 128));
        m.put("Midnight", new Color(0, 0, 128));
        m.put("Plum", new Color(128, 0, 128));
        m.put("Tin", new Color(127, 127, 127));
        m.put("Nickel", new Color(128, 128, 128));
        
        m.put("Maraschino", new Color(255, 0, 0));
        m.put("Lemon", new Color(255, 255, 0));
        m.put("Spring", new Color(0, 255, 0));
        m.put("Turquoise", new Color(0, 255, 255));
        m.put("Blueberry", new Color(0, 0, 255));
        m.put("Magenta", new Color(255, 0, 255));
        m.put("Steel", new Color(102, 102, 102));
        m.put("Aluminium", new Color(153, 153, 153));
        
        m.put("Salmon", new Color(255, 102, 102));
        m.put("Banana", new Color(255, 255, 102));
        m.put("Flora", new Color(102, 255, 102));
        m.put("Ice", new Color(102, 255, 255));
        m.put("Orchid", new Color(102, 102, 255));
        m.put("Bubblegum", new Color(255, 102, 255));
        m.put("Iron", new Color(76, 76, 76));
        m.put("Magnesium", new Color(179, 179, 179));
        
        
        m.put("Mocha", new Color(128, 64, 0));
        m.put("Fern", new Color(64, 128, 0));
        m.put("Moss", new Color(0, 128, 64));
        m.put("Ocean", new Color(0, 64, 128));
        m.put("Eggplant", new Color(64, 0, 128));
        m.put("Maroon", new Color(128, 0, 64));
        m.put("Tungsten", new Color(51, 51, 51));
        m.put("Silver", new Color(204, 204, 204));
        
        
        m.put("Tangerine", new Color(255, 128, 0));
        m.put("Lime", new Color(128, 255, 0));
        m.put("Sea Foam", new Color(0, 255, 128));
        m.put("Aqua", new Color(0, 128, 255));
        m.put("Grape", new Color(128, 0, 255));
        m.put("Strawberry", new Color(255, 0, 128));
        
        m.put("Lead", new Color(25, 25, 25));
        m.put("Mercury", new Color(230, 230, 230));
        
        m.put("Cantaloupe", new Color(255, 204, 102));
        m.put("Honeydew", new Color(204, 255, 102));
        m.put("Spindrift", new Color(102, 255, 204));
        m.put("Sky", new Color(102, 204, 255));
        m.put("Lavender", new Color(204, 102, 255));
        m.put("Carnation", new Color(255, 111, 207));
        
        m.put("Licorice", new Color(0, 0, 0));
        m.put("Snow", new Color(255, 255, 255));
        
        m.put("Transparent", null);
        DEFAULT_COLORS = Collections.unmodifiableMap(m);
    }
    
    
    private static class ToolButtonListener implements ItemListener {
        private Tool tool;
        private DrawingEditor editor;
        public ToolButtonListener(Tool t, DrawingEditor editor) {
            this.tool = t;
            this.editor = editor;
        }
        public void itemStateChanged(ItemEvent evt) {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                editor.setTool(tool);
            }
        }
    }
    
    /** Prevent instance creation. */
    private ToolBarButtonFactory() {
    }
    
    public static Collection<Action> createDrawingActions(DrawingEditor editor) {
        LinkedList<Action> a = new LinkedList<Action>();
        a.add(new CutAction());
        a.add(new CopyAction());
        a.add(new PasteAction());
        a.add(new RefreshAction());
        a.add(new DeleteAction());
        a.add(new SelectAllAction());
        a.add(new SelectSameAction(editor));
        
        return a;
    }
    public static Collection<Action> createSelectionActions(DrawingEditor editor) {
        LinkedList<Action> a = new LinkedList<Action>();
        a.add(new DuplicateAction());
        
        a.add(null); // separator
        a.add(new GroupAction(editor));
        a.add(new UngroupAction(editor));
        
        a.add(null); // separator
        a.add(new MoveToFrontAction(editor));
        a.add(new MoveToBackAction(editor));
        
        return a;
    }
    
    public static void addSelectionToolTo(JToolBar tb, final DrawingEditor editor) {
        addSelectionToolTo(tb, editor, createDrawingActions(editor), createSelectionActions(editor));
    }
    public static void addSelectionToolTo(JToolBar tb, final DrawingEditor editor,
            Collection<Action> drawingActions, Collection<Action> selectionActions) {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        
        JToggleButton t;
        Tool tool;
        HashMap<String,Object> attributes;
        
        ButtonGroup group;
        if (tb.getClientProperty("toolButtonGroup") instanceof ButtonGroup) {
            group = (ButtonGroup) tb.getClientProperty("toolButtonGroup");
        } else {
            group = new ButtonGroup();
            tb.putClientProperty("toolButtonGroup", group);
        }
        
        // Selection tool
        Tool selectionTool = new DelegationSelectionTool(
                drawingActions, selectionActions
                );
        editor.setTool(selectionTool);
        t = new JToggleButton();
        final JToggleButton defaultToolButton = t;
        
        ToolListener toolHandler;
        if (tb.getClientProperty("toolHandler") instanceof ToolListener) {
            toolHandler = (ToolListener) tb.getClientProperty("toolHandler");
        } else {
            toolHandler = new ToolListener() {
                public void toolStarted(ToolEvent event) {
                }
                
                public void toolDone(ToolEvent event) {
//                	if(event.getTool() instanceof CreationTool){
//						CreationTool creationTool = (CreationTool) event.getTool();
//						Figure f = creationTool.getPrototype();
//						if(!(f instanceof SVGLine)){
//							defaultToolButton.setSelected(true);
//						}
//                	}
                }
                
                public void areaInvalidated(ToolEvent e) {
                }
            };
            tb.putClientProperty("toolHandler", toolHandler);
        }
        
        labels.configureToolBarButton(t, "selectionTool");
        t.setSelected(true);
        t.addItemListener(
                new ToolButtonListener(selectionTool, editor)
                );
        t.setFocusable(false);
        group.add(t);
        tb.add(t);
    }
    
    /**
     * Method addSelectionToolTo must have been invoked prior to this on the
     * JToolBar.
     *
     */
    public static void addToolTo(JToolBar tb, DrawingEditor editor,
            Tool tool, String labelKey,
            ResourceBundleUtil labels) {
        
        ButtonGroup group = (ButtonGroup) tb.getClientProperty("toolButtonGroup");
        ToolListener toolHandler = (ToolListener) tb.getClientProperty("toolHandler");
        
        JToggleButton t = new JToggleButton();
        labels.configureToolBarButton(t, labelKey);
        //在按钮按的时候，把editor当前的画图工具调成tool
        t.addItemListener(new ToolButtonListener(tool, editor));
        t.setFocusable(false);
        tool.addToolListener(toolHandler);
        group.add(t);
        tb.add(t);
    }
    
    
    
    public static void addZoomButtonsTo(JToolBar bar, final DrawingEditor editor) {
        bar.add(createZoomButton(editor));
    }
    public static AbstractButton createZoomButton(final DrawingEditor editor) {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        
        final JPopupButton zoomPopupButton = new JPopupButton();
        
        labels.configureToolBarButton(zoomPopupButton, "viewZoom");
        zoomPopupButton.setFocusable(false);
        if (editor.getDrawingViews().size() == 0) {
            zoomPopupButton.setText("100 %");
        } else {
            zoomPopupButton.setText((int) (editor.getDrawingViews().iterator().next().getScaleFactor() * 100) + " %");
        }
        editor.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                // String constants are interned
                if (evt.getPropertyName() == "focusedView") {
                    if (evt.getNewValue() == null) {
                        zoomPopupButton.setText("100 %");
                    } else {
                        zoomPopupButton.setText((int) (editor.getFocusedView().getScaleFactor() * 100) + " %");
                    }
                }
            }
        });
        
        double[] factors = {16, 8, 5, 4, 3, 2, 1.5, 1.25, 1, 0.75, 0.5, 0.25, 0.10};
        for (int i=0; i < factors.length; i++) {
            zoomPopupButton.add(
                    new ZoomEditorAction(editor, factors[i], zoomPopupButton) {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    super.actionPerformed(e);
                    zoomPopupButton.setText((int) (editor.getView().getScaleFactor() * 100) + " %");
                }
            });
        }
        //zoomPopupButton.setPreferredSize(new Dimension(16,16));
        zoomPopupButton.setFocusable(false);
        return zoomPopupButton;
    }
    public static AbstractButton createZoomButton(final DrawingView view) {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        
        final JPopupButton zoomPopupButton = new JPopupButton();
        
        labels.configureToolBarButton(zoomPopupButton, "viewZoom");
        zoomPopupButton.setFocusable(false);
        zoomPopupButton.setText((int) (view.getScaleFactor() * 100) + " %");
        
        view.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                // String constants are interned
                if (evt.getPropertyName() == "scaleFactor") {
                    zoomPopupButton.setText((int) (view.getScaleFactor() * 100) + " %");
                }
            }
        });
        
        double[] factors = {5, 4, 3, 2, 1.5, 1.25, 1, 0.75, 0.5, 0.25, 0.10};
        for (int i=0; i < factors.length; i++) {
            zoomPopupButton.add(
                    new ZoomAction(view, factors[i], zoomPopupButton) {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    super.actionPerformed(e);
                    zoomPopupButton.setText((int) (view.getScaleFactor() * 100) + " %");
                }
            });
        }
        //zoomPopupButton.setPreferredSize(new Dimension(16,16));
        zoomPopupButton.setFocusable(false);
        return zoomPopupButton;
    }
    /**
     * Creates toolbar buttons and adds them to the specified JToolBar
     */
    public static void addAttributesButtonsTo(JToolBar bar, DrawingEditor editor) {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        JButton b;
        
        b = bar.add(new PickAttributesAction(editor));
        b.setFocusable(false);
        setButtonSize(b);
        b = bar.add(new ApplyAttributesAction(editor));
        b.setFocusable(false);
        setButtonSize(b);
        bar.addSeparator();
        
        addColorButtonsTo(bar, editor);
        bar.addSeparator();
        addStrokeButtonsTo(bar, editor);
        bar.addSeparator();
        addFontButtonsTo(bar, editor);
    }
    private static final Dimension PREFERRED_SIZE = new Dimension(32, 32);
    public static void setButtonSize(AbstractButton button){
		button.setPreferredSize(PREFERRED_SIZE);
		button.setMaximumSize(PREFERRED_SIZE);
		button.setMinimumSize(PREFERRED_SIZE);
	}
    public static void addColorButtonsTo(JToolBar bar, DrawingEditor editor) {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        addColorButtonTo(bar, editor, STROKE_COLOR, DEFAULT_COLORS, 8, "attributeStrokeColor", labels);
        addColorButtonTo(bar, editor, FILL_COLOR, DEFAULT_COLORS, 8, "attributeFillColor", labels);
//        addColorButtonTo(bar, editor, TEXT_COLOR, DEFAULT_COLORS, 8, "attributeTextColor", labels);
    }
    /**
     * @param colorMap a Map with named colors. This is usually a LinkedHashMap
     * so that the colors are in a specific order.
     */
    public static void addColorButtonTo(JToolBar bar, DrawingEditor editor, AttributeKey attributeKey, Map<String,Color> colorMap, int columnCount, String labelKey, ResourceBundleUtil labels) {
    	final JPopupButton popupButton = new JPopupButton();
        if(FILL_COLOR==attributeKey){
        	popupButton.setStateUpdater(fillColorUpdater);
        }
        popupButton.setAction(new DefaultAttributeAction(editor, attributeKey), new Rectangle(0, 0, 16, 16));
        popupButton.setColumnCount(columnCount, false);
        for (Map.Entry<String,Color> entry : colorMap.entrySet()) {
            AttributeAction a;
            popupButton.add(a=
                    new AttributeAction(
                    editor,
                    attributeKey,
                    entry.getValue(),
                    new ColorIcon(entry.getValue())
                    )
                    );
            a.putValue(Action.SHORT_DESCRIPTION, entry.getKey());
        }
        
        ImageIcon chooserIcon = new ImageIcon(
                ToolBarButtonFactory.class.getResource("/org/jhotdraw/draw/action/images/showColorChooser.png")
                );
        
        popupButton.add(
                new ColorChooserAction(
                editor,
                attributeKey,
                chooserIcon
                )
                );
        labels.configureToolBarButton(popupButton,labelKey);
        popupButton.setIcon(new ColorAttributeIcon(editor,
                attributeKey,
                labels.getImageIcon(labelKey, ToolBarButtonFactory.class).getImage()
                ));
        
        popupButton.setFocusable(false);
        
        editor.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                popupButton.repaint();
            }
        });
        Dimension size = new Dimension(42, 32);
		popupButton.setMaximumSize(size);
		popupButton.setMinimumSize(size);
		popupButton.setPreferredSize(size);
        bar.add(popupButton);
    }
    public static void addStrokeButtonsTo(JToolBar bar, DrawingEditor editor) {
        addStrokeDecorationButtonTo(bar, editor);
        addStrokeWidthButtonTo(bar, editor);
        addStrokeDashesButtonTo(bar, editor);
        addStrokeTypeButtonTo(bar, editor);
        //addStrokePlacementButtonTo(bar, editor);
    }
	private static StateUpdater fillColorUpdater=new StateUpdater() {
		
		@Override
		public void update(AbstractSelectedAction action) {
			DrawingView view = action.getView();
			if (view != null) {
				boolean result=view.getSelectionCount() > 0;
				if(result){
					for(Figure f:view.getSelectedFigures()){
						if(f instanceof SVGLine){
							result=false;
							break;
						}
					}
				}
				action.setEnabled(view.isEnabled() &&
						result
						);
			} else {
				action.setEnabled(false);
			}
			
		}
	};
	private static StateUpdater lineDecorationUpdater=new StateUpdater() {
		
		@Override
		public void update(AbstractSelectedAction action) {
			DrawingView view = action.getView();
			if (view != null) {
				boolean result=view.getSelectionCount() > 0;
				if(result){
					for(Figure f:view.getSelectedFigures()){
						if(!(f instanceof SVGLine||f instanceof SVGPath)){
							result=false;
							break;
						}
					}
				}
				action.setEnabled(view.isEnabled() &&
						result
	                    );
	        } else {
	        	action.setEnabled(false);
	        }
			
		}
	};
	private static StateUpdater strokePlacementUpdater=new StateUpdater() {
		
		@Override
		public void update(AbstractSelectedAction action) {
			DrawingView view = action.getView();
			if (view != null) {
				boolean result=view.getSelectionCount() > 0;
				if(result){
					for(Figure f:view.getSelectedFigures()){
						if(f instanceof SVGLine||f instanceof SVGText||f instanceof SVGPath){
							result=false;
							break;
						}
					}
				}
				action.setEnabled(view.isEnabled() &&
						result
						);
			} else {
				action.setEnabled(false);
			}
			
		}
	};
    public static void addStrokeWidthButtonTo(JToolBar bar, DrawingEditor editor) {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        
        JPopupButton strokeWidthPopupButton = new JPopupButton();
        
        labels.configureToolBarButton(strokeWidthPopupButton,"attributeStrokeWidth");
        strokeWidthPopupButton.setFocusable(false);
        
        double[] widths = {0.5d, 1d, 2d, 3d, 5d, 7d, 9d, 11d};
        NumberFormat formatter = NumberFormat.getInstance();
        if (formatter instanceof DecimalFormat) {
            ((DecimalFormat) formatter).setMaximumFractionDigits(1);
            ((DecimalFormat) formatter).setMinimumFractionDigits(0);
        }
        for (int i=0; i < widths.length; i++) {
            String label = Double.toString(widths[i]);
            strokeWidthPopupButton.add(
                    new AttributeAction(
                    editor,
                    STROKE_WIDTH,
                    new Double(widths[i]),
                    label,
                    new StrokeIcon(new BasicStroke((float) widths[i], BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL))
                    )
                    );
        }
        bar.add(strokeWidthPopupButton);
    }
    public static void addStrokeDecorationButtonTo(JToolBar bar, DrawingEditor editor) {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        
        JPopupButton strokeDecorationPopupButton = new JPopupButton(lineDecorationUpdater);
        labels.configureToolBarButton(strokeDecorationPopupButton,"attributeStrokeDecoration");
        strokeDecorationPopupButton.setFocusable(false);
        strokeDecorationPopupButton.setColumnCount(2, false);
        LineDecoration[] decorations = {
            new ArrowTip(0.35, 12, 11.3),
            new ArrowTip(0.35, 13, 7),
            null
        };
        for (int i=0; i < decorations.length; i++) {
            strokeDecorationPopupButton.add(
                    new AttributeAction(
                    editor,
                    START_DECORATION,
                    decorations[i],
                    null,
                    new LineDecorationIcon(decorations[i], true)
                    )
                    );
            strokeDecorationPopupButton.add(
                    new AttributeAction(
                    editor,
                    END_DECORATION,
                    decorations[i],
                    null,
                    new LineDecorationIcon(decorations[i], false)
                    )
                    );
        }
        
        bar.add(strokeDecorationPopupButton);
    }
    public static void addStrokeDashesButtonTo(JToolBar bar, DrawingEditor editor) {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        
        JPopupButton strokeDashesPopupButton = new JPopupButton();
        labels.configureToolBarButton(strokeDashesPopupButton,"attributeStrokeDashes");
        strokeDashesPopupButton.setFocusable(false);
        double[][] dashes = {
            null,
            {4d, 4d},
            {2d, 2d},
            {4d, 2d},
            {2d, 4d},
            {8d, 2d},
            {6d, 2d, 2d, 2d}
        };
        //strokeDashesPopupButton.setColumnCount(2, false);
        for (int i=0; i < dashes.length; i++) {
            
            float[] fdashes;
            if (dashes[i] == null) {
                fdashes = null;
            } else {
                fdashes = new float[dashes[i].length];
                for (int j = 0; j < dashes[i].length; j++) {
                    fdashes[j] = (float) dashes[i][j];
                }
            }
            strokeDashesPopupButton.add(
                    new AttributeAction(
                    editor,
                    STROKE_DASHES,
                    dashes[i],
                    null,
                    new StrokeIcon(new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10f, fdashes, 0))
                    )
                    );
        }
        bar.add(strokeDashesPopupButton);
    }
    public static void addStrokeTypeButtonTo(JToolBar bar, DrawingEditor editor) {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        
        JPopupButton strokeTypePopupButton = new JPopupButton();
        labels.configureToolBarButton(strokeTypePopupButton,"attributeStrokeType");
        strokeTypePopupButton.setFocusable(false);

        strokeTypePopupButton.add(
                new AttributeAction(
                editor,
                STROKE_TYPE,
                AttributeKeys.StrokeType.BASIC,
                labels.getString("attributeStrokeTypeBasic"),
                new StrokeIcon(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL))
                )
                );
        HashMap<AttributeKey,Object> attr = new HashMap<AttributeKey,Object>();
        attr.put(STROKE_TYPE, AttributeKeys.StrokeType.DOUBLE);
        attr.put(STROKE_INNER_WIDTH_FACTOR, 2d);
        strokeTypePopupButton.add(
                new AttributeAction(
                editor,
                attr,
                labels.getString("attributeStrokeTypeDouble"),
                new StrokeIcon(new DoubleStroke(2, 1))
                )
                );
        attr = new HashMap<AttributeKey,Object>();
        attr.put(STROKE_TYPE, AttributeKeys.StrokeType.DOUBLE);
        attr.put(STROKE_INNER_WIDTH_FACTOR, 3d);
        strokeTypePopupButton.add(
                new AttributeAction(
                editor,
                attr,
                labels.getString("attributeStrokeTypeDouble"),
                new StrokeIcon(new DoubleStroke(3, 1))
                )
                );
        attr = new HashMap<AttributeKey,Object>();
        attr.put(STROKE_TYPE, AttributeKeys.StrokeType.DOUBLE);
        attr.put(STROKE_INNER_WIDTH_FACTOR, 4d);
        strokeTypePopupButton.add(
                new AttributeAction(
                editor,
                attr,
                labels.getString("attributeStrokeTypeDouble"),
                new StrokeIcon(new DoubleStroke(4, 1))
                )
                );
        
        
        bar.add(strokeTypePopupButton);
    }
    public static void addStrokePlacementButtonTo(JToolBar bar, DrawingEditor editor) {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        
        JPopupButton strokePlacementPopupButton = new JPopupButton(strokePlacementUpdater);
        labels.configureToolBarButton(strokePlacementPopupButton,"attributeStrokePlacement");
        strokePlacementPopupButton.setFocusable(false);
        
        HashMap<AttributeKey,Object> attr;
        attr = new HashMap<AttributeKey,Object>();
        attr.put(STROKE_PLACEMENT, AttributeKeys.StrokePlacement.CENTER);
        attr.put(FILL_UNDER_STROKE, AttributeKeys.Underfill.CENTER);
        strokePlacementPopupButton.add(
                new AttributeAction(
                editor,
                attr,
                labels.getString("attributeStrokePlacementCenter"),
                null
                )
                );
        attr = new HashMap<AttributeKey,Object>();
        attr.put(STROKE_PLACEMENT, AttributeKeys.StrokePlacement.INSIDE);
        attr.put(FILL_UNDER_STROKE, AttributeKeys.Underfill.CENTER);
        strokePlacementPopupButton.add(
                new AttributeAction(
                editor,
                attr,
                labels.getString("attributeStrokePlacementInside"),
                null
                )
                );
        attr = new HashMap<AttributeKey,Object>();
        attr.put(STROKE_PLACEMENT, AttributeKeys.StrokePlacement.OUTSIDE);
        attr.put(FILL_UNDER_STROKE, AttributeKeys.Underfill.CENTER);
        strokePlacementPopupButton.add(
                new AttributeAction(
                editor,
                attr,
                labels.getString("attributeStrokePlacementOutside"),
                null
                )
                );
        attr = new HashMap<AttributeKey,Object>();
        attr.put(STROKE_PLACEMENT, AttributeKeys.StrokePlacement.CENTER);
        attr.put(FILL_UNDER_STROKE, AttributeKeys.Underfill.FULL);
        strokePlacementPopupButton.add(
                new AttributeAction(
                editor,
                attr,
                labels.getString("attributeStrokePlacementCenterFilled"),
                null
                )
                );
        attr = new HashMap<AttributeKey,Object>();
        attr.put(STROKE_PLACEMENT, AttributeKeys.StrokePlacement.INSIDE);
        attr.put(FILL_UNDER_STROKE, AttributeKeys.Underfill.FULL);
        strokePlacementPopupButton.add(
                new AttributeAction(
                editor,
                attr,
                labels.getString("attributeStrokePlacementInsideFilled"),
                null
                )
                );
        attr = new HashMap<AttributeKey,Object>();
        attr.put(STROKE_PLACEMENT, AttributeKeys.StrokePlacement.OUTSIDE);
        attr.put(FILL_UNDER_STROKE, AttributeKeys.Underfill.FULL);
        strokePlacementPopupButton.add(
                new AttributeAction(
                editor,
                attr,
                labels.getString("attributeStrokePlacementOutsideFilled"),
                null
                )
                );
        attr = new HashMap<AttributeKey,Object>();
        attr.put(STROKE_PLACEMENT, AttributeKeys.StrokePlacement.CENTER);
        attr.put(FILL_UNDER_STROKE, AttributeKeys.Underfill.NONE);
        strokePlacementPopupButton.add(
                new AttributeAction(
                editor,
                attr,
                labels.getString("attributeStrokePlacementCenterUnfilled"),
                null
                )
                );
        attr = new HashMap<AttributeKey,Object>();
        attr.put(STROKE_PLACEMENT, AttributeKeys.StrokePlacement.INSIDE);
        attr.put(FILL_UNDER_STROKE, AttributeKeys.Underfill.NONE);
        strokePlacementPopupButton.add(
                new AttributeAction(
                editor,
                attr,
                labels.getString("attributeStrokePlacementInsideUnfilled"),
                null
                )
                );
        attr = new HashMap<AttributeKey,Object>();
        attr.put(STROKE_PLACEMENT, AttributeKeys.StrokePlacement.OUTSIDE);
        attr.put(FILL_UNDER_STROKE, AttributeKeys.Underfill.NONE);
        strokePlacementPopupButton.add(
                new AttributeAction(
                editor,
                attr,
                labels.getString("attributeStrokePlacementOutsideUnfilled"),
                null
                )
                );
        
        bar.add(strokePlacementPopupButton);
    }
    
    public static void addFontButtonsTo(JToolBar bar, DrawingEditor editor) {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        
        StateUpdater stateUpdater = new StateUpdater() {
        	
        	@Override
        	public void update(AbstractSelectedAction action) {
        		DrawingView view = action.getView();
        		if (view != null) {
        			boolean result=view.getSelectionCount() > 0;
        			if(result){
        				for(Figure f:view.getSelectedFigures()){
        					if(!(f instanceof SVGText)){
        						result=false;
        						break;
        					}
        				}
        			}
        			action.setEnabled(view.isEnabled() &&
        					result
        					);
        		} else {
        			action.setEnabled(false);
        		}
        	}
        	
        };
        JPopupButton fontPopupButton = new JPopupButton(stateUpdater);
        JPopupButton boldToggleButton = new JPopupButton(stateUpdater);
        JPopupButton italicToggleButton = new JPopupButton(stateUpdater);
        JPopupButton underlineToggleButton = new JPopupButton(stateUpdater);
        
        
        labels.configureToolBarButton(fontPopupButton, "attributeFont");
        fontPopupButton.setFocusable(false);
        
        Font[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        HashSet<String> fontExclusionList = new HashSet<String>(Arrays.asList(new String[] {
            // Mac OS X 10.3 Font Exclusion List
            "#GungSeo", "#HeadLineA", "#PCMyungjo", "#PilGi", "Al Bayan", "Apple LiGothic",
            "Apple LiSung", "AppleMyungjo", "Arial Hebrew", "Ayuthaya", "Baghdad",
            "BiauKai", "Charcoal CY", "Corsiva Hebrew", "DecoType Naskh",
            "Devanagari MT", "Fang Song", "GB18030 Bitmap", "Geeza Pro",
            "Geezah", "Geneva CY", "Gujarati MT", "Gurmukhi MT", "Hei",
            "Helvetica CY", "Hiragino Kaku Gothic Std", "Hiragino Maru Gothic Pro",
            "Hiragino Mincho Pro", "Hiragino Kaku Gothic Pro",
            "InaiMathi",
            "Kai",
            "Krungthep", "KufiStandardGK", "LiHei Pro", "LiSong Pro",
            "Mshtakan",
            "Monaco CY",
            "Nadeem",
            "New Peninim MT", "Osaka",
            "Plantagenet Cherokee",
            "Raanana", "STFangsong", "STHeiti",
            "STKaiti", "STSong", "Sathu", "Silom",
            "Thonburi", "Times CY",
            
            // Windows XP Professional Font Exclusion List
            "Arial Unicode MS", "Batang", "Estrangelo Edessa", "Gautami",
            "Kartika", "Latha", "Lucida Sans Unicode", "Mangal", "Marlett",
            "MS Mincho", "MS Outlook", "MV Boli", "OCR-B-10 BT",
            "Raavi", "Shruti", "SimSun", "Sylfaen", "Symbol", "Tunga",
            "Vrinda", "Wingdings", "Wingdings 2", "Wingdings 3",
            "ZWAdobeF"
        }));
        LinkedList<Font> fontList = new LinkedList<Font>();
        HashSet<String> names=new HashSet<String>();
        for (int i=0; i < allFonts.length; i++) {
            Font e = allFonts[i];
			if (! fontExclusionList.contains(e.getFamily())) {
            	String fontName = e.getFontName();
				if(!names.contains(fontName)){
            		fontList.add(e);
            		names.add(fontName);
            	}
            }
        }
        allFonts = new Font[fontList.size()];
        allFonts = (Font[]) fontList.toArray(allFonts);
        Arrays.sort(allFonts, new Comparator<Font>() {
            public int compare(Font f1, Font f2) {
                int result = f1.getFamily().compareTo(f2.getFamily());
                if (result == 0) {
                    result = f1.getFontName().compareTo(f2.getFontName());
                }
                return result;
            }
        });
        LinkedList<Font> fontFamilies = new LinkedList<Font>();
        JMenu submenu = null;
        for (int i=0; i < allFonts.length; i++) {
            if (submenu != null) {
                if (! allFonts[i].getFamily().equals(allFonts[i - 1].getFamily())) {
                    submenu = null;
                }
            }
            if (submenu == null) {
                if (i < allFonts.length - 2
                        && allFonts[i].getFamily().equals(allFonts[i + 1].getFamily())) {
                    fontFamilies.add(allFonts[i]);
                    submenu = new JMenu(allFonts[i].getFamily());
                    //submenu.setFont(JPopupButton.ITEM_FONT);
                    fontPopupButton.add(submenu);
                    
                }
            }
            AttributeAction action = new AttributeAction(
                    editor,
                    FONT_FACE,
                    allFonts[i],
                    (submenu == null) ? allFonts[i].getFamily() : allFonts[i].getFontName(),
                    null,
                    new StyledEditorKit.FontFamilyAction(allFonts[i].getFontName(),allFonts[i].getFamily())
                    );
            
            if (submenu == null) {
                fontFamilies.add(allFonts[i]);
                fontPopupButton.add(action);
            } else {
                JMenuItem item = submenu.add(action);
                //item.setFont(JPopupButton.itemFont);
            }
        }
        fontPopupButton.setColumnCount( Math.max(1, fontFamilies.size()/32), true);
        
        
        
        boldToggleButton.setAction(new AttributeToggler(editor,
        		FONT_BOLD, Boolean.TRUE, Boolean.FALSE,
        		new StyledEditorKit.BoldAction()
        		));
        italicToggleButton.setAction(new AttributeToggler(editor,
        		FONT_ITALIC, Boolean.TRUE, Boolean.FALSE,
        		new StyledEditorKit.ItalicAction()
        		));
        underlineToggleButton.setAction(new AttributeToggler(editor,
        		FONT_UNDERLINED, Boolean.TRUE, Boolean.FALSE,
        		new StyledEditorKit.UnderlineAction()
        		));
        
        labels.configureToolBarButton(boldToggleButton, "attributeFontBold");
        boldToggleButton.setFocusable(false);
        
        labels.configureToolBarButton(italicToggleButton, "attributeFontItalic");
        italicToggleButton.setFocusable(false);
        
        labels.configureToolBarButton(underlineToggleButton, "attributeFontUnderline");
        underlineToggleButton.setFocusable(false);
        
        
        bar.add(fontPopupButton).setFocusable(false);
        bar.add(boldToggleButton).setFocusable(false);
        bar.add(italicToggleButton).setFocusable(false);
        bar.add(underlineToggleButton).setFocusable(false);
    }
    /**
     * Creates toolbar buttons and adds them to the specified JToolBar
     */
    public static void addAlignmentButtonsTo(JToolBar bar, final DrawingEditor editor) {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        
        bar.add(new AlignAction.West(editor)).setFocusable(false);
        bar.add(new AlignAction.East(editor)).setFocusable(false);
        bar.add(new AlignAction.Horizontal(editor)).setFocusable(false);
        bar.add(new AlignAction.North(editor)).setFocusable(false);
        bar.add(new AlignAction.South(editor)).setFocusable(false);
        bar.add(new AlignAction.Vertical(editor)).setFocusable(false);
        bar.addSeparator();
        bar.add(new MoveAction.West(editor)).setFocusable(false);
        bar.add(new MoveAction.East(editor)).setFocusable(false);
        bar.add(new MoveAction.North(editor)).setFocusable(false);
        bar.add(new MoveAction.South(editor)).setFocusable(false);
        bar.addSeparator();
        bar.add(new MoveToFrontAction(editor)).setFocusable(false);
        bar.add(new MoveToBackAction(editor)).setFocusable(false);
        
    }
    /**
     * Creates toolbar buttons and adds them to the specified JToolBar
     */
    public static AbstractButton createToggleGridButton(final DrawingEditor editor) {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        final JToggleButton toggleGridButton;
        
        toggleGridButton = new JToggleButton();
        labels.configureToolBarButton(toggleGridButton, "alignGrid");
        toggleGridButton.setFocusable(false);
        toggleGridButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                Constrainer c;
                if (toggleGridButton.isSelected()) {
                    c = new GridConstrainer(10,10);
                } else {
                    c = new GridConstrainer(1,1);
                }
                for (DrawingView v : editor.getDrawingViews()) {
                    v.setConstrainer(c);
                    v.getContainer().repaint();
                }
            }
        });
        
        return toggleGridButton;
    }
    /**
     * Creates toolbar buttons and adds them to the specified JToolBar
     */
    public static AbstractButton createToggleGridButton(final DrawingView view) {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        final JToggleButton toggleGridButton;
        
        toggleGridButton = new JToggleButton();
        labels.configureToolBarButton(toggleGridButton, "alignGrid");
        toggleGridButton.setFocusable(false);
        toggleGridButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                Constrainer c;
                if (toggleGridButton.isSelected()) {
                    c = new GridConstrainer(10,10);
                } else {
                    c = new GridConstrainer(1,1);
                }
                view.setConstrainer(c);
                view.getContainer().repaint();
            }
        });
        view.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                // String constants are interned
                if (evt.getPropertyName() == "gridConstrainer") {
                    Constrainer c = (Constrainer) evt.getNewValue();
                    toggleGridButton.setSelected(c.isVisible());
                }
            }
        });
        
        return toggleGridButton;
    }
    
}
