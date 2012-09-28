/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class TransferableObject implements Transferable {

    protected static DataFlavor objectFlavor = new DataFlavor(Object.class, "TransferableObject");
    protected static DataFlavor propertiesFlavor = new DataFlavor(Map.class, "TransferableProperties");
    protected static DataFlavor[] supportedFlavors = {objectFlavor, propertiesFlavor};
    private Object object;
    private Map<String, Object> properties = new HashMap<String, Object>();

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public TransferableObject(Object obj) {
        this.object = obj;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        if (flavor.equals(objectFlavor) || flavor.equals(DataFlavor.stringFlavor) || flavor.equals(propertiesFlavor)) {
            return true;
        }
        return false;
    }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(objectFlavor)) {
            return object;
        } else if (flavor.equals(DataFlavor.stringFlavor)) {
            return object.toString();
        } else if (flavor.equals(propertiesFlavor)) {
            return properties;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
