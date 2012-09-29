/*
 * @(#)PasteAction.java  1.0  October 9, 2005
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

package org.jhotdraw.app.action;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.util.Collection;

import javax.swing.text.DefaultEditorKit;

import org.jhotdraw.app.EditableComponent;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.samples.svg.figures.Paste;
import org.jhotdraw.util.ResourceBundleUtil;

import com.lorent.whiteboard.client.Client;
/**
     * Pastes the contents of the system clipboard at the caret position.
     * Acts on the EditableComponent or JTextComponent which had the focus when
     * the ActionEvent was generated.
 *
 * @author Werner Randelshofer
 * @version 1.0 October 9, 2005 Created.
 */
public class PasteAction extends DefaultEditorKit.PasteAction {
    public final static String ID = "paste";
   
    /** Creates a new instance. */
    public PasteAction() {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.app.Labels");
        labels.configureAction(this, ID);
    }
    
    public void actionPerformed(ActionEvent evt) {
        Component focusOwner = KeyboardFocusManager.
                getCurrentKeyboardFocusManager().
                getPermanentFocusOwner();
        if (focusOwner != null && focusOwner instanceof EditableComponent) {
            ((EditableComponent) focusOwner).paste();
        } else {
            super.actionPerformed(evt);
        }
    }
}
