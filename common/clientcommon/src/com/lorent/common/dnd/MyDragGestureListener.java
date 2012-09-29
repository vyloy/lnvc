/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.dnd;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Administrator
 */
public class MyDragGestureListener implements DragGestureListener {
    private Map<String,Object> properties = new HashMap<String, Object>();
    
    public void setPropery(String key,Object value){
        properties.put(key, value);
    }
    
    public void dragGestureRecognized(DragGestureEvent dge) {
        Cursor cursor = null;
        Component component = dge.getComponent();
        if (dge.getDragAction() == DnDConstants.ACTION_COPY) {
            cursor = DragSource.DefaultCopyDrop;
        }
        else if(dge.getDragAction() == DnDConstants.ACTION_MOVE){
            cursor = DragSource.DefaultMoveDrop;
        }
        TransferableObject transferableObject = new TransferableObject(component);
        transferableObject.setProperties(properties);
        dge.startDrag(cursor,transferableObject );
    }
}
