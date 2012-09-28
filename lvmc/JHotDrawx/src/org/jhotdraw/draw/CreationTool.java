/*
 * @(#)CreationTool.java  2.1.1  2006-07-20
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
�
 */

package org.jhotdraw.draw;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;

import org.jhotdraw.samples.svg.figures.SVGText;
import org.jhotdraw.undo.CompositeEdit;

import com.lorent.whiteboard.client.Client;
import com.lorent.whiteboard.model.RemoteFigure;
import com.lorent.whiteboard.model.Updater;
/**
 * A tool to create new figures. The figure to be created is specified by a
 * prototype.
 *
 * @author Werner Randelshofer
 * @version 2.1.1 2006-07-20 Minimal size treshold was enforced too eagerly.
 * <br>2.1 2006-07-15 Changed to create prototype creation from class name. 
 * <br>2.0 2006-01-14 Changed to support double precision coordinates.
 * <br>1.0 2003-12-01 Derived from JHotDraw 5.4b1.
 */
public class CreationTool extends AbstractTool {
    private Map<AttributeKey, Object> attributes;
    private String name;
    private Dimension minimalSizeTreshold = new Dimension(10,10);
    /**
     * We set the figure to the minimal size, if it is smaller than the minimal size treshold.
     */
    private Dimension minimalSize = new Dimension(40,40);
    /**
     * The prototype for new figures.
     */
    private Figure prototype;
    /**
     * The created figure.
     */
    protected Figure createdFigure;
    
    protected CompositeEdit creationEdit;
    
    /** Creates a new instance. */
    public CreationTool(String prototypeClassName) {
        this(prototypeClassName, null, null);
    }
    public CreationTool(String prototypeClassName, Map<AttributeKey, Object> attributes) {
        this(prototypeClassName, attributes, null);
    }
    public CreationTool(String prototypeClassName, Map<AttributeKey, Object> attributes, String name) {
        try {
        this.prototype = (Figure) Class.forName(prototypeClassName).newInstance();
        } catch (Exception e) {
            InternalError error = new InternalError("Unable to create Figure from "+prototypeClassName);
            error.initCause(e);
            throw error;
        }
        this.attributes = attributes;
        this.name = name;
    }
    public CreationTool(Figure prototype) {
        this(prototype, null, null);
    }
    /** Creates a new instance. */
    public CreationTool(Figure prototype, Map<AttributeKey, Object> attributes) {
        this(prototype, attributes, null);
    }
    /** Creates a new instance. */
    public CreationTool(Figure prototype, Map<AttributeKey, Object> attributes, String name) {
        this.prototype = prototype;
        this.attributes = attributes;
        this.name = name;
    }
    
    public Figure getPrototype() {
        return prototype;
    }
    
    public void activate(DrawingEditor editor) {
        super.activate(editor);
        //getView().clearSelection();
        //getView().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }
    
    public void deactivate(DrawingEditor editor) {
        super.deactivate(editor);
        if (getView() != null) {
        getView().setCursor(Cursor.getDefaultCursor());
        }
        if (createdFigure != null) {
            if (createdFigure instanceof CompositeFigure) {
                ((CompositeFigure) createdFigure).layout();
            }
            createdFigure = null;
        }
    }
    
    
    public void mousePressed(MouseEvent evt) {
        super.mousePressed(evt);
        if(evt.getButton()!=MouseEvent.BUTTON1)
        	return;
        getView().clearSelection();
        // FIXME - Localize this label
        creationEdit = new CompositeEdit("Figur erstellen");
        getDrawing().fireUndoableEditHappened(creationEdit);
        createdFigure = createFigure();
        Point2D.Double p = constrainPoint(viewToDrawing(anchor));
        anchor.x = evt.getX();
        anchor.y = evt.getY();
        createdFigure.willChange();
        createdFigure.basicSetBounds(p, p);
        createdFigure.changed();
        getDrawing().add(createdFigure);
    }
    
    protected Figure createFigure() {
        Figure f = (Figure) prototype.clone();
        getEditor().applyDefaultAttributesTo(f);
//        if(f instanceof SVGText){
//        	f.setAttribute(AttributeKeys.FILL_COLOR, Color.BLACK);
//        }
//        if (attributes != null) {
//            for (Map.Entry<AttributeKey, Object> entry : attributes.entrySet()) {
//                f.setAttribute(entry.getKey(), entry.getValue());
//            }
//        }
        return f;
    }
    
    protected Figure getCreatedFigure() {
        return createdFigure;
    }
    protected Figure getAddedFigure() {
        return createdFigure;
    }
    
    public void mouseDragged(MouseEvent evt) {
        if (createdFigure != null) {
            Point2D.Double p = constrainPoint(new Point(evt.getX(), evt.getY()));
            createdFigure.willChange();
            createdFigure.basicSetBounds(
                    constrainPoint(new Point(anchor.x, anchor.y)),
                    p
                    );
            createdFigure.changed();
        }
    }
    public void mouseReleased(MouseEvent evt) {
        if (createdFigure != null) {
            Rectangle2D.Double bounds = createdFigure.getBounds();
            if (bounds.width == 0 && bounds.height == 0) {
                getDrawing().remove(createdFigure);
            } else {
                if (Math.abs(anchor.x - evt.getX()) < minimalSizeTreshold.width && 
                        Math.abs(anchor.y - evt.getY()) < minimalSizeTreshold.height) {
                    createdFigure.basicSetBounds(
                            constrainPoint(new Point(anchor.x, anchor.y)),
                            constrainPoint(new Point(
                            anchor.x + (int) Math.max(bounds.width, minimalSize.width), 
                            anchor.y + (int) Math.max(bounds.height, minimalSize.height)
                            ))
                            );
                }
                getDrawing().remove(createdFigure);
                if(createdFigure instanceof Updater<?>){
                	Updater<?> rf=(Updater<?>) createdFigure;
                	Client.getInstance().broadcast(rf);
                }
            }
            if (createdFigure instanceof CompositeFigure) {
                ((CompositeFigure) createdFigure).layout();
            }
            createdFigure = null;
            getDrawing().fireUndoableEditHappened(creationEdit);
            fireToolDone();
        }
    }
}
