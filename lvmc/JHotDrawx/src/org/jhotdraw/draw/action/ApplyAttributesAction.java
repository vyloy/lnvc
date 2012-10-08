/*
 * @(#)ApplyAttributesAction.java  1.0  25. November 2003
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

import java.util.Locale;

import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.FigureSelectionEvent;
import org.jhotdraw.samples.svg.figures.ChangeFiguresAttributes;
import org.jhotdraw.util.ResourceBundleUtil;

import com.lorent.whiteboard.client.Client;
/**
 * ApplyAttributesAction.
 *
 * @author Werner Randelshofer
 * @version 1.0 25. November 2003  Created.
 */
public class ApplyAttributesAction extends AbstractSelectedAction {
    private ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels", Locale.getDefault());
    
    /** Creates a new instance. */
    public ApplyAttributesAction(DrawingEditor editor) {
        super(editor);
        labels.configureAction(this, "attributesApply");
//        setEnabled(true);
    }
    
    public void actionPerformed(java.awt.event.ActionEvent e) {
        applyAttributes();
    }
    
    public void applyAttributes() {
        DrawingEditor editor = getEditor();
        ChangeFiguresAttributes updater = new ChangeFiguresAttributes();
        for (Figure figure : getView().getSelectedFigures()) {
            editor.applyDefaultAttributesTo(figure);
            updater.add(figure);
        }
        updater.setAttributes(editor.getDefaultAttributes());
        Client.getInstance().broadcast(updater);
    }
    public void selectionChanged(FigureSelectionEvent evt) {
        setEnabled(getView().getSelectionCount() == 1);
    }
}
