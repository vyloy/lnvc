/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.event;

/**
 *
 * @author Administrator
 */
public class MyEvent {
    
    private String id;
    private Object[] paras;
    private boolean runInEDT;
    
    

    public boolean isRunInEDT() {
		return runInEDT;
	}

	public void setRunInEDT(boolean runInEDT) {
		this.runInEDT = runInEDT;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object[] getParas() {
        return paras;
    }

    public void setParas(Object[] paras) {
        this.paras = paras;
    }
    
    
    
}
