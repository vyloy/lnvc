/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.dnd;

import java.awt.Component;
import java.awt.dnd.DropTargetDropEvent;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public interface OnDropCallBack {
    void onDrop(DropTargetDropEvent dtde,Component source,Map<String,Object> properties);
}
