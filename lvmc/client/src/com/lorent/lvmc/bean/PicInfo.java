/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.bean;

/**
 *
 * @author test
 */
public class PicInfo {

    int pos;
    String val;

    public PicInfo(int pos, String val) {
        this.pos = pos;
        this.val = val;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
