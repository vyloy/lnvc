/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.dnd;

import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class MyDropTargetListener extends DropTargetAdapter implements OnDropCallBack {
    private DropTarget dropTarget;
    private static Logger log = Logger.getLogger(MyDropTargetListener.class);
    
    public MyDropTargetListener(Component component){
        dropTarget = new DropTarget(component, DnDConstants.ACTION_COPY,this,true,null);
    }
    
    public MyDropTargetListener(Component component,int ops){
    	dropTarget = new DropTarget(component, ops, this,true,null);
    }
   
    public void drop(DropTargetDropEvent dtde) {
        Transferable transferable = dtde.getTransferable();
        DataFlavor[] transferDataFlavors = transferable.getTransferDataFlavors();
        try {
            Component transferData = (Component) transferable.getTransferData(TransferableObject.objectFlavor);
            Map<String,Object> properties = (Map<String,Object>) transferable.getTransferData(TransferableObject.propertiesFlavor);
            onDrop(dtde, transferData,properties);
            dtde.dropComplete(true);
        } catch (UnsupportedFlavorException ex) {
//            Logger.getLogger(MyDropTargetListener.class.getName()).log(Level.SEVERE, null, ex);
        	log.error("MyDropTargetListener", ex);
        } catch (IOException ex) {
//            Logger.getLogger(MyDropTargetListener.class.getName()).log(Level.SEVERE, null, ex);
        	log.error("MyDropTargetListener", ex);
        }
        dtde.rejectDrop();
    }

    public void onDrop(DropTargetDropEvent dtde, Component source,Map<String,Object> properties) {
        //component.add((Component)transferData);
        System.out.println("transferable: "+dtde.getTransferable()+" transferDataFlavors:"+dtde.getCurrentDataFlavors());
    }
}
