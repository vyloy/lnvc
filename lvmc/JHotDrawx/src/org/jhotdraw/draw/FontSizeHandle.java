/*
 * @(#)FontSizeHandle.java  2.0  2006-01-14
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
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import org.jhotdraw.samples.svg.figures.ChangeTextSize;
import org.jhotdraw.undo.CompositeEdit;

import com.lorent.whiteboard.client.Client;
import com.lorent.whiteboard.model.RemoteFigure;
/**
 * FontSizeHandle.
 *
 * @author Werner Randelshofer
 * @version 2.0 2006-01-14 Changed to support double precison coordinates.
 * <br>1.0 2003-12-01 Derived from JHotDraw 5.4b1.
 */
public class FontSizeHandle extends LocatorHandle {
    private float oldSize;
    private CompositeEdit edit;
    
    /** Creates a new instance. */
    public FontSizeHandle(TextHolder owner) {
        super(owner, new FontSizeLocator());
    }
    public FontSizeHandle(TextHolder owner, Locator locator) {
        super(owner, locator);
    }
    
    /**
     * Draws this handle.
     */
    public void draw(Graphics2D g) {
        drawDiamond(g, Color.yellow, Color.black);
    }
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
    }
    protected Rectangle basicGetBounds() {
        Rectangle r = new Rectangle(getLocation());
        r.grow(getHandlesize() / 2 + 1, getHandlesize() / 2 + 1);
        return r;
    }
    
    public void trackStart(Point anchor, int modifiersEx) {
        view.getDrawing().fireUndoableEditHappened(edit = new CompositeEdit("Schriftgr\u00f6sse"));
        TextHolder textOwner = (TextHolder) getOwner();
        oldSize = textOwner.getFontSize();
    }
    public void trackStep(Point anchor, Point lead, int modifiersEx) {
        TextHolder textOwner = (TextHolder) getOwner();
        
        float newSize = (float) Math.max(1, oldSize + view.viewToDrawing(new Point(0, lead.y - anchor.y)).y);
        textOwner.setFontSize(newSize);
    }
    public void trackEnd(Point anchor, Point lead, int modifiersEx) {
        view.getDrawing().fireUndoableEditHappened(edit);
        Figure textFigure = getOwner();
        if(textFigure instanceof RemoteFigure && textFigure instanceof TextFigure){
        	Client.getInstance().broadcast(new ChangeTextSize((RemoteFigure) textFigure, ((TextFigure)textFigure).getFontSize()));
        }
    }
}
