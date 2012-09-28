/*
 * @(#)DragTracker.java  2.0  2006-01-14
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

import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.LinkedList;

import org.jhotdraw.samples.svg.figures.IdentifiableTransform;
import org.jhotdraw.samples.svg.figures.ReverseTransforms;
import org.jhotdraw.samples.svg.figures.TransformFigures;

import com.lorent.whiteboard.client.Client;
import com.lorent.whiteboard.model.RemoteFigure;

/**
 * DragTracker.
 *
 * @author Werner Randelshofer
 * @version 2.0 2006-01-14 Changed to support double precision coordinates.
 * <br>1.0 2003-12-01 Derived from JHotDraw 5.4b1.
 */
public class DragTracker extends AbstractTool {
    Figure anchorFigure;
    Point2D.Double oldPoint;
    Point2D.Double anchorPoint;
    private boolean isDragging;
    
    
    /** Creates a new instance. */
    public DragTracker(Figure figure) {
        anchorFigure = figure;
    }
    
    public void mouseMoved(MouseEvent evt) {
        updateCursor(editor.findView((Container) evt.getSource()), new Point(evt.getX(), evt.getY()));
    }
    
    public void mousePressed(MouseEvent evt) {
        super.mousePressed(evt);
        DrawingView view = getView();
        view.dragging();
        if (evt.isShiftDown()) {
            view.setHandleDetailLevel(0);
            view.toggleSelection(anchorFigure);
            if (! view.isFigureSelected(anchorFigure)) {
                anchorFigure = null;
            }
        } else if (! view.isFigureSelected(anchorFigure)) {
            view.setHandleDetailLevel(0);
            if(!evt.isControlDown())
            	view.clearSelection();
            view.addToSelection(anchorFigure);
        }

        Point2D.Double origin = new Point2D.Double(Double.MAX_VALUE, Double.MAX_VALUE);
        for (Figure f : view.getSelectedFigures()) {
            Rectangle2D.Double b = f.getBounds();
            origin.x = Math.min(origin.x, b.x);
            origin.y = Math.min(origin.y, b.y);
            f.dragging();
        }
        Point2D.Double constrainedOrigin = view.getConstrainer().constrainPoint(new Point2D.Double(origin.x,origin.y));
        oldPoint = view.getConstrainer().constrainPoint(
                view.viewToDrawing(new Point(evt.getX(), evt.getY()))
                );
        oldPoint.x += origin.x - constrainedOrigin.x;
        oldPoint.y += origin.y - constrainedOrigin.y;
        anchorPoint = oldPoint;
    }
    public void mouseDragged(MouseEvent evt) {
        DrawingView view = getView();
        if (isDragging = false) {
        	isDragging = true;
			updateCursor(editor.findView((Container) evt.getSource()),
					new Point(evt.getX(), evt.getY()));
		}
        
        Point2D.Double newPoint = view.getConstrainer().constrainPoint(
                view.viewToDrawing(new Point(evt.getX(), evt.getY()))
                );
        AffineTransform tx = new AffineTransform();
        tx.translate(newPoint.x - oldPoint.x,newPoint.y - oldPoint.y);
        for (Figure f : view.getSelectedFigures()) {
			f.dragging();
            f.willChange();
            f.basicTransform(tx);
            f.changed();
        }
        oldPoint = newPoint;
    }
    
    private void broadcastTranslate(Collection<Figure> draggedFigures){
    	AffineTransform tx = new AffineTransform();
    	tx.translate(oldPoint.x - anchorPoint.x, oldPoint.y - anchorPoint.y );
    	AffineTransform anchor = new AffineTransform();
    	anchor.translate(-tx.getTranslateX(),  -tx.getTranslateY());
    	
    	for(Figure f:draggedFigures){
    		LinkedList<IdentifiableTransform> rollback=f.translateFinished(tx, anchor,getView());
			if(rollback!=null&&f instanceof RemoteFigure){
				Client.getInstance().broadcast(new ReverseTransforms((RemoteFigure) f,rollback));
			}
    	}
    	if(tx.getTranslateX()==0&&tx.getTranslateY()==0){
    		return;
    	}
    	if(draggedFigures!=null&&!draggedFigures.isEmpty()){
            TransformFigures updater = new TransformFigures();
            updater.add(draggedFigures);
            updater.setAffineTransform(tx);
        	Client.getInstance().broadcast(updater);
        }
    }
    
    public void mouseReleased(MouseEvent evt) {
        super.mouseReleased(evt);
        isDragging = false;
        
        int x = evt.getX();
        int y = evt.getY();
        updateCursor(editor.findView((Container) evt.getSource()),new Point(evt.getX(), evt.getY()));
        Point2D.Double p = getView().getConstrainer().constrainPoint(getView().viewToDrawing(new Point(x,y)));
        Collection<Figure> draggedFigures = new LinkedList<Figure>(getView().getSelectedFigures());
        broadcastTranslate(draggedFigures);
        
        
        Figure dropTarget = getDrawing().findFigureExcept(p, draggedFigures);
       
        if (dropTarget != null) {
            boolean snapBack = dropTarget.handleDrop(p, draggedFigures, getView());
            if (snapBack) {
                AffineTransform tx = new AffineTransform();
                tx.translate(anchorPoint.x - oldPoint.x, anchorPoint.y - oldPoint.y);
                for (Figure f : draggedFigures) {
                    f.willChange();
                    f.basicTransform(tx);
                    f.changed();
                }
            } else {
                AffineTransform tx = new AffineTransform();
                tx.translate(-anchorPoint.x + oldPoint.x, -anchorPoint.y + oldPoint.y);
                if (! tx.isIdentity()) {
                getDrawing().fireUndoableEditHappened(new TransformEdit(
                        draggedFigures, tx
                        ));
                }
            }
        } else {
            AffineTransform tx = new AffineTransform();
            tx.translate(-anchorPoint.x + oldPoint.x, -anchorPoint.y + oldPoint.y);
                if (! tx.isIdentity()) {
            getDrawing().fireUndoableEditHappened(new TransformEdit(
                    draggedFigures, tx
                    ));
            }
        }
        fireToolDone();
        getView().dragged();
    }
}
