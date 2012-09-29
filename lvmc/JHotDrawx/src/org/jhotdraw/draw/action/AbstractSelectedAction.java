/*
 * @(#)AbstractSelectedAction.java  3.1.1  2006-07-09
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

import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.FigureSelectionEvent;
import org.jhotdraw.draw.FigureSelectionListener;
import javax.swing.*;
import java.beans.*;
import java.io.Serializable;

import javax.swing.undo.*;
import org.jhotdraw.util.*;
import java.util.*;
/**
 * Abstract super class for actions which act on the selected figures of a drawing
 * editor. If no figures are selected, the action is disabled.
 *
 * @author Werner Randelshofer
 *
 * @version 3.1.1. 2006-07-09 Fixed enabled state. 
 * <br>3.1 2006-03-15 Support for enabled state of view added.
 * <br>3.0 2006-02-24 Changed to support multiple views.
 * <br>2.0 2006-02-14 Updated to work with multiple views.
 * <br>1.0 2003-12-01 Created.
 */
public abstract class AbstractSelectedAction
        extends AbstractAction  {
    private DrawingEditor editor;
    protected StateUpdater stateUpdater;
    protected transient ResourceBundleUtil labels =
            ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
    private transient PropertyChangeListener propertyChangeHandler = new PropertyChangeListener() {
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("enabled")) {
                updateEnabledState();
            }
        }
    };
    
    private class EventHandler implements PropertyChangeListener, FigureSelectionListener {
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("focusedView")) {
                if (evt.getOldValue() != null) {
                    DrawingView view = ((DrawingView) evt.getOldValue());
                    view.removeFigureSelectionListener(this);
                    view.removePropertyChangeListener(propertyChangeHandler);
                }
                if (evt.getNewValue() != null) {
                    DrawingView view = ((DrawingView) evt.getNewValue());
                    view.addFigureSelectionListener(this);
                    view.addPropertyChangeListener(propertyChangeHandler);
                }
                updateEnabledState();
            }
        }
        public void selectionChanged(FigureSelectionEvent evt) {
            updateEnabledState();
            
        }
    };
    
    private transient EventHandler eventHandler = new EventHandler();
    
    
    /** Creates an action which acts on the selected figures on the current view
     * of the specified editor.
     */
    public AbstractSelectedAction(DrawingEditor editor) {
        setEditor(editor);
        updateEnabledState();
    }
    
    protected void updateEnabledState() {
    	if(stateUpdater==null){
	        if (getView() != null) {
	            setEnabled(getView().isEnabled() &&
	                    getView().getSelectionCount() > 0
	                    );
	        } else {
	            setEnabled(false);
	        }
    	}else{
    		stateUpdater.update(this);
    	}
    }
    
    public void dispose() {
        if (this.editor != null) {
            this.editor.removePropertyChangeListener(eventHandler);
            if (this.editor.getView() != null) {
                this.editor.getView().removeFigureSelectionListener(eventHandler);
            }
        }
        this.editor = null;
    }
    
    public StateUpdater getStateUpdater() {
		return stateUpdater;
	}

	public void setStateUpdater(StateUpdater stateUpdater) {
		this.stateUpdater = stateUpdater;
	}

	public void setEditor(DrawingEditor editor) {
        if (this.editor != null) {
            this.editor.removePropertyChangeListener(eventHandler);
            if (getView() != null) {
                getView().removeFigureSelectionListener(eventHandler);
            }
        }
        this.editor = editor;
        if (this.editor != null) {
            this.editor.addPropertyChangeListener(eventHandler);
        }
    }
    public DrawingEditor getEditor() {
        return editor;
    }
    protected DrawingView getView() {
        return (editor == null) ? null : editor.getFocusedView();
    }
    protected Drawing getDrawing() {
        return (getView() == null) ? null : getView().getDrawing();
    }
    protected void fireUndoableEditHappened(UndoableEdit edit) {
        getDrawing().fireUndoableEditHappened(edit);
    }
    
}
